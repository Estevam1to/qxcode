package com.qxcode.Utils;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.lang.InterruptedException;
import java.lang.ProcessBuilder;


public class JudgePy implements IJudge {
    private final File userFile;
    private long time;
    private final ArrayList<File> outputsExpecteds;
    private final ArrayList<File> outputsUser;
    private final ArrayList<File> inputs;
    private final ArrayList<File> diffs;
    //private ControllerQuestion controllerQuestion;

    private final String pathQuestion = "../../../../resources/com/qxcode/Arquivos/File/Question.py";
    private final String pathOutputUser = "../../../../resources/com/qxcode/Arquivos/OutputUser";
    private final String pathOutputExpected = "../../../../resources/com/qxcode/Arquivos/OutputExpecteds";
    private final String pathInput = "../../../../resources/com/qxcode/Arquivos/Inputs";
    private final String pathDiff = "../../../../resources/com/qxcode/Arquivos/Diffs";

    public JudgePy() {
        //  controllerQuestion.getExtension();
        userFile = new File(pathQuestion);
        outputsExpecteds = new ArrayList<File>();
        outputsUser = new ArrayList<File>();
        inputs = new ArrayList<File>();
        diffs = new ArrayList<File>();
        carregar(pathDiff, inputs);
        carregar(pathOutputExpected, outputsExpecteds);
    }

    private void carregar(String path, ArrayList<File> list) {
        File file = new File(path);
        if (file.isDirectory() && file.exists()) {
            File[] files = file.listFiles();
            assert files != null;
            for (File file1 : files) {
                list.add(file1);
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
                pbExecucao.redirectOutput(new File(pathOutputUser, "userOut0" + (i + 1) + ".out"));
                Process processExecucao = pbExecucao.start();
                processExecucao.waitFor();
            }
            tempoFinal = System.currentTimeMillis();
            //System.out.println("Executado em = " + (tempoFinal - tempoInicial) + " ms");
            time = tempoFinal - tempoInicial;
        } catch (IOException e) {
            System.out.println("Erro de I/O" + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Erro de interrupção" + e.getMessage());
        }

    }

    private boolean verifyIsNull(ArrayList<File> list) {
        for (File file : list) {
            if (file.length() == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean verifyDiff() {
        carregar(pathOutputUser, outputsUser);

        for (int i = 0; i < outputsUser.size(); ++i) {
            String pathOutputUser = outputsUser.get(i).getAbsolutePath();
            String pathOutputExpected = outputsExpecteds.get(i).getAbsolutePath();
            try {
                System.out.println("Comparando " + outputsExpecteds.get(i).getName() + " com " + outputsUser.get(i).getName());
                ProcessBuilder pbDiff = new ProcessBuilder("diff", pathOutputExpected, pathOutputUser);
                pbDiff.redirectOutput(new File(pathDiff, "diff0" + (i + 1) + ".out"));
                Process pDiff = pbDiff.start();
                pDiff.waitFor();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
        carregar(pathDiff, diffs);
        if (verifyIsNull(diffs)) {
            return false;
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

    public String getResult() {
        String result = "";
        if (time > 1500) {
            result = "TLE";
        } else if (verifyDiff()) {
            result = "AC";
        }else if(!verifyDiff()){
            result = "WA";
        } else {
            result = "ERRO";
        }
        return result;
    }
}
