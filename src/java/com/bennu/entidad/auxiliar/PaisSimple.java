/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bennu.entidad.auxiliar;

import com.bennu.entidad.EstadoRegion;
import com.bennu.entidad.Pais;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author administrador
 */
public final class PaisSimple {
    private long idPais;
    private String nombre;
    private List<EstadoRegionSimple> estadoRegionList;
    private long idCat;
    private long idSubCat;


    public PaisSimple(){
    }

    public PaisSimple(Pais pais){
        this(pais, 0, 0);
    }
    
    
    public PaisSimple(Pais pais, long idCat, long idSubCat) {
        this.idCat = idCat;
        this.idSubCat = idSubCat;
        this.setIdPais(pais.getIdPais());
        this.setNombre(pais.getNombre());
        this.setEstadoRegionList(pais.getEstadoRegionList());

    }
    
    
    public long getIdPais() {
        return idPais;
    }

    public void setIdPais(long idPais) {
        this.idPais = idPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<EstadoRegionSimple> getEstadoRegionList() {
        return estadoRegionList;
    }

    public void setEstadoRegionList(List<EstadoRegion> estadoRegionLista) {
        List<EstadoRegionSimple> lista = new ArrayList<>();
        estadoRegionLista.forEach(elemento -> lista.add( new EstadoRegionSimple(elemento, this.idCat, this.idSubCat) ));
        this.estadoRegionList = lista;
    }
    

    
}
