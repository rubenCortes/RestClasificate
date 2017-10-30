/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bennu.servicio;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author administrador
 */
@javax.ws.rs.ApplicationPath("recursos")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.bennu.filtro.FiltroCrossOriginResourceSharingFilter.class);
        resources.add(com.bennu.servicio.CategoriaFacadeREST.class);
        resources.add(com.bennu.servicio.EstadoRegionFacadeREST.class);
        resources.add(com.bennu.servicio.ImagenFacadeREST.class);
        resources.add(com.bennu.servicio.MensajeFacadeREST.class);
        resources.add(com.bennu.servicio.PaisFacadeREST.class);
        resources.add(com.bennu.servicio.PoblacionFacadeREST.class);
        resources.add(com.bennu.servicio.SubCategoriaFacadeREST.class);
        resources.add(com.bennu.servicio.UsuarioFacadeREST.class);
    }
    
}
