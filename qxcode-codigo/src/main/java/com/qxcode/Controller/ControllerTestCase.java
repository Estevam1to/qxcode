package com.qxcode.Controller;

import com.qxcode.DAO.TestCaseDAO;
import javafx.fxml.FXML;

import java.io.*;
import java.util.List;

public class ControllerTestCase {
    TestCaseDAO dao = new TestCaseDAO();
    @FXML
    public void initialize() {

    }

    public void saveTestCases(List<File> inputFiles, List<File> outputFiles, int idQuestion) {
        for(int i = 0; i < inputFiles.size(); i++){
            String input = "";
            String output = "";
            try{
                BufferedReader br = new BufferedReader(new FileReader(inputFiles.get(i)));
                StringBuilder sb = new StringBuilder();
                String line;
                while((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                input = sb.toString();
            } catch (FileNotFoundException e) {
                throw new RuntimeException();
            } catch (IOException e ) {
                throw new RuntimeException();
            }
            try{
                BufferedReader br = new BufferedReader(new FileReader(outputFiles.get(i)));
                StringBuilder sb = new StringBuilder();
                String line;
                while((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                output = sb.toString();
            } catch (FileNotFoundException e) {
                throw new RuntimeException();
            } catch (IOException e ) {
                throw new RuntimeException();
            }
            dao.insertTestCase(input, output, idQuestion);
        }
    }
}
