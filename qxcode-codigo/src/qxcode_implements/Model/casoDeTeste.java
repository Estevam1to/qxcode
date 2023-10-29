package model;

import javax.persistence.*;

@Table(schema = "cass_de_teste")
public class casoDeTeste {
    @Column(name = "id_caso_de_teste")
    int id;

    @Column(name = "id_questão")
    int id_questão;

    private String input;
    private String output;

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Questão");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();


    casoDeTeste() {}

    public void setEntrada(String entrada) {
        this.input = entrada;
    }

    public void setSaida(String saida) {
        this.output = saida;
    }

    public String getEntrada() {
        return this.input;
    }

    public String getSaida() {
        return this.output;
    }
}
