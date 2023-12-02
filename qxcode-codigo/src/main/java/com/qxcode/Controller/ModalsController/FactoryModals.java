package com.qxcode.Controller.ModalsController;

public class FactoryModals {
    public IControllerModal getController(String result) {
        switch (result) {
            case "WA_RESULT":
                return new ControllerModalWa();
            case "AC_RESULT":
                return new ControllerModalAc();
            case "TLE_RESULT":
                return new ControllerModalTle();
            case "RE_RESULT":
                return new ControllerModalRe();
            default:
                return null;
        }
    }
}
