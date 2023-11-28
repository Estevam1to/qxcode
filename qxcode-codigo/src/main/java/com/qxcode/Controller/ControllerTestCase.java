package com.qxcode.Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.qxcode.DAO.TestCaseDAO;

public class ControllerTestCase {

    private final TestCaseDAO testCaseDAO;

    public ControllerTestCase() {
        this.testCaseDAO = new TestCaseDAO();
    }

    public void insert(String inputs, String outputs, int questionId) {
        testCaseDAO.insert(inputs, outputs, questionId);
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

    public String getExOutputByQuestionId(int id) {
        return testCaseDAO.getExOutputsStringByQuestionId(id);
    }

    public String getExInputByQuestionId(int id) {
        return testCaseDAO.getExInputsStringByQuestionId(id);
    }


    public void saveTestCases(List<File> inputFiles, List<File> outputFiles, int idQuestion) {
        for(int i = 0; i < inputFiles.size(); i++){
            String input = parseFileToString(inputFiles.get(i));
            String output = parseFileToString(outputFiles.get(i));
            System.out.println(input);
            System.out.println(output);
            insert(input, output, idQuestion);
        }
    }

    private String parseFileToString(File file) {
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = br.readLine()) != null) {
                sb.append(line).append("/");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e ) {
            throw new RuntimeException();
        }
    }
}
