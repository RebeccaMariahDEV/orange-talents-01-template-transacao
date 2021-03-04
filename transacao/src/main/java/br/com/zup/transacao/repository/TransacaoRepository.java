package br.com.zup.transacao.repository;

import br.com.zup.transacao.cartao.Cartao;
import br.com.zup.transacao.transacao.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findTop10ByCartaoOrderByEfetivadaEmDesc(Cartao cartao);
}
