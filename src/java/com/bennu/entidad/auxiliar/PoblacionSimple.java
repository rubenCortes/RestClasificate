/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bennu.entidad.auxiliar;

import com.bennu.entidad.Mensaje;
import com.bennu.entidad.Poblacion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author administrador
 */
public final class PoblacionSimple {
    private long idPoblacion;
    private String nombre;
    private long numeroMensajes;
    

    public PoblacionSimple() {
    }
    
    public PoblacionSimple( Poblacion poblacion ) {
        this(poblacion, 0, 0);
    }
    
    public PoblacionSimple( Poblacion poblacion, long idCat, long idSubCat ) {
        this.setIdPoblacion(poblacion.getIdPoblacion());
        this.setNombre(poblacion.getNombre());
        
        List<Mensaje> listaMensajes = new ArrayList<>();
        
        poblacion.getUsuarioList().forEach(usuario -> usuario.getMensajeList().forEach(mensaje -> listaMensajes.add(mensaje) ));
              
        if (idCat > 0 && idSubCat == 0) {
            this.numeroMensajes = listaMensajes.stream().filter(mensaje -> mensaje.getSubCategoria().getCategoria().getIdCategoria() == idCat).count();
        } else if (idSubCat > 0) {
            
            this.numeroMensajes = listaMensajes.stream().filter(mensaje -> mensaje.getSubCategoria().getIdSubCategoria() == idSubCat).count();
            
        } else {
         
            this.numeroMensajes = listaMensajes.stream().count();
                  
        }


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
    
    public long getNumeroMensajes() {
        return numeroMensajes;
    }

    public void setNumeroMensajes(long numeroMensajes) {
        this.numeroMensajes = numeroMensajes;
    }    
    
}
