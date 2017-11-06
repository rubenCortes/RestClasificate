/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bennu.servicio;

import com.bennu.entidad.Usuario;
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

/**
 *
 * @author administrador
 */
@Stateless
@Path("usuario")
public class UsuarioFacadeREST extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "RestClasificatePU")
    private EntityManager em;

    public UsuarioFacadeREST() {
        super(Usuario.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Usuario entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Usuario entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Usuario find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Usuario> findAll() {
        return super.findAll();
    }
    
    @GET
    @Path("validar/{correo}/{clave}")
    public Response validarUsuario(@PathParam("correo") String correo, @PathParam("clave") String clave) {
        
        List<Usuario> listaSalida = this.buscarUsuarioValido(correo, clave);
        
        Response.ResponseBuilder respuesta;
        
        if (listaSalida.size() > 0) { 
            Usuario usuarioValidado = listaSalida.get(0);
            respuesta = Response.status(Response.Status.OK).entity(usuarioValidado).type(MediaType.APPLICATION_JSON);
        } else {
            String texto = "Usuario no registrado o credenciales incorrectas.";
            respuesta = Response.status(Response.Status.NOT_FOUND).entity(texto).type(MediaType.TEXT_PLAIN);
        }

        return respuesta.build();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    private List<Usuario> buscarUsuarioValido(String correo, String clave) {
        TypedQuery<Usuario> consultaUsuario= em.createNamedQuery("Usuario.validar", Usuario.class);
        consultaUsuario.setParameter("correo", correo);
        consultaUsuario.setParameter("clave", clave);
        
        return consultaUsuario.getResultList(); 
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
