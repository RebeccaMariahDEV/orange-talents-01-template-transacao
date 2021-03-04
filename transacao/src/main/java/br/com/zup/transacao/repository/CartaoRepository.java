package br.com.zup.transacao.repository;

import br.com.zup.transacao.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
//    Optional<Cartao> findByIdCartao(String idCartao);
    Optional<Cartao> findByIdCartao(String id);

}
