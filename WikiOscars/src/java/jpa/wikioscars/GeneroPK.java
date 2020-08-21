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
import javax.validation.constraints.Size;

/**
 *
 * @author Yabito123
 */
@Embeddable
public class GeneroPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idGenero")
    private int idGenero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Pelicula_idPelicula")
    private int peliculaidPelicula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Categoria_idCategoria")
    private String categoriaidCategoria;

    public GeneroPK() {
    }

    public GeneroPK(int idGenero, int peliculaidPelicula, String categoriaidCategoria) {
        this.idGenero = idGenero;
        this.peliculaidPelicula = peliculaidPelicula;
        this.categoriaidCategoria = categoriaidCategoria;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public int getPeliculaidPelicula() {
        return peliculaidPelicula;
    }

    public void setPeliculaidPelicula(int peliculaidPelicula) {
        this.peliculaidPelicula = peliculaidPelicula;
    }

    public String getCategoriaidCategoria() {
        return categoriaidCategoria;
    }

    public void setCategoriaidCategoria(String categoriaidCategoria) {
        this.categoriaidCategoria = categoriaidCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idGenero;
        hash += (int) peliculaidPelicula;
        hash += (categoriaidCategoria != null ? categoriaidCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeneroPK)) {
            return false;
        }
        GeneroPK other = (GeneroPK) object;
        if (this.idGenero != other.idGenero) {
            return false;
        }
        if (this.peliculaidPelicula != other.peliculaidPelicula) {
            return false;
        }
        if ((this.categoriaidCategoria == null && other.categoriaidCategoria != null) || (this.categoriaidCategoria != null && !this.categoriaidCategoria.equals(other.categoriaidCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.wikioscars.GeneroPK[ idGenero=" + idGenero + ", peliculaidPelicula=" + peliculaidPelicula + ", categoriaidCategoria=" + categoriaidCategoria + " ]";
    }
    
}
