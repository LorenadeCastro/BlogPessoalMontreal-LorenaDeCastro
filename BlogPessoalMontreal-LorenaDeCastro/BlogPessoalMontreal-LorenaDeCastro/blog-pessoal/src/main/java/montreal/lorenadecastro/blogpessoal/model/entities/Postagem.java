package montreal.lorenadecastro.blogpessoal.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "postagens")
public class Postagem {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    @Column(length = 500)
    private String texto;

    @CreationTimestamp
    private Instant data;
    

    @ManyToOne
    @JoinColumn(name = "tema_id")
    @JsonManagedReference
    @JsonIgnore
    @JsonIgnoreProperties("postagens")
    private Tema tema;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonManagedReference
    @JsonIgnore

    private Usuario usuario;

	public Postagem() {
	}

	public Postagem(Long id, @NotBlank String titulo, @NotBlank String texto, Instant data, Tema tema,
			Usuario usuario) {
		this.titulo = titulo;
		this.texto = texto;
		this.data = data;
		this.tema = tema;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Instant getData() {
		return data;
	}

	public void setData(Instant data) {
		this.data = data;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
    
    
    
    
}
