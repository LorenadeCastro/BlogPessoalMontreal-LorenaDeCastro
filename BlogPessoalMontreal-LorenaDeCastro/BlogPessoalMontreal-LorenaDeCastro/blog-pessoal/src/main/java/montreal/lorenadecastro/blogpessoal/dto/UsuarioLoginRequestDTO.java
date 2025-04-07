package montreal.lorenadecastro.blogpessoal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UsuarioLoginRequestDTO {

    @NotBlank
    @Email
    private String usuario;

    @NotBlank
    private String senha;
    
    

    public UsuarioLoginRequestDTO() {
		super();
	}
	public UsuarioLoginRequestDTO(@NotBlank @Email String usuario, @NotBlank String senha) {
		super();
		this.usuario = usuario;
		this.senha = senha;
	}
	public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}

