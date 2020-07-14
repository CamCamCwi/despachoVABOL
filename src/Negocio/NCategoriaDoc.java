
package Negocio;

import Datos.DCategoriaDoc;

public class NCategoriaDoc {
    
    private DCategoriaDoc dcategoriadoc;
    private String respuesta = "";

    public NCategoriaDoc() {
        dcategoriadoc = new DCategoriaDoc();
    }
    
    public String RegistrarCategoriaDoc(String catDoc_nombre, String catDoc_descripcion){
            if(catDoc_nombre.length()!= 0 && catDoc_descripcion.length()!=0){
                if(catDoc_nombre.length() <= 125){
                    if (catDoc_descripcion.length() <= 255){
                        this.dcategoriadoc.setCatDoc_nombre(catDoc_nombre);
                        this.dcategoriadoc.setCatDoc_descripcion(catDoc_descripcion);
                        if(this.dcategoriadoc.Registrar()){
                            respuesta = "Se registró correctamente la categoría documento\n\n";
                            respuesta = respuesta + this.dcategoriadoc.Listar();
                        }else{
                            respuesta = "No se pudo registrar la categoría documento";
                        }
                    }else{
                        respuesta = "La descripción es demasiado larga";
                    }
                }else{
                   respuesta = "El nombre es demasiado largo";
                }
            }else{
                respuesta = "No se permiten datos vacios o nulos";
            }
        return respuesta;
    }
    
    public String ModificarCategoriaDoc(int catDoc_id, String catDoc_nombre, String catDoc_descripcion){
        //if(this.dcategoriadoc.Existe(catDoc_id)){
        if(catDoc_nombre.length()!= 0 && catDoc_descripcion.length()!=0 && Integer.toString(catDoc_id).length()!= 0){
            if(this.dcategoriadoc.Existe(catDoc_id)){
                if(catDoc_nombre.length() <= 125){
                    if (catDoc_descripcion.length() <= 255){
                        this.dcategoriadoc.setCatDoc_id(catDoc_id);
                        this.dcategoriadoc.setCatDoc_nombre(catDoc_nombre);
                        this.dcategoriadoc.setCatDoc_descripcion(catDoc_descripcion);
                        if(this.dcategoriadoc.Modificar()){
                            respuesta = "Se modificó correctamente la categoría documento\n\n";
                            respuesta = respuesta + this.dcategoriadoc.Listar();
                        }else{
                            respuesta = "No se pudo modificar la categoría documento";
                        }
                    }else{
                        respuesta = "La descripción es demasiado larga"; 
                    }
                }else{
                   respuesta = "El nombre es demasiado largo"; 
                }
            }else{
                respuesta = "La categoria documento que quiere modificar no existe";
            }   
        }else{
            respuesta = "No se permiten datos vacios o nulos";
        }
       return respuesta;
    }
    
    public String EliminarCategoriaDoc(int catDoc_id){

        if(Integer.toString(catDoc_id).length()!=0){
            if(this.dcategoriadoc.Existe(catDoc_id)){
                this.dcategoriadoc.setCatDoc_id(catDoc_id);
                if(this.dcategoriadoc.Eliminar()){
                    respuesta = "Se eliminó correctamente la categoría documento\n\n";
                    respuesta = respuesta + this.dcategoriadoc.Listar();
                }else{
                    respuesta = "No se pudo eliminar la categoría documento";
                }
            }else{
                respuesta = "La categoria documento que quiere eliminar no existe"; 
            }
                
        }else{
            respuesta = "No se permiten datos vacios o nulos";
        }
      return respuesta;
    }
    
    public String ListarCategoriaDoc(){
        return this.dcategoriadoc.Listar();
    }
    
}
