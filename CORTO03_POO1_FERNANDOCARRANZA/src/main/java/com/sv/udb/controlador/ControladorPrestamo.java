/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Libros;
import com.sv.udb.modelo.Prestamos;
import com.sv.udb.modelo.Usuarios;
import java.sql.Connection;
import com.sv.udb.recursos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernando Carranza
 */
public class ControladorPrestamo {
    
    private Connection conn;

    public ControladorPrestamo() {
        this.conn = new Conexion().getConn();
    }
    public boolean prestarLibros(int codi_libr,int codi_usua,String fech_pres) {
        boolean resp = false;
        try {
            this.conn = new Conexion().getConn();
            PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO prestamos VALUES(NULL,?,?,?,NULL)");
            cmd.setInt(1, codi_libr);
            cmd.setInt(2, codi_usua);
            cmd.setString(3,fech_pres);
            cmd.executeUpdate();
            resp = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error al guardar en Prestar Libro: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }
    public boolean devolverLibros(int codi_pres,String fech_devo) {
        boolean resp = false;
        try {
            this.conn = new Conexion().getConn();
            PreparedStatement cmd = this.conn.prepareStatement("UPDATE prestamos SET fech_devo=? WHERE codi_pres=?");
            cmd.setString(1, fech_devo);
            cmd.setInt(2,codi_pres);
            cmd.executeUpdate();
            resp = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error al guardar en Devolver Libro: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }

    public List<Prestamos> consTodo() {
        this.conn = new Conexion().getConn();
        List<Prestamos> resp = new ArrayList<>();
        try {
            PreparedStatement cmd = conn.prepareStatement("SELECT p.codi_pres, l.*,u.*,fech_pres,fech_devo FROM prestamos as p INNER JOIN libros as l ON p.codi_libr=l.codi_libr INNER JOIN usuarios as u ON p.codi_usua= u.codi_usua;");

            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                resp.add(new Prestamos(rs.getInt(1), new Libros(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7)), new Usuarios(rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11)), rs.getString(12), rs.getString(13))); // <----- Hay que llenar con los objetos
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar Bodega: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }

    public Prestamos consTodo(int codi_pres) {
        this.conn = new Conexion().getConn();
        Prestamos resp = null;
        try {
            PreparedStatement cmd = conn.prepareStatement("SELECT p.codi_pres, l.*,u.*,fech_pres,fech_devo FROM prestamos as p INNER JOIN libros as l ON p.codi_libr=l.codi_libr INNER JOIN usuarios as u ON p.codi_usua= u.codi_usua WHERE p.codi_pres=?;");
            cmd.setInt(1, codi_pres);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                resp = new Prestamos(rs.getInt(1), new Libros(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7)), new Usuarios(rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11)), rs.getString(12), rs.getString(13)); // <----- Hay que llenar con los objetos
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar Prestamo: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }

}
