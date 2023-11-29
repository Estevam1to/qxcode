package com.qxcode.Controller;

import com.qxcode.Main;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class NavBar2Component {

    public void backScreen(MouseEvent mouseEvent) throws IOException {
        Main.backLastScreen();
    }

}
