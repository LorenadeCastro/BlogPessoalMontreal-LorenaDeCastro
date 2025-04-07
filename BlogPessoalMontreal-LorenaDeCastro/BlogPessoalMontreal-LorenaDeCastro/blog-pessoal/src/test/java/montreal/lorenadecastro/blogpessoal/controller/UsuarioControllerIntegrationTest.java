package montreal.lorenadecastro.blogpessoal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import montreal.daniel.blogpessoal.dto.UsuarioDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCadastrarUsuarioComSucesso() throws Exception {
        UsuarioDTO dto = new UsuarioDTO("Novo Usuário", "novo@email.com", "12345678", "foto.png");

        mockMvc.perform(post("/usuarios/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Novo Usuário"))
                .andExpect(jsonPath("$.usuario").value("novo@email.com"));
    }
    
    @Test
    void deveAutenticarUsuarioComSucesso() throws Exception {
        UsuarioDTO dto = new UsuarioDTO("Usuário Login", "login@email.com", "12345678", null);

        mockMvc.perform(post("/usuarios/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/usuarios/logar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuario").value("login@email.com"))
                .andExpect(jsonPath("$.token").isNotEmpty());
    }
    
    @Test
    void deveRetornarErroAoAutenticarUsuarioComSenhaIncorreta() throws Exception {
        UsuarioDTO dto = new UsuarioDTO("Usuário Teste", "teste@email.com", "senhaCorreta", null);

        mockMvc.perform(post("/usuarios/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        UsuarioDTO dtoInvalido = new UsuarioDTO("Usuário Teste", "teste@email.com", "senhaErrada", null);

        mockMvc.perform(post("/usuarios/logar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoInvalido)))
                .andExpect(status().isUnauthorized());
    }


}
