package br.com.zup.transacao.controller;

import br.com.zup.transacao.cartao.Cartao;
import br.com.zup.transacao.cartao.EventoCartao;
import br.com.zup.transacao.compras.Estabelecimento;
import br.com.zup.transacao.compras.EventoEstabelecimento;
import br.com.zup.transacao.repository.CartaoRepository;
import br.com.zup.transacao.repository.EstabelecimentoRepository;
import br.com.zup.transacao.repository.TransacaoRepository;
import br.com.zup.transacao.transacao.EventoTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TransacaoController {

    CartaoRepository cartaoRepository;
    EstabelecimentoRepository estabelecimentoRepository;
    TransacaoRepository transacaoRepository;

    @Autowired
    public TransacaoController(CartaoRepository cartaoRepository,
                               EstabelecimentoRepository estabelecimentoRepository,
                               TransacaoRepository transacaoRepository) {
        this.cartaoRepository = cartaoRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.transacaoRepository = transacaoRepository;
    }

    @GetMapping("compras/{id}/transacoes")
    public ResponseEntity<?> pesquisaDeCompras(@PathVariable("id") Long id){

        //verifica se tem o id desse cartão
        Optional<Cartao> findCartao = cartaoRepository.findById(id);
        if (findCartao.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //mostra a lista das 10 compras
        var compras =
                transacaoRepository.findTop10ByCartaoOrderByEfetivadaEmDesc(findCartao.get());

        return ResponseEntity.ok().body(compras);
    }

}
