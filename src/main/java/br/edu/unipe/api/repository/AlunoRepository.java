package br.edu.unipe.api.repository;

import br.edu.unipe.api.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository
        extends JpaRepository<Aluno,Integer> {

          Aluno findByEmail(String email);
          Aluno findByEmailAndNome(String email, String nome);

          @Query("select u from Aluno u where u.email=:email")
          Aluno buscarPorEmail(@Param("email") String email);

}
