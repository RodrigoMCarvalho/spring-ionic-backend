package br.com.cursomvc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.cursomvc.dto.CategoriaDTO;
import br.com.cursomvc.models.Categoria;
import br.com.cursomvc.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping("/todos")
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> categorias = service.findAll();
		
		return ResponseEntity.ok().body(categorias);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAllDTO() {
		List<Categoria> categorias = service.findAll();
		List<CategoriaDTO> categoriasDto = categorias.stream().map(cat 
				-> new CategoriaDTO(cat)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(categoriasDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable("id") Integer id) {
		Categoria categoria = service.findById(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody Categoria categoria){
			categoria = service.save(categoria);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()   //obtém a URI do novo recurso que foi inserido
					.path("/{id}").buildAndExpand(categoria.getId()).toUri();
			return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Categoria categoria, @PathVariable("id") Integer id) {
		categoria.setId(id);
		categoria = service.update(categoria);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Categoria> delete(@PathVariable("id") Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
