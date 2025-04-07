package montreal.lorenadecastro.blogpessoal.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import montreal.daniel.blogpessoal.model.entities.Tema;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {
}
