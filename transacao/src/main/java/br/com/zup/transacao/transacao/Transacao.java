package br.com.zup.transacao.transacao;

import br.com.zup.transacao.cartao.Cartao;
import br.com.zup.transacao.compras.Estabelecimento;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transacao {
    @Id
    private String id;

    private BigDecimal valor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estabelecimento", referencedColumnName = "id")
    private Estabelecimento estabelecimento;

    //mapeia oq para quem
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartao", referencedColumnName = "id")
    private Cartao cartao;

    private LocalDateTime efetivadaEm;

    @Deprecated
    public Transacao(){}

    public Transacao(String id, BigDecimal valor, Estabelecimento estabelecimento,
                     Cartao cartao, LocalDateTime efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }
}
