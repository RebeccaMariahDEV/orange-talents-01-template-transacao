package br.com.zup.transacao.transacao;

import br.com.zup.transacao.cartao.Cartao;
import br.com.zup.transacao.cartao.EventoCartao;
import br.com.zup.transacao.compras.Estabelecimento;
import br.com.zup.transacao.compras.EventoEstabelecimento;
import br.com.zup.transacao.repository.CartaoRepository;
import br.com.zup.transacao.repository.EstabelecimentoRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EventoTransacao {

    private String id;

    private BigDecimal valor;

    private EventoEstabelecimento estabelecimento;

    private EventoCartao cartao;

    private LocalDateTime efetivadaEm;

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EventoEstabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public EventoCartao getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    //criando uma nova transacao para mensagens do kafka
    public Transacao criar(CartaoRepository cartaoRepository,
                           EstabelecimentoRepository estabelecimentoRepository) {

        //pesquisa se tem o cartão, se não cria um cartão
        var findCartao = cartaoRepository.findByIdCartao(cartao.getId());
        Cartao cartao = findCartao.isPresent() ? findCartao.get() : new Cartao(this.cartao.getId(),
                this.cartao.getEmail());

        //pesquisa se tem o estabelecimento
        var findEstabelecimento =
                estabelecimentoRepository.findByNome(estabelecimento.getNome());

        Estabelecimento estabelecimento = findEstabelecimento.isPresent() ? findEstabelecimento.get() :
                new Estabelecimento(this.estabelecimento.getNome(),
                        this.estabelecimento.getCidade(),
                        this.estabelecimento.getEndereco());


        return new Transacao(id, valor, estabelecimento, cartao, this.efetivadaEm);
    }

    @Override
    public String toString() {
        return "EventoTransacao{" +
                "id='" + id + '\'' +
                ", valor=" + valor +
                ", estabelecimento=" + estabelecimento +
                ", cartao=" + cartao +
                ", efetivadaEm=" + efetivadaEm +
                '}';
    }
}
