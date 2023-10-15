package qxcode_implements.Controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

class TransformaEmArquivo {
    private String code;
    private File file;

    public TransformaEmArquivo(String code) {
        this.code = code;
    }

    // Cria o arquivo .java
    public void criarArquivo() {
        file = new File("src/qxcode_resources/Arquivos/Question.java");
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
}
