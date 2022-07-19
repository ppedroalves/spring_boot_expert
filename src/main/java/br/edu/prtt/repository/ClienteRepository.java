package br.edu.prtt.repository;


import br.edu.prtt.domain.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface ClienteRepository extends JpaRepository<Cliente, Integer>  {


    @Query(value = "select c from Cliente c where c.nome like :nome" )
    List<Cliente> encontrarPorNome( @Param( "nome") String nome);

    void deleteByNome(String nome);

    boolean existsByNome(String nome);

    List<Cliente> findByNomeOrId(String nome, Integer id);

}
