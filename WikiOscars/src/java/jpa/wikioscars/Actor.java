/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.wikioscars;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author Yabito123
 */
@Entity
@Table(name = "Actor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actor.findAll", query = "SELECT a FROM Actor a")
    , @NamedQuery(name = "Actor.findByIdActor", query = "SELECT a FROM Actor a WHERE a.idActor = :idActor")
    , @NamedQuery(name = "Actor.findByNombreActor", query = "SELECT a FROM Actor a WHERE a.nombreActor = :nombreActor")
    , @NamedQuery(name = "Actor.findByApellidoActor", query = "SELECT a FROM Actor a WHERE a.apellidoActor = :apellidoActor")
    , @NamedQuery(name = "Actor.findByEdadNac", query = "SELECT a FROM Actor a WHERE a.edadNac = :edadNac")})
public class Actor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idActor")
    private Integer idActor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombreActor")
    private String nombreActor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "apellidoActor")
    private String apellidoActor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "edadNac")
    private String edadNac;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actor")
    private List<Reparto> repartoList;

    public Actor() {
    }

    public Actor(Integer idActor) {
        this.idActor = idActor;
    }

    public Actor(Integer idActor, String nombreActor, String apellidoActor, String edadNac) {
        this.idActor = idActor;
        this.nombreActor = nombreActor;
        this.apellidoActor = apellidoActor;
        this.edadNac = edadNac;
    }

    public Integer getIdActor() {
        return idActor;
    }

    public void setIdActor(Integer idActor) {
        this.idActor = idActor;
    }

    public String getNombreActor() {
        return nombreActor;
    }

    public void setNombreActor(String nombreActor) {
        this.nombreActor = nombreActor;
    }

    public String getApellidoActor() {
        return apellidoActor;
    }

    public void setApellidoActor(String apellidoActor) {
        this.apellidoActor = apellidoActor;
    }

    public String getEdadNac() {
        return edadNac;
    }

    public void setEdadNac(String edadNac) {
        this.edadNac = edadNac;
    }

    @XmlTransient
    public List<Reparto> getRepartoList() {
        return repartoList;
    }

    public void setRepartoList(List<Reparto> repartoList) {
        this.repartoList = repartoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActor != null ? idActor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actor)) {
            return false;
        }
        Actor other = (Actor) object;
        if ((this.idActor == null && other.idActor != null) || (this.idActor != null && !this.idActor.equals(other.idActor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.wikioscars.Actor[ idActor=" + idActor + " ]";
    }
    
}
