package Negocio;

import Datos.DCategoriaAnuncio;
import Datos.DAnuncio;
import java.sql.Date;
import java.sql.Time;

public class NAnuncio {

    private DCategoriaAnuncio dcategoriaanuncio;
    private DAnuncio danuncio;
    //private DAbogado dabogado;
    private String respuesta = "";

    public NAnuncio() {
        dcategoriaanuncio = new DCategoriaAnuncio();
        danuncio = new DAnuncio();
        //dabogado = new DAbogado();
    }

    public String RegistrarAnuncio(String anu_titulo, String anu_contenido, int anu_estado, Date anu_fechapub, Time anu_horapub, int anu_abogado, int anu_categoria) {
        if (anu_titulo.length() != 0 && anu_contenido.length() != 0 && Integer.toString(anu_categoria).length() != 0
               && Integer.toString(anu_abogado).length() != 0 && dcategoriaanuncio.Existe(anu_categoria) 
                /*&& dabogado.Existe(anu_abogado) */) {
            if (anu_titulo.length() <= 125) {
                if (anu_contenido.length() <= 255) {
                    if (anu_estado >= 0 && anu_estado < 2) {
                        this.danuncio.setAnu_titulo(anu_titulo);
                        this.danuncio.setAnu_contenido(anu_contenido);
                        this.danuncio.setAnu_estado(anu_estado);
                        this.danuncio.setAnu_fechapub(anu_fechapub);
                        this.danuncio.setAnu_horapub(anu_horapub);
                        this.danuncio.setAnu_abogado(anu_abogado);
                        this.danuncio.setAnu_categoria(anu_categoria);
                        if (this.danuncio.Registrar()) {
                            respuesta = this.danuncio.Listar("Se registró correctamente el anuncio.");
                        } else {
                            respuesta = "No se pudo registrar el anuncio";
                        }
                    } else {
                        respuesta = "El valor del estado debe ser '0' o '1'";
                    }
                } else {
                    respuesta = "El contenido es demasiado largo";
                }
            } else {
                respuesta = "El titulo es demasiado largo";
            }
        } else {
            respuesta = "No se permiten datos vacios o nulos o llaves foraneas inexistentes";
        }
        return respuesta;
    }

    public String ModificarAnuncio(int anu_id, String anu_titulo, String anu_contenido, int anu_estado, Date anu_fechapub, Time anu_horapub, int anu_abogado, int anu_categoria) {
        if (anu_titulo.length() != 0 && anu_contenido.length() != 0 && Integer.toString(anu_categoria).length() != 0
               && Integer.toString(anu_abogado).length() != 0 && dcategoriaanuncio.Existe(anu_categoria)
                /*&& dabogado.Existe(anu_abogado) */ && Integer.toString(anu_id).length() != 0 && danuncio.Existe(anu_id)) {
            if (anu_titulo.length() <= 125) {
                if (anu_contenido.length() <= 255) {
                    if (anu_estado >= 0 && anu_estado < 2) {
                        this.danuncio.setAnu_id(anu_id);
                        this.danuncio.setAnu_titulo(anu_titulo);
                        this.danuncio.setAnu_contenido(anu_contenido);
                        this.danuncio.setAnu_estado(anu_estado);
                        this.danuncio.setAnu_fechapub(anu_fechapub);
                        this.danuncio.setAnu_horapub(anu_horapub);
                        this.danuncio.setAnu_abogado(anu_abogado);
                        this.danuncio.setAnu_categoria(anu_categoria);
                        if (this.danuncio.Modificar()) {
                            respuesta = this.danuncio.Listar("Se modificó correctamente el anuncio.");
                        } else {
                            respuesta = "No se pudo modificar el anuncio";
                        }
                    } else {
                        respuesta = "El valor del estado debe ser '0' o '1'";
                    }
                } else {
                    respuesta = "El contenido es demasiado largo";
                }
            } else {
                respuesta = "El titulo es demasiado largo";
            }
        } else {
            respuesta = "No se permiten datos vacios o nulos o llaves foraneas y primarias inexistentes";
        }
        return respuesta;
    }

    public String EliminarAnuncio(int anu_id) {
        if (Integer.toString(anu_id).length() != 0) {
            if (this.danuncio.Existe(anu_id)) {
                this.danuncio.setAnu_id(anu_id);
                if (this.danuncio.Eliminar()) {
                    respuesta = this.danuncio.Listar("Se eliminó correctamente el anuncio.");
                } else {
                    respuesta = "No se pudo eliminar el anuncio";
                }
            } else {
                respuesta = "El anuncio que quiere eliminar no existe";
            }
        } else {
            respuesta = "No se permiten datos vacios o nulos";
        }
        return respuesta;
    }

    public String ListarAnuncio() {
        return this.danuncio.Listar("");
    }
}
