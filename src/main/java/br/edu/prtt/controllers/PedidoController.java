package br.edu.prtt.controllers;


import br.edu.prtt.domain.entidades.Pedido;
import br.edu.prtt.domain.entidades.dto.PedidoDTO;
import br.edu.prtt.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer cadastrarPedido(@RequestBody PedidoDTO pedidoDTO){
        Pedido pedidoSalvo = pedidoService.salvar(pedidoDTO);
        return pedidoSalvo.getId();
    }


}
