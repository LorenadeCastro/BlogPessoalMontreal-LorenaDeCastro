package montreal.lorenadecastro.blogpessoal.controller;

import jakarta.validation.Valid;
import montreal.daniel.blogpessoal.model.entities.Postagem;
import montreal.daniel.blogpessoal.model.entities.Tema;
import montreal.daniel.blogpessoal.model.entities.Usuario;
import montreal.daniel.blogpessoal.model.repositories.PostagemRepository;
import montreal.daniel.blogpessoal.model.repositories.TemaRepository;
import montreal.daniel.blogpessoal.model.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TemaRepository temaRepository;

    @GetMapping
    public ResponseEntity<List<Postagem>> listarTodas() {
        return ResponseEntity.ok(postagemRepository.findAll());
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<Postagem>> filtrar(
            @RequestParam(required = false) Long autor,
            @RequestParam(required = false) Long tema) {

        List<Postagem> postagens;

        if (autor != null && tema != null) {
            postagens = postagemRepository.findAllByUsuarioIdAndTemaId(autor, tema);
        } else if (autor != null) {
            postagens = postagemRepository.findAllByUsuarioId(autor);
        } else if (tema != null) {
            postagens = postagemRepository.findAllByTemaId(tema);
        } else {
            postagens = postagemRepository.findAll();
        }

        return ResponseEntity.ok(postagens);
    }

    @PostMapping
    public ResponseEntity<Postagem> criarPostagem(
            @Valid @RequestBody Postagem postagem,
            @AuthenticationPrincipal UserDetails userDetails) {

        Optional<Usuario> usuario = usuarioRepository.findByUsuario(userDetails.getUsername());
        if (usuario.isEmpty())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        postagem.setUsuario(usuario.get());

        if (postagem.getTema() != null) {
            Optional<Tema> temaOptional = temaRepository.findById(postagem.getTema().getId());

            if (temaOptional.isPresent()) {
                postagem.setTema(temaOptional.get());
            } else {
                Tema novoTema = postagem.getTema();
                temaRepository.save(novoTema);
                postagem.setTema(novoTema);
            }
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Postagem> atualizarPostagem(
            @PathVariable Long id,
            @Valid @RequestBody Postagem postagemAtualizada,
            @AuthenticationPrincipal UserDetails userDetails) {

        Optional<Postagem> postagem = postagemRepository.findById(id);
        if (postagem.isEmpty())
            return ResponseEntity.notFound().build();

        if (!postagem.get().getUsuario().getUsuario().equals(userDetails.getUsername()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        postagemAtualizada.setId(id);
        postagemAtualizada.setUsuario(postagem.get().getUsuario());

        if (postagemAtualizada.getTema() != null) {
            Optional<Tema> temaOptional = temaRepository.findById(postagemAtualizada.getTema().getId());

            if (temaOptional.isPresent()) {
                postagemAtualizada.setTema(temaOptional.get());
            } else {
                temaRepository.save(postagemAtualizada.getTema());
            }
        }

        return ResponseEntity.ok(postagemRepository.save(postagemAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPostagem(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        Optional<Postagem> postagem = postagemRepository.findById(id);
        if (postagem.isEmpty())
            return ResponseEntity.notFound().build();

        if (!postagem.get().getUsuario().getUsuario().equals(userDetails.getUsername()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        postagemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

