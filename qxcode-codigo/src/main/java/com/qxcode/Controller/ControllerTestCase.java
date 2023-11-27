package com.qxcode.Controller;

import com.qxcode.DAO.TestCaseDAO;
import javafx.fxml.FXML;

import java.io.*;
import java.util.List;

public class ControllerTestCase {
    TestCaseDAO dao;

    public ControllerTestCase() {
        dao = new TestCaseDAO();
    }

    public void saveTestCases(List<File> inputFiles, List<File> outputFiles, int idQuestion) {
        for(int i = 0; i < inputFiles.size(); i++){
            String input = parseFileToString(inputFiles.get(i));
            String output = parseFileToString(outputFiles.get(i));
            dao.insertTestCase(input, output, idQuestion);
        }
    }

    private String parseFileToString(File file) {
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = br.readLine()) != null) {
                  sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e ) {
            throw new RuntimeException();
        }
    }
}
