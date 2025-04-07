package montreal.lorenadecastro.blogpessoal.security;

import montreal.lorenadecastro.blogpessoal.model.entities.Usuario;
import montreal.lorenadecastro.blogpessoal.model.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return User.builder()
                .username(usuario.getUsuario())
                .password(usuario.getSenha())
                .authorities(usuario.getRole()) 
                .build();
    }
}

