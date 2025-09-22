package br.edu.insper.curso.repository;

import br.edu.insper.curso.model.Curso;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Teste ultra simples, sem JPA/Spring: só verifica interações.
 */
class CursoRepositoryTest {

    @Test
    void salva_semBanco() {
        CursoRepository repo = mock(CursoRepository.class);

        Curso c = new Curso();
        when(repo.save(any(Curso.class))).thenReturn(c);

        Curso salvo = repo.save(c);

        verify(repo, times(1)).save(c);
        assertSame(c, salvo);
    }

    @Test
    void listar_semBanco() {
        CursoRepository repo = mock(CursoRepository.class);

        repo.findAll();

        verify(repo, times(1)).findAll();
    }
}
