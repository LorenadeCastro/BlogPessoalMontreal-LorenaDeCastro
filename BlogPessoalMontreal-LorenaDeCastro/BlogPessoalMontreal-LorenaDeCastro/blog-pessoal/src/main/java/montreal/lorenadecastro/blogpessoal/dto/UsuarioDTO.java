package montreal.lorenadecastro.blogpessoal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {

    @NotBlank
    @Size(min = 3, max = 255)
    private String nome;

    @NotBlank
    @Email
    private String usuario;

    @NotBlank
    @Size(min = 8)
    private String senha;

    private String foto;

    

    public UsuarioDTO() {}

    public UsuarioDTO(String nome, String usuario, String senha, String foto) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getFoto() {
        return foto;
    }

    

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    
}
