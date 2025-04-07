package montreal.daniel.blogpessoal.service;

import montreal.daniel.blogpessoal.dto.UsuarioDTO;
import montreal.daniel.blogpessoal.model.entities.Usuario;
import montreal.daniel.blogpessoal.model.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarUsuarioComSucesso() {
        UsuarioDTO dto = new UsuarioDTO("Daniel", "daniel@email.com", "12345678", "foto.png");

        Usuario usuarioMock = new Usuario(dto.getNome(), dto.getUsuario(), dto.getSenha(), dto.getFoto());
        usuarioMock.setId(1L);

        when(usuarioRepository.findByUsuario(dto.getUsuario())).thenReturn(Optional.empty());
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioMock);

        Usuario result = usuarioService.cadastrarUsuario(dto);

        assertNotNull(result);
        assertEquals("Daniel", result.getNome());
        assertEquals("daniel@email.com", result.getUsuario());
    }

    @Test
    void deveLancarExcecaoSeUsuarioJaExistir() {
        UsuarioDTO dto = new UsuarioDTO("Daniel", "daniel@email.com", "12345678", "foto.png");

        when(usuarioRepository.findByUsuario(dto.getUsuario()))
                .thenReturn(Optional.of(new Usuario()));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.cadastrarUsuario(dto);
        });

        assertTrue(exception.getMessage().contains("Usuário já existente"));
    }
}

