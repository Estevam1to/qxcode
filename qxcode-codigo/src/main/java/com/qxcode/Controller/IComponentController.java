package com.qxcode.Controller;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

public interface IComponentController {
    void setInfo();
    void entrarDetalhes(MouseEvent mouseEvent) throws IOException;
}