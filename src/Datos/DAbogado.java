
package Datos;

import java.sql.*;


/**
 *
 * @author Gers
 */
public class DAbogado {

    private int ci;
    private String apellidoP;
    private String apellidoM;
    private String nombre;
    private Date fNacimiento;
    private String genero;
    private String especialidad;
    private int celular;
    private int nroColAbogado;
    private int nroMinJusticia;
    private int numRegCorte;
    private int fk_abg_usuario;

    public int getFk_abg_usuario() {
        return fk_abg_usuario;
    }

    public void setFk_abg_usuario(int fk_abg_usuario) {
        this.fk_abg_usuario = fk_abg_usuario;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getfNacimiento() {
        return fNacimiento;
    }

    public void setfNacimiento(Date fNacimiento) {
        this.fNacimiento = fNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public int getNroColAbogado() {
        return nroColAbogado;
    }

    public void setNroColAbogado(int nroColAbogado) {
        this.nroColAbogado = nroColAbogado;
    }

    public int getNroMinJusticia() {
        return nroMinJusticia;
    }

    public void setNroMinJusticia(int nroMinJusticia) {
        this.nroMinJusticia = nroMinJusticia;
    }

    public int getNumRegCorte() {
        return numRegCorte;
    }

    public void setNumRegCorte(int numRegCorte) {
        this.numRegCorte = numRegCorte;
    }
     private Connection abrirConexion(){
        DConexion con = new DConexion();
        Connection DBC= con.getConexion();
        return DBC;
    }

    public boolean Registrar() {
        PreparedStatement ps = null;
        Connection con = abrirConexion();
        String sql = "INSERT INTO abogado (abg_ci,abg_nombre,abg_apellidop,abg_apellidom,abg_especialidad,abg_celular,abg_fnacimiento,abg_genero,abg_nrocolabogados,abg_nrominjusticia,abg_numregcorte,abg_usuario) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, this.getCi());
            ps.setString(2, this.getNombre());
            ps.setString(3, this.getApellidoP());
            ps.setString(4, this.getApellidoM());
            ps.setString(5, this.getEspecialidad());
            ps.setInt(6, this.getCelular());
            ps.setDate(7, this.getfNacimiento());
            ps.setString(8, this.getGenero());
            ps.setInt(9, this.getNroColAbogado());
            ps.setInt(10, this.getNroMinJusticia());
            ps.setInt(11, this.getNumRegCorte());
            ps.setInt(12, this.getFk_abg_usuario());
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
        Connection con = abrirConexion();
        String sql = "UPDATE abogado SET abg_nombre=?,abg_apellidop=?,abg_apellidom=?,abg_especialidad=?,abg_celular=?,abg_fnacimiento=?,abg_genero=?,abg_nrocolabogados=?,abg_nrominjusticia=?,abg_numregcorte=? WHERE abg_ci=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getNombre());
            ps.setString(2, this.getApellidoP());
            ps.setString(3, this.getApellidoM());
            ps.setString(4, this.getEspecialidad());
            ps.setInt(5, this.getCelular());
            ps.setDate(6, this.getfNacimiento());
            ps.setString(7, this.getGenero());
            ps.setInt(8, this.getNroColAbogado());
            ps.setInt(9, this.getNroMinJusticia());
            ps.setInt(10, this.getNumRegCorte());           
            ps.setInt(11, this.getCi());
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
        Connection con = abrirConexion();
        String sql = "DELETE FROM abogado WHERE abg_ci=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.getCi());
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

    public String Listar(String mensaje) {
        Statement Consulta;
        ResultSet resultado = null;
        String imprimir = "Content-Type: text/html; charset=\"UTF-8\"\n" +
"\n" +
"<h3>"+ mensaje +"</h3>\n"+
"\n"+
"<h1>Listado de Abogados</h1>"+
"<table style=\"border-collapse: collapse; width: 100%; border: 2px solid black;\">\n" +
"\n" +
"  <tr>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">CI</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">NOMBRE</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">APELLIDO PATERNO</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">APELLIDO MATERNO</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">ESPECIALIDAD</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">CELULAR</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">FECHA DE NACIMIENTO</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">GENERO</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">NR EN COLEGIO DE ABOGADOS</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">NRO MINISTERIO DE JUSTICIA</th>\n" +
"\n" +                
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">NRO DE REGISTRO EN LA CORTE</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">ID USUARIO</th>\n" +
"\n" +                
"  </tr>\n" +
"\n";
        try {
            String query = "SELECT *"
                    + "FROM abogado";
            Connection con = abrirConexion();
            Consulta = (Statement) con.createStatement();
            resultado = Consulta.executeQuery(query);
            ResultSetMetaData rsMd = resultado.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            while (resultado.next()) {
                imprimir= imprimir+
"  <tr>\n" +
"\n";                        
                for (int i = 0; i < cantidadColumnas; i++) {
                    imprimir= imprimir+
"    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">" + resultado.getString(i+1) + "</td>\n" +
"\n";                    
                    
                }
                imprimir=imprimir+
"  </tr>\n" +
"\n" ;                        
            }
            imprimir=imprimir+
"\n" +
"</table>";                    
            Consulta.close();

            con.close();

        } catch (Exception e) {
            System.out.println("no se pudo listar los datos");
        }
        return imprimir;
    }
    public boolean Existe(int ci){
        setCi(ci);
        PreparedStatement ps = null;
        Connection con = abrirConexion();
        String sql = "SELECT * FROM abogado where abg_ci = ?";
        ResultSet resultado = null;
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.getCi());
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
    public String find(int ci){
        PreparedStatement ps = null;
        Connection con = abrirConexion();
        this.setCi(ci);
        String sql = "SELECT * FROM Abogado WHERE abg_ci = ?";
        ResultSet resultado = null;
        String imprimir = "Content-Type: text/html; charset=\"UTF-8\"\n" +
"\n" +
"<h3></h3>\n"+
"\n"+
"<h1>Datos del Abogado</h1>"+
"<table style=\"border-collapse: collapse; width: 100%; border: 2px solid black;\">\n" +
"\n" +
"  <tr>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">CI</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">NOMBRE</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">APELLIDO PATERNO</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">APELLIDO MATERNO</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">ESPECIALIDAD</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">CELULAR</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">FECHA DE NACIMIENTO</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">GENERO</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">NR EN COLEGIO DE ABOGADOS</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">NRO MINISTERIO DE JUSTICIA</th>\n" +
"\n" +                
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">NRO DE REGISTRO EN LA CORTE</th>\n" +
"\n" +
"    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">ID USUARIO</th>\n" +
"\n" +                
"  </tr>\n" +
"\n";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.getCi());            
            resultado = ps.executeQuery();
            ResultSetMetaData rsMd = resultado.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            while (resultado.next()) {
                imprimir= imprimir+
"  <tr>\n" +
"\n";                        
                for (int i = 0; i < cantidadColumnas; i++) {
                    imprimir= imprimir+
"    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">" + resultado.getString(i+1) + "</td>\n" +
"\n";                    
                    
                }
                imprimir=imprimir+
"  </tr>\n" +
"\n" ;                        
            }
            imprimir=imprimir+
"\n" +
"</table>";  
            ps.close();

            con.close();
            return imprimir;

        } catch (Exception e) {
        }
        return "No se pudo encontrar el Abogado";
    }
     public String ObtenerMailbyCI(){
        PreparedStatement ps = null;
        Connection con = abrirConexion();

        String sql = "SELECT usu_email FROM usuario,abogado WHERE abg_usuario = usu_id and abg_ci = ?";
        ResultSet resultado = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.ci);            
            resultado = ps.executeQuery();
            resultado.next();
            return resultado.getString("usu_email");
            
        } catch (Exception e) {
        }
        return "-1";
        
    }
}

