/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bennu.entidad.auxiliar;

import com.bennu.entidad.Categoria;
import com.bennu.entidad.SubCategoria;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author administrador
 */
public final class CategoriaSimple {
    
    private long idCategoria;
    private String nombre;
    private List<SubCategoriaSimple> subCategoriaLista;
    private int numeroMensajes;



    public CategoriaSimple() {
    }

    public CategoriaSimple(Categoria categoria){
        this.setIdCategoria(categoria.getIdCategoria());
        this.setNombre(categoria.getNombre());
        this.setSubCategoriaLista(categoria.getSubCategoriaList());
        List<Integer> contenido = new ArrayList<>();
        categoria.getSubCategoriaList().forEach(subCat -> contenido.add(subCat.getMensajeList().size()));
        this.numeroMensajes = contenido.stream().mapToInt(Integer::intValue).sum();


    }
    
    
    
    /**
     * Get the value of idCategoria
     *
     * @return the value of idCategoria
     */
    public long getIdCategoria() {
        return idCategoria;
    }

    /**
     * Set the value of idCategoria
     *
     * @param idCategoria new value of idCategoria
     */
    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }


    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<SubCategoriaSimple> getSubCategoriaLista() {
        return subCategoriaLista;
    }
    
    public void setSubCategoriaLista(List<SubCategoria> subCategoriaLista) {
        List<SubCategoriaSimple> lista = new ArrayList<>();
        subCategoriaLista.forEach(elemento -> lista.add( new SubCategoriaSimple(elemento) ));
        this.subCategoriaLista = lista;
    }
    
    public int getNumeroMensajes() {
        return numeroMensajes;
    }

    public void setNumeroMensajes(int numeroMensajes) {
        this.numeroMensajes = numeroMensajes;
    }
    
}
