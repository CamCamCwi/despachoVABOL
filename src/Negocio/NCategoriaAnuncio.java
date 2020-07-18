package Negocio;

import Datos.DCategoriaAnuncio;

public class NCategoriaAnuncio {

    private DCategoriaAnuncio dcategoriaanuncio;
    private String respuesta = "";

    public NCategoriaAnuncio() {
        dcategoriaanuncio = new DCategoriaAnuncio();
    }

    public String RegistrarCategoriaAnuncio(String cat_nombre, String cat_descripcion) {
        if (cat_nombre.length() != 0 && cat_descripcion.length() != 0) {
            if (cat_nombre.length() <= 125) {
                if (cat_descripcion.length() <= 300) {
                    this.dcategoriaanuncio.setCat_nombre(cat_nombre);
                    this.dcategoriaanuncio.setCat_descripcion(cat_descripcion);
                    if (this.dcategoriaanuncio.Registrar()) {
                        respuesta = "Se registró correctamente la categoría anuncio";
                    } else {
                        respuesta = "No se pudo registrar la categoría anuncio";
                    }
                } else {
                    respuesta = "La descripción es demasiado larga";
                }
            } else {
                respuesta = "El nombre es demasiado largo";
            }
        } else {
            respuesta = "No se permiten datos vacios o nulos";
        }
        return respuesta;
    }

    public String ModificarCategoriaAnuncio(int cat_id, String cat_nombre, String cat_descripcion) {
        if (Integer.toString(cat_id).length() != 0) {
            if (this.dcategoriaanuncio.Existe(cat_id) && cat_nombre.length() != 0 && cat_descripcion.length() != 0) {
                if (cat_nombre.length() <= 125) {
                    if (cat_descripcion.length() <= 255) {
                        this.dcategoriaanuncio.setCat_id(cat_id);
                        this.dcategoriaanuncio.setCat_nombre(cat_nombre);
                        this.dcategoriaanuncio.setCat_descripcion(cat_descripcion);
                        if (this.dcategoriaanuncio.Modificar()) {
                            respuesta = "Se modificó correctamente la categoría anuncio";
                        } else {
                            respuesta = "No se pudo modificar la categoría anuncio";
                        }
                    } else {
                        respuesta = "La descripción es demasiado larga";
                    }
                } else {
                    respuesta = "El nombre es demasiado largo";
                }
            } else {
                respuesta = "No se permiten datos vacios o nulos y el cat_id debe existir";
            }
        } else {
            respuesta = "Debe insertar un cat_id válido";
        }
        return respuesta;
    }

    public String EliminarCategoriaAnuncio(int cat_id) {
        if (Integer.toString(cat_id).length() != 0) {
            if (this.dcategoriaanuncio.Existe(cat_id)) {
                this.dcategoriaanuncio.setCat_id(cat_id);
                if (this.dcategoriaanuncio.Eliminar()) {
                    respuesta = "Se eliminó correctamente la categoria anuncio";
                } else {
                    respuesta = "No se pudo eliminar la categoria anuncio";
                }
            } else {
                respuesta = "La categoria anuncio que quiere eliminar no existe";
            }
        } else {
            respuesta = "No se permiten datos vacios o nulos";
        }
        return respuesta;
    }

    public String ListarCategoriaAnuncio() {
        return this.dcategoriaanuncio.Listar("");
    }
}
