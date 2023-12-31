package com.qxcode.Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class TranformaEmArquivo {
    private String code;
    private String language;
    private File file;
    
    public TranformaEmArquivo(String code, String language) {
        this.code = code;
        this.language = language;
    }
    
    // Cria o arquivo .java
    public void criarArquivo() {
        file = null;
        if (language.equals("Python")) {
            file = new File("src/main/resources/com/qxcode/Arquivos/File/Question.py");
        } else if (language.equals("C++") || language.equals("C")) {
            file = new File("src/main/resources/com/qxcode/Arquivos/File/Question.cpp");
        } else if (language.equals("Java")) {
            file = new File("src/main/resources/com/qxcode/Arquivos/File/Question.java");
        }

        try {
            file.createNewFile();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    // Escreve no arquivo .java
    public void escreverArquivo() {
        if (file != null) {
            try {
                // Cria um objeto FileWriter
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter writer = new BufferedWriter(fileWriter);
    
                // Escreve o conteúdo da string no arquivo
                writer.write(code);
    
                // Fecha o BufferedWriter e FileWriter para garantir que os dados sejam gravados
                writer.close();
                fileWriter.close();
                System.out.println("String foi escrita no arquivo com sucesso.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("O arquivo não foi criado. Execute 'criarArquivo()' primeiro.");
        }
    }

    public void solver() {
        criarArquivo();
        escreverArquivo();
    }   
}
