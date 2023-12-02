package com.qxcode.Controller.ComponentController;

import com.qxcode.Model.Category;
import com.qxcode.Model.IModel;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public interface IComponentController {
    public <T> void setModel(T model);
    void setInfo();
    void entrarDetalhes(MouseEvent mouseEvent) throws IOException;
}