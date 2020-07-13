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
        String sql = "UPDATE categoriaDoc SET catDoc_nombre = ?, catDoc_descripcion = ? where catDoc_id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getCatDoc_nombre());
            ps.setString(2, this.getCatDoc_descripcion());
            ps.setInt(3, this.getCatDoc_id());
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
        String sql = "DELETE from categoriaDoc where catDoc_id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.getCatDoc_id());
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
        String tabla = "";
        Statement Consulta;
        ResultSet resultado = null; 
        tabla = "Content-Type: text/html; charset=\"UTF-8\"\n" +
"\n" +
"<table style=\"border-collapse: collapse; width: 100%; border: 2px solid black;\">\n" +
"\n" +
"  <tr>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">Hoy</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">Mañana</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">Miércoles</th>\n" +
"\n" +
"  </tr>\n" +
"\n";
        try {
            String query = "SELECT * FROM categoriaDoc ORDER BY catDoc_id";            
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
                    //tabla = tabla + "<td style = \"text-align: left; padding: 8px; border: 2px solid black;\">"+resultado.getString(i+1)+"</td>\n" + "\n";
                    tabla = tabla +
"    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Soleado</td>\n" +
"\n";
                }
                //tabla = tabla + "  </tr>\n" + "\n";
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
        System.out.println(tabla + "ESTAAAAAAAAAAAA");
        return tabla;
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
