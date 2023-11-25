package com.qxcode.Utils;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.lang.InterruptedException;
import java.lang.ProcessBuilder;


public class JudgePY implements IJudge {
    private final File userFile;
    private final ArrayList<File> outputsExpecteds;
    private final ArrayList<File> outputsUser;
    private final ArrayList<File> inputs;

    //private ControllerQuestion controllerQuestion;

    public JudgePY() {
        //  controllerQuestion.getExtension();
        userFile = new File("../../../../resources/com/qxcode/Arquivos/File/Question.py");
        outputsExpecteds = new ArrayList<File>();
        outputsUser = new ArrayList<File>();
        inputs = new ArrayList<File>();

        File path = new File("../../../../resources/com/qxcode/Arquivos/OutputExpecteds");
        if (path.isDirectory() && path.exists()) {
            File[] files = path.listFiles();
            for (File file : files) {
                outputsExpecteds.add(file);
            }
        }
        path = new File("../../../../resources/com/qxcode/Arquivos/Inputs");
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
            for (int i = 0; i < inputs.size(); ++i) {
                ProcessBuilder pbExecucao = new ProcessBuilder("python3", userFile.getName());
                pbExecucao.redirectInput(inputs.get(i));
                pbExecucao.redirectOutput(new File("../../../../resources/com/qxcode/Arquivos/OutputUser", "userOut0" + (i + 1) + ".out"));
                Process processExecucao = pbExecucao.start();
                processExecucao.waitFor();
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
        File path = new File("../../../../resources/com/qxcode/Arquivos/OutputUser");
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
            String pathOutputUser = outputsUser.get(i).getAbsolutePath();
            String pathOutputExpected = outputsExpecteds.get(i).getAbsolutePath();
            try {
                System.out.println("Comparando " + outputsExpecteds.get(i).getName() + " com " + outputsUser.get(i).getName());
                ProcessBuilder pbDiff = new ProcessBuilder("diff", pathOutputExpected, pathOutputUser);
                pbDiff.redirectOutput(new File("../../../../resources/com/qxcode/Arquivos/Diffs", "diff0" + (i + 1) + ".out"));
                Process pDiff = pbDiff.start();
                pDiff.waitFor();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        File pasta = new File("../../../../resources/com/qxcode/Arquivos/Diffs");
        if (pasta.isDirectory() && pasta.exists()) {
            File[] files = pasta.listFiles();
            assert files != null;
            for (File file : files) {
                if (file.length() != 0) return false;
            }
        }
        return true;
    }

    public  void destroyArquivos() {
        File path = new File("../../../../resources/com/qxcode/Arquivos/OutputUser");
        if (path.isDirectory() && path.exists()) {
            File[] files = path.listFiles();
            assert files != null;
            for (File file : files) {
                file.delete();
            }
        }

        path = new File("../../../../resources/com/qxcode/Arquivos/Diffs");
        if (path.isDirectory() && path.exists()) {
            File[] files = path.listFiles();
            assert files != null;
            for (File file : files) {
                file.delete();
            }
        }

        path = new File("../../../../resources/com/qxcode/Arquivos/Inputs");
        if (path.isDirectory() && path.exists()) {
            File[] files = path.listFiles();
            assert files != null;
            for (File file : files) {
                file.delete();
            }
        }

        path = new  File("../../../../resources/com/qxcode/Arquivos/OutputExpecteds");
        if (path.isDirectory() && path.exists()) {
            File[] files = path.listFiles();
            assert files != null;
            for (File file : files) {
                file.delete();
            }
        }

        File error = new File("./error.txt");
        error.delete();

        File question = new File("./question");
        question.delete();
    }
}
