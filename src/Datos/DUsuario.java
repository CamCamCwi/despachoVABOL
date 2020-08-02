/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Gers
 */
public class DUsuario {

    private int id;
    private String Usuario;
    private String contraseña;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = getMD5(contraseña);
    }

    private Connection abrirConexion() {
        DConexion con = new DConexion();
        Connection DBC = con.getConexion();
        return DBC;
    }

    public boolean registrar() {

        PreparedStatement ps = null;
        Connection con = abrirConexion();

        String sql = "INSERT INTO usuario (usu_email,usu_contraseña) VALUES (?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getUsuario());
            ps.setString(2, this.getContraseña());
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

    public boolean modificarContraseña() {

        PreparedStatement ps = null;
        Connection con = abrirConexion();

        String sql = "UPDATE usuario SET usu_contraseña=? WHERE usu_email=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getContraseña());
            ps.setString(2, this.getUsuario());
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

    public boolean eliminar() {
        PreparedStatement ps = null;
        Connection con = abrirConexion();

        String sql = "DELETE FROM usuario WHERE usu_email = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getUsuario());
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

    public boolean Existe() {
        PreparedStatement ps = null;
        Connection con = abrirConexion();
        String sql = "SELECT * FROM usuario where usu_email = ? and usu_contraseña = ?";
        ResultSet resultado = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getUsuario());
            ps.setString(2, this.getContraseña());
            resultado = ps.executeQuery();
            if (resultado.next()) {
                return true;
            } else {
                return false;
            }
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
    public boolean ExisteBYid(int ident) {
        this.setId(ident);
        PreparedStatement ps = null;
        Connection con = abrirConexion();
        String sql = "SELECT * FROM usuario where usu_id = ? ";
        ResultSet resultado = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, ident);
            resultado = ps.executeQuery();
            if (resultado.next()) {
                return true;
            } else {
                return false;
            }
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

    public int ObtenerID() {
        PreparedStatement ps = null;
        Connection con = abrirConexion();

        String sql = "SELECT * FROM usuario WHERE usu_email = ? and usu_contraseña = ?";
        ResultSet resultado = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getUsuario());
            ps.setString(2, this.getContraseña());
            resultado = ps.executeQuery();
            resultado.next();
            return resultado.getInt("usu_id");

        } catch (Exception e) {
        }
        return 0;
    }

    public  String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
