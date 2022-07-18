package br.edu.prtt;

import br.edu.prtt.domain.entidades.Cliente;
import br.edu.prtt.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class App
{

    @Bean
    public CommandLineRunner init (@Autowired ClienteRepository clienteRepository){
        return args -> {
            Cliente cliente = new Cliente();
            cliente.setNome("Prtt");
            clienteRepository.salvar(cliente);

            Cliente cliente2 = new Cliente("outro cliente");
            clienteRepository.salvar(cliente2);


            clienteRepository.obterTodos().stream()
                    .forEach(System.out::println);

            clienteRepository.obterTodos().stream()
                    .forEach(c -> {
                        c.setNome(c.getNome() + " atualizado");
                        clienteRepository.atualizar(c);
                    });

            clienteRepository.obterTodos().stream()
                    .forEach(System.out::println);

            clienteRepository.obterTodos().stream()
                    .forEach(c -> clienteRepository.deletar(c));

            System.out.println("CLIENTES DEPOIS DE DELETAR: ");
            clienteRepository.obterTodos().stream()
                    .forEach(System.out::println);






        };
    }

    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
