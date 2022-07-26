package br.edu.prtt.services.impl;


import br.edu.prtt.domain.entidades.Cliente;
import br.edu.prtt.domain.entidades.ItemPedido;
import br.edu.prtt.domain.entidades.Pedido;
import br.edu.prtt.domain.entidades.Produto;
import br.edu.prtt.domain.entidades.dto.ItemPedidoDTO;
import br.edu.prtt.domain.entidades.dto.PedidoDTO;
import br.edu.prtt.exception.RegraNegocioException;
import br.edu.prtt.repository.ClienteRepository;
import br.edu.prtt.repository.ItemPedidoRepository;
import br.edu.prtt.repository.PedidoRepository;
import br.edu.prtt.repository.ProdutoRepository;
import br.edu.prtt.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl  implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
       List<ItemPedido> items = converterItems(pedido, dto.getItems());
       pedidoRepository.save(pedido);
       itemPedidoRepository.saveAll(items);
       pedido.setItens(items);

        return pedido;
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Lista de items vazia.");
        }

        return items.stream().map(dto -> {
            Integer idProduto = dto.getProduto();
            Produto produto = produtoRepository.findById(idProduto)
                    .orElseThrow(() -> new RegraNegocioException("Produto não encontrado"));

            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(dto.getQuantidade());
            return item;

        }).collect(Collectors.toList());
    }
}
