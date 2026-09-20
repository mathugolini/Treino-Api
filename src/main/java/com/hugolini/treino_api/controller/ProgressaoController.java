package com.hugolini.treino_api.controller;

import com.hugolini.treino_api.model.ExercicioTreino;
import com.hugolini.treino_api.service.ProgressaoService;
import com.hugolini.treino_api.service.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/exercicios/{exercicioTreinoId}/progressao")
public class ProgressaoController {

    private ProgressaoService progressaoService;
    private TreinoService treinoService;

    @Autowired
    public ProgressaoController(ProgressaoService progressaoService, TreinoService treinoService) {
        this.progressaoService = progressaoService;
        this.treinoService = treinoService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> sugerirProgressao(
            @PathVariable Long exercicioTreinoId,
            @RequestParam int repeticoesAlvo) {
        ExercicioTreino ultimoRegistro = treinoService.buscarExercicio(exercicioTreinoId);
        double cargaSugerida = progressaoService.sugerirProximaCarga(ultimoRegistro, repeticoesAlvo);
        return ResponseEntity.ok(Map.of(
                "exercicio"
                , ultimoRegistro.getNomeExercicio(),
                "cargaAnterior"
                , ultimoRegistro.getCargaKg(),
                "cargaSugerida"
                , cargaSugerida
        ));
    }
}
