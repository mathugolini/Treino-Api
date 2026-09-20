package com.hugolini.treino_api.service;

import com.hugolini.treino_api.model.ExercicioTreino;
import org.springframework.stereotype.Service;

@Service
public class ProgressaoService {

    private static final double PROGRESSAO_PERCENTUAL = 0.05; // 5% de progressão

    public double sugerirProximaCarga(ExercicioTreino ultimoRegistro, int repeticoesAlvo) {
        boolean antigiuMeta = ultimoRegistro.getRepeticoes() >= repeticoesAlvo;

        if (!antigiuMeta) {
            return ultimoRegistro.getCargaKg();
        }

        double novaCarga = ultimoRegistro.getCargaKg() * (1 + PROGRESSAO_PERCENTUAL);
        return arredondarParaMultiplosDe(novaCarga, 0.25);
    }

    private double arredondarParaMultiplosDe(double valor, double multiplo) {
        return Math.round(valor / multiplo) * multiplo;
    }

}
