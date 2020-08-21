/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.wikioscars;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yabito123
 */
@Entity
@Table(name = "Genero")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genero.findAll", query = "SELECT g FROM Genero g")
    , @NamedQuery(name = "Genero.findByIdGenero", query = "SELECT g FROM Genero g WHERE g.generoPK.idGenero = :idGenero")
    , @NamedQuery(name = "Genero.findByPeliculaidPelicula", query = "SELECT g FROM Genero g WHERE g.generoPK.peliculaidPelicula = :peliculaidPelicula")
    , @NamedQuery(name = "Genero.findByCategoriaidCategoria", query = "SELECT g FROM Genero g WHERE g.generoPK.categoriaidCategoria = :categoriaidCategoria")})
public class Genero implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GeneroPK generoPK;
    @JoinColumn(name = "Categoria_idCategoria", referencedColumnName = "idCategoria", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Categoria categoria;
    @JoinColumn(name = "Pelicula_idPelicula", referencedColumnName = "idPelicula", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pelicula pelicula;

    public Genero() {
    }

    public Genero(GeneroPK generoPK) {
        this.generoPK = generoPK;
    }

    public Genero(int idGenero, int peliculaidPelicula, String categoriaidCategoria) {
        this.generoPK = new GeneroPK(idGenero, peliculaidPelicula, categoriaidCategoria);
    }

    public GeneroPK getGeneroPK() {
        return generoPK;
    }

    public void setGeneroPK(GeneroPK generoPK) {
        this.generoPK = generoPK;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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
        hash += (generoPK != null ? generoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genero)) {
            return false;
        }
        Genero other = (Genero) object;
        if ((this.generoPK == null && other.generoPK != null) || (this.generoPK != null && !this.generoPK.equals(other.generoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.wikioscars.Genero[ generoPK=" + generoPK + " ]";
    }
    
}
