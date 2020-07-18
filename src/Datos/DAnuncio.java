package Datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

public class DAnuncio {

    private int anu_id;
    private String anu_titulo;
    private String anu_contenido;
    private int anu_estado;
    private Date anu_fechapub;
    private Time anu_horapub;
    private int anu_abogado;
    private int anu_categoria;

    private DConexion conexion;

    public DAnuncio() {
        conexion = new DConexion();
    }

    public int getAnu_id() {
        return anu_id;
    }

    public void setAnu_id(int anu_id) {
        this.anu_id = anu_id;
    }

    public String getAnu_titulo() {
        return anu_titulo;
    }

    public void setAnu_titulo(String anu_titulo) {
        this.anu_titulo = anu_titulo;
    }

    public String getAnu_contenido() {
        return anu_contenido;
    }

    public void setAnu_contenido(String anu_contenido) {
        this.anu_contenido = anu_contenido;
    }

    public int getAnu_estado() {
        return anu_estado;
    }

    public void setAnu_estado(int anu_estado) {
        this.anu_estado = anu_estado;
    }

    public Date getAnu_fechapub() {
        return anu_fechapub;
    }

    public void setAnu_fechapub(Date anu_fechapub) {
        this.anu_fechapub = anu_fechapub;
    }

    public Time getAnu_horapub() {
        return anu_horapub;
    }

    public void setAnu_horapub(Time anu_horapub) {
        this.anu_horapub = anu_horapub;
    }

    public int getAnu_abogado() {
        return anu_abogado;
    }

    public void setAnu_abogado(int anu_abogado) {
        this.anu_abogado = anu_abogado;
    }

    public int getAnu_categoria() {
        return anu_categoria;
    }

    public void setAnu_categoria(int anu_categoria) {
        this.anu_categoria = anu_categoria;
    }

    public boolean Registrar() {
        PreparedStatement ps = null;
        Connection con = conexion.getConexion();
        
        String sql = "INSERT INTO anuncio(anu_titulo, anu_contenido, anu_estado, anu_fechapub, anu_horapub, anu_abogado, anu_categoria) VALUES (?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getAnu_titulo());
            ps.setString(2, this.getAnu_contenido());
            ps.setInt(3, this.getAnu_estado());
            ps.setDate(4, this.getAnu_fechapub());
            ps.setTime(5, this.getAnu_horapub());
            ps.setInt(6, this.getAnu_abogado());
            ps.setInt(7, this.getAnu_categoria());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean Modificar() {
        PreparedStatement ps = null;
        Connection con = conexion.getConexion();
        String sql = "UPDATE anuncio SET anu_titulo=?, anu_contenido=?, anu_estado=?, anu_fechapub=?, anu_horapub=?, anu_abogado=?, anu_categoria=? where anu_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getAnu_titulo());
            ps.setString(2, this.getAnu_contenido());
            ps.setInt(3, this.getAnu_estado());
            ps.setDate(4, this.getAnu_fechapub());
            ps.setTime(5, this.getAnu_horapub());
            ps.setInt(6, this.getAnu_abogado());
            ps.setInt(7, this.getAnu_categoria());
            ps.setInt(8, this.getAnu_id());
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

    public boolean Eliminar() {
        PreparedStatement ps = null;
        Connection con = conexion.getConexion();
        String sql = "DELETE from anuncio where anu_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.anu_id);
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

    public String Listar(String mensaje) {
        String tabla = "";
        Statement Consulta;
        ResultSet resultado = null;
        tabla = "Content-Type: text/html; charset=\"UTF-8\"\n" +
"\n" +
"<h3>"+ mensaje +"</h3>\n"+
"\n"+
"<h1>Anuncios: </h1>"+
"<table style=\"border-collapse: collapse; width: 100%; border: 2px solid black;\">\n" +
"\n" +
"  <tr>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">ID</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">Titulo</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">Contenido</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">Estado</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">Fecha</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">Hora</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">ID Abogado</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">Categoria</th>\n" +
"\n" +                
"  </tr>\n" +
"\n";    
        try {
            String query = "SELECT * FROM anuncio ORDER BY anu_id";
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

    public boolean Existe(int anu_id){
        PreparedStatement ps = null;
        Connection con = conexion.getConexion();
        String sql = "SELECT * FROM anuncio where anu_id = ?";
        ResultSet resultado = null;
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, anu_id);
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
