/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Libros;
import com.sv.udb.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernando Carranza
 */
public class ControladorLibro {
    private  Connection conn;

    public ControladorLibro() {
        this.conn = new Conexion().getConn();
    }
    public List<Libros> consTodo()
    {
        this.conn = new Conexion().getConn();
        List<Libros> resp = new ArrayList<>();
        try
        {
            PreparedStatement cmd = conn.prepareStatement("SELECT * FROM `libros`;");
           
            ResultSet rs = cmd.executeQuery();
            while(rs.next())
            {
                resp.add(new Libros(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6)));
            }
        }
        catch(SQLException ex)
        {
            System.err.println("Error al consultar Piezas: " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            }
            catch(SQLException ex)
            {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }
}
