package br.edu.prtt.domain.entidades.dto;

import br.edu.prtt.validation.NotEmptyList;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;




@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDTO {

    @NotNull(message = "Informe o codigo do cliente")
    private Integer cliente;
    private BigDecimal total;
    @NotEmptyList(message = "Pedido nao pode ser realizado sem itens")
    private List<ItemPedidoDTO> items;




}
