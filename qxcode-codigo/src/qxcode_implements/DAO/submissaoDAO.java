package DAO;

import model.submissao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;

public class submissaoDAO implements IDAO<submissao> {
    Connection conn = null;

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Questão");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();


    @Override
    public Connection Connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:/home/teamate/Documents/Projeto_integrado/ModelagemBD/QXcode.db");
            if (conn != null) {
                System.out.println("Connection Successful");
            } else {
                System.out.println("Connection Failed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    @Override
    public void Close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean Insert(submissao submissao) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(submissao);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean Update(submissao submissao) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(submissao);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean Delete(submissao submissao) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(submissao) ? submissao : entityManager.merge(submissao));
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public submissao FindById(int id) {
        try {
           return entityManager.find(submissao.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
