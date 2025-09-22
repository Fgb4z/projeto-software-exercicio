package br.edu.insper.curso.repository;

import br.edu.insper.curso.model.Curso;
import br.edu.insper.pessoa.PessoaApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = PessoaApplication.class)
class CursoRepositoryTest {
    @Autowired CursoRepository repo;

    @Test void persiste() {
        Curso c = new Curso();
        c.setTitulo("T");
        c.setDescricao("D");
        c.setCargaHoraria(8);
        c.setInstrutor("I");
        Curso s = repo.save(c);
        assertThat(s.getId()).isNotNull();
        assertThat(s.getDataCadastro()).isNotNull();
    }
}
