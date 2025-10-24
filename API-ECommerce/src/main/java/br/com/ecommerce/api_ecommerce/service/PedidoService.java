package br.com.ecommerce.api_ecommerce.service;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ecommerce.api_ecommerce.domain.ItemPedido;
import br.com.ecommerce.api_ecommerce.domain.Pedido;
import br.com.ecommerce.api_ecommerce.domain.Produto;
import br.com.ecommerce.api_ecommerce.domain.StatusPedido;
import br.com.ecommerce.api_ecommerce.dto.ItemInsertDTO;
import br.com.ecommerce.api_ecommerce.dto.ItemPedidoDTO;
import br.com.ecommerce.api_ecommerce.dto.PedidoCompletoDTO;
import br.com.ecommerce.api_ecommerce.dto.PedidoInsertDTO;
import br.com.ecommerce.api_ecommerce.entity.Cliente;
import br.com.ecommerce.api_ecommerce.repository.ClienteRepository;
import br.com.ecommerce.api_ecommerce.repository.PedidoRepository;
import br.com.ecommerce.api_ecommerce.repository.ProdutoRepository;
import br.com.ecommerce.api_ecommerce.service.exceptions.ProdutoSemEstoqueException;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    public List<PedidoCompletoDTO> listarTodos () {
    	List<Pedido> pedidos = pedidoRepository.findAll();
    	
    	return pedidos.stream()
                .map(p -> {
                    PedidoCompletoDTO dto = toDTO(p);

                    double total = p.getItens().stream()
                            .mapToDouble(i -> (i.getValorVenda() * i.getQuantidade()) - i.getDesconto())
                            .sum();
                    dto.setTotal(total);

                    return dto;
                })
                .collect(Collectors.toList());
    }
    
    @Transactional
    public PedidoCompletoDTO inserir(PedidoInsertDTO pddDTO) {
        Cliente cliente = clienteRepository.findById(pddDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);

        HashSet<ItemPedido> itens = new HashSet<ItemPedido>();

        for (ItemInsertDTO itemDTO : pddDTO.getItens()) {
            Produto produto = produtoRepository.findById(itemDTO.getProduto().getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            if (produto.getEstoque() < itemDTO.getQuantidade()) {
                throw new ProdutoSemEstoqueException("Estoque insuficiente para: " + produto.getNome());
            }

            ItemPedido item = new ItemPedido(
                pedido,                          
                produto,                           
                produto.getPreco(),                 
                itemDTO.getDesconto(),             
                itemDTO.getQuantidade()
            );

            itens.add(item);


            produto.setEstoque(produto.getEstoque() - itemDTO.getQuantidade());
            produtoRepository.save(produto);
        }
        
        Double total = itens.stream()
        	    .mapToDouble(i -> (i.getValorVenda() * i.getQuantidade()) - i.getDesconto())
        	    .sum();

        	pedido.setItens(itens);
        	pedido.setDataPedido(LocalDate.now());
        	pedido.setValorTotal(total);
        	pedidoRepository.save(pedido);


        pedido.setItens(itens);
        pedido.setDataPedido(LocalDate.now());
        pedido.setValorVenda(total);
        pedidoRepository.save(pedido);

        return toDTO(pedido);
    }

    public PedidoCompletoDTO listarPorNumero(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        double total = pedido.getItens().stream()
                .mapToDouble(i -> (i.getValorVenda() * i.getQuantidade()) - i.getDesconto())
                .sum();

        PedidoCompletoDTO dto = toDTO(pedido);
        dto.setTotal(total);
        return dto;
    }

    public PedidoCompletoDTO alterarStatus(Long id, String statusStr) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        try {
            StatusPedido status = StatusPedido.valueOf(statusStr.toUpperCase());
            pedido.setStatus(status);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Status inválido: " + statusStr);
        }

        pedido.setDataPedido(LocalDate.now());
        pedidoRepository.save(pedido);
        return toDTO(pedido);
    }

    private PedidoCompletoDTO toDTO(Pedido pedido) {
        PedidoCompletoDTO dto = new PedidoCompletoDTO();
        dto.setId(pedido.getId());
        dto.setClienteNome(pedido.getCliente().getNome());
        dto.setDataPedido(pedido.getDataPedido());
        dto.setStatus(pedido.getStatus());

        List<ItemPedidoDTO> itensDTO = pedido.getItens().stream().map(i -> {
            ItemPedidoDTO itemDTO = new ItemPedidoDTO();
            itemDTO.setNomeProduto(i.getProduto().getNome());
            itemDTO.setQuantidade(i.getQuantidade());
            itemDTO.setValorVenda(i.getValorVenda());
            itemDTO.setDesconto(i.getDesconto());
            itemDTO.setSubtotal((i.getValorVenda() * i.getQuantidade()) - i.getDesconto());
            return itemDTO;
        }).toList();

        dto.setItens(itensDTO);

     
        double total = pedido.getValorTotal() != null ? pedido.getValorTotal()
            : itensDTO.stream().mapToDouble(ItemPedidoDTO::getSubtotal).sum();
        dto.setTotal(total);

        return dto;
    }
    
    public void deletar(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        
        pedidoRepository.delete(pedido);
    }

}

