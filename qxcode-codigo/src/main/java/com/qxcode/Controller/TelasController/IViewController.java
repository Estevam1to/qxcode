package com.qxcode.Controller.TelasController;

import java.io.IOException;

public interface IViewController {
    void initialize() throws IOException;
    void initNavBar() throws IOException;
    String getTela();
}
