package Negocio;

import Datos.DAbogado;
import Datos.DCategoriaDoc;
import Datos.DDocumento;
import java.sql.Date;
import java.sql.Time;

public class NDocumento {

    private DDocumento ddocumento;
    private DAbogado dabogado;
    private DCategoriaDoc dcategoriadoc;
    private String respuesta = "";

    public NDocumento() {
        ddocumento = new DDocumento();
        dabogado = new DAbogado();
        dcategoriadoc = new DCategoriaDoc();
    }

    public String RegistrarDocumento(String doc_titulo, String doc_descripcion, Date doc_fechasubida, Time doc_horasubida, String doc_url, String doc_cliente, int doc_abogado, int doc_categoriadoc, int doc_idmail) {
        if (doc_titulo.length() != 0 && doc_descripcion.length() != 0 && doc_url.length() != 0
                && doc_cliente.length() != 0 && Integer.toString(doc_idmail).length() != 0
                && Integer.toString(doc_abogado).length() != 0 && Integer.toString(doc_categoriadoc).length() != 0
                && dcategoriadoc.Existe(doc_categoriadoc) /*&& dabogado.Existe(doc_abogado)*/) {
            if (doc_titulo.length() <= 125) {
                if (doc_descripcion.length() <= 255) {
                    if (doc_url.length() <= 1000) {
                        if (doc_cliente.length() <= 30) {
                            this.ddocumento.setDoc_titulo(doc_titulo);
                            this.ddocumento.setDoc_descripcion(doc_descripcion);
                            this.ddocumento.setDoc_fechasubida(doc_fechasubida);
                            this.ddocumento.setDoc_horasubida(doc_horasubida);
                            this.ddocumento.setDoc_url(doc_url);
                            this.ddocumento.setDoc_cliente(doc_cliente);
                            this.ddocumento.setDoc_abogado(doc_abogado);
                            this.ddocumento.setDoc_categoriadoc(doc_categoriadoc);
                            this.ddocumento.setDoc_idmail(doc_idmail);
                            
                            if (this.ddocumento.Registrar()) {
                                respuesta = "Se registró correctamente el documento";
                            } else {
                                respuesta = "No se pudo registrar el documento";
                            }
                        } else {
                            respuesta = "El valor del CI Cliente es demasiado largo";
                        }
                    } else {
                        respuesta = "El valor de la url es demasiado largo";
                    }
                } else {
                    respuesta = "La descripcion es demasiado larga";
                }
            } else {
                respuesta = "El titulo es demasiado largo";
            }
        } else {
            respuesta = "No se permiten datos vacios o nulos o llaves foraneas inexistentes";
        }
        return respuesta;
    }
    
    public String ModificarDocumento(int doc_id, String doc_titulo, String doc_descripcion, Date doc_fechasubida, Time doc_horasubida, String doc_url, String doc_cliente, int doc_abogado, int doc_categoriadoc, int doc_idmail) {
        if (Integer.toString(doc_id).length() != 0 && ddocumento.Existe(doc_id) 
                && doc_titulo.length() != 0 && doc_descripcion.length() != 0 && doc_url.length() != 0
                && doc_cliente.length() != 0 && Integer.toString(doc_idmail).length() != 0
                && Integer.toString(doc_abogado).length() != 0 && Integer.toString(doc_categoriadoc).length() != 0
                && dcategoriadoc.Existe(doc_categoriadoc) /*&& dabogado.Existe(doc_abogado)*/) {
            if (doc_titulo.length() <= 125) {
                if (doc_descripcion.length() <= 255) {
                    if (doc_url.length() <= 1000) {
                        if (doc_cliente.length() <= 30) {
                            this.ddocumento.setDoc_id(doc_id);
                            this.ddocumento.setDoc_titulo(doc_titulo);
                            this.ddocumento.setDoc_descripcion(doc_descripcion);
                            this.ddocumento.setDoc_fechasubida(doc_fechasubida);
                            this.ddocumento.setDoc_horasubida(doc_horasubida);
                            this.ddocumento.setDoc_url(doc_url);
                            this.ddocumento.setDoc_cliente(doc_cliente);
                            this.ddocumento.setDoc_abogado(doc_abogado);
                            this.ddocumento.setDoc_categoriadoc(doc_categoriadoc);
                            this.ddocumento.setDoc_idmail(doc_idmail);
                            
                            if (this.ddocumento.Modificar()) {
                                respuesta = "Se Modificó correctamente el documento";
                            } else {
                                respuesta = "No se pudo modificar el documento";
                            }
                        } else {
                            respuesta = "El valor del CI Cliente es demasiado largo";
                        }
                    } else {
                        respuesta = "El valor de la url es demasiado largo";
                    }
                } else {
                    respuesta = "La descripcion es demasiado larga";
                }
            } else {
                respuesta = "El titulo es demasiado largo";
            }
        } else {
            respuesta = "No se permiten datos vacios o nulos o llaves foraneas inexistentes";
        }
        return respuesta;
    }
    public String EliminarDocumento(int doc_id) {
        if (Integer.toString(doc_id).length() != 0) {
            if (this.ddocumento.Existe(doc_id)) {
                this.ddocumento.setDoc_id(doc_id);
                if (this.ddocumento.Eliminar()) {
                    respuesta = "Se eliminó correctamente el documento";
                } else {
                    respuesta = "No se pudo eliminar el documento";
                }
            } else {
                respuesta = "El documento que quiere eliminar no existe";
            }
        } else {
            respuesta = "No se permiten datos vacios o nulos";
        }
        return respuesta;
    }
    
    public String ListarDocumento() {
        return this.ddocumento.Listar();
    }
}