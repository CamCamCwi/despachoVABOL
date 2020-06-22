package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DCategoriaAnuncio {

    private int cat_id;
    private String cat_nombre;
    private String cat_descripcion;

    private DConexion conexion;

    public DCategoriaAnuncio() {
        conexion = new DConexion();
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_nombre() {
        return cat_nombre;
    }

    public void setCat_nombre(String cat_nombre) {
        this.cat_nombre = cat_nombre;
    }

    public String getCat_descripcion() {
        return cat_descripcion;
    }

    public void setCat_descripcion(String cat_descripcion) {
        this.cat_descripcion = cat_descripcion;
    }

    public boolean Registrar() {
        PreparedStatement ps = null;
        Connection con = conexion.getConexion();
        
        String sql = "INSERT INTO categoriaanuncio (cat_nombre, cat_descripcion, cat_id) VALUES (?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getCat_nombre());
            ps.setString(2, this.getCat_descripcion());
            ps.setInt(3, 1);
            ps.execute();
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);

            }
        }
    }

    public boolean Modificar(){
        PreparedStatement ps = null;
        Connection con = conexion.getConexion();
        String sql = "UPDATE categoriaanuncio SET cat_nombre = ?, cat_descripcion = ? where cat_id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getCat_nombre());
            ps.setString(2, this.getCat_descripcion());
            ps.setInt(3, this.getCat_id());
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
        String sql = "DELETE from categoriaanuncio where cat_id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.getCat_id());
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
            String query = "SELECT * FROM categoriaanuncio";            
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
    
    public boolean Existe(int cat_id){
        PreparedStatement ps = null;
        Connection con = conexion.getConexion();
        String sql = "SELECT * FROM categoriaanuncio where cat_id = ?";
        ResultSet resultado = null;
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, cat_id);
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
