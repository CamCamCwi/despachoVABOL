
package Datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

public class DDocumento {
    private int doc_id;
    private String doc_titulo;
    private String doc_descripcion;
    private Date doc_fechasubida;
    private Time doc_horasubida;
    private String doc_url;
    private String doc_cliente;
    private int doc_abogado;
    private int doc_categoriadoc;
    private int doc_idmail;
    
    private DConexion conexion;

    public DDocumento() {
        conexion = new DConexion();
    }

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }

    public String getDoc_titulo() {
        return doc_titulo;
    }

    public void setDoc_titulo(String doc_titulo) {
        this.doc_titulo = doc_titulo;
    }

    public String getDoc_descripcion() {
        return doc_descripcion;
    }

    public void setDoc_descripcion(String doc_descripcion) {
        this.doc_descripcion = doc_descripcion;
    }

    public Date getDoc_fechasubida() {
        return doc_fechasubida;
    }

    public void setDoc_fechasubida(Date doc_fechasubida) {
        this.doc_fechasubida = doc_fechasubida;
    }

    public Time getDoc_horasubida() {
        return doc_horasubida;
    }

    public void setDoc_horasubida(Time doc_horasubida) {
        this.doc_horasubida = doc_horasubida;
    }

    public String getDoc_url() {
        return doc_url;
    }

    public void setDoc_url(String doc_url) {
        this.doc_url = doc_url;
    }

    public String getDoc_cliente() {
        return doc_cliente;
    }

    public void setDoc_cliente(String doc_cliente) {
        this.doc_cliente = doc_cliente;
    }

    public int getDoc_abogado() {
        return doc_abogado;
    }

    public void setDoc_abogado(int doc_abogado) {
        this.doc_abogado = doc_abogado;
    }

    public int getDoc_categoriadoc() {
        return doc_categoriadoc;
    }

    public void setDoc_categoriadoc(int doc_categoriadoc) {
        this.doc_categoriadoc = doc_categoriadoc;
    }

    public int getDoc_idmail() {
        return doc_idmail;
    }

    public void setDoc_idmail(int doc_idmail) {
        this.doc_idmail = doc_idmail;
    }
    /*
    int doc_id,String doc_titulo,String doc_descripcion,Date doc_fechasubida,Time doc_horasubida,String doc_url,String doc_cliente,int doc_abogado,int doc_categoriadoc,int doc_idmail
    doc_id,doc_titulo,doc_descripcion,doc_fechasubida,doc_horasubida,doc_url,doc_cliente,doc_abogado,doc_categoriadoc,doc_idmail
    */
    public boolean Registrar() {
        PreparedStatement ps = null;
        Connection con = conexion.getConexion();
        
        String sql = "INSERT INTO documento (doc_titulo,doc_descripcion,doc_fechasubida,doc_horasubida,doc_url,doc_cliente,doc_abogado,doc_categoriadoc,doc_idmail) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getDoc_titulo());
            ps.setString(2, this.getDoc_descripcion());
            ps.setDate(3, this.getDoc_fechasubida());
            ps.setTime(4, this.getDoc_horasubida());
            ps.setString(5, this.getDoc_url());
            ps.setString(6, this.getDoc_cliente());
            ps.setInt(7, this.getDoc_abogado());
            ps.setInt(8, this.getDoc_categoriadoc());
            ps.setInt(9, this.getDoc_idmail());
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
        
        String sql = "UPDATE documento SET doc_titulo=?,doc_descripcion=?,doc_fechasubida=?,doc_horasubida=?,doc_url=?,doc_cliente=?,doc_abogado=?,doc_categoriadoc=?,doc_idmail=? where doc_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getDoc_titulo());
            ps.setString(2, this.getDoc_descripcion());
            ps.setDate(3, this.getDoc_fechasubida());
            ps.setTime(4, this.getDoc_horasubida());
            ps.setString(5, this.getDoc_url());
            ps.setString(6, this.getDoc_cliente());
            ps.setInt(7, this.getDoc_abogado());
            ps.setInt(8, this.getDoc_categoriadoc());
            ps.setInt(9, this.getDoc_idmail());
            ps.setInt(10, this.getDoc_id());
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
    
    public boolean Eliminar() {
        PreparedStatement ps = null;
        Connection con = conexion.getConexion();
        String sql = "DELETE from documento where doc_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.getDoc_id());
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
    
    public String Listar() {
        String imprimir = "";
        Statement Consulta;
        ResultSet resultado = null;
        try {
            String query = "SELECT * FROM documento";
            Connection con = conexion.getConexion();
            Consulta = (Statement) con.createStatement();
            resultado = Consulta.executeQuery(query);
            ResultSetMetaData rsMd = resultado.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            while (resultado.next()) {
                for (int i = 0; i < cantidadColumnas; i++) {
                    imprimir = imprimir + resultado.getString(i + 1) + " ";
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
    
    public boolean Existe(int doc_id){
        PreparedStatement ps = null;
        Connection con = conexion.getConexion();
        String sql = "SELECT * FROM documento where doc_id = ?";
        ResultSet resultado = null;
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, doc_id);
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
    
    public int getDocPorTitulo(String doc_titulo){
        PreparedStatement ps = null;
        int idMail = -1;
        Connection con = conexion.getConexion();
        String sql = "SELECT doc_idmail FROM documento where doc_titulo = ?";
        ResultSet resultado = null;
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, doc_titulo);
            resultado = ps.executeQuery();
            if(resultado.next()) {
                idMail = Integer.valueOf(resultado.getString(1));
            }
        }catch(SQLException e){
            idMail = -1;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.err.println(e);
            }
        } 
        return idMail;
    }
}
