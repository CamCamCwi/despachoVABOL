/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DUsuario;

/**
 *
 * @author Gerson
 */
public class NUsuario {
    
    DUsuario dato;
    
    public NUsuario(){
        this.dato= new DUsuario();
    }
    
    public String Login(String mail, String Contraseña){
        this.dato.setUsuario(mail);
        this.dato.setContraseña(Contraseña);                               
        return dato.Login();
    }
    
}
