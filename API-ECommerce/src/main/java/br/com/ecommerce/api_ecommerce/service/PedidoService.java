package br.com.ecommerce.api_ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	public PedidoCompletoDTO inserir (PedidoInsertDTO pddDTO) {
		Optional <Cliente> clienteOpt = clienteRepository.findById(pddDTO.getClienteId());
		if (!clienteOpt.isPresent()) {
		    throw new RuntimeException("Cliente não encontrado");
		}
		
	Pedido pedido = new Pedido();
	pedido.setCliente(cliente);
	pedido.setStatus(StatusPedido.NOVO);
	
	List<ItemPedido> itens = new ArrayList();
	
	for (ItemInsertDTO itemDTO : pddDTO.getItens()) {
		Optional <Produto> produtoOpt = produtoRepository.findById(pddDTO.getProdutoId());
		if (!produtoOpt.isPresent()) {
		    throw new RuntimeException("Produto não encontrado");
		}
		
		if (produto.getEstoque() < itemDTO.getQuantidade()) {
            throw new ProdutoSemEstoqueException("Estoque insuficiente para: " + produto.getNome());
        }
		
		ItemPedido = item = new ItemPedido();
		item.setPedido(pedido);
		item.setProduto(produto);
		item.setQuantidade(ItemDTO.getQuantidade());
		item.setValorVenda(produto.getPreco());
		item.setDesconto(itemDTO.getDesconto());
		
		item.add(item);
	}
	
	pedido.setItens(itens);
	pedidoRepository.save(pedido);
	
	return toDTO(pedido);
	
	}
	
	public PedidoCompletoDTO listarPorNumero (Long id) {
		Optional <Pedido> pedidoOpt = pedidoRepository.findById(pddDTO.getPedidoId());
		if (!pedidoOpt.isPresent()) {
		    throw new RuntimeException("Pedido não encontrado");
		}
		
		double total = pedido.getItens().stream()
                .mapToDouble(i -> (i.getValorVenda() * i.getQuantidade()) - i.getDesconto())
                .sum();
		
		PedidoCompleto pddDTO = ToDTO(pedido);
		pddDTO.setTotal(total);
		return pddDTO;
	}
	
	public PedidoCompletoDTO alterarStatus (Long id, String statusStr) {
		Optional <Pedido> pedidoOpt = pedidoRepository.findById(pddDTO.getPedidoId());
		if (!pedidoOpt.isPresent()) {
		    throw new RuntimeException("Pedido não encontrado");
		}
		try {
            StatusPedido status = StatusPedido.valueOf(statusStr.toUpperCase());
            pedido.setStatus(status);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Status inválido: " + statusStr);
        }
		
        pedidoRepository.save(pedido);
        return toDTO(pedido);
        
	}
	
	private PedidoCompletoDTO toDTO(Pedido pedido) {
        PedidoCompletoDTO dto = new PedidoCompletoDTO();
        pdddto.setId(pedido.getId());
        pdddto.setClienteNome(pedido.getCliente().getNome());
        pdddto.setDataPedido(pedido.getDataPedido());
        pdddto.setStatus(pedido.getStatus());
        
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
        return dto;
    }
	
}
