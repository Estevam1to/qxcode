package com.qxcode.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import com.qxcode.Controller.ControllerTestCaseDAO;
public class WriteInFile {

    public class WriteFileInputOutput {

        ControllerTestCaseDAO controller = new ControllerTestCaseDAO();

        public void WriteInputsByQuestionId(int id) {
            String Destiny = "src/main/resources/com/qxcode/Arquivos/Inputs";
            String Filename = id + ".in";
            String Content = controller.getInputsStringByQuestionId(id);

            File f = new File(Destiny);

            try {
                File file = new File(f, Filename);
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(Content);
                writer.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void WriteOutputsByQuestionId(int id) {
            String Destiny = "src/main/resources/com/qxcode/Arquivos/OutputExpecteds";
            String Filename = id + ".out";
            String Content = controller.getOutputsStringByQuestionId(id);

            File f = new File(Destiny);

            try {
                File file = new File(f, Filename);
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(Content);
                writer.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
