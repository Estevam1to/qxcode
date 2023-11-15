package com.qxcode.Controller;

import com.qxcode.DAO.CategoryDAO;
import com.qxcode.JDBC.JDBC;
import com.qxcode.Model.Category;

import javafx.fxml.FXML;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControllerCategory {
    CategoryDAO dao = new CategoryDAO();
    @FXML
    public void initialize() {
       
    }
    
    public String getTela() {
        return "/com/qxcode/View/components/categoryComponent.fxml";
    }

    public List<Category> getAllCategories() {
        List<Category> categories = dao.getAllCategories();
       return categories;
    }




}
