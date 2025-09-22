package br.edu.insper.controller;

import br.edu.insper.curso.repository.CursoRepository;
import br.edu.insper.pessoa.PessoaApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = PessoaApplication.class)
@AutoConfigureMockMvc
class CursoControllerTest {
    @Autowired MockMvc mvc;
    @Autowired CursoRepository repo;

    @BeforeEach void clean(){ repo.deleteAll(); }

    @Test void fluxoBasico() throws Exception {
        String body = "{\"titulo\":\"Java\",\"descricao\":\"API\",\"cargaHoraria\":12,\"instrutor\":\"Ana\"}";
        mvc.perform(post("/cursos").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.dataCadastro", notNullValue()));
        mvc.perform(get("/cursos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
        Long id = repo.findAll().get(0).getId();
        mvc.perform(delete("/cursos/"+id)).andExpect(status().isNoContent());
        mvc.perform(get("/cursos")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
    }
}
