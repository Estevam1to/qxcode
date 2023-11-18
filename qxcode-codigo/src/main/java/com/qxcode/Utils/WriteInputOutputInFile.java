package com.qxcode.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import com.qxcode.Controller.ControllerTestCaseDAO;

public class WriteInputOutputInFile {

    ControllerTestCaseDAO controller = new ControllerTestCaseDAO();

    public void WriteInputsByQuestionId(int id) {
        ArrayList<String> listInput = controller.getInputListByQuestionId(id);

        for (int i = 0; i < listInput.size(); i++) {
            String destiny = "src/FilesWritten";
            String filename = (i + 1) + ".in";
            String content = listInput.get(i).replace('/', '\n');;

            File f = new File(destiny);

            try {
                File file = new File(f, filename);
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(content);
                writer.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void WriteOutputsByQuestionId(int id) {

        ArrayList<String> listOutput = controller.getOutputListByQuestionId(id);

        for (int i = 0; i< listOutput.size(); i++){

            String destiny = "src/outputs";
            String filename = (i + 1) + ".out";
            String content = listOutput.get(i).replace('/', '\n');

            File out = new File(destiny);

            try {
                File file = new File(out, filename);
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(content);
                writer.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
