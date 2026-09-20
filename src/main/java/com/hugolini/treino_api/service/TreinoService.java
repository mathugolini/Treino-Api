package com.hugolini.treino_api.service;

import com.hugolini.treino_api.model.Aluno;
import com.hugolini.treino_api.model.ExercicioTreino;
import com.hugolini.treino_api.model.Treino;
import com.hugolini.treino_api.repository.ExercicioTreinoRepository;
import com.hugolini.treino_api.repository.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreinoService {

    private final TreinoRepository treinoRepository;
    private final ExercicioTreinoRepository exercicioTreinoRepository;
    private final AlunoService alunoService;


    @Autowired
    public TreinoService(TreinoRepository treinoRepository, ExercicioTreinoRepository exercicioTreinoRepository, AlunoService alunoService) {
        this.treinoRepository = treinoRepository;
        this.exercicioTreinoRepository = exercicioTreinoRepository;
        this.alunoService = alunoService;
    }

    public Treino registrar(Long alunoId, Treino treino) {
        Aluno aluno = alunoService.buscarPorId(alunoId);
        treino.setAluno(aluno);

        for (ExercicioTreino exercicio : treino.getExercicios()) {
            exercicio.setTreino(treino);
        }
        return treinoRepository.save(treino);
    }

    public List<Treino> listarPorAluno(Long alunodId) {
        return treinoRepository.findByAlunoId(alunodId);
    }

    public ExercicioTreino buscarExercicio (Long exercicioTreinoId) {
        return exercicioTreinoRepository.findById(exercicioTreinoId)
                .orElseThrow(() -> new RuntimeException("Exercício não encontrado com o ID: " + exercicioTreinoId));
    }


}
