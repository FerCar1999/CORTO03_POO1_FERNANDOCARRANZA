/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.vistas;


import com.sv.udb.recursos.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Fernando Carranza
 */
@WebServlet(name = "RepoServ", urlPatterns = {"/RepoServ"})
public class RepoServ extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String salida;
        ServletOutputStream out = response.getOutputStream();
        try {
            Connection cn = new Conexion().getConn();
            Map params = request.getParameterMap();
            for (Object temp : params.entrySet()) {
                System.err.println("Params: " + temp);
            }
            Map parametros = null;
            String reporte = "librosPrestados.jasper";
            byte[] bytes = null;
            String urlreporte = getServletContext().getRealPath("/reportes/" + reporte);
            try {
                bytes = JasperRunManager.runReportToPdf(urlreporte, parametros, cn);
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "inline; filename=permiso.pdf");
                response.setContentLength(bytes.length);
                out.write(bytes, 0, bytes.length);
                out.flush();
                out.close();
            } catch (Exception e) {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWrites = new PrintWriter(stringWriter);
                e.printStackTrace(printWrites);
                response.setContentType("text/plain");
                response.getOutputStream().print(stringWriter.toString());
            }
            finally {
                if (!cn.isClosed()) {
                    cn.close();
                }
            }
        } catch (Exception e) {
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
