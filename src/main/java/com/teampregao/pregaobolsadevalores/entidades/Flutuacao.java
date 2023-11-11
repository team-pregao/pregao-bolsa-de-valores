package com.teampregao.pregaobolsadevalores.entidades;

import java.time.LocalDateTime;

public class Flutuacao {
    private final Id id;
    private final Ativo ativo;
    private final double flutuacao;
    private final double precoAtivo;
    private LocalDateTime horario;

    public Flutuacao(Id id, Ativo ativo, double flutuacao, double precoAtivo) {
        this.id = id;
        this.ativo = ativo;
        this.flutuacao = flutuacao;
        this.precoAtivo = precoAtivo;
        this.horario = LocalDateTime.now();
    }

    public Id getId() {
        return id;
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public double getPrecoAtivo() {
        return precoAtivo;
    }

    public double getFlutuacao() {
        return flutuacao;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }
}
