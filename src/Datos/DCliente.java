
package Datos;
import java.sql.*;


public class DCliente {
    private String    nit;
    private String nRepresentante;
    private String razonSocial;
    private String rubro;
    private int    telefono;
    private String ciudad;
    private String país;
    private String paginaWeb;
    private String direccion;
    private String descripcion;
    private int cl_usuario;

    public int getCl_usuario() {
        return cl_usuario;
    }

    public void setCl_usuario(int cl_usuario) {
        this.cl_usuario = cl_usuario;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getnRepresentante() {
        return nRepresentante;
    }

    public void setnRepresentante(String nRepresentante) {
        this.nRepresentante = nRepresentante;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPaís() {
        return país;
    }

    public void setPaís(String país) {
        this.país = país;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    private Connection abrirConexion(){
        DConexion con = new DConexion();
        Connection DBC= con.getConexion();
        return DBC;
    }
    public boolean Registrar() {
        PreparedStatement ps = null;
        Connection con = abrirConexion();
        String sql = "INSERT INTO cliente (cl_nit,cl_ciudad,cl_descripcion,cl_direccion,cl_nrepresentante,cl_paginaweb,cl_pais,cl_razonsocial,cl_rubro,cl_telefono,cl_usuario) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, this.getNit());
            ps.setString(2, this.getCiudad());
            ps.setString(3, this.getDescripcion());
            ps.setString(4, this.getDireccion());
            ps.setString(5, this.getnRepresentante());
            ps.setString(6, this.getPaginaWeb());
            ps.setString(7, this.getPaís());
            ps.setString(8, this.getRazonSocial());
            ps.setString(9, this.getRubro());
            ps.setInt(10, this.getTelefono());
            ps.setInt(11, this.getCl_usuario());
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
        String sql = "UPDATE cliente SET cl_ciudad = ?,cl_descripcion = ?,cl_direccion = ?,cl_nrepresentante = ?,cl_paginaweb = ?,cl_pais = ?,cl_razonsocial = ?,cl_rubro = ?,cl_telefono = ? WHERE cl_nit = ? ";
        try {
            ps = con.prepareStatement(sql);
            
            ps.setString(1, this.getCiudad());
            ps.setString(2, this.getDescripcion());
            ps.setString(3, this.getDireccion());
            ps.setString(4, this.getnRepresentante());
            ps.setString(5, this.getPaginaWeb());
            ps.setString(6, this.getPaís());
            ps.setString(7, this.getRazonSocial());
            ps.setString(8, this.getRubro());
            ps.setInt(9, this.getTelefono());
            ps.setInt(10, this.getCl_usuario());                        
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
        String sql = "DELETE FROM cliente WHERE cl_nit = ? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getNit());
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

    public String Listar() {
        String imprimir = "";
        Statement Consulta;
        ResultSet resultado = null;
        try {
            String query = "SELECT *"
                    + "FROM cliente";
            Connection con = abrirConexion();
            Consulta = (Statement) con.createStatement();
            resultado = Consulta.executeQuery(query);
            ResultSetMetaData rsMd = resultado.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            while (resultado.next()) {
                for (int i = 0; i < cantidadColumnas; i++) {
                    imprimir = imprimir + resultado.getString(i + 1) + " ";
                    //datos[i] = resultado.getString(i+1);
                }
                imprimir += "\n";
            }
            Consulta.close();

            con.close();

        } catch (Exception e) {
            System.out.println("no se pudo listar los datos");
        }
        return imprimir;
    }
    public boolean Existe(String nit){
        setNit(nit);
        PreparedStatement ps = null;
        Connection con = abrirConexion();
        String sql = "SELECT * FROM cliente WHERE cl_nit= ?";
        ResultSet resultado = null;
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getNit());
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
    public String find (String nit){
        PreparedStatement ps = null;
        Connection con = abrirConexion();
        this.setNit(nit);
        String sql = "SELECT * FROM Cliente WHERE cl_nit = ?";
        ResultSet resultado = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getNit());            
            resultado = ps.executeQuery();
            resultado.next();
            return (resultado.getString("cl_nit") + " " + resultado.getString("cl_ciudad") + " " + resultado.getString("cl_descripcion")  + " " + resultado.getString("cl_direccion") + " " + resultado.getString("cl_nrepresentante") + " " + resultado.getString("cl_paginaweb") + " " + resultado.getString("cl_pais") + " " + resultado.getString("cl_razonsocial") + " " + resultado.getString("cl_rubro") + " " + String.valueOf(resultado.getInt("cl_telefono")));

        } catch (Exception e) {
        }
        return "No se pudo encontrar el Cliente";
    }
    public String ObtenerMailbyNIT(){
        PreparedStatement ps = null;
        Connection con = abrirConexion();

        String sql = "SELECT usu_email FROM usuario,cliente WHERE cl_usuario = usu_id and cl_nit = ?";
        ResultSet resultado = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, this.nit);            
            resultado = ps.executeQuery();
            resultado.next();
            return resultado.getString("usu_email");
            
        } catch (Exception e) {
        }
        return "-1";
    }
    
    
}
