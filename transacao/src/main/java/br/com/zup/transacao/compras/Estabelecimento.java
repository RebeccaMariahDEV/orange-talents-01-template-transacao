package br.com.zup.transacao.compras;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cidade;

    private String nome;

    private String endereco;

//    private LocalDate comprandoEm;

    @Deprecated
    public Estabelecimento() {
    }

    public Estabelecimento(String cidade, String nome, String endereco) {
        this.cidade = cidade;
        this.nome = nome;
        this.endereco = endereco;
//        this.comprandoEm = comprandoEm;
    }

    public Long getId() {
        return id;
    }

    public String getCidade() {
        return cidade;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

}
