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
@Table(name = "Pelicula")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pelicula.findAll", query = "SELECT p FROM Pelicula p")
    , @NamedQuery(name = "Pelicula.findByIdPelicula", query = "SELECT p FROM Pelicula p WHERE p.idPelicula = :idPelicula")
    , @NamedQuery(name = "Pelicula.findByTituloPelicula", query = "SELECT p FROM Pelicula p WHERE p.tituloPelicula = :tituloPelicula")
    , @NamedQuery(name = "Pelicula.findByFechaEstreno", query = "SELECT p FROM Pelicula p WHERE p.fechaEstreno = :fechaEstreno")})
public class Pelicula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPelicula")
    private Integer idPelicula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tituloPelicula")
    private String tituloPelicula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "fechaEstreno")
    private String fechaEstreno;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pelicula")
    private List<Genero> generoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pelicula")
    private List<Reparto> repartoList;

    public Pelicula() {
    }

    public Pelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }

    public Pelicula(Integer idPelicula, String tituloPelicula, String fechaEstreno) {
        this.idPelicula = idPelicula;
        this.tituloPelicula = tituloPelicula;
        this.fechaEstreno = fechaEstreno;
    }

    public Integer getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTituloPelicula() {
        return tituloPelicula;
    }

    public void setTituloPelicula(String tituloPelicula) {
        this.tituloPelicula = tituloPelicula;
    }

    public String getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(String fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    @XmlTransient
    public List<Genero> getGeneroList() {
        return generoList;
    }

    public void setGeneroList(List<Genero> generoList) {
        this.generoList = generoList;
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
        hash += (idPelicula != null ? idPelicula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pelicula)) {
            return false;
        }
        Pelicula other = (Pelicula) object;
        if ((this.idPelicula == null && other.idPelicula != null) || (this.idPelicula != null && !this.idPelicula.equals(other.idPelicula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.wikioscars.Pelicula[ idPelicula=" + idPelicula + " ]";
    }
    
}
