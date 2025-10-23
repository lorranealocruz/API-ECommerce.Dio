package br.com.ecommerce.api_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ecommerce.api_ecommerce.domain.ItemPedidoPK;
import br.com.ecommerce.api_ecommerce.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, ItemPedidoPK> {

}
