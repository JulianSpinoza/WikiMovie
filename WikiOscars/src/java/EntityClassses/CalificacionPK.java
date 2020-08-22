/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClassses;

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
public class CalificacionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idCalificacion")
    private int idCalificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPeliculafk")
    private int idPeliculafk;

    public CalificacionPK() {
    }

    public CalificacionPK(int idCalificacion, int idPeliculafk) {
        this.idCalificacion = idCalificacion;
        this.idPeliculafk = idPeliculafk;
    }

    public int getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(int idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public int getIdPeliculafk() {
        return idPeliculafk;
    }

    public void setIdPeliculafk(int idPeliculafk) {
        this.idPeliculafk = idPeliculafk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCalificacion;
        hash += (int) idPeliculafk;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalificacionPK)) {
            return false;
        }
        CalificacionPK other = (CalificacionPK) object;
        if (this.idCalificacion != other.idCalificacion) {
            return false;
        }
        if (this.idPeliculafk != other.idPeliculafk) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.CalificacionPK[ idCalificacion=" + idCalificacion + ", idPeliculafk=" + idPeliculafk + " ]";
    }
    
}
