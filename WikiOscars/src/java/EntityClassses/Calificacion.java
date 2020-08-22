/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClassses;

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
@Table(name = "Calificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calificacion.findAll", query = "SELECT c FROM Calificacion c")
    , @NamedQuery(name = "Calificacion.findByIdCalificacion", query = "SELECT c FROM Calificacion c WHERE c.calificacionPK.idCalificacion = :idCalificacion")
    , @NamedQuery(name = "Calificacion.findByIdPeliculafk", query = "SELECT c FROM Calificacion c WHERE c.calificacionPK.idPeliculafk = :idPeliculafk")
    , @NamedQuery(name = "Calificacion.findByValor", query = "SELECT c FROM Calificacion c WHERE c.valor = :valor")})
public class Calificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CalificacionPK calificacionPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Valor")
    private String valor;
    @JoinColumn(name = "idPeliculafk", referencedColumnName = "idPelicula", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pelicula pelicula;
    @JoinColumn(name = "iidUsuariofk", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario iidUsuariofk;

    public Calificacion() {
    }

    public Calificacion(CalificacionPK calificacionPK) {
        this.calificacionPK = calificacionPK;
    }

    public Calificacion(CalificacionPK calificacionPK, String valor) {
        this.calificacionPK = calificacionPK;
        this.valor = valor;
    }

    public Calificacion(int idCalificacion, int idPeliculafk) {
        this.calificacionPK = new CalificacionPK(idCalificacion, idPeliculafk);
    }

    public CalificacionPK getCalificacionPK() {
        return calificacionPK;
    }

    public void setCalificacionPK(CalificacionPK calificacionPK) {
        this.calificacionPK = calificacionPK;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Usuario getIidUsuariofk() {
        return iidUsuariofk;
    }

    public void setIidUsuariofk(Usuario iidUsuariofk) {
        this.iidUsuariofk = iidUsuariofk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (calificacionPK != null ? calificacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calificacion)) {
            return false;
        }
        Calificacion other = (Calificacion) object;
        if ((this.calificacionPK == null && other.calificacionPK != null) || (this.calificacionPK != null && !this.calificacionPK.equals(other.calificacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.Calificacion[ calificacionPK=" + calificacionPK + " ]";
    }
    
}
