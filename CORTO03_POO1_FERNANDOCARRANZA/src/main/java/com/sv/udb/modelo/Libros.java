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
public class Libros {
    private int codi_libr;
    private String nomb_libr;
	private String auto_libr;
	private String gene_libr;
	private int anio_libr;
	private int esta_libr;

    public Libros() {
    }

    public Libros(int codi_libr, String nomb_libr, String auto_libr, String gene_libr, int anio_libr, int esta_libr) {
        this.codi_libr = codi_libr;
        this.nomb_libr = nomb_libr;
        this.auto_libr = auto_libr;
        this.gene_libr = gene_libr;
        this.anio_libr = anio_libr;
        this.esta_libr = esta_libr;
    }

    public int getCodi_libr() {
        return codi_libr;
    }

    public void setCodi_libr(int codi_libr) {
        this.codi_libr = codi_libr;
    }

    public String getNomb_libr() {
        return nomb_libr;
    }

    public void setNomb_libr(String nomb_libr) {
        this.nomb_libr = nomb_libr;
    }

    public String getAuto_libr() {
        return auto_libr;
    }

    public void setAuto_libr(String auto_libr) {
        this.auto_libr = auto_libr;
    }

    public String getGene_libr() {
        return gene_libr;
    }

    public void setGene_libr(String gene_libr) {
        this.gene_libr = gene_libr;
    }

    public int getAnio_libr() {
        return anio_libr;
    }

    public void setAnio_libr(int anio_libr) {
        this.anio_libr = anio_libr;
    }

    public int getEsta_libr() {
        return esta_libr;
    }

    public void setEsta_libr(int esta_libr) {
        this.esta_libr = esta_libr;
    }

    @Override
    public String toString() {
        return  nomb_libr ;
    }

    
        
}
