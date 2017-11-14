/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bennu.entidad.auxiliar;

import com.bennu.entidad.EstadoRegion;
import com.bennu.entidad.Mensaje;
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
    private long numeroMensajes;
    private long idCat;
    private long idSubCat;


    

    // Sin este constructor vacio el servicio REST genera un error.
     public EstadoRegionSimple(){
    }

    public EstadoRegionSimple(EstadoRegion estadoRegion){
        this(estadoRegion, 0, 0);
    }
    
    public EstadoRegionSimple(EstadoRegion estadoRegion, long idCat, long idSubCat) {
        this.idCat = idCat;
        this.idSubCat = idSubCat;
        this.setIdEstadoRegion(estadoRegion.getIdEstadoRegion());
        this.setNombre(estadoRegion.getNombre());
        this.setPoblacionList(estadoRegion.getPoblacionList());
        
        List<Mensaje> listaMensajes = new ArrayList<>();
        
        estadoRegion.getPoblacionList().
                forEach(poblacion -> poblacion.getUsuarioList().forEach(usuario -> usuario.getMensajeList().forEach(mensaje -> listaMensajes.add(mensaje) ) ));
        
        if (idCat > 0 && idSubCat == 0) {
            this.numeroMensajes = listaMensajes.stream().filter(mensaje -> mensaje.getSubCategoria().getCategoria().getIdCategoria() == idCat).count();
            System.out.println("IdCategoria: " + idCat + ", Mensajes de estado " + this.idEstadoRegion + ": " + this.numeroMensajes);
        } else if (idSubCat > 0) {
            
            this.numeroMensajes = listaMensajes.stream().filter(mensaje -> mensaje.getSubCategoria().getIdSubCategoria() == idSubCat).count();
            
        } else {
         
            this.numeroMensajes = listaMensajes.stream().count();
                  
        }

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
        poblacionLista.forEach(elemento -> lista.add( new PoblacionSimple(elemento, this.idCat, this.idSubCat) ));
        this.poblacionList = lista;
    }
    
    public long getNumeroMensajes() {
        return numeroMensajes;
    }

    public void setNumeroMensajes(long numeroMensajes) {
        this.numeroMensajes = numeroMensajes;
    }
    
}
