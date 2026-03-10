package br.com.ecommerce.api_ecommerce.repository;


import br.com.ecommerce.api_ecommerce.domain.Transportadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportadoraRepository extends JpaRepository<Transportadora, Long> {
    
}