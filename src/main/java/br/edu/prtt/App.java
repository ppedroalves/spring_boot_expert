package br.edu.prtt;

import br.edu.prtt.domain.entidades.Cliente;
import br.edu.prtt.domain.entidades.Produto;
import br.edu.prtt.repository.ClienteRepository;
import br.edu.prtt.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@SpringBootApplication
public class App
{

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired ClienteRepository clienteRepository,
                                               @Autowired ProdutoRepository produtoRepository){
        return args -> {
            Cliente c = new Cliente("Fulano");
            Produto p = new Produto("Pizza", BigDecimal.valueOf(100));
            produtoRepository.save(p);
            clienteRepository.save(c);
        };
    }

    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
