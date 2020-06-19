
package Datos;

import java.sql.Date;
import java.sql.Time;

public class DAnuncio {
    private int anu_id;
    private String anu_titulo;
    private String anu_contenido;
    private int anu_estado;
    private Date anu_fechapub;
    private Time anu_horapub;
    private int anu_abogado;
    private int anu_categoria;

    public int getAnu_id() {
        return anu_id;
    }

    public void setAnu_id(int anu_id) {
        this.anu_id = anu_id;
    }

    public String getAnu_titulo() {
        return anu_titulo;
    }

    public void setAnu_titulo(String anu_titulo) {
        this.anu_titulo = anu_titulo;
    }

    public String getAnu_contenido() {
        return anu_contenido;
    }

    public void setAnu_contenido(String anu_contenido) {
        this.anu_contenido = anu_contenido;
    }

    public int getAnu_estado() {
        return anu_estado;
    }

    public void setAnu_estado(int anu_estado) {
        this.anu_estado = anu_estado;
    }

    public Date getAnu_fechapub() {
        return anu_fechapub;
    }

    public void setAnu_fechapub(Date anu_fechapub) {
        this.anu_fechapub = anu_fechapub;
    }

    public Time getAnu_horapub() {
        return anu_horapub;
    }

    public void setAnu_horapub(Time anu_horapub) {
        this.anu_horapub = anu_horapub;
    }

    public int getAnu_abogado() {
        return anu_abogado;
    }

    public void setAnu_abogado(int anu_abogado) {
        this.anu_abogado = anu_abogado;
    }

    public int getAnu_categoria() {
        return anu_categoria;
    }

    public void setAnu_categoria(int anu_categoria) {
        this.anu_categoria = anu_categoria;
    }
    
    
}
