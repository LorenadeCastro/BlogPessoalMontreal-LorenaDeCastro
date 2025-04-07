package montreal.daniel.blogpessoal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import montreal.daniel.blogpessoal.dto.TemaDTO;
import montreal.daniel.blogpessoal.model.entities.Tema;
import montreal.daniel.blogpessoal.model.entities.Usuario;
import montreal.daniel.blogpessoal.model.repositories.TemaRepository;
import montreal.daniel.blogpessoal.model.repositories.UsuarioRepository;

@Service
public class TemaService {

    @Autowired
    private TemaRepository temaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario getUsuarioLogado() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioRepository.findByUsuario(username)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    @Transactional
    public Tema criarTema(TemaDTO dto) {
        Usuario usuario = getUsuarioLogado();
        Tema tema = new Tema();
        tema.setDescricao(dto.descricao());
        tema.setUsuario(usuario); 
        return temaRepository.save(tema);
    }

    @Transactional
    public Tema atualizarTema(Long id, TemaDTO dto) {
        Usuario usuario = getUsuarioLogado();
        Tema tema = temaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tema não encontrado!"));

        if (tema.getUsuario() == null || !tema.getUsuario().getId().equals(usuario.getId())) {
            throw new RuntimeException("Você não tem permissão para atualizar este tema.");
        }


        tema.setDescricao(dto.descricao());
        return temaRepository.save(tema);
    }

    @Transactional
    public void excluirTema(Long id) {
        Usuario usuario = getUsuarioLogado();
        Tema tema = temaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tema não encontrado!"));

        if (!tema.getUsuario().getId().equals(usuario.getId())) {
            throw new RuntimeException("Você não tem permissão para excluir este tema.");
        }

        temaRepository.deleteById(id);
    }

    public List<Tema> listarTemas() {
        return temaRepository.findAll();
    }

    public Optional<Tema> buscarPorId(Long id) {
        return temaRepository.findById(id);
    }
}

