package com.qxcode.Controller;

import com.qxcode.DAO.QuestionDAO;
import com.qxcode.Model.Category;
import com.qxcode.Model.Question;

import java.util.List;

public class ControllerQuestion {

    private final QuestionDAO questionDAO;

    public ControllerQuestion() {
        this.questionDAO = new QuestionDAO();
    }

    public String getTela() {
        return "/com/qxcode/View/components/questionComponent.fxml";
    }

    public List<Question> getQuestionByCategory(int category) {
        return questionDAO.getQuestionsByCategory(category);
    }

    public List<Question> getFavoriteQuestions(){return questionDAO.getFavoriteQuestions();}

    public void insertQuestion(String title, String description, Integer difficulty, String examples, int categoryId) {
        questionDAO.insertQuestion(title, description, difficulty, examples, categoryId);
    }

    public Question getQuestionByTitle(String title) {
        return questionDAO.getQuestionByTitle(title);
    }

    public List<Question> getFavoriteQuestion() {
        return questionDAO.getFavoriteQuestions();
    }
    public List<Question> getAllQuestions() {
        return questionDAO.getAllQuestions();
    }

    public void updateQuestionFavorite(int id) {
        questionDAO.updateQuestionFavorite(id);
    }
}
