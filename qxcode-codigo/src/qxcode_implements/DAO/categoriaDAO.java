package DAO;

import model.categoria;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;

public class categoriaDAO implements IDAO<categoria> {

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

    public void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean Insert(categoria categoria) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(categoria);
            entityManager.getTransaction().commit();
            return true;
        }   catch (Exception e) {
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
    public boolean Update(categoria categoria) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(categoria);
            entityManager.getTransaction().commit();
            return true;
        } catch(Exception e) {
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
    public boolean Delete(categoria categoria) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(categoria);
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
    public categoria FindById(int id) {
        try {
            return entityManager.find(categoria.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
