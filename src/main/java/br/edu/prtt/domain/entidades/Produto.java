package br.edu.prtt.domain.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    @Column(name="descricao")
    @NotEmpty(message = "A descrição é obrigatoria")
    private String descricao;
    @Column(name="preco_unitario")
    @NotNull(message = "O preço é obrigatorio")
    private BigDecimal preco;

    public Produto() {
    }

    public Produto(String descricao, BigDecimal preco) {
        this.descricao = descricao;
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
