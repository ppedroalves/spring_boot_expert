package br.edu.prtt.controllers;


import br.edu.prtt.domain.entidades.Produto;
import br.edu.prtt.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {


    @Autowired
    ProdutoRepository produtoRepository;


    @GetMapping("{id}")
    public Produto getProdutoById(@PathVariable Integer id){
        return produtoRepository.findById(id)
                .orElseThrow(() ->  new ResponseStatusException( HttpStatus.NOT_FOUND, "Produto nao encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarProduto(@RequestBody Produto produto){
        produtoRepository.save(produto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto(@PathVariable Integer id){
        produtoRepository.findById(id)
                .map(p -> {
                    produtoRepository.delete(p);
                    return p;
                })
                .orElseThrow(() ->  new ResponseStatusException( HttpStatus.NOT_FOUND, "Produto nao encontrado"));
    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarProduto(@PathVariable Integer id, @RequestBody Produto produto){
        produtoRepository
                .findById(id)
                .map( p -> {
                    produto.setId(p.getId());
                    produtoRepository.save(produto);
                    return p;
                })
                .orElseThrow(() ->  new ResponseStatusException( HttpStatus.NOT_FOUND, "Produto nao encontrado"));
    }

    @GetMapping
    public List<Produto> buscarCliente(Produto filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return produtoRepository.findAll(example);

    }




}
