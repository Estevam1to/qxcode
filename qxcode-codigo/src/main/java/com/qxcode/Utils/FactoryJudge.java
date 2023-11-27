package com.qxcode.Utils;

public class FactoryJudge {
    public IJudge getJudge(String language) {
        if (language.equals("Python")) {
            return new JudgePy();
        } else if (language.equals("C++")) {
            return new JudgeCpp();
        } else if (language.equals("Java")) {
            return new JudgeJava();
        }
        return null;
    }
}
