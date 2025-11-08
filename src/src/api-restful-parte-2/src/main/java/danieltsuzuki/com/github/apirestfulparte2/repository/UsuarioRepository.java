package danieltsuzuki.com.github.apirestfulparte2.repository;


import danieltsuzuki.com.github.apirestfulparte2.model.Usuario;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepositoryImplementation<Usuario, Long> {
    Optional<Usuario> findByNomeAndSenha(String nome, String senha);
}
