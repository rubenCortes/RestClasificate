/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bennu.servicio;

import com.bennu.entidad.EstadoRegion;
import com.bennu.entidad.auxiliar.EstadoRegionSimple;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author administrador
 */
@Stateless
@Path("estadoregion")
public class EstadoRegionFacadeREST extends AbstractFacade<EstadoRegion> {

    @PersistenceContext(unitName = "RestClasificatePU")
    private EntityManager em;

    public EstadoRegionFacadeREST() {
        super(EstadoRegion.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(EstadoRegion entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, EstadoRegion entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public EstadoRegion find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<EstadoRegion> findAll() {
        return super.findAll();
    }
    
    @GET
    @Path("simple")
    @Produces({MediaType.APPLICATION_JSON})
    public List<EstadoRegionSimple> getEstadoRegionListSimple() {
        
        List<EstadoRegionSimple> listaSimple = new ArrayList<>();
        
        this.findAll().forEach(estado -> listaSimple.add(new EstadoRegionSimple(estado)));
        
        return listaSimple;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<EstadoRegion> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
