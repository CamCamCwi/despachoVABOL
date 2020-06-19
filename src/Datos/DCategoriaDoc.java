package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    
    public void Registrar(){
        PreparedStatement ps = null; 
        Connection con = conexion.getConexion();
        String sql = "INSERT INTO categoriaDoc(catDoc_nombre, catDoc_descripcion) VALUES (?,?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getCatDoc_nombre());
            ps.setString(2, this.getCatDoc_descripcion());
            ps.execute();
            System.out.println("Categoria de docuemento registrada");
        }catch(SQLException e){
            System.err.println(e);
            System.out.println("Categoria de docuemento no registrado");
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.err.println(e);
            }
        }  
    }
    
    public void Modificar(){
        PreparedStatement ps = null;
        Connection con = conexion.getConexion();
        String sql = "UPDATE categoriaDoc SET catDoc_nombre = ?, catDoc_descripcion = ? where catDoc_id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getCatDoc_nombre());
            ps.setString(2, this.getCatDoc_descripcion());
            ps.setInt(3, this.getCatDoc_id());
            ps.execute();
            System.out.println("Categoria de docuemento modificado");
        }catch(SQLException e){
            System.out.println("Categoria de docuemento no modificado");
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.err.println(e);
            }
        }  
    }
    
    public void Eliminar(){
        PreparedStatement ps = null;
        Connection con = conexion.getConexion();
        String sql = "DELETE from categoriaDoc where catDoc_id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.getCatDoc_id());
            ps.execute();
            System.out.println("Categoria de docuemento eliminado");
        }catch(SQLException e){
            System.err.println(e);
            System.out.println("Categoria de docuemento no eliminado");
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.err.println(e);
            }
        }  
    }
}
