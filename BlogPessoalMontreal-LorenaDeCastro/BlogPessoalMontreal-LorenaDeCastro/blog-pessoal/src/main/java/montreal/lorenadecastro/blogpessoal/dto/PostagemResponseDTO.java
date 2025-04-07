package montreal.lorenadecastro.blogpessoal.dto;

public class PostagemResponseDTO {
    private Long id;
    private String titulo;
    private String texto;
    private String temaDescricao;
    private String autorNome;

    public PostagemResponseDTO(Long id, String titulo, String texto, String temaDescricao, String autorNome) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.temaDescricao = temaDescricao;
        this.autorNome = autorNome;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTexto() {
        return texto;
    }

    public String getTemaDescricao() {
        return temaDescricao;
    }

    public String getAutorNome() {
        return autorNome;
    }
}


