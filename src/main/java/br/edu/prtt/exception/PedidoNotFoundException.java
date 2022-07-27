package br.edu.prtt.exception;

public class PedidoNotFoundException extends RuntimeException {
    public PedidoNotFoundException() {
        super("Pedido não encontrado.");
    }
}
