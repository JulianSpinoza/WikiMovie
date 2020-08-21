/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.wikioscars;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Yabito123
 */
@Embeddable
public class RepartoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idReparto")
    private int idReparto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Actor_idActor")
    private int actoridActor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Pelicula_idPelicula")
    private int peliculaidPelicula;

    public RepartoPK() {
    }

    public RepartoPK(int idReparto, int actoridActor, int peliculaidPelicula) {
        this.idReparto = idReparto;
        this.actoridActor = actoridActor;
        this.peliculaidPelicula = peliculaidPelicula;
    }

    public int getIdReparto() {
        return idReparto;
    }

    public void setIdReparto(int idReparto) {
        this.idReparto = idReparto;
    }

    public int getActoridActor() {
        return actoridActor;
    }

    public void setActoridActor(int actoridActor) {
        this.actoridActor = actoridActor;
    }

    public int getPeliculaidPelicula() {
        return peliculaidPelicula;
    }

    public void setPeliculaidPelicula(int peliculaidPelicula) {
        this.peliculaidPelicula = peliculaidPelicula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idReparto;
        hash += (int) actoridActor;
        hash += (int) peliculaidPelicula;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RepartoPK)) {
            return false;
        }
        RepartoPK other = (RepartoPK) object;
        if (this.idReparto != other.idReparto) {
            return false;
        }
        if (this.actoridActor != other.actoridActor) {
            return false;
        }
        if (this.peliculaidPelicula != other.peliculaidPelicula) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.wikioscars.RepartoPK[ idReparto=" + idReparto + ", actoridActor=" + actoridActor + ", peliculaidPelicula=" + peliculaidPelicula + " ]";
    }
    
}
