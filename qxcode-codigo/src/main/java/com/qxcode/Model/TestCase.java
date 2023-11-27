package com.qxcode.Model;


public class TestCase {

    private int id;
    private int id_question;
    private String input;
    private String output;

    public TestCase(int id, String input, String output,  int id_question) {
        this.id = id;
        this.id_question = id_question;
        this.input = input;
        this.output = output;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getInput() {
        return this.input;
    }

    public String getOutput() { return this.output; }

    public int getId() { return this.id; }

    public void setQuestionId(int id) {
        this.id_question = id;
    }

    public int getQuestion_Id() { return this.id_question; }

    public void setId(int id) {
        this.id = id;
    }
}
