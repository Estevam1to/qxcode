package com.qxcode.Controller;

import com.qxcode.Model.Question;
import com.qxcode.DAO.QuestionDAO;

import java.util.List;

public class ControllerQuestionDAO {

    private final QuestionDAO QuestionDAO;

    public ControllerQuestionDAO() {
        this.QuestionDAO = new QuestionDAO();
    }

    public List<Question> getQuestionByCategory(int categoryId) {
        return QuestionDAO.getQuestionsByCategory(categoryId);
    }

    public void InsertQuestion(String title, String description, Integer difficulty, String examples, int categoryId) {
        QuestionDAO.insertQuestion(title, description, difficulty, examples, categoryId);
    }

    public Question getQuestionByTitle(String title) {
        return QuestionDAO.getQuestionByTitle(title);
    }

    public List<Question> getFavoriteQuestion() {
        return QuestionDAO.getFavoriteQuestions();
    }
    public List<Question> getAllQuestions() {
        return QuestionDAO.getAllQuestions();
    }

}
