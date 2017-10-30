/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bennu.entidad;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author administrador
 */
@Entity
@Table(name = "estado_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoRegion.findAll", query = "SELECT e FROM EstadoRegion e")
    , @NamedQuery(name = "EstadoRegion.findByIdEstadoRegion", query = "SELECT e FROM EstadoRegion e WHERE e.idEstadoRegion = :idEstadoRegion")
    , @NamedQuery(name = "EstadoRegion.findByNombre", query = "SELECT e FROM EstadoRegion e WHERE e.nombre = :nombre")})
public class EstadoRegion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_region")
    private Integer idEstadoRegion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais")
    @ManyToOne
    private Pais pais;
    @OneToMany(mappedBy = "estadoRegion")
    private List<Poblacion> poblacionList;

    public EstadoRegion() {
    }

    public EstadoRegion(Integer idEstadoRegion) {
        this.idEstadoRegion = idEstadoRegion;
    }

    public EstadoRegion(Integer idEstadoRegion, String nombre) {
        this.idEstadoRegion = idEstadoRegion;
        this.nombre = nombre;
    }

    public Integer getIdEstadoRegion() {
        return idEstadoRegion;
    }

    public void setIdEstadoRegion(Integer idEstadoRegion) {
        this.idEstadoRegion = idEstadoRegion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @XmlTransient
    public List<Poblacion> getPoblacionList() {
        return poblacionList;
    }

    public void setPoblacionList(List<Poblacion> poblacionList) {
        this.poblacionList = poblacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoRegion != null ? idEstadoRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoRegion)) {
            return false;
        }
        EstadoRegion other = (EstadoRegion) object;
        if ((this.idEstadoRegion == null && other.idEstadoRegion != null) || (this.idEstadoRegion != null && !this.idEstadoRegion.equals(other.idEstadoRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EstadoRegion[ idEstadoRegion=" + idEstadoRegion + " ]";
    }
    
}
