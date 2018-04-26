/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.vistas;

import com.sv.udb.controlador.ControladorPrestamo;
import com.sv.udb.modelo.Prestamos;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fernando Carranza
 */
@WebServlet(name = "DevoServ", urlPatterns = {"/DevoServ"})
public class DevoServ extends HttpServlet {

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
                boolean esValido = request.getMethod().equals("POST");
        String mens = "";
        boolean respDevo = false;
        if (!esValido) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            String CRUD = request.getParameter("btonDese");
            if (CRUD.equals("Devolver")) {
                SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date now = new Date();
                String strDate = sdfDate.format(now);
                if (new ControladorPrestamo().devolverLibros(Integer.parseInt(request.getParameter("codiDevo")), strDate)) {
                    mens = "Datos guardados";
                    respDevo=true;
                } else {
                    mens = "Error al guardar";
                }
            }
            else if (CRUD.equals("Consultar")) {
                int codi = Integer.parseInt(request.getParameter("codiDevoRadi") == null ? "-1"
                        : request.getParameter("codiDevoRadi"));
                Prestamos obje = new ControladorPrestamo().consTodo(codi);
                if (obje != null) {
                    request.setAttribute("codiDevo", obje.getCodi_pres());
                    request.setAttribute("codiLibrDevo", obje.getCodi_libr().getCodi_libr());
                    request.setAttribute("nombLibrDevo", obje.getCodi_libr().getNomb_libr());
                    request.setAttribute("codiUsuaDevo", obje.getCodi_usua().getCodi_usua());
                    request.setAttribute("nombUsuaDevo", obje.getCodi_usua().getNomb_usua());
                    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    SimpleDateFormat sdfDateX = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    Date fechaP = null;
                    Date fechaD = null;
                    String fechaPr = null;
                    String fechaDe = null;
                    try {
                        fechaP = sdfDate.parse(obje.getFech_pres());
                        fechaD = sdfDate.parse(obje.getFech_devo());
                        fechaPr = sdfDateX.format(fechaP);
                        fechaDe = sdfDateX.format(fechaD);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    request.setAttribute("fechaPDevo", fechaPr);
                    request.setAttribute("fechaDDevo", fechaDe);
                    mens = "Información consultada";
                } else {
                    mens = "Error al consultar";
                }
            }
            request.setAttribute("mensAlerJD", mens);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
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
        processRequest(request, response);
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