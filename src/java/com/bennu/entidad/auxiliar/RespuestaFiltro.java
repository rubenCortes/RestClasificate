/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bennu.entidad.auxiliar;

/**
 *
 * @author administrador
 */
public class RespuestaFiltro {
    private long idCategoria;
    private long idSubCategoria;
    private long idPoblacion;
    private long idEstadoRegion;
    private long categoria;
    /**
     * Get the value of categoria
     *
     * @return the value of categoria
     */
    
    public RespuestaFiltro() {
    }

    public long getCategoria() {
        return categoria;
    }

    /**
     * Set the value of categoria
     *
     * @param categoria new value of categoria
     */
    public void setCategoria(long categoria) {
        this.categoria = categoria;
    }


    /**
     * Get the value of idPoblacion
     *
     * @return the value of idPoblacion
     */
    public long getIdPoblacion() {
        return idPoblacion;
    }

    /**
     * Set the value of idPoblacion
     *
     * @param idPoblacion new value of idPoblacion
     */
    public void setIdPoblacion(long idPoblacion) {
        this.idPoblacion = idPoblacion;
    }


    /**
     * Get the value of idEstadoRegion
     *
     * @return the value of idEstadoRegion
     */
    public long getIdEstadoRegion() {
        return idEstadoRegion;
    }

    /**
     * Set the value of idEstadoRegion
     *
     * @param idEstadoRegion new value of idEstadoRegion
     */
    public void setIdEstadoRegion(long idEstadoRegion) {
        this.idEstadoRegion = idEstadoRegion;
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
     * Get the value of idSubCategoria
     *
     * @return the value of idSubCategoria
     */
    public long getIdSubCategoria() {
        return idSubCategoria;
    }

    /**
     * Set the value of idSubCategoria
     *
     * @param idSubCategoria new value of idSubCategoria
     */
    public void setIdSubCategoria(long idSubCategoria) {
        this.idSubCategoria = idSubCategoria;
    }
    
}
