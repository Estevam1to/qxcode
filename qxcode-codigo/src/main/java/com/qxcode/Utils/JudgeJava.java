package com.qxcode.Utils;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.lang.InterruptedException;
import java.lang.ProcessBuilder;


public class JudgeJava implements IJudge {
    private final File userFile;
    private final ArrayList<File> outputsExpecteds;
    private final ArrayList<File> outputsUser;
    private final ArrayList<File> inputs;

    private final String pathQuestion = "../../../../resources/com/qxcode/Arquivos/File/Question.java";
    private final String pathOutputUser = "../../../../resources/com/qxcode/Arquivos/OutputUser";
    private final String pathOutputExpected = "../../../../resources/com/qxcode/Arquivos/OutputExpecteds";
    private final String pathInput = "../../../../resources/com/qxcode/Arquivos/Inputs";
    private final String pathDiff = "../../../../resources/com/qxcode/Arquivos/Diffs";

    //private ControllerQuestion controllerQuestion;

    public JudgeJava() {
        //  controllerQuestion.getExtension();
        userFile = new File(pathQuestion);
        outputsExpecteds = new ArrayList<File>();
        outputsUser = new ArrayList<File>();
        inputs = new ArrayList<File>();
        carregarInputs();
        carregarOutputs();
    }

    private void carregarOutputs() {
        File path = new File(pathOutputExpected);
        if (path.isDirectory() && path.exists()) {
            File[] files = path.listFiles();
            for (File file : files) {
                outputsExpecteds.add(file);
            }
        }
    }

    private void carregarInputs() {
        File path = new File(pathInput);
        if (path.isDirectory() && path.exists()) {
            File[] files = path.listFiles();
            for (File file : files) {
                inputs.add(file);
            }
        }
    }

    public void compilar() {
        long tempoInicial = System.currentTimeMillis();
        long tempoFinal = 0;
        try {
            ProcessBuilder pbCompilacao = new ProcessBuilder("javac", userFile.getName());
            pbCompilacao.directory(userFile.getParentFile());
            pbCompilacao.redirectError(new File("error.txt"));
            File error = new File("./error.txt");
            Process process = pbCompilacao.start();
            process.waitFor();
            if (error.length() == 0) {
                for (int i = 0; i < inputs.size(); ++i) {
                    ProcessBuilder pbExecucao = new ProcessBuilder("java", "Question");
                    pbExecucao.redirectInput(inputs.get(i));
                    pbExecucao.redirectOutput(new File(pathOutputUser, "userOut0" + (i + 1) + ".out"));
                    Process processExecucao = pbExecucao.start();
                    processExecucao.waitFor();
                }

            } else {
                System.out.println("Não compilou");
            }
            tempoFinal = System.currentTimeMillis();
            System.out.println("Executado em = " + (tempoFinal - tempoInicial) + " ms");
        } catch (IOException e) {
            System.out.println("Erro de I/O" + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Erro de interrupção" + e.getMessage());
        }

    }

    public void carregarUserOutputs() {
        File path = new File(pathOutputUser);
        if (path.isDirectory() && path.exists()) {
            File[] files = path.listFiles();
            for (File file : files) {
                outputsUser.add(file);
            }
        }
    }

    public boolean verifyDiff() {
        carregarUserOutputs();

        for (int i = 0; i < outputsUser.size(); ++i) {
            String pathOutputUserTest = outputsUser.get(i).getAbsolutePath();
            String pathOutputExpectedTest = outputsExpecteds.get(i).getAbsolutePath();
            try {
                System.out.println("Comparando " + outputsExpecteds.get(i).getName() + " com " + outputsUser.get(i).getName());
                ProcessBuilder pbDiff = new ProcessBuilder("diff", pathOutputExpectedTest, pathOutputUserTest);
                pbDiff.redirectOutput(new File(pathDiff, "diff0" + (i + 1) + ".out"));
                Process pDiff = pbDiff.start();
                pDiff.waitFor();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        File pasta = new File(pathDiff);
        if (pasta.isDirectory() && pasta.exists()) {
            File[] files = pasta.listFiles();
            assert files != null;
            for (File file : files) {
                if (file.length() != 0) return false;
            }
        }
        return true;
    }

    private void Destroy(String path) {
        File file = new File(path);
        if (file.isDirectory() && file.exists()) {
            File[] files = file.listFiles();
            assert files != null;
            for (File file1 : files) {
                file1.delete();
            }
        }
    }

    public  void destroyArquivos() {
        ArrayList<String> paths = new ArrayList<String>();
        paths.add(pathOutputUser);
        paths.add(pathDiff);
        paths.add(pathOutputExpected);
        paths.add(pathInput);

        for (String path : paths) {
            Destroy(path);
        }

        File error = new File("./error.txt");
        error.delete();

        File question = new File(pathQuestion);
        question.delete();
    }
}
