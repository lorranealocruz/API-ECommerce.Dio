package br.com.ecommerce.api_ecommerce.repository;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.ecommerce.api_ecommerce.domain.StatusPedido;

import br.com.ecommerce.api_ecommerce.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {


    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.dataPedido = :data")
    Long countPedidosPorData(@Param("data") LocalDate data);

    @Query("SELECT COALESCE(SUM(p.ValorTotal), 0) FROM Pedido p WHERE p.dataPedido = :data")
    Double sumValorTotalPorData(@Param("data") LocalDate data);

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.dataPedido = :data AND p.status = :status")
    Long countPedidosPorDataEStatus(@Param("data") LocalDate data, @Param("status") StatusPedido status);

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.dataPedido = :data AND p.status = 'AGUARDANDO_PAGAMENTO'")
    Long countPedidosPendentesPorData(@Param("data") LocalDate data);
}
