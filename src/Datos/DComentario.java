
package Datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

public class DComentario {
    private int com_id;
    private Date com_fecha;
    private Time com_hora;
    private String com_contenido;
    private int com_doc;
    private int com_usuario;
    private int com_com2;
    
    private DConexion conexion;

    public DComentario() {
        conexion = new DConexion();
    }

    public int getCom_id() {
        return com_id;
    }

    public void setCom_id(int com_id) {
        this.com_id = com_id;
    }

    public Date getCom_fecha() {
        return com_fecha;
    }

    public void setCom_fecha(Date com_fecha) {
        this.com_fecha = com_fecha;
    }

    public Time getCom_hora() {
        return com_hora;
    }

    public void setCom_hora(Time com_hora) {
        this.com_hora = com_hora;
    }

    public String getCom_contenido() {
        return com_contenido;
    }

    public void setCom_contenido(String com_contenido) {
        this.com_contenido = com_contenido;
    }

    public int getCom_doc() {
        return com_doc;
    }

    public void setCom_doc(int com_doc) {
        this.com_doc = com_doc;
    }

    public int getCom_usuario() {
        return com_usuario;
    }

    public void setCom_usuario(int com_usuario) {
        this.com_usuario = com_usuario;
    }

    public int getCom_com2() {
        return com_com2;
    }

    public void setCom_com2(int com_com2) {
        this.com_com2 = com_com2;
    }
    
    public boolean Registrar(){
        PreparedStatement ps = null; 
        Connection con = conexion.getConexion();
        String sql = "INSERT INTO comentario(com_fecha, com_hora, com_contenido, com_doc, com_usuario, com_com2) VALUES (?,?,?,?,?,?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setDate(1, this.getCom_fecha());
            ps.setTime(2, this.getCom_hora());
            ps.setString(3, this.getCom_contenido());
            ps.setInt(4, this.getCom_doc());
            ps.setInt(5, this.getCom_usuario());
            ps.setInt(6, this.getCom_com2());
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
        String sql = "UPDATE comentario SET com_fecha = ?, com_hora = ?, com_contenido = ?, com_doc = ?, com_usuario = ?, "
                   + "com_com2 = ? where com_id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setDate(1, this.getCom_fecha());
            ps.setTime(2, this.getCom_hora());
            ps.setString(3, this.getCom_contenido());
            ps.setInt(4, this.getCom_doc());
            ps.setInt(5, this.getCom_usuario());
            ps.setInt(6, this.getCom_com2());
            ps.setInt(7, this.getCom_id());
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
    
    public boolean Eliminar(){
        PreparedStatement ps = null;
        Connection con = conexion.getConexion();
        String sql = "DELETE from comentario where com_id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.getCom_id());
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
    
    public String Listar(){
        String imprimir="";
        Statement Consulta;
        ResultSet resultado = null;        
        try {
            String query = "SELECT * FROM comentario";            
            Connection con = conexion.getConexion();            
            Consulta = (Statement) con.createStatement();
            resultado = Consulta.executeQuery(query);            
            ResultSetMetaData rsMd = resultado.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            while (resultado.next()) {
                for (int i = 0; i < cantidadColumnas; i++) {
                    imprimir =imprimir  +resultado.getString(i+1)+ " ";
                }
                imprimir += "\n";
            }
            Consulta.close();
            
            con.close();
            
        } catch (Exception e) {
           imprimir = "No se pudieron listar los datos";
        }
        return imprimir;
    }
    
    public boolean Existe(int com_id){
        PreparedStatement ps = null;
        Connection con = conexion.getConexion();
        String sql = "SELECT * FROM comentario where com_id = ?";
        ResultSet resultado = null;
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, com_id);
            resultado = ps.executeQuery();
            if(resultado.next()) {
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