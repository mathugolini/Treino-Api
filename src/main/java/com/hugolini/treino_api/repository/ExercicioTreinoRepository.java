package com.hugolini.treino_api.repository;

import com.hugolini.treino_api.model.ExercicioTreino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExercicioTreinoRepository extends JpaRepository<ExercicioTreino, Long> {

}
