/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bennu.entidad.auxiliar;

import com.bennu.entidad.Poblacion;

/**
 *
 * @author administrador
 */
public final class PoblacionSimple {
    private long idPoblacion;
    private String nombre;

    public PoblacionSimple() {
    }
    
    public PoblacionSimple( Poblacion poblacion ) {
        this.setIdPoblacion(poblacion.getIdPoblacion());
        this.setNombre(poblacion.getNombre());
    }

    public long getIdPoblacion() {
        return idPoblacion;
    }

    public void setIdPoblacion(long idPoblacion) {
        this.idPoblacion = idPoblacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
