package Negocio;

import Datos.DCliente;
import Datos.DUsuario;

/**
 *
 * @author Gers
 */
public class NCliente {

    DCliente dato;
    DUsuario user;

    public NCliente() {
        this.dato = new DCliente();
        this.user = new DUsuario();
    }

    private String ValidarDatos() {
        String res = "";
        res += (dato.getNit().length() < 1) ? "Nit del cliente no puede ser nulo," : "";
        res += (dato.getCiudad().length() < 1) ? "Ciudad no puede ser nulo," : "";
        res += (dato.getDescripcion().length() < 1) ? "Descripcion no puede ser nulo," : "";
        res += (dato.getDireccion().length() < 1) ? "Direccion no puede ser nulo," : "";
        res += (dato.getnRepresentante().length() < 1) ? "Numero del representante no puede ser nulo," : "";
        res += (dato.getPaís().length() < 1) ? "Pais no puede ser nulo," : "";
        res += (dato.getRazonSocial().length() < 1) ? "Razon social no puede ser nulo," : "";
        res += (dato.getRubro().length() < 1) ? "Rubro no puede ser nulo," : "";
        res += (dato.getTelefono() < 1) ? "Numero de telefono no puede ser nulo," : "";
        res += (user.getUsuario().length() < 5) ? "Mail no puede ser nulo," : "";
        res += (user.getContraseña().length() < 8) ? "Longitud de contraseña incorrecta," : "";
        return res;
    }

    public String RegistrarCliente(String cl_nit, String cl_ciudad, String cl_descripcion, String cl_direccion, String cl_nrepresentante, String cl_paginaweb, String cl_pais, String cl_razonsocial, String cl_rubro, int cl_telefono, String mail, String Contraseña) {
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
        user.setUsuario(mail);
        user.setContraseña(Contraseña);
        String res = ValidarDatos();
        if (res.length() == 0) {
//            user.setContraseña(HASH(user.getContraseña())); 
            if (user.registrar()) {  
                int id_usuario=user.ObtenerID();               
                dato.setCl_usuario(id_usuario);
                return dato.Registrar() ? dato.Listar("Cliente Registrado con exito") : "Fallo al registrar al Cliente";
            }
        }
        return res;
    }

    public String ModificarCliente(String cl_nit, String cl_ciudad, String cl_descripcion, String cl_direccion, String cl_nrepresentante, String cl_paginaweb, String cl_pais, String cl_razonsocial, String cl_rubro, int cl_telefono) {
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
        String res = "";
        res += (dato.getNit().length() < 1) ? "Nit del cliente no puede ser nulo," : "";
        res += (dato.getCiudad().length() < 1) ? "Ciudad no puede ser nulo," : "";
        res += (dato.getDescripcion().length() < 1) ? "Descripcion no puede ser nulo," : "";
        res += (dato.getDireccion().length() < 1) ? "Direccion no puede ser nulo," : "";
        res += (dato.getnRepresentante().length() < 1) ? "Numero del representante no puede ser nulo," : "";
        res += (dato.getPaís().length() < 1) ? "Pais no puede ser nulo," : "";
        res += (dato.getRazonSocial().length() < 1) ? "Razon social no puede ser nulo," : "";
        res += (dato.getRubro().length() < 1) ? "Rubro no puede ser nulo," : "";
        res += (dato.getTelefono() < 1) ? "Numero de telefono no puede ser nulo," : "";
        if (res.length() == 0) {
            return dato.Modificar() ? dato.Listar("Cliente Modificado con exito") : "No se pudo modificar al Cliente";
        }
        return res;
    }

    public String EliminarCliente(String Nit) {

        if (! Nit.isEmpty()) {
            dato.setNit(Nit);
            if (dato.Existe(Nit)) {
                user.setUsuario(dato.ObtenerMailbyNIT());                               
                return  user.eliminar()? dato.Listar("Cliente Eliminado con Exito"):"Fallo al eliminar cliente";
            } else {
                return "No Existe el Cliente";
            }
        } else {
            return "Nit no es un formato valido";
        }
    }

    public String ListarCliente() {
        return dato.Listar("");
    }

    public String ModificarContraseñaCliente(String mail, String anterior_contraseña, String nueva_contraseña) {
        DUsuario usuario = new DUsuario();
        usuario.setUsuario(mail);
        //aplicar hash a la anterior contraseña
        usuario.setContraseña(anterior_contraseña);
        if (usuario.Existe()) {
            usuario.setContraseña(nueva_contraseña);
            usuario.modificarContraseña();
            return "Contraseña del usuario " + mail + " modificada con exito";
        }
        return "fallo al modificar, contraseña o usuario  incorrectas";
    }

    public String FindCliente(String nit) {
        if (nit.length()>0) {
            dato.setNit(nit);
            return dato.find(nit);
        } else {
            return "Formato del NIT no valido";
        }
    }
}
