package br.edu.insper.curso.controller;

import br.edu.insper.curso.model.Curso;
import br.edu.insper.curso.repository.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CursoControllerStandaloneTest {

    @InjectMocks
    private CursoController controller;

    @Mock
    private CursoRepository repo;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void post_get_delete_semBanco() throws Exception {
        when(repo.save(any(Curso.class))).thenAnswer(inv -> inv.getArgument(0));
        when(repo.findAll()).thenReturn(List.of(new Curso()));
        when(repo.existsById(1L)).thenReturn(true);
        doNothing().when(repo).deleteById(1L);

        String body = "{\"titulo\":\"Java\",\"descricao\":\"API\",\"cargaHoraria\":12,\"instrutor\":\"Ana\"}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        ).andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/cursos")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/cursos/1")
        ).andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(repo, times(1)).save(any(Curso.class));
        verify(repo, times(1)).findAll();
        verify(repo, times(1)).existsById(1L);
        verify(repo, times(1)).deleteById(1L);
    }
}
