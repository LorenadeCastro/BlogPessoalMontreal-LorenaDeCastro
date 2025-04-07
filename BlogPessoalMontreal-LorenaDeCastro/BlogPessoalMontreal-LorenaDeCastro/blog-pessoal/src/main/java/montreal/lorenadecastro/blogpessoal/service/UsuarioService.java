package montreal.daniel.blogpessoal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import montreal.daniel.blogpessoal.dto.UsuarioDTO;
import montreal.daniel.blogpessoal.dto.UsuarioUpdateDTO;
import montreal.daniel.blogpessoal.model.entities.Usuario;
import montreal.daniel.blogpessoal.model.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;

	public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public Usuario cadastrarUsuario(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioDTO.getNome());
		usuario.setUsuario(usuarioDTO.getUsuario());
		usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha())); 
		usuario.setFoto(usuarioDTO.getFoto());

		usuario.setRole("USER");

		return usuarioRepository.save(usuario);
	}

	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}

	public Optional<Usuario> buscarPorId(Long id) {
		return usuarioRepository.findById(id);
	}

	public Optional<Usuario> buscarPorUsuario(String usuario) {
		return usuarioRepository.findByUsuario(usuario);
	}

	public Optional<Usuario> atualizarUsuario(Long id, UsuarioUpdateDTO dto) {
		return usuarioRepository.findById(id).map(usuario -> {
			usuario.setNome(dto.getNome());
			usuario.setUsuario(dto.getUsuario());
			usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
			usuario.setFoto(dto.getFoto());

			usuario.setRole("USER");

			return usuarioRepository.save(usuario);
		});
	}

	public boolean deletarUsuario(Long id) {
		if (usuarioRepository.existsById(id)) { 
			usuarioRepository.deleteById(id);
			return true; 
		}
		return false; 
	}

}
