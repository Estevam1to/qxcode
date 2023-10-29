package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;

@Table(schema = "questão")
@Entity
public class Questao {

    @Column(name = "id_questão")
    private int id;

    @Column(name = "descrição")
    String descricao;

    @Column(name = "titulo")
    String titulo;

    @Column(name = "dificuldade")
    int dificuldade;

    @Column(name = "exemplos")
    String exemplos;

    ArrayList<casoDeTeste> CasosDeTeste;

    public Questao(String descricao, String titulo, int dificuldade, String exemplos) {
        this.descricao = descricao;
        this.titulo = titulo;
        this.dificuldade = dificuldade;
        this.exemplos = exemplos;

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public ArrayList<casoDeTeste> getCasosDeTeste() {
        return CasosDeTeste;
    }

}
