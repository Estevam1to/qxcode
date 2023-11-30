package com.qxcode.Utils;

public interface IJudge {
    public boolean compilar();
    public boolean verifyDiff();
    public void destroyArquivos();
    public boolean getTime();
}
