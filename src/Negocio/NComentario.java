package Negocio;

import Datos.DComentario;
import Datos.DDocumento;
import Datos.DUsuario;
import java.sql.Date;
import java.sql.Time;

public class NComentario {
    
    private DComentario dcomentario;
    private DDocumento ddocumento;
    private DUsuario dusuario;
    private String respuesta = "";
    
    public NComentario() {
        dcomentario = new DComentario();
        ddocumento = new DDocumento();
        dusuario = new DUsuario();
    }
    
    public String RegistrarComentario(Date com_fecha, Time com_hora, String com_contenido, int com_doc, int com_usuario, String com_com2){
        // Debo verificar si los datos de la llaves foraneas existen
        this.respuesta = "";
        if(com_contenido.length()!=0 && Integer.toString(com_doc).length()!=0 && Integer.toString(com_usuario).length()!=0 && com_com2.length()!=0){
            if(com_contenido.length() <= 125){
                if(this.ddocumento.Existe(com_doc)){
                    
                    this.dcomentario.setCom_fecha(com_fecha);
                    this.dcomentario.setCom_hora(com_hora);
                    this.dcomentario.setCom_contenido(com_contenido);
                    this.dcomentario.setCom_doc(com_doc);
                    this.dcomentario.setCom_usuario(com_usuario);
                    if(com_com2.equals("-") == false){
                        int c2 = Integer.parseInt(com_com2);
                        if(dcomentario.Existe(c2)){
                            this.dcomentario.setCom_com2(c2);
                        }else{
                            respuesta = "El comentario al cual quiere comentar no existe, intente nuevamente";
                        }
                    }
                    
                    if(respuesta.equals("")){
                        if(this.dcomentario.Registrar()){
                        //respuesta = "Se registró correctamente el comentario: Comentario[" + 
                        //            this.dcomentario.getCom_fecha() + ", " + this.dcomentario.getCom_hora() + ", " 
                        //            + this.dcomentario.getCom_contenido() + ", " + this.dcomentario.getCom_doc() + ", " 
                        //            + this.dcomentario.getCom_usuario() + ", " + this.dcomentario.getCom_com2() + "]";
                            
                            respuesta = this.dcomentario.Listar(this.dcomentario.getCom_doc(),"Se registró correctamente el comentario");
                        }else{
                            respuesta = "No se pudo registrar el comentario";
                        }
                    }
                    
                }else{
                    respuesta = "El usuario o el documento al cual quiere comentar no existe, intente nuevamente";
                }
                
            }else{
                respuesta = "El contenido del comentario es demasiado largo";
            }
        }else{
            respuesta = "No se permiten datos vacios o nulos";
        }
      return respuesta;
    }
    
    public String ModificarComentario(int com_id, Date com_fecha, Time com_hora, String com_contenido){
        // Verificar si los datos de la llave foranea existen 
        //
        if(com_contenido.length()!=0 && Integer.toString(com_id).length()!=0){
            if(this.dcomentario.Existe(com_id)){
                if(com_contenido.length() <= 125){
                    this.dcomentario.setCom_fecha(com_fecha);
                    this.dcomentario.setCom_hora(com_hora);
                    this.dcomentario.setCom_contenido(com_contenido);
                    this.dcomentario.setCom_id(com_id);
                    if(this.dcomentario.Modificar()){
                        //respuesta = "Se modificó correctamente el comentario: Comentario[" + this.dcomentario.getCom_id() + ", " 
                        //        + this.dcomentario.getCom_fecha() + ", " + this.dcomentario.getCom_hora() + ", " 
                        //        + this.dcomentario.getCom_contenido() + ", " + this.dcomentario.getCom_doc() + ", " 
                        //        + this.dcomentario.getCom_usuario() + ", " + this.dcomentario.getCom_com2() + "]";
                        String com_documento = this.dcomentario.getValue("com_doc");
                        respuesta = this.dcomentario.Listar(Integer.parseInt(com_documento),"Se modificó correctamente el comentario");
                    }else{
                        respuesta = "No se pudo modificar el comentario";
                    }
                }else{
                    respuesta = "El contenido del comentario es demasiado largo";
                }
            }else{
                respuesta = "El comentario que quiere modificar no existe";
            }
                
        }else{
            respuesta = "No se permiten datos vacios o nulos";
        }
      return respuesta;
    }
    
    public String EliminarComentario(int com_id){
        
        if(Integer.toString(com_id).length()!=0){
            if(this.dcomentario.Existe(com_id)){
                this.dcomentario.setCom_id(com_id);
                if(this.dcomentario.Eliminar()){
                    //respuesta = "Se eliminó correctamente el comentario: Comentario[" + this.dcomentario.getCom_id() + "]";
                    int doc_id = Integer.parseInt(this.dcomentario.getValue("com_doc"));
                    respuesta = this.dcomentario.Listar(doc_id,"Se eliminó correctamente el comentario");
                }else{
                    respuesta = "No se pudo eliminar el comentario";
                }
            }else{
                respuesta = "El comentario que quiere eliminar no existe";
            } 
        }else{
            respuesta = "No se permiten datos vacios o nulos";
        }
        
      return respuesta;
    }
    
    public String ListarComentarios(int doc_id){
        return this.dcomentario.Listar(doc_id, "");
    }
}
