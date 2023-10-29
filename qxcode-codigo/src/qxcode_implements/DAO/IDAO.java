package DAO;

import java.sql.Connection;

public interface IDAO<T> {

    public Connection Connect();

    public void Close();

    public boolean Insert(T obj);

    public boolean Update(T obj);

    public boolean Delete(T obj);

    public T FindById(int id);
    
}
