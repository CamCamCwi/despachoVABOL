
package Datos;
import java.sql.*;


public class DCliente {
    private int    nit;
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

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
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
    public void Registrar() {
        PreparedStatement ps = null;
        Connection con = abrirConexion();
        String sql = "INSERT INTO cliente (cl_nit,cl_ciudad,cl_descripcion,cl_direccion,cl_nrepresentante,cl_paginaweb,cl_pais,cl_razonsocial,cl_rubro,cl_telefono,cl_usuario) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, this.getNit());
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

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

    }

    public void Modificar() {
        PreparedStatement ps = null;
        Connection con = abrirConexion();
        String sql = "UPDATE cliente SET cl_ciudad = ?,cl_descripcion = ?,cl_direccion = ?,cl_nrepresentante = ?,cl_paginaweb = ?,cl_pais = ?,cl_razonsocial = ?,cl_rubro = ?,cl_telefono = ?,cl_usuario= ? WHERE cl_nit = ? ";
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
            
            ps.setInt(11, this.getNit());
            ps.execute();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

    }

    public void Eliminar() {
        PreparedStatement ps = null;
        Connection con = abrirConexion();
        String sql = "DELETE FROM cliente WHERE cl_nit = ? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.getNit());
            ps.execute();

        } catch (SQLException e) {
            System.err.println(e);
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
    
    
    
}
