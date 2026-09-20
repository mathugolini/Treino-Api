package com.hugolini.treino_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hugolini.treino_api.utils.TipoExercicio;
import jakarta.persistence.*;

@Entity
public class ExercicioTreino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeExercicio;

    @Enumerated(EnumType.STRING)
    private TipoExercicio tipo;

    private Integer series;
    private Integer repeticoes;
    private Double cargaKg;

    @ManyToOne
    @JoinColumn(name = "treino_id")
    @JsonIgnore
    private Treino treino;

    public ExercicioTreino() {}

    public ExercicioTreino(Long id, String nomeExercicio, TipoExercicio tipo, Integer series, Integer repeticoes, Double cargaKg, Treino treino) {
        this.id = id;
        this.nomeExercicio = nomeExercicio;
        this.tipo = tipo;
        this.series = series;
        this.repeticoes = repeticoes;
        this.cargaKg = cargaKg;
        this.treino = treino;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeExercicio() {
        return nomeExercicio;
    }

    public void setNomeExercicio(String nomeExercicio) {
        this.nomeExercicio = nomeExercicio;
    }

    public TipoExercicio getTipo() {
        return tipo;
    }

    public void setTipo(TipoExercicio tipo) {
        this.tipo = tipo;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(Integer repeticoes) {
        this.repeticoes = repeticoes;
    }

    public Double getCargaKg() {
        return cargaKg;
    }

    public void setCargaKg(Double cargaKg) {
        this.cargaKg = cargaKg;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }
}
