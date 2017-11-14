/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bennu.servicio;

import com.bennu.entidad.Mensaje;
import com.bennu.entidad.Usuario;
import com.bennu.entidad.auxiliar.RespuestaFiltro;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author administrador
 */
@Stateless
@Path("mensaje")
public class MensajeFacadeREST extends AbstractFacade<Mensaje> {

    @PersistenceContext(unitName = "RestClasificatePU")
    private EntityManager em;

    public MensajeFacadeREST() {
        super(Mensaje.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public Response crear(Mensaje entity) {
        entity.setCreacion(Date.valueOf(LocalDate.now()));
        super.create(entity);
        String mensaje = "Mensaje creado satisfactoriamente";
        ResponseBuilder respuesta = Response.status(Status.CREATED).entity(mensaje);
        return respuesta.build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Mensaje entity) {
        super.edit(entity);
        
    }

    @DELETE
    @Path("{id}")
    public Response borrar(@PathParam("id") Integer id) {
        super.remove(super.find(id));
        ResponseBuilder respuesta;
        if (super.find(id) == null) {
            String mensaje = "Mensaje borrado satisfactoriamente.";
            respuesta = Response.status(Status.OK).entity(mensaje);            
        } else {
            String mensaje = "Error al borrar el mensaje.";
            respuesta = Response.status(Status.METHOD_NOT_ALLOWED).entity(mensaje);
        }

        return respuesta.build();
    }

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Integer id) {
        
        Mensaje mensaje = super.find(id);
                    
        ResponseBuilder respuesta;

            if (mensaje != null) {
                respuesta = Response.status(Status.OK).entity(mensaje).type(MediaType.APPLICATION_JSON);
            } else {
                String texto = "Mensaje no encontrado";
                respuesta = Response.status(Status.NOT_FOUND).entity(texto).type(MediaType.TEXT_PLAIN);
            }

            return respuesta.build();
        
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Mensaje> findAll() {
        return super.findAll();
    }

    @GET
    @Path("filtro")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response mensajesFiltrados (
            @QueryParam("cat") long cat,
            @QueryParam("sub") long sub, 
            @QueryParam("est") long est, 
            @QueryParam("pob") long pob,
            @QueryParam("can") long can) {
        
        
        List<Mensaje> resultado = new ArrayList<>();
        
        if ( cat > 0 && sub == 0 && est == 0 && pob == 0 ) {
            TypedQuery<Mensaje> consulta = em.createNamedQuery("Mensaje.findByCategoria", Mensaje.class);
            consulta.setParameter("idCategoria", cat);
            resultado = consulta.getResultList();
        } else if ( cat > 0 && sub == 0 && est > 0 && pob == 0 ) {
            TypedQuery<Mensaje> consulta = em.createNamedQuery("Mensaje.findByCatEst", Mensaje.class);
            consulta.setParameter("idCategoria", cat);
            consulta.setParameter("idEstadoRegion ", est);
            resultado = consulta.getResultList();
        } else if ( cat > 0 && sub == 0 && pob > 0 ) {
            TypedQuery<Mensaje> consulta = em.createNamedQuery("Mensaje.findByCatPob", Mensaje.class);
            consulta.setParameter("idCategoria", cat);
            consulta.setParameter("idPoblacion", pob);
            resultado = consulta.getResultList();
        } else if ( sub > 0 && est == 0 && pob == 0 ) { 
            TypedQuery<Mensaje> consulta = em.createNamedQuery("Mensaje.findBySubCategoria", Mensaje.class);
            consulta.setParameter("idSubCategoria", sub); 
            resultado = consulta.getResultList();
        } else if ( sub > 0 && est > 0 && pob == 0 ) {
            TypedQuery<Mensaje> consulta = em.createNamedQuery("Mensaje.findBySubCatEst", Mensaje.class);
            consulta.setParameter("idSubCategoria", sub);
            consulta.setParameter("idEstadoRegion", est);
            resultado = consulta.getResultList();
        } else if ( sub > 0 && pob > 0 ) {
            TypedQuery<Mensaje> consulta = em.createNamedQuery("Mensaje.findBySubCatPob", Mensaje.class);
            consulta.setParameter("idSubCategoria", sub);
            consulta.setParameter("idPoblacion", pob);
            resultado = consulta.getResultList();
        } else if ( cat == 0 && sub == 0 && pob > 0 ) {
            TypedQuery<Mensaje> consulta = em.createNamedQuery("Mensaje.findByPoblacion", Mensaje.class);
            consulta.setParameter("idPoblacion", pob);
            resultado = consulta.getResultList();
        } else if ( cat == 0 && sub == 0 && est > 0 && pob == 0 ) {
            TypedQuery<Mensaje> consulta = em.createNamedQuery("Mensaje.findByEstado", Mensaje.class);
            consulta.setParameter("idEstadoRegion", est);
            resultado = consulta.getResultList();
        } else {
            TypedQuery<Mensaje> consulta = em.createNamedQuery("Mensaje.findAll", Mensaje.class);
            resultado = consulta.getResultList();
        }
        
        Response.ResponseBuilder respuesta;
        
        if (resultado.size() > 0) { 
            System.out.println("Registros encontados: " + resultado.size());
            // Response no acepta un arraylist como parametro de entrada, por lo cual, hay que convertir el arraylist en un array
            respuesta = Response.status(Response.Status.OK).entity(resultado.toArray( new Mensaje[resultado.size()] )).type(MediaType.APPLICATION_JSON);
        } else {
            String texto = "Registros no encontrados.";
            respuesta = Response.status(Response.Status.NOT_FOUND).entity(texto).type(MediaType.TEXT_PLAIN);
        }
        return respuesta.build();
    }
    
    
    @GET
    @Path("usuario/{id}")
    public Response mensajesPorUsuario(@PathParam("id") Integer id) {
        TypedQuery<Mensaje> consultaMensajePorUsuario = em.createNamedQuery("Mensaje.findByUsuario", Mensaje.class);
        consultaMensajePorUsuario.setParameter("idUsuario", id);
        List<Mensaje> resultado = consultaMensajePorUsuario.getResultList();
        // System.out.println("Id Usuario: " + id + ", nùmero de mensajes: " + resultado.size());
        Response.ResponseBuilder respuesta;
        
        if (resultado.size() > 0) { 
            // Response no acepta un arraylist como parametro de entrada, por lo cual, hay que convertir el arraylist en un array
            respuesta = Response.status(Response.Status.OK).entity(resultado.toArray( new Mensaje[resultado.size()] )).type(MediaType.APPLICATION_JSON);
        } else {
            String texto = "Usuario no posee clasificados.";
            respuesta = Response.status(Response.Status.NOT_FOUND).entity(texto).type(MediaType.TEXT_PLAIN);
        }

        return respuesta.build();
    }
    
    
    @GET
    @Path("subcategoria/{id}")
    public Response mensajesPorSubCategoria(@PathParam("id") Integer id) {
        TypedQuery<Mensaje> consultaMensajePorSubCategoria = em.createNamedQuery("Mensaje.findBySubCategoria", Mensaje.class);
        consultaMensajePorSubCategoria.setParameter("idSubCategoria", id);
        List<Mensaje> resultado = consultaMensajePorSubCategoria.getResultList();
        // System.out.println("Id Usuario: " + id + ", nùmero de mensajes: " + resultado.size());
        Response.ResponseBuilder respuesta;
        
        if (resultado.size() > 0) { 
            // Response no acepta un arraylist como parametro de entrada, por lo cual, hay que convertir el arraylist en un array
            respuesta = Response.status(Response.Status.OK).entity(resultado.toArray( new Mensaje[resultado.size()] )).type(MediaType.APPLICATION_JSON);
        } else {
            String texto = "No hay clasificados en esta categoría.";
            respuesta = Response.status(Response.Status.NOT_FOUND).entity(texto).type(MediaType.TEXT_PLAIN);
        }

        return respuesta.build();
    }
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Mensaje> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
