package br.edu.prtt.repository;


import br.edu.prtt.domain.entidades.Cliente;
import br.edu.prtt.domain.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PedidoRepository extends JpaRepository<Pedido, Integer>  {
    List<Pedido> findByCliente(Cliente cliente);

}
