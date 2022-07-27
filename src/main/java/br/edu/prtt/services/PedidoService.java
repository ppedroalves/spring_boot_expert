package br.edu.prtt.services;


import br.edu.prtt.domain.entidades.Pedido;
import br.edu.prtt.domain.entidades.dto.PedidoDTO;
import br.edu.prtt.domain.entidades.enums.StatusPedido;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizarPedido(Integer id, StatusPedido status);


}
