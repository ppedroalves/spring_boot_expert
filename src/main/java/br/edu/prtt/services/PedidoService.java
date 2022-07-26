package br.edu.prtt.services;


import br.edu.prtt.domain.entidades.Pedido;
import br.edu.prtt.domain.entidades.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

}
