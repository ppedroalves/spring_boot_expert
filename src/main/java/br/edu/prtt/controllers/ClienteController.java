package br.edu.prtt.controllers;


import br.edu.prtt.domain.entidades.Cliente;
import br.edu.prtt.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {


    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable("id") Integer id){
        return clienteRepository.findById(id)
                .orElseThrow(() ->  new ResponseStatusException( HttpStatus.NOT_FOUND, "Cliente nao encontrado"));


    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvarCliente( @RequestBody Cliente cliente){
         return clienteRepository.save(cliente);

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente( @PathVariable("id") Integer id){
        clienteRepository.findById(id)
                .map(c -> {
                    clienteRepository.delete(c);
                    return c;
                })
                .orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Cliente nao encontrado"));

    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarCliente(@PathVariable Integer id,@RequestBody Cliente cliente){

         clienteRepository
                .findById(id)
                .map(c -> {
                    cliente.setId(c.getId());
                    clienteRepository.save(cliente);
                    return cliente;
                }).orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
    }

    @GetMapping
    public List<Cliente> buscarCliente(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
                                        .matching()
                                        .withIgnoreCase()
                                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return clienteRepository.findAll(example);

    }



}
