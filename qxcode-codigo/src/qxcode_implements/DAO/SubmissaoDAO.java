package qxcode_implements.JDBC;

import java.sql.Connection;

public class SubmissaoDAO {
    Connection cnx;

    public SubmissaoDAO(Connection cnx) {
        this.cnx = cnx;
    }

    public void insertSubmissao(Submissao submissao) {
        // ...
    }

    public void updateSubmissao(Submissao submissao) {
        // ...
    }

    public void deleteSubmissao(Submissao submissao) {
        // ...
    }

    public Submissao getSubmissao(int id) {
        // ...
    }

}
