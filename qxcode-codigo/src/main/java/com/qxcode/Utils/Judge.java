package com.qxcode.Utils;

import java.io.File;
import java.util.ArrayList;

public class Judge {
    private File arquivo;
    private ArrayList<File> outputsExpecteds;
    private ArrayList<File> outputsUser;
    private ArrayList<File> inputs;
    private ProcessBuilder builder;
    
    public Judge(String entrada) {
        arquivo = new File("src/qxcode_resources/Arquivos/Question.java");
        outputsExpecteds = new ArrayList<File>();
        outputsUser = new ArrayList<File>();
        inputs = new ArrayList<File>();
    }

    public void compilar() {
        int count = 0;
        for (File input : inputs) {
            try {
                String cmd = "javac " + arquivo.getName() + " < " + input.getName();
                builder = new ProcessBuilder("bash", "-c", cmd);
                builder.redirectOutput(new File("src/qxcode_resources/Arquivos/Output-User/" + count + ".out"));
                builder.start();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
       
    }

    public boolean diff() {
        boolean result = true;
        for (File outputExpected : outputsExpecteds) {
            for (File userOutput : outputsUser) {
                try {
                    String cmd = "diff " + outputExpected.getName() + " " + userOutput.getName();
                    builder = new ProcessBuilder("bash", "-c", cmd);
                    builder.start();
                } catch (Exception e) {
                    System.out.println(e);
                    result = false;
                }
            }
        } 
        return result;  
    }

    public void result() {
        if(diff()) {
            System.out.println("Parabéns, você acertou todos os testes!");
        } else {
            System.out.println("Você errou algum teste, tente novamente!");
        }
    }
}
