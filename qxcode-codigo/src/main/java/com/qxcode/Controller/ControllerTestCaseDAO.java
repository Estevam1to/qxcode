package com.qxcode.Controller;

import java.util.ArrayList;
import com.qxcode.DAO.TestCaseDAO;

public class ControllerTestCaseDAO {

    private final TestCaseDAO testCaseDAO;

    public ControllerTestCaseDAO() {
        this.testCaseDAO = new TestCaseDAO();
    }

    public ArrayList<String> getInputListByQuestionId(int id) {
        return testCaseDAO.getInputByQuestionId(id);
    }

    public ArrayList<String> getOutputListByQuestionId(int id) {
        return testCaseDAO.getOutputByQuestionId(id);
    }

    public String getOutputsStringByQuestionId(int id) {
        return testCaseDAO.getOutputsStringByQuestionId(id);
    }

    public String getInputsStringByQuestionId(int id) {
        return testCaseDAO.getInputsStringByQuestionId(id);
    }


}
