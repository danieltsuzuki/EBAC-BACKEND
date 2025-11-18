package danieltsuzuki.com.github.testesdesoftware.repositories;

import danieltsuzuki.com.github.testesdesoftware.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
