package qxcode_implements.DAO;
import java.sql.Connection;
public class QuestaoDAO {
    Connection cnx;

    public QuestaoDAO(Connection cnx) {
        this.cnx = cnx;
    }

    public void insertQuestao(Questao questao) {
        // ...
    }

    public void updateQuestao(Questao questao) {
        // ...
    }

    public void deleteQuestao(Questao questao) {
        // ...
    }

    public Questao getQuestao(int id) {
        // ...
    }
}
