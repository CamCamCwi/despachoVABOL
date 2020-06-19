
package Negocio;

import Datos.DCategoriaDoc;

public class NCategoriaDoc {
    
    private DCategoriaDoc dcategoriadoc;

    public NCategoriaDoc() {
        dcategoriadoc = new DCategoriaDoc();
    }
    
    public void RegistrarCategoriaDoc(String catDoc_nombre, String catDoc_descripcion){
            if(catDoc_nombre.length()!= 0 && catDoc_descripcion.length()!=0){
                if(catDoc_nombre.length() <= 125){
                    if (catDoc_descripcion.length() <= 255){
                        this.dcategoriadoc.setCatDoc_nombre(catDoc_nombre);
                        this.dcategoriadoc.setCatDoc_descripcion(catDoc_descripcion);
                        this.dcategoriadoc.Registrar();
                    }else{
                        System.out.println("La descripción es demaciada largo"); 
                    }
                }else{
                   System.out.println("El nombre es demaciado largo"); 
                }
            }else{
                System.out.println("Los datos enviados estan vacios o nulos");
            }
    }
    
    public void ModificarCategoriaDoc(int catDoc_id, String catDoc_nombre, String catDoc_descripcion){
        // Buscar el catDoc_id
        if(catDoc_nombre.length()!= 0 && catDoc_descripcion.length()!=0 && Integer.toString(catDoc_id).length()!= 0){
                if(catDoc_nombre.length() <= 125){
                    if (catDoc_descripcion.length() <= 255){
                        this.dcategoriadoc.setCatDoc_id(catDoc_id);
                        this.dcategoriadoc.setCatDoc_nombre(catDoc_nombre);
                        this.dcategoriadoc.setCatDoc_descripcion(catDoc_descripcion);
                        this.dcategoriadoc.Modificar();
                    }else{
                        System.out.println("La descripción es demaciada largo"); 
                    }
                }else{
                   System.out.println("El nombre es demaciado largo"); 
                }
            }else{
                System.out.println("Los datos enviados estan vacios o nulos");
            }
    }
    
    public void EliminarCategoriaDoc(int catDoc_id){
        // Verificamos si existe
        if(Integer.toString(catDoc_id).length()!=0){
            this.dcategoriadoc.setCatDoc_id(catDoc_id);
            this.dcategoriadoc.Eliminar();
        }else{
            System.out.println("Los datos enviados estan vacios o nulos");
        }
    }
    
    public void ListarCategoriaDoc(){
        
    }
    
}
