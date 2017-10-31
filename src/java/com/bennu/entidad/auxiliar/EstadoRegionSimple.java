/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bennu.entidad.auxiliar;

import com.bennu.entidad.EstadoRegion;
import com.bennu.entidad.Poblacion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author administrador
 */
public final class EstadoRegionSimple {
    private long idEstadoRegion;
    private String nombre;
    private List<PoblacionSimple> poblacionList;

    // Sin este constructor vacio el servicio REST genera un error.
     public EstadoRegionSimple(){
    }

    public EstadoRegionSimple(EstadoRegion estadoRegion){
        this.setIdEstadoRegion(estadoRegion.getIdEstadoRegion());
        this.setNombre(estadoRegion.getNombre());
        this.setPoblacionList(estadoRegion.getPoblacionList());
    }
    
    
    public long getIdEstadoRegion() {
        return idEstadoRegion;
    }

    public void setIdEstadoRegion(long idEstadoRegion) {
        this.idEstadoRegion = idEstadoRegion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<PoblacionSimple> getPoblacionList() {
        return poblacionList;
    }

    
    public void setPoblacionList(List<Poblacion> poblacionLista) {
        List<PoblacionSimple> lista = new ArrayList<>();
        poblacionLista.forEach(elemento -> lista.add( new PoblacionSimple(elemento) ));
        this.poblacionList = lista;
    }
    
    
}
