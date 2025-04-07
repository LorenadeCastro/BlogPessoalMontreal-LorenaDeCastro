package montreal.lorenadecastro.blogpessoal.dto;

public class UsuarioUpdateDTO {
    private String nome;
    private String usuario;
    private String senha;
    private String foto;

    public UsuarioUpdateDTO() {}

    public UsuarioUpdateDTO(String nome, String usuario, String senha, String foto) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
