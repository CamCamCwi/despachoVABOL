package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DCategoriaDoc {
    private int catDoc_id;
    private String catDoc_nombre;
    private String catDoc_descripcion;
    
    private DConexion conexion;

    public DCategoriaDoc() {
        conexion = new DConexion();
    }

    public int getCatDoc_id() {
        return catDoc_id;
    }

    public void setCatDoc_id(int catDoc_id) {
        this.catDoc_id = catDoc_id;
    }

    public String getCatDoc_nombre() {
        return catDoc_nombre;
    }

    public void setCatDoc_nombre(String catDoc_nombre) {
        this.catDoc_nombre = catDoc_nombre;
    }

    public String getCatDoc_descripcion() {
        return catDoc_descripcion;
    }

    public void setCatDoc_descripcion(String catDoc_descripcion) {
        this.catDoc_descripcion = catDoc_descripcion;
    }
    
    public boolean Registrar(){
        PreparedStatement ps = null; 
        Connection con = conexion.getConexion();
        String sql = "INSERT INTO categoriaDoc(catDoc_nombre, catDoc_descripcion) VALUES (?,?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getCatDoc_nombre());
            ps.setString(2, this.getCatDoc_descripcion());
            ps.execute();
            //System.out.println("Categoria de docuemento registrada");
            return true;
        }catch(SQLException e){
            //System.err.println(e);
            //System.out.println("Categoria de docuemento no registrado");
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
        String sql = "UPDATE categoriaDoc SET catDoc_nombre = ?, catDoc_descripcion = ? where catDoc_id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getCatDoc_nombre());
            ps.setString(2, this.getCatDoc_descripcion());
            ps.setInt(3, this.getCatDoc_id());
            ps.execute();
            //System.out.println("Categoria de docuemento modificado");
            return true;
        }catch(SQLException e){
            //System.out.println("Categoria de docuemento no modificado");
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
        String sql = "DELETE from categoriaDoc where catDoc_id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.getCatDoc_id());
            ps.execute();
            //System.out.println("Categoria de docuemento eliminado");
            return true;
        }catch(SQLException e){
            //System.err.println(e);
            //System.out.println("Categoria de docuemento no eliminado");
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
            String query = "SELECT * FROM categoriaDoc";            
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
    
    public boolean Existe(int catDoc_id){
        PreparedStatement ps = null;
        Connection con = conexion.getConexion();
        String sql = "SELECT * FROM categoriaDoc where catDoc_id = ?";
        ResultSet resultado = null;
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, catDoc_id);
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
