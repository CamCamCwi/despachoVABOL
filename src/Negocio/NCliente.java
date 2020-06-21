package Negocio;

import Datos.DCliente;

/**
 *
 * @author Gers
 */
public class NCliente {
    DCliente dato;
    public NCliente(int cl_nit,String cl_ciudad,String cl_descripcion,String cl_direccion,String cl_nrepresentante,String cl_paginaweb,String cl_pais,String cl_razonsocial,String cl_rubro,int cl_telefono,int cl_usuario){
        this.dato = new DCliente();
        dato.setNit(cl_nit);
        dato.setCiudad(cl_ciudad);
        dato.setDescripcion(cl_descripcion);
        dato.setDireccion(cl_direccion);
        dato.setnRepresentante(cl_nrepresentante);
        dato.setPaginaWeb(cl_paginaweb);
        dato.setPaís(cl_pais);
        dato.setRazonSocial(cl_razonsocial);
        dato.setRubro(cl_rubro);
        dato.setTelefono(cl_telefono);
        dato.setCl_usuario(cl_usuario);
    }
    public void RegistrarCliente(){
        dato.Registrar();
    }
    public void ModificarCliente(){
        dato.Modificar();
    }
    public void EliminarCliente(){
        dato.Eliminar();
    }
    public String ListarCliente(){
        return dato.Listar();
    }
}
