package br.edu.unipe.api.repository;

import br.edu.unipe.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository
        extends JpaRepository<Usuario,Integer> {

          Usuario findByEmail(String email);
          Usuario findByEmailAndNome(String email, String nome);

          @Query("select u from Usuario u where u.email=:email")
          Usuario buscarPorEmail(@Param("email") String email);

}
