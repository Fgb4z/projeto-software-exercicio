package br.edu.insper.curso.controller;
import br.edu.insper.curso.model.Curso;
import br.edu.insper.curso.dto.CursoRequest;
import br.edu.insper.curso.repository.CursoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/cursos")
public class CursoController {
    private final CursoRepository repo;
    public CursoController(CursoRepository repo){ this.repo = repo; }
    @PostMapping
    public ResponseEntity<Curso> criar(@RequestBody CursoRequest r){
        Curso c = new Curso();
        c.setTitulo(r.titulo());
        c.setDescricao(r.descricao());
        c.setCargaHoraria(r.cargaHoraria());
        c.setInstrutor(r.instrutor());
        c = repo.save(c);
        return ResponseEntity.created(URI.create("/cursos/"+c.getId())).body(c);
    }
    @GetMapping
    public List<Curso> listar(){ return repo.findAll(); }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        if(!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
