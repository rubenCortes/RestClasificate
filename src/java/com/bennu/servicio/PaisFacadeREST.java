/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bennu.servicio;

import com.bennu.entidad.Pais;
import com.bennu.entidad.auxiliar.PaisSimple;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author administrador
 */
@Stateless
@Path("pais")
public class PaisFacadeREST extends AbstractFacade<Pais> {

    @PersistenceContext(unitName = "RestClasificatePU")
    private EntityManager em;

    public PaisFacadeREST() {
        super(Pais.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public Response crear(Pais entity) {
        String nombrePais = entity.getNombre();
        ResponseBuilder respuesta;
        if (this.existePais(nombrePais)) {
            String mensaje = "Pais : " + nombrePais + " existe";
            respuesta = Response.status(Status.CONFLICT).entity(mensaje);
        } else {
            super.create(entity);
            List<Pais> resultado = this.buscarPais(nombrePais);
            
            if (resultado.size() > 0) {
                String mensaje = "Pais: " + nombrePais + " creado satisfactoriamente.";
                respuesta = Response.status(Status.CREATED).entity(mensaje);
            } else {
                String mensaje = "Pais: "+ nombrePais + " no encontrado, falla al guardar";
                respuesta = Response.status(Status.NOT_FOUND).entity(mensaje);
            }

        }
        return respuesta.build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Pais entity) {
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
    public Pais find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Pais> findAll() {
        return super.findAll();
    }
    
    @GET
    @Path("simple")
    @Produces({MediaType.APPLICATION_JSON})
    public List<PaisSimple> getPaisListSimple() {
        
        List<PaisSimple> listaSimple = new ArrayList<>();
        
        this.findAll().forEach(pais -> listaSimple.add(new PaisSimple(pais)));
        
        return listaSimple;
    }
    
    @GET
    @Path("nombre/{nombre}")
    //@Produces({MediaType.APPLICATION_JSON})
    public Response getPaisPorNombre(@PathParam("nombre") String nombre) {
        
        try {
            
            List<Pais> resultado = this.buscarPais(nombre);

            ResponseBuilder respuesta;

            if (resultado.size() > 0) {
                PaisSimple paisPorNombre = new PaisSimple(resultado.get(0));
                respuesta = Response.status(Status.OK).entity(paisPorNombre).type(MediaType.APPLICATION_JSON);
            } else {
                String mensaje = "Pais : "+ nombre +" no encontrado";
                respuesta = Response.status(Status.NOT_FOUND).entity(mensaje).type(MediaType.TEXT_PLAIN);
            }

            return respuesta.build();
        
        } catch (Exception e) {
            System.err.println("Error en getPaisPorNombre: " + e.getMessage());
        }
        
        return null;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Pais> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
    private List<Pais> buscarPais(String nombre) {
        TypedQuery<Pais> consultaPais= em.createNamedQuery("Pais.findByNombre", Pais.class);
        consultaPais.setParameter("nombre", nombre);
        return consultaPais.getResultList(); 
    }
    
    private boolean existePais(String nombre) {
        if (this.buscarPais(nombre).size() > 0) return true;
        else return false;
    }
    
}
