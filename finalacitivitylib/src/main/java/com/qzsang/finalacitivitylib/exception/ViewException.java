//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qzsang.finalacitivitylib.exception;

public class ViewException extends AfinalException {
    private static final long serialVersionUID = 1L;
    private String strMsg = null;

    public ViewException(String strExce) {
        this.strMsg = strExce;
    }

    public void printStackTrace() {
        if(this.strMsg != null) {
            System.err.println(this.strMsg);
        }

        super.printStackTrace();
    }
}
