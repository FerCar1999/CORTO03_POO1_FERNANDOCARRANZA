/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import lombok.Data;

/**
 *
 * @author Fernando Carranza
 */
public class Prestamos {
    private int codi_pres;
    private Libros codi_libr;
    private Usuarios codi_usua;
    private String fech_pres;
    private String fech_devo;

    public Prestamos() {
    }

    public Prestamos(int codi_pres, Libros codi_libr, Usuarios codi_usua, String fech_pres, String fech_devo) {
        this.codi_pres = codi_pres;
        this.codi_libr = codi_libr;
        this.codi_usua = codi_usua;
        this.fech_pres = fech_pres;
        this.fech_devo = fech_devo;
    }

    public int getCodi_pres() {
        return codi_pres;
    }

    public void setCodi_pres(int codi_pres) {
        this.codi_pres = codi_pres;
    }

    public Libros getCodi_libr() {
        return codi_libr;
    }

    public void setCodi_libr(Libros codi_libr) {
        this.codi_libr = codi_libr;
    }

    public Usuarios getCodi_usua() {
        return codi_usua;
    }

    public void setCodi_usua(Usuarios codi_usua) {
        this.codi_usua = codi_usua;
    }

    public String getFech_pres() {
        return fech_pres;
    }

    public void setFech_pres(String fech_pres) {
        this.fech_pres = fech_pres;
    }

    public String getFech_devo() {
        return fech_devo;
    }

    public void setFech_devo(String fech_devo) {
        this.fech_devo = fech_devo;
    }
    
}
