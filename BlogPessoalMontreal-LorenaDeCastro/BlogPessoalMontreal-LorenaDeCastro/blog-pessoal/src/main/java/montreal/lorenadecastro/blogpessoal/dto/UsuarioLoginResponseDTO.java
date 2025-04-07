package montreal.lorenadecastro.blogpessoal.dto;

public class UsuarioLoginResponseDTO {

    private String usuario;
    private String token;

    

    public UsuarioLoginResponseDTO() {
		super();
	}
	public UsuarioLoginResponseDTO(String usuario, String token) {
		super();
		this.usuario = usuario;
		this.token = token;
	}
	public String getUsuario() { return usuario; }
    public String getToken() { return token; }
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public void setToken(String token) {
		this.token = token;
	}
    
    

	
    
    
}
