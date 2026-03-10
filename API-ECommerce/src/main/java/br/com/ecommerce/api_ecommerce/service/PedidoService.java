package br.com.ecommerce.api_ecommerce.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ecommerce.api_ecommerce.domain.Cupom;
import br.com.ecommerce.api_ecommerce.domain.ItemPedido;
import br.com.ecommerce.api_ecommerce.domain.Pedido;
import br.com.ecommerce.api_ecommerce.domain.Produto;
import br.com.ecommerce.api_ecommerce.domain.StatusPedido;
import br.com.ecommerce.api_ecommerce.dto.ItemInsertDTO;
import br.com.ecommerce.api_ecommerce.dto.ItemPedidoDTO;
import br.com.ecommerce.api_ecommerce.dto.PedidoCompletoDTO;
import br.com.ecommerce.api_ecommerce.dto.PedidoInsertDTO;
import br.com.ecommerce.api_ecommerce.entity.Cliente;
import br.com.ecommerce.api_ecommerce.exception.BadRequestException;
import br.com.ecommerce.api_ecommerce.exception.ProdutoSemEstoqueException;
import br.com.ecommerce.api_ecommerce.exception.ResourceNotFoundException;
import br.com.ecommerce.api_ecommerce.repository.ClienteRepository;
import br.com.ecommerce.api_ecommerce.repository.PedidoRepository;
import br.com.ecommerce.api_ecommerce.repository.ProdutoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CupomService cupomService;

    public List<PedidoCompletoDTO> listarTodos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public PedidoCompletoDTO inserir(PedidoInsertDTO pddDTO) {
        Cliente cliente = clienteRepository.findById(pddDTO.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + pddDTO.getClienteId()));

        Cupom cupomValido = null;
        Double percentualDesconto = 0.0;
        if (pddDTO.getCodigoCupom() != null && !pddDTO.getCodigoCupom().isBlank()) {
            try {
                cupomValido = cupomService.validarECarregarCupom(pddDTO.getCodigoCupom());
                percentualDesconto = cupomValido.getPercentualDesconto() / 100.0;
            } catch (ResourceNotFoundException | BadRequestException e) {
                throw new BadRequestException("Cupom inválido: " + e.getMessage());
            }
        }

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
        pedido.setDataPedido(LocalDate.now());

        Set<ItemPedido> itens = new HashSet<>();
        double subtotalItens = 0.0;

        for (ItemInsertDTO itemDTO : pddDTO.getItens()) {
            Produto produto = produtoRepository.findById(itemDTO.getIdProduto())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + itemDTO.getIdProduto()));

            if (produto.getEstoque() < itemDTO.getQuantidade()) {
                throw new ProdutoSemEstoqueException("Estoque insuficiente para o produto ID: " + produto.getId());
            }

            double descontoItem = (itemDTO.getDesconto() != null) ? itemDTO.getDesconto() : 0.0;

            ItemPedido item = new ItemPedido(
                    pedido,
                    produto,
                    produto.getPreco(),
                    descontoItem,
                    itemDTO.getQuantidade()
            );
            itens.add(item);

            subtotalItens += (item.getValorVenda() * item.getQuantidade()) - item.getDesconto();

            produto.setEstoque(produto.getEstoque() - itemDTO.getQuantidade());
             
        }

        double valorDescontoCupom = 0.0;
        if (cupomValido != null) {
            valorDescontoCupom = subtotalItens * percentualDesconto;
            pedido.setCupomAplicado(cupomValido);
            pedido.setValorDescontoTotal(valorDescontoCupom);
        }

        double totalFinal = subtotalItens - valorDescontoCupom;

        pedido.setItens(itens);
        pedido.setValorTotal(totalFinal);

        pedidoRepository.save(pedido);

        
        itens.forEach(item -> produtoRepository.save(item.getProduto()));


        return toDTO(pedido);
    }

    public PedidoCompletoDTO listarPorNumero(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado com ID: " + id));
        return toDTO(pedido);
    }

    @Transactional
    public PedidoCompletoDTO alterarStatus(Long id, String statusStr) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado com ID: " + id));

        try {
            StatusPedido status = StatusPedido.valueOf(statusStr.toUpperCase());
            pedido.setStatus(status);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Status inválido: " + statusStr);
        }

        pedidoRepository.save(pedido);
        return toDTO(pedido);
    }

    @Transactional
    public void deletar(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado com ID: " + id));
        try {
            
            pedidoRepository.delete(pedido);
        } catch (Exception e) {
             throw new BadRequestException("Não foi possível deletar o pedido. Verifique dependências.");
        }
    }

    private PedidoCompletoDTO toDTO(Pedido pedido) {
        PedidoCompletoDTO dto = new PedidoCompletoDTO();
        dto.setId(pedido.getId());
        if (pedido.getCliente() != null) {
            dto.setClienteNome(pedido.getCliente().getNome());
        }
        dto.setDataPedido(pedido.getDataPedido());
        dto.setStatus(pedido.getStatus());

        List<ItemPedidoDTO> itensDTO = pedido.getItens().stream().map(i -> {
            ItemPedidoDTO itemDTO = new ItemPedidoDTO();
            if (i.getProduto() != null) {
                itemDTO.setNomeProduto(i.getProduto().getNome()); 
            }
            itemDTO.setQuantidade(i.getQuantidade());
            itemDTO.setValorVenda(i.getValorVenda());
            itemDTO.setDesconto(i.getDesconto());
            itemDTO.setSubtotal((i.getValorVenda() * i.getQuantidade()) - i.getDesconto());
            return itemDTO;
        }).toList();

        dto.setItens(itensDTO);

        if (pedido.getCupomAplicado() != null) {
            dto.setCodigoCupomAplicado(pedido.getCupomAplicado().getCodigo());
            dto.setValorDescontoAplicado(pedido.getValorDescontoTotal());
        } else {
            dto.setCodigoCupomAplicado(null);
            dto.setValorDescontoAplicado(0.0);
        }

        dto.setValorTotal(pedido.getValorTotal() != null ? pedido.getValorTotal() : 0.0);

        return dto;
    }

    public List<String> listarNomesClientes() {
        System.out.println(">>> Entrou no método listarNomesClientes()");

        return pedidoRepository.findAll()
                .stream()
                .map(p -> {
                    if (p.getCliente() == null) {
                        System.out.println("⚠ Pedido sem cliente: ID " + p.getId());
                        return "Cliente não informado (Pedido ID: " + p.getId() + ")";
                    }
                    return p.getCliente().getNome();
                })
                .distinct()
                .collect(Collectors.toList());
    }
}