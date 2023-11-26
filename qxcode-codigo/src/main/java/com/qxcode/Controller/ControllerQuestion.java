package com.qxcode.Controller;

import com.qxcode.DAO.QuestionDAO;
import com.qxcode.Model.Category;
import com.qxcode.Model.Question;

import java.util.List;

public class ControllerQuestion {

    QuestionDAO dao;

    public ControllerQuestion(){
        dao = new QuestionDAO();
    }

    public String getTela() {
        return "/com/qxcode/View/components/questionComponent.fxml";
    }

    public List<Question> getQuestionByCategory(String category) {
        return dao.getQuestionsByCategory(category);
    }
}
