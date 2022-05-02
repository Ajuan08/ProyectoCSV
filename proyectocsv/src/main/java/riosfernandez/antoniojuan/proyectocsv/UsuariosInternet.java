package riosfernandez.antoniojuan.proyectocsv;


public class UsuariosInternet {
    private String pais;
    private String año;
    private String NumUsuarios;

    public UsuariosInternet() {
    }

    public UsuariosInternet(String pais, String año, String NumUsuarios) {
        this.pais = pais;
        this.año = año;
        this.NumUsuarios = NumUsuarios;
    }
    
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getNumUsuarios() {
        return NumUsuarios;
    }

    public void setNumUsuarios(String NumUsuarios) {
        this.NumUsuarios = NumUsuarios;
    }
    
}
