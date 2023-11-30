package com.qxcode.Utils;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.lang.InterruptedException;
import java.lang.ProcessBuilder;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


public class JudgePy implements IJudge {
    private final File userFile;
    private long time;
    private final ArrayList<File> outputsExpecteds;
    private final ArrayList<File> outputsUser;
    private final ArrayList<File> inputs;
    private final ArrayList<File> diffs;
    //private ControllerQuestion controllerQuestion;

    private final String pathQuestion = "src/main/resources/com/qxcode/Arquivos/File/Question.py";
    private final String pathOutputUser = "src/main/resources/com/qxcode/Arquivos/OutputUser";
    private final String pathOutputExpected = "src/main/resources/com/qxcode/Arquivos/OutputExpecteds";
    private final String pathInput = "src/main/resources/com/qxcode/Arquivos/Inputs";
    private final String pathDiff = "src/main/resources/com/qxcode/Arquivos/Diffs";

    public JudgePy() {
        //  controllerQuestion.getExtension();
        userFile = new File(pathQuestion);
        outputsExpecteds = new ArrayList<File>();
        outputsUser = new ArrayList<File>();
        inputs = new ArrayList<File>();
        diffs = new ArrayList<File>();
        carregar(pathInput, inputs);
        carregar(pathOutputExpected, outputsExpecteds);
    }

    private void sortArray(ArrayList<File> list) {
        list.sort(new Comparator<>() {
            @Override
            public int compare(File file1, File file2) {
                int num1 = Integer.parseInt(file1.getName().replaceAll("\\D", ""));
                int num2 = Integer.parseInt(file2.getName().replaceAll("\\D", ""));
                return Integer.compare(num1, num2);
            }
        });
    }

    private void carregar(String path, ArrayList<File> list) {
        File pasta = new File(path);
        if (pasta.isDirectory() && pasta.exists()) {
            File[] files = pasta.listFiles();
            Collections.addAll(list, files);
        }
        sortArray(list);
    }

    public boolean compilar() {
        long tempoInicial = System.currentTimeMillis();
        long tempoFinal = 0;

        try {
            for (int i = 0; i < inputs.size(); i ++) {
                ProcessBuilder pbExecucao = new ProcessBuilder("python3", userFile.getName());
                pbExecucao.redirectError(new File("./error.txt"));
                pbExecucao.directory(userFile.getParentFile());
                pbExecucao.redirectInput(inputs.get(i));
                //System.out.println("Executando " + inputs.get(i).getName());
                pbExecucao.redirectOutput(new File(pathOutputUser, "userOut0" + (i + 1) + ".out"));
                Process processExecucao = pbExecucao.start();
                processExecucao.waitFor();
                processExecucao.destroy();
            }
            tempoFinal = System.currentTimeMillis();
            //System.out.println("Executado em = " + (tempoFinal - tempoInicial) + " ms");
            time = tempoFinal - tempoInicial;
        } catch (IOException | InterruptedException e) {
            return false;
        }
        return true;
    }

    private boolean verifyIsNull(ArrayList<File> list) {
        for (File file : list) {
            if (file.length() != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean verifyDiff() {
        carregar(pathOutputUser, outputsUser);

        for (int i = 0; i < outputsUser.size(); ++i) {
            String pathOutputUser = outputsUser.get(i).getAbsolutePath();
            String pathOutputExpected = outputsExpecteds.get(i).getAbsolutePath();
            try {
                //System.out.println("Comparando " + outputsExpecteds.get(i).getName() + " com " + outputsUser.get(i).getName());
                ProcessBuilder pbDiff = new ProcessBuilder("diff", pathOutputExpected, pathOutputUser);
                pbDiff.redirectOutput(new File(pathDiff, "diff0" + (i + 1) + ".out"));
                Process pDiff = pbDiff.start();
                pDiff.waitFor();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
        carregar(pathDiff, diffs);
        if (!verifyIsNull(diffs)) {
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

    public boolean getTime() {
        return time > 2000;
    }

}
