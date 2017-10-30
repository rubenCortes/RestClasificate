/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bennu.entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author administrador
 */
@Entity
@Table(name = "poblacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Poblacion.findAll", query = "SELECT p FROM Poblacion p")
    , @NamedQuery(name = "Poblacion.findByIdPoblacion", query = "SELECT p FROM Poblacion p WHERE p.idPoblacion = :idPoblacion")
    , @NamedQuery(name = "Poblacion.findByNombre", query = "SELECT p FROM Poblacion p WHERE p.nombre = :nombre")})
public class Poblacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_poblacion")
    private Integer idPoblacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "poblacion")
    private List<Usuario> usuarioList;
    @JoinColumn(name = "id_estado_region", referencedColumnName = "id_estado_region")
    @ManyToOne
    private EstadoRegion estadoRegion;

    public Poblacion() {
    }

    public Poblacion(Integer idPoblacion) {
        this.idPoblacion = idPoblacion;
    }

    public Poblacion(Integer idPoblacion, String nombre) {
        this.idPoblacion = idPoblacion;
        this.nombre = nombre;
    }

    public Integer getIdPoblacion() {
        return idPoblacion;
    }

    public void setIdPoblacion(Integer idPoblacion) {
        this.idPoblacion = idPoblacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public EstadoRegion getEstadoRegion() {
        return estadoRegion;
    }

    public void setEstadoRegion(EstadoRegion estadoRegion) {
        this.estadoRegion = estadoRegion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPoblacion != null ? idPoblacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Poblacion)) {
            return false;
        }
        Poblacion other = (Poblacion) object;
        if ((this.idPoblacion == null && other.idPoblacion != null) || (this.idPoblacion != null && !this.idPoblacion.equals(other.idPoblacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Poblacion[ idPoblacion=" + idPoblacion + " ]";
    }
    
}
