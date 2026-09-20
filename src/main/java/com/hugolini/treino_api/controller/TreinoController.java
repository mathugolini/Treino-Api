package com.hugolini.treino_api.controller;

import com.hugolini.treino_api.model.Treino;
import com.hugolini.treino_api.service.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos/{alunoId}/treinos")
public class TreinoController {

    private final TreinoService treinoService;

    @Autowired
    public TreinoController(TreinoService treinoService) {
        this.treinoService = treinoService;
    }

    @PostMapping
    public ResponseEntity<Treino> registrar(@PathVariable Long alunoId, @RequestBody Treino treino) {
        return ResponseEntity.status(201).body(treinoService.registrar(alunoId, treino));
    }

    public ResponseEntity<List<Treino>> listarPorAluno(@PathVariable Long algunoId) {
        return ResponseEntity.ok(treinoService.listarPorAluno(algunoId));
    }

}
