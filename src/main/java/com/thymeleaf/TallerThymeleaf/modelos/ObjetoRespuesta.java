package com.thymeleaf.TallerThymeleaf.modelos;

public class ObjetoRespuesta {

    private String mensaje;
    private Object objetc;

    public ObjetoRespuesta(String mensaje, Object objetc) {
        this.mensaje = mensaje;
        this.objetc = objetc;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getObjetc() {
        return objetc;
    }

    public void setObjetc(Object objetc) {
        this.objetc = objetc;
    }
}
