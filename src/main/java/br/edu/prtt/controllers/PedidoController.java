package br.edu.prtt.controllers;


import br.edu.prtt.domain.entidades.ItemPedido;
import br.edu.prtt.domain.entidades.Pedido;
import br.edu.prtt.domain.entidades.dto.AtualizacaoStatusPedidoDTO;
import br.edu.prtt.domain.entidades.dto.InformacaoItemPedidoDTO;
import br.edu.prtt.domain.entidades.dto.InformacaoPedidoDTO;
import br.edu.prtt.domain.entidades.dto.PedidoDTO;
import br.edu.prtt.domain.entidades.enums.StatusPedido;
import br.edu.prtt.exception.RegraNegocioException;
import br.edu.prtt.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    @GetMapping("{id}")
    public InformacaoPedidoDTO getById( @PathVariable("id")  Integer id){
        return pedidoService.obterPedidoCompleto(id)
                .map(this::converter)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido nao encontrado"));
    }



    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelarPedido(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        pedidoService.atualizarPedido(id, StatusPedido.valueOf(novoStatus));

    }

    private InformacaoPedidoDTO converter(Pedido pedido){
            return InformacaoPedidoDTO.builder()
                    .codigo(pedido.getId())
                    .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                    .nomeCliente(pedido.getCliente().getNome())
                    .total(pedido.getTotal())
                    .statusPedido(pedido.getStatus().name())
                    .items(converterItemPedido(pedido.getItens()))
                    .build();
    }

    private List<InformacaoItemPedidoDTO> converterItemPedido(List<ItemPedido> items){
        if(CollectionUtils.isEmpty(items)){
            return Collections.emptyList();
        }

        return items.stream()
                .map(i ->
                            InformacaoItemPedidoDTO.builder()
                            .descricao(i.getProduto().getDescricao())
                            .precoUnitario(i.getProduto().getPreco())
                            .quantidade(i.getQuantidade())
                            .build()
                ).collect(Collectors.toList());
    }

}
