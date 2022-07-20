package br.edu.prtt.repository;


import br.edu.prtt.domain.entidades.Produto;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProdutoRepository extends JpaRepository<Produto, Integer>  {




}
