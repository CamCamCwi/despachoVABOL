package Negocio;

import Datos.DSolicitudContacto;
import java.sql.Date;

public class NSolicitudContacto {

    private DSolicitudContacto dsolicitudcontacto;
    //private DAbogado dabogado;
    private String respuesta = "";

    public NSolicitudContacto() {
        dsolicitudcontacto = new DSolicitudContacto();
        //dabogado = new DAbogado();
    }

    public String RegistrarSolicitudContacto(String sol_nombre, String sol_apellido, Date sol_fecha, int sol_celular, String sol_estado, String sol_email, String sol_contenido) {
        if (sol_nombre.length() != 0 && sol_apellido.length() != 0 && sol_estado.length() != 0
                && sol_email.length() != 0 && sol_contenido.length() != 0
                && Integer.toString(sol_celular).length() != 0) {
            if (sol_nombre.length() <= 125) {
                if (sol_apellido.length() <= 125) {
                    if (sol_estado.length() <= 125) {
                        if (sol_email.length() <= 125) {
                            if (sol_contenido.length() <= 255) {

                                this.dsolicitudcontacto.setSol_nombre(sol_nombre);
                                this.dsolicitudcontacto.setSol_apellido(sol_apellido);
                                this.dsolicitudcontacto.setSol_fecha(sol_fecha);
                                this.dsolicitudcontacto.setSol_celular(sol_celular);
                                this.dsolicitudcontacto.setSol_estado(sol_estado);
                                this.dsolicitudcontacto.setSol_email(sol_email);
                                this.dsolicitudcontacto.setSol_contenido(sol_contenido);

                                if (this.dsolicitudcontacto.Registrar()) {
                                    respuesta = "Se registró correctamente la solicitud de contacto";
                                } else {
                                    respuesta = "No se pudo registrar la solicitud de contacto";
                                }
                            } else {
                                respuesta = "El contenido es demasiado largo";
                            }
                        } else {
                            respuesta = "El email es demasiado largo";
                        }
                    } else {
                        respuesta = "El estado es demasiado largo";
                    }
                } else {
                    respuesta = "El apellido es demasiado largo";
                }
            } else {
                respuesta = "El nombre es demasiado largo";
            }
        } else {
            respuesta = "No se permiten datos vacios o nulos";
        }
        return respuesta;
    }

    public String ModificarSolicitudContacto(int sol_id, String sol_estado, int sol_abogado) {
        if (Integer.toString(sol_id).length() != 0 && dsolicitudcontacto.Existe(sol_id)
                && sol_estado.length() != 0) {
            if (sol_estado.length() <= 125) {

                this.dsolicitudcontacto.setSol_id(sol_id);
                this.dsolicitudcontacto.setSol_estado(sol_estado);
                //if (dabogado.Existe(anu_abogado)) {
                this.dsolicitudcontacto.setSol_abogado(sol_abogado);
                //}

                if (this.dsolicitudcontacto.Modificar()) {
                    respuesta = "Se modificó correctamente la solicitud de contacto";
                } else {
                    respuesta = "No se pudo registrar la solicitud de contacto";
                }
            } else {
                respuesta = "El estado es demasiado largo";
            }
        } else {
            respuesta = "No se permiten datos vacios o nulos o llaves foraneas inexistentes o sol_id inexistente";
        }
        return respuesta;
    }

    public String ListarSolicitudContacto() {
        return this.dsolicitudcontacto.Listar("");
    }
}
