/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.wikioscars;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yabito123
 */
@Entity
@Table(name = "Reparto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reparto.findAll", query = "SELECT r FROM Reparto r")
    , @NamedQuery(name = "Reparto.findByIdReparto", query = "SELECT r FROM Reparto r WHERE r.repartoPK.idReparto = :idReparto")
    , @NamedQuery(name = "Reparto.findByPersonaje", query = "SELECT r FROM Reparto r WHERE r.personaje = :personaje")
    , @NamedQuery(name = "Reparto.findByActoridActor", query = "SELECT r FROM Reparto r WHERE r.repartoPK.actoridActor = :actoridActor")
    , @NamedQuery(name = "Reparto.findByPeliculaidPelicula", query = "SELECT r FROM Reparto r WHERE r.repartoPK.peliculaidPelicula = :peliculaidPelicula")})
public class Reparto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RepartoPK repartoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "personaje")
    private String personaje;
    @JoinColumn(name = "Actor_idActor", referencedColumnName = "idActor", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Actor actor;
    @JoinColumn(name = "Pelicula_idPelicula", referencedColumnName = "idPelicula", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pelicula pelicula;

    public Reparto() {
    }

    public Reparto(RepartoPK repartoPK) {
        this.repartoPK = repartoPK;
    }

    public Reparto(RepartoPK repartoPK, String personaje) {
        this.repartoPK = repartoPK;
        this.personaje = personaje;
    }

    public Reparto(int idReparto, int actoridActor, int peliculaidPelicula) {
        this.repartoPK = new RepartoPK(idReparto, actoridActor, peliculaidPelicula);
    }

    public RepartoPK getRepartoPK() {
        return repartoPK;
    }

    public void setRepartoPK(RepartoPK repartoPK) {
        this.repartoPK = repartoPK;
    }

    public String getPersonaje() {
        return personaje;
    }

    public void setPersonaje(String personaje) {
        this.personaje = personaje;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (repartoPK != null ? repartoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reparto)) {
            return false;
        }
        Reparto other = (Reparto) object;
        if ((this.repartoPK == null && other.repartoPK != null) || (this.repartoPK != null && !this.repartoPK.equals(other.repartoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.wikioscars.Reparto[ repartoPK=" + repartoPK + " ]";
    }
    
}
