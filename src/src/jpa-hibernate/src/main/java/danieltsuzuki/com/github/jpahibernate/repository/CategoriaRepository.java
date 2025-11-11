package danieltsuzuki.com.github.jpahibernate.repository;

import danieltsuzuki.com.github.jpahibernate.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
