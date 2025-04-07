package montreal.lorenadecastro.blogpessoal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import montreal.daniel.blogpessoal.dto.TemaDTO;
import montreal.daniel.blogpessoal.model.entities.Tema;
import montreal.daniel.blogpessoal.service.TemaService;

import java.util.List;

@RestController
@RequestMapping("/temas")
public class TemaController {

	@Autowired
	private TemaService temaService;

	@PreAuthorize("hasRole('USER')")
	@PostMapping
	public ResponseEntity<Tema> criarTema(@RequestBody @Valid TemaDTO dto) {
		Tema temaCriado = temaService.criarTema(dto);
		return ResponseEntity.ok(temaCriado);
	}

	@PreAuthorize("hasRole('USER')")
	@PutMapping("/{id}")
	public ResponseEntity<Tema> atualizarTema(@PathVariable Long id, @RequestBody @Valid TemaDTO dto) {
		Tema temaAtualizado = temaService.atualizarTema(id, dto);
		return ResponseEntity.ok(temaAtualizado);
	}

	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirTema(@PathVariable Long id) {
		temaService.excluirTema(id);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/{id}")
	public ResponseEntity<Tema> buscarPorId(@PathVariable Long id) {
		return temaService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping
	public ResponseEntity<List<Tema>> listarTemas() {
		return ResponseEntity.ok(temaService.listarTemas());
	}
}
