package com.qxcode.Controller;
import com.qxcode.Utils.WriteInputOutputInFile;

public class ControllerWriteInputOutputInFile {

    private WriteInputOutputInFile writeInputOutput;

    ControllerWriteInputOutputInFile () {
        this.writeInputOutput = new WriteInputOutputInFile();
    }

    public void WriteInputByQuestionId(int id) {
        writeInputOutput.WriteInputsByQuestionId(id);
    }

    public void WriteOutputByQuestionId(int id) {
        writeInputOutput.WriteOutputsByQuestionId(id);
    }
}
