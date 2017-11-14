/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bennu.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author administrador
 */
@Entity
@Table(name = "mensaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensaje.findAll", query = "SELECT m FROM Mensaje m")
    , @NamedQuery(name = "Mensaje.findByIdMensaje", query = "SELECT m FROM Mensaje m WHERE m.idMensaje = :idMensaje")
    , @NamedQuery(name = "Mensaje.findByCreacion", query = "SELECT m FROM Mensaje m WHERE m.creacion = :creacion")
    , @NamedQuery(name = "Mensaje.findByContenido", query = "SELECT m FROM Mensaje m WHERE m.contenido = :contenido")
    , @NamedQuery(name = "Mensaje.findByUsuario", query = "SELECT m FROM Mensaje m WHERE m.usuario.idUsuario = :idUsuario")
    , @NamedQuery(name = "Mensaje.findByCategoria", query = "SELECT m FROM Mensaje m WHERE m.subCategoria.categoria.idCategoria = :idCategoria")
    , @NamedQuery(name = "Mensaje.findBySubCategoria", query = "SELECT m FROM Mensaje m WHERE m.subCategoria.idSubCategoria = :idSubCategoria")
    , @NamedQuery(name = "Mensaje.findByPoblacion", query = "SELECT m FROM Mensaje m WHERE m.usuario.poblacion.idPoblacion = :idPoblacion")
    , @NamedQuery(name = "Mensaje.findByEstado", query = "SELECT m FROM Mensaje m WHERE m.usuario.poblacion.estadoRegion.idEstadoRegion = :idEstadoRegion")
    , @NamedQuery(name = "Mensaje.findByCatEst", query = "SELECT m FROM Mensaje m WHERE m.subCategoria.categoria.idCategoria = :idCategoria and m.usuario.poblacion.estadoRegion.idEstadoRegion = :idEstadoRegion")
    , @NamedQuery(name = "Mensaje.findByCatPob", query = "SELECT m FROM Mensaje m WHERE m.subCategoria.categoria.idCategoria = :idCategoria and m.usuario.poblacion.idPoblacion = :idPoblacion")
    , @NamedQuery(name = "Mensaje.findBySubCatEst", query = "SELECT m FROM Mensaje m WHERE m.subCategoria.idSubCategoria = :idSubCategoria and m.usuario.poblacion.estadoRegion.idEstadoRegion = :idEstadoRegion")
    , @NamedQuery(name = "Mensaje.findBySubCatPob", query = "SELECT m FROM Mensaje m WHERE m.subCategoria.idSubCategoria = :idSubCategoria and m.usuario.poblacion.idPoblacion = :idPoblacion")})
public class Mensaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mensaje")
    private Integer idMensaje;
    @Column(name = "creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "contenido")
    private String contenido;
    @OneToMany(mappedBy = "mensaje")
    private List<Imagen> imagenList;
    @JoinColumn(name = "id_sub_categoria", referencedColumnName = "id_sub_categoria")
    @ManyToOne
    private SubCategoria subCategoria;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario usuario;

    public Mensaje() {
    }

    public Mensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Mensaje(Integer idMensaje, String contenido) {
        this.idMensaje = idMensaje;
        this.contenido = contenido;
    }

    public Integer getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @XmlTransient
    public List<Imagen> getImagenList() {
        return imagenList;
    }

    public void setImagenList(List<Imagen> imagenList) {
        this.imagenList = imagenList;
    }

    public SubCategoria getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(SubCategoria subCategoria) {
        this.subCategoria = subCategoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMensaje != null ? idMensaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensaje)) {
            return false;
        }
        Mensaje other = (Mensaje) object;
        if ((this.idMensaje == null && other.idMensaje != null) || (this.idMensaje != null && !this.idMensaje.equals(other.idMensaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mensaje[ idMensaje=" + idMensaje + " ]";
    }
    
}
