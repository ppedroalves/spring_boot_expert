package br.edu.prtt;

import br.edu.prtt.domain.entidades.Cliente;
import br.edu.prtt.domain.entidades.Pedido;
import br.edu.prtt.repository.ClienteRepository;
import br.edu.prtt.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
public class App
{

    @Bean
    public CommandLineRunner init (@Autowired ClienteRepository clienteRepository, @Autowired PedidoRepository  pedidoRepository){
        return args -> {
            Cliente cliente = new Cliente();
            cliente.setNome("Prtt");
            clienteRepository.save(cliente);


            Pedido p = new Pedido();
            p.setCliente(cliente);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidoRepository.save(p);

            Cliente c1 = clienteRepository.findClienteFetchPedidos(cliente.getId());
            System.out.println(c1);
            System.out.println(c1.getPedidos());


//            clienteRepository.findAll().stream()
//                    .forEach(System.out::println);
//
//            clienteRepository.findAll().stream()
//                    .forEach(c -> {
//                        c.setNome(c.getNome() + " atualizado");
//                        clienteRepository.save(c);
//                    });
//
//            List<Cliente> clienteList = clienteRepository.encontrarPorNome("P");
//
//
//            System.out.println("Buscando um cliente: ");
//            clienteList.stream()
//                    .forEach(System.out::println);
//
//            //clienteRepository.findAll().stream()
//                    //.forEach(System.out::println);
//
//            clienteRepository.findAll().stream()
//                    .forEach(c -> clienteRepository.delete(c));
//
//            System.out.println("CLIENTES DEPOIS DE DELETAR: ");
//            clienteRepository.findAll().stream()
//                    .forEach(System.out::println);






        };
    }

    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
