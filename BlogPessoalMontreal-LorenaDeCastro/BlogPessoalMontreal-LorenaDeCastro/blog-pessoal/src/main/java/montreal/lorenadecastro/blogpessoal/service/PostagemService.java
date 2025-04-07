package montreal.daniel.blogpessoal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import montreal.daniel.blogpessoal.dto.PostagemDTO;
import montreal.daniel.blogpessoal.dto.PostagemResponseDTO;
import montreal.daniel.blogpessoal.model.entities.Postagem;
import montreal.daniel.blogpessoal.model.entities.Tema;
import montreal.daniel.blogpessoal.model.entities.Usuario;
import montreal.daniel.blogpessoal.model.repositories.PostagemRepository;
import montreal.daniel.blogpessoal.model.repositories.TemaRepository;
import montreal.daniel.blogpessoal.model.repositories.UsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TemaRepository temaRepository;

    public Postagem criarPostagem(PostagemDTO dto, UserDetails userDetails) {
        Optional<Usuario> usuario = usuarioRepository.findByUsuario(userDetails.getUsername());

        if (usuario.isEmpty()) {
            throw new RuntimeException("Usuário autenticado não encontrado!");
        }

        Tema tema;
        if (dto.temaId() != null) {
            tema = temaRepository.findById(dto.temaId())
                    .orElseThrow(() -> new RuntimeException("Tema não encontrado!"));
        } else {
            tema = new Tema();
            tema.setDescricao(dto.temaDescricao());
            temaRepository.save(tema);
        }

        Postagem postagem = new Postagem();
        postagem.setTitulo(dto.titulo());
        postagem.setTexto(dto.texto());
        postagem.setUsuario(usuario.get());
        postagem.setTema(tema);

        return postagemRepository.save(postagem);
    }

    public Postagem atualizarPostagem(Long id, PostagemDTO dto, UserDetails userDetails) {
        Postagem postagem = postagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Postagem não encontrada!"));

        if (!postagem.getUsuario().getUsuario().equals(userDetails.getUsername())) {
            throw new RuntimeException("Apenas o autor pode atualizar a postagem.");
        }

        postagem.setTitulo(dto.titulo());
        postagem.setTexto(dto.texto());

        return postagemRepository.save(postagem);
    }

    public void excluirPostagem(Long id, UserDetails userDetails) {
        Postagem postagem = postagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Postagem não encontrada!"));

        if (!postagem.getUsuario().getUsuario().equals(userDetails.getUsername())) {
            throw new RuntimeException("Apenas o autor pode excluir a postagem.");
        }

        postagemRepository.deleteById(id);
    }

    public List<PostagemResponseDTO> listarPostagens() {
        return postagemRepository.findAll().stream().map(postagem ->
                new PostagemResponseDTO(
                        postagem.getId(),
                        postagem.getTitulo(),
                        postagem.getTexto(),
                        postagem.getTema().getDescricao(),
                        postagem.getUsuario().getNome()
                )
        ).collect(Collectors.toList());
    }

    public List<PostagemResponseDTO> filtrarPostagens(Long usuarioId, Long temaId) {
        List<Postagem> postagens;

        if (usuarioId != null && temaId != null) {
            postagens = postagemRepository.findByUsuarioIdAndTemaId(usuarioId, temaId);
        } else if (usuarioId != null) {
            postagens = postagemRepository.findByUsuarioId(usuarioId);
        } else if (temaId != null) {
            postagens = postagemRepository.findByTemaId(temaId);
        } else {
            return listarPostagens();
        }

        return postagens.stream().map(postagem ->
                new PostagemResponseDTO(
                        postagem.getId(),
                        postagem.getTitulo(),
                        postagem.getTexto(),
                        postagem.getTema().getDescricao(),
                        postagem.getUsuario().getNome()
                )
        ).collect(Collectors.toList());
    }
}



