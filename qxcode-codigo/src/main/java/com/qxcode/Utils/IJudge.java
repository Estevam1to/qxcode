package com.qxcode.Utils;

public interface IJudge {
    public void compilar();
    public void carregarUserOutputs();
    public boolean verifyDiff();
    public void destroyArquivos();
}
