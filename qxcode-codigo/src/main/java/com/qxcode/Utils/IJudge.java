package com.qxcode.Utils;

public interface IJudge {
    public void compilar();
    public boolean verifyDiff();
    public void destroyArquivos();
    public String getResult();
}
