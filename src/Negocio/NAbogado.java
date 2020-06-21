
package Negocio;

import Datos.DAbogado;

/**
 *
 * @author Gers
 */
public class NAbogado {
    private DAbogado dato;
    
    public NAbogado (int abg_ci, String abg_nombre,String abg_apellidop,String abg_apellidom,String abg_especialidad,int abg_celular, String abg_fnacimiento,String abg_genero,int abg_nrocolabogados, int abg_nrominjusticia, int abg_numregcorte,int fk_abg_usuario){
        dato=new DAbogado();
        dato.setNombre(abg_nombre);
        dato.setApellidoP(abg_apellidop);
        dato.setApellidoM(abg_apellidom);
        dato.setEspecialidad(abg_especialidad);
        dato.setCelular(abg_celular);        
        dato.setfNacimiento(abg_fnacimiento);
        dato.setGenero(abg_genero);
        dato.setNroColAbogado(abg_nrocolabogados);
        dato.setNroMinJusticia(abg_nrominjusticia);
        dato.setNumRegCorte(abg_numregcorte);
        dato.setFk_abg_usuario(fk_abg_usuario);
    }
    
    public void RegistrarAbogado(){
        dato.Registrar();
    }
    public void ModificaAbogado(){
        dato.Modificar();
    }
    public void EliminarAbogado(){
        dato.Eliminar();
    }
    public String ListarAbogado(){
        return dato.Listar();
    }
}