package br.edu.prtt.domain.entidades.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;




@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDTO {

    private Integer cliente;
    private BigDecimal total;
    private List<ItemPedidoDTO> items;




}