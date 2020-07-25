package Negocio;

import Datos.DAbogado;
import Datos.DUsuario;
import java.sql.Date;

/**
 *
 * @author Gers
 */
public class NAbogado {

    private DAbogado dato;
    private DUsuario user;

    public NAbogado() {

        dato = new DAbogado();
        user = new DUsuario();        
    }

    private String ValidarDatos() {
        String res = "";
        res += (dato.getCi() < 1) ? "Ci del Abogado no puede ser nulo," :"";
        res += (dato.getNombre().length() < 1) ? "Nombre no puede ser nulo," :"";
        res += (dato.getApellidoP().length() < 1) ? "ApellidoP no puede ser nulo," :"";
        res += (dato.getApellidoM().length() < 1) ? "ApellidM no puede ser nulo," :"";
        res += (dato.getEspecialidad().length() < 1) ? "Especialidad no puede ser nulo," :"";
        res += (dato.getCelular() < 1) ? "Celular del Abogado no puede ser nulo," :"";
        res += (dato.getfNacimiento().toString().length() < 10) ? "Fecha de nacimiento del Abogado Debe ser formato DD-MM-AAAA," :"";
        res += (dato.getGenero().length() < 1) ? "Genero no puede ser nulo," : "";
        res += (dato.getNroColAbogado() < 1) ? "Numero en Colegio de Abogados no puede ser nulo," :"";
        res += (dato.getNroMinJusticia() < 1) ? "Numero en Ministerio de justicia no puede ser nulo," :"";
        res += (dato.getNumRegCorte() < 1) ? "Numero de Registro en la corte no puede ser nulo," :"";
        res += (user.getUsuario().length() < 5) ? "Mail no puede ser nulo," :"";
        res += (user.getContraseña().length() < 8) ? "Longitud de contraseña incorrecta,":"";
        return res;
    }

    public String RegistrarAbogado(int abg_ci, String abg_nombre, String abg_apellidop, String abg_apellidom, String abg_especialidad, int abg_celular, String abg_fnacimiento, String abg_genero, int abg_nrocolabogados, int abg_nrominjusticia, int abg_numregcorte,String mail, String contraseña) {
        dato.setCi(abg_ci);
        dato.setNombre(abg_nombre);
        dato.setApellidoP(abg_apellidop);
        dato.setApellidoM(abg_apellidom);
        dato.setEspecialidad(abg_especialidad);
        dato.setCelular(abg_celular);
        dato.setfNacimiento(Date.valueOf(abg_fnacimiento));
        dato.setGenero(abg_genero);
        dato.setNroColAbogado(abg_nrocolabogados);
        dato.setNroMinJusticia(abg_nrominjusticia);
        dato.setNumRegCorte(abg_numregcorte);        
        user.setUsuario(mail);
        user.setContraseña(contraseña);
        String res = ValidarDatos();
        if (res.length() == 0) {
//            user.setContraseña(HASH(user.getContraseña()));            
            if (user.registrar()){
                int id_usuario=user.ObtenerID();
                dato.setFk_abg_usuario(id_usuario);                
            }                                            
            return dato.Registrar()?"Abogado Registrado con exito":"Fallo al Registrar el Abogado";
        }
        return res;
    }

    public String ModificaAbogado(int abg_ci, String abg_nombre, String abg_apellidop, String abg_apellidom, String abg_especialidad, int abg_celular, String abg_fnacimiento, String abg_genero, int abg_nrocolabogados, int abg_nrominjusticia, int abg_numregcorte) {        
        dato.setCi(abg_ci);
        dato.setNombre(abg_nombre);
        dato.setApellidoP(abg_apellidop);
        dato.setApellidoM(abg_apellidom);
        dato.setEspecialidad(abg_especialidad);
        dato.setCelular(abg_celular);
        dato.setfNacimiento(Date.valueOf(abg_fnacimiento));
        dato.setGenero(abg_genero);
        dato.setNroColAbogado(abg_nrocolabogados);
        dato.setNroMinJusticia(abg_nrominjusticia);
        dato.setNumRegCorte(abg_numregcorte);
        String res = "";
        res += (dato.getCi() < 1) ? "Ci del Abogado no puede ser nulo," : "";
        res += (dato.getNombre().length() < 1) ? "Nombre no puede ser nulo," : "";
        res += (dato.getApellidoP().length() < 1) ? "ApellidoP no puede ser nulo," : "";
        res += (dato.getApellidoM().length() < 1) ? "ApellidM no puede ser nulo," : "";
        res += (dato.getEspecialidad().length() < 1) ? "Especialidad no puede ser nulo," : "";
        res += (dato.getCelular() < 1) ? "Celular del Abogado no puede ser nulo," : "";
        res += (dato.getfNacimiento().toString().length() < 10) ? "Fecha de nacimiento del Abogado Debe ser formato DD-MM-AAAA," : "";
        res += (dato.getGenero().length() < 1) ? "Genero no puede ser nulo," : "";
        res += (dato.getNroColAbogado() < 1) ? "Numero en Colegio de Abogados no puede ser nulo," : "";
        res += (dato.getNroMinJusticia() < 1) ? "Numero en Ministerio de justicia no puede ser nulo," : "";
        res += (dato.getNumRegCorte() < 1) ? "Numero de Registro en la corte no puede ser nulo," : "";
        if (res.length() == 1) {
            return dato.Modificar() ? "Abogado Modificado con exito" : "no se pudo modificar al Abogado";
        }
        return res;
    }

    public String EliminarAbogado(int ci) {
        if (ci > 1) {
            dato.setCi(ci);                
            if (dato.Existe(ci)) {                
                user.setUsuario(dato.ObtenerMailbyCI());
                user.eliminar();                
                return "Abogado Eliminado con Exito";
            } else {
                return "El Abogado no existe";
            }

        }else{
            return "Ci no es una longitud valida";
        }

    }

    public String ListarAbogado() {
        return dato.Listar();
    }

    public String ModificarContraseñaAbogado(String email, String anterior_contraseña, String nueva_contraseña) {
        DUsuario usuario = new DUsuario();
        usuario.setUsuario(email);
        //aplicar hash a la anterior contraseña
        usuario.setContraseña(anterior_contraseña);
        if (usuario.Existe()) {
            usuario.setContraseña(nueva_contraseña);
            usuario.modificarContraseña();
            return "Contraseña del usuario " + email + " modificada con exito";
        }
        return "fallo al modificar , usuario o contraseña incorrectas";
    }
    public String FindAbogado(int ci){
        if (ci >0) {
            dato.setCi(ci);
            return dato.find(ci);            
        }else{
            return "Formato del CI no valido";
        }
    }
}
