package com.qxcode.DAO;

import java.sql.ResultSet;
import java.util.List;

public interface IDAO <T> {

    public T getById(int id);

    public T getByTitle(String title);

    public T resultSetToObject(ResultSet resultSet) throws Exception;
}
