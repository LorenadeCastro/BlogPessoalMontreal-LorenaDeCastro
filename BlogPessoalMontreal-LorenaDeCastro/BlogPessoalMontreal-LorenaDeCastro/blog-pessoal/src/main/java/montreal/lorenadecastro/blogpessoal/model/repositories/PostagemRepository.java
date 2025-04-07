package montreal.lorenadecastro.blogpessoal.model.repositories;

import montreal.l.blogpessoal.model.entities.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	
	List<Postagem> findAllByUsuarioId(Long idUsuario);  
	List<Postagem> findAllByTemaId(Long idTema);
	List<Postagem> findAllByUsuarioIdAndTemaId(Long idUsuario, Long idTema);

    List<Postagem> findByUsuarioId(Long usuarioId);

    List<Postagem> findByTemaId(Long temaId);

    List<Postagem> findByUsuarioIdAndTemaId(Long usuarioId, Long temaId);
}

