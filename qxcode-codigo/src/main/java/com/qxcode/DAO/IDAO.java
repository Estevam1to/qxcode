package com.qxcode.DAO;

import com.qxcode.Model.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IDAO <T> {

    public T getById(int id);

    public T getByTitle(String title);

    public T resultSetToObject(ResultSet resultSet) throws Exception;

    public T resultSetFromView(ResultSet resultSet) throws Exception;
}
