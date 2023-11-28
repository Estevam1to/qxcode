package com.qxcode.Utils;

public class FactoryJudge {
    public IJudge getJudge(String language) {
        switch (language) {
            case "Python":
                return new JudgePy();
            case "C++":
                return new JudgeCpp();
            case "Java":
                return new JudgeJava();
        }
        return null;
    }
}
