/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import lombok.Data;
import lombok.ToString;

/**
 *
 * @author Fernando Carranza
 */
public class Usuarios {
    private int codi_usua;
    private String nomb_usua;
    private String acce_usua;
    private String cont_usua;

    public Usuarios() {
    }

    public Usuarios(int codi_usua, String nomb_usua, String acce_usua, String cont_usua) {
        this.codi_usua = codi_usua;
        this.nomb_usua = nomb_usua;
        this.acce_usua = acce_usua;
        this.cont_usua = cont_usua;
    }    

    public int getCodi_usua() {
        return codi_usua;
    }

    public void setCodi_usua(int codi_usua) {
        this.codi_usua = codi_usua;
    }

    public String getNomb_usua() {
        return nomb_usua;
    }

    public void setNomb_usua(String nomb_usua) {
        this.nomb_usua = nomb_usua;
    }

    public String getAcce_usua() {
        return acce_usua;
    }

    public void setAcce_usua(String acce_usua) {
        this.acce_usua = acce_usua;
    }

    public String getCont_usua() {
        return cont_usua;
    }

    public void setCont_usua(String cont_usua) {
        this.cont_usua = cont_usua;
    }

    @Override
    public String toString() {
        return nomb_usua ;
    }
    
}
