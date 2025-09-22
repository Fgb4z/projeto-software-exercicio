package br.edu.insper.curso.model;
import jakarta.persistence.*;
import java.time.Instant;
@Entity
public class Curso {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Column(length=2000)
    private String descricao;
    private Integer cargaHoraria;
    private String instrutor;
    private Instant dataCadastro;
    @PrePersist
    void pre(){ this.dataCadastro = Instant.now(); }
    public Long getId(){ return id; }
    public String getTitulo(){ return titulo; }
    public void setTitulo(String v){ this.titulo = v; }
    public String getDescricao(){ return descricao; }
    public void setDescricao(String v){ this.descricao = v; }
    public Integer getCargaHoraria(){ return cargaHoraria; }
    public void setCargaHoraria(Integer v){ this.cargaHoraria = v; }
    public String getInstrutor(){ return instrutor; }
    public void setInstrutor(String v){ this.instrutor = v; }
    public Instant getDataCadastro(){ return dataCadastro; }
}
