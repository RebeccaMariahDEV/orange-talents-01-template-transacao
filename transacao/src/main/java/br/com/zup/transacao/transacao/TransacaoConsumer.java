package br.com.zup.transacao.transacao;

import br.com.zup.transacao.repository.CartaoRepository;
import br.com.zup.transacao.repository.EstabelecimentoRepository;
import br.com.zup.transacao.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransacaoConsumer {
    @Autowired
    TransacaoRepository transacaoRepository;
    @Autowired
    CartaoRepository cartaoRepository;
    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @KafkaListener(topics = "${transaction.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void ouvir(EventoTransacao eventoTransacao) {
        Transacao transacao = eventoTransacao.criar(cartaoRepository, estabelecimentoRepository);
        System.out.println(eventoTransacao);
        transacaoRepository.save(transacao);
    }
}
