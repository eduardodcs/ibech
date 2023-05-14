package br.com.mack.ibech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mack.ibech.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
