package br.com.alura.leilao.util;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LeilaoBuilder {

    private String nome;

    private BigDecimal valorInicial;

    private LocalDate data;

    private Usuario usuario;

    public Leilao criar() {
        return new Leilao(nome, valorInicial, usuario);
    }

    public LeilaoBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public LeilaoBuilder comValorInicial(String valorInicial) {
        this.valorInicial = new BigDecimal(valorInicial);
        return  this;
    }

    public LeilaoBuilder comData(LocalDate data) {
        this.data = data;
        return this;
    }

    public LeilaoBuilder comUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }
}
