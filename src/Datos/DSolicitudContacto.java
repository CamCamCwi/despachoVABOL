
package Datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DSolicitudContacto {
    private int sol_id;
    private String sol_nombre;
    private String sol_apellido;
    private Date sol_fecha;
    private int sol_celular;
    private String sol_estado;
    private String sol_email;
    private String sol_contenido;
    private int sol_abogado;
    
    private DConexion conexion;

    public DSolicitudContacto() {
        conexion = new DConexion();
    }

    public int getSol_id() {
        return sol_id;
    }

    public void setSol_id(int sol_id) {
        this.sol_id = sol_id;
    }

    public String getSol_nombre() {
        return sol_nombre;
    }

    public void setSol_nombre(String sol_nombre) {
        this.sol_nombre = sol_nombre;
    }

    public String getSol_apellido() {
        return sol_apellido;
    }

    public void setSol_apellido(String sol_apellido) {
        this.sol_apellido = sol_apellido;
    }

    public Date getSol_fecha() {
        return sol_fecha;
    }

    public void setSol_fecha(Date sol_fecha) {
        this.sol_fecha = sol_fecha;
    }

    public int getSol_celular() {
        return sol_celular;
    }

    public void setSol_celular(int sol_celular) {
        this.sol_celular = sol_celular;
    }

    public String getSol_estado() {
        return sol_estado;
    }

    public void setSol_estado(String sol_estado) {
        this.sol_estado = sol_estado;
    }

    public String getSol_email() {
        return sol_email;
    }

    public void setSol_email(String sol_email) {
        this.sol_email = sol_email;
    }

    public String getSol_contenido() {
        return sol_contenido;
    }

    public void setSol_contenido(String sol_contenido) {
        this.sol_contenido = sol_contenido;
    }

    public int getSol_abogado() {
        return sol_abogado;
    }

    public void setSol_abogado(int sol_abogado) {
        this.sol_abogado = sol_abogado;
    }
    
    public boolean Registrar(){
        PreparedStatement ps = null; 
        Connection con = conexion.getConexion();
        
        String sql = "INSERT INTO solicitudcontacto (sol_nombre, sol_apellido, sol_fecha, sol_celular, sol_estado, sol_email, sol_contenido) VALUES (?,?,?,?,?,?,?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getSol_nombre());
            ps.setString(2, this.getSol_apellido());
            ps.setDate(3, this.getSol_fecha());
            ps.setInt(4, this.getSol_celular());
            ps.setString(5, this.getSol_estado());
            ps.setString(6, this.getSol_email());
            ps.setString(7, this.getSol_contenido());
            ps.execute();
            return true;
        }catch(SQLException e){
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.err.println(e);
                
            }
        }  
    }
    
    public boolean Modificar(){
        PreparedStatement ps = null;
        Connection con = conexion.getConexion();
        String sql = "UPDATE solicitudcontacto SET sol_estado=?, sol_abogado=? where sol_id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getSol_estado());
            ps.setInt(2, this.getSol_abogado());
            ps.setInt(3, this.getSol_id());
            ps.execute();
            return true;
        }catch(SQLException e){
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.err.println(e);
            }
        }  
    }

    public String Listar(String mensaje){
        String tabla = "";
        Statement Consulta;
        ResultSet resultado = null; 
        tabla = "Content-Type: text/html; charset=\"UTF-8\"\n" +
"\n" +
"<h3>"+ mensaje +"</h3>\n"+
"\n"+
"<h1>Solicitud de Contacto: </h1>"+
"<table style=\"border-collapse: collapse; width: 100%; border: 2px solid black;\">\n" +
"\n" +
"  <tr>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">ID</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">Nombre</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">Apellido</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">Fecha</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">Celular</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">Estado</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">Email</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">Contenido</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">ID Abogado</th>\n" +
"\n" +                
"  </tr>\n" +
"\n"; 
        try {
            String query = "SELECT * FROM solicitudcontacto ORDER BY sol_id";            
            Connection con = conexion.getConexion();            
            Consulta = (Statement) con.createStatement();
            resultado = Consulta.executeQuery(query);            
            ResultSetMetaData rsMd = resultado.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            while (resultado.next()) {
                tabla = tabla +
"  <tr>\n" +
"\n";
                for (int i = 0; i < cantidadColumnas; i++) {
                    tabla = tabla +
"    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">" + resultado.getString(i+1) + "</td>\n" +
"\n";
                }
                tabla = tabla +
"  </tr>\n" +
"\n" ;
            }
            tabla = tabla +
"\n" +
"</table>";
            Consulta.close();
            
            con.close();
            
        } catch (Exception e) {
            tabla = "No se pudieron listar los datos";
        }
        return tabla;
    }
    
    public boolean Existe(int sol_id){
        PreparedStatement ps = null;
        Connection con = conexion.getConexion();
        String sql = "SELECT * FROM solicitudcontacto where sol_id = ?";
        ResultSet resultado = null;
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, sol_id);
            resultado = ps.executeQuery();
            if (resultado.next()) {
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.err.println(e);
            }
        }  
    }
}
