package montreal.lorenadecastro.blogpessoal.model.entities;

public class UsuarioLogin {
    private String usuario;
    private String senha;
    private String token;

    public UsuarioLogin() {}

    public UsuarioLogin(String usuario, String senha, String token) {
        this.usuario = usuario;
        this.senha = senha;
        this.token = token;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
