/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.Persistence;

import Servlets.exceptions.NonexistentEntityException;
import Servlets.exceptions.PreexistingEntityException;
import Servlets.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import jpa.wikioscars.Categoria;
import jpa.wikioscars.Genero;
import jpa.wikioscars.GeneroPK;
import jpa.wikioscars.Pelicula;

/**
 *
 * @author Yabito123
 */
public class GeneroJpaController implements Serializable {

    public GeneroJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Genero genero) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (genero.getGeneroPK() == null) {
            genero.setGeneroPK(new GeneroPK());
        }
        genero.getGeneroPK().setPeliculaidPelicula(genero.getPelicula().getIdPelicula());
        genero.getGeneroPK().setCategoriaidCategoria(genero.getCategoria().getIdCategoria());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Categoria categoria = genero.getCategoria();
            if (categoria != null) {
                categoria = em.getReference(categoria.getClass(), categoria.getIdCategoria());
                genero.setCategoria(categoria);
            }
            Pelicula pelicula = genero.getPelicula();
            if (pelicula != null) {
                pelicula = em.getReference(pelicula.getClass(), pelicula.getIdPelicula());
                genero.setPelicula(pelicula);
            }
            em.persist(genero);
            if (categoria != null) {
                categoria.getGeneroList().add(genero);
                categoria = em.merge(categoria);
            }
            if (pelicula != null) {
                pelicula.getGeneroList().add(genero);
                pelicula = em.merge(pelicula);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findGenero(genero.getGeneroPK()) != null) {
                throw new PreexistingEntityException("Genero " + genero + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Genero genero) throws NonexistentEntityException, RollbackFailureException, Exception {
        genero.getGeneroPK().setPeliculaidPelicula(genero.getPelicula().getIdPelicula());
        genero.getGeneroPK().setCategoriaidCategoria(genero.getCategoria().getIdCategoria());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Genero persistentGenero = em.find(Genero.class, genero.getGeneroPK());
            Categoria categoriaOld = persistentGenero.getCategoria();
            Categoria categoriaNew = genero.getCategoria();
            Pelicula peliculaOld = persistentGenero.getPelicula();
            Pelicula peliculaNew = genero.getPelicula();
            if (categoriaNew != null) {
                categoriaNew = em.getReference(categoriaNew.getClass(), categoriaNew.getIdCategoria());
                genero.setCategoria(categoriaNew);
            }
            if (peliculaNew != null) {
                peliculaNew = em.getReference(peliculaNew.getClass(), peliculaNew.getIdPelicula());
                genero.setPelicula(peliculaNew);
            }
            genero = em.merge(genero);
            if (categoriaOld != null && !categoriaOld.equals(categoriaNew)) {
                categoriaOld.getGeneroList().remove(genero);
                categoriaOld = em.merge(categoriaOld);
            }
            if (categoriaNew != null && !categoriaNew.equals(categoriaOld)) {
                categoriaNew.getGeneroList().add(genero);
                categoriaNew = em.merge(categoriaNew);
            }
            if (peliculaOld != null && !peliculaOld.equals(peliculaNew)) {
                peliculaOld.getGeneroList().remove(genero);
                peliculaOld = em.merge(peliculaOld);
            }
            if (peliculaNew != null && !peliculaNew.equals(peliculaOld)) {
                peliculaNew.getGeneroList().add(genero);
                peliculaNew = em.merge(peliculaNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                GeneroPK id = genero.getGeneroPK();
                if (findGenero(id) == null) {
                    throw new NonexistentEntityException("The genero with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(GeneroPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Genero genero;
            try {
                genero = em.getReference(Genero.class, id);
                genero.getGeneroPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The genero with id " + id + " no longer exists.", enfe);
            }
            Categoria categoria = genero.getCategoria();
            if (categoria != null) {
                categoria.getGeneroList().remove(genero);
                categoria = em.merge(categoria);
            }
            Pelicula pelicula = genero.getPelicula();
            if (pelicula != null) {
                pelicula.getGeneroList().remove(genero);
                pelicula = em.merge(pelicula);
            }
            em.remove(genero);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Genero> findGeneroEntities() {
        return findGeneroEntities(true, -1, -1);
    }

    public List<Genero> findGeneroEntities(int maxResults, int firstResult) {
        return findGeneroEntities(false, maxResults, firstResult);
    }

    private List<Genero> findGeneroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Genero.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Genero findGenero(GeneroPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Genero.class, id);
        } finally {
            em.close();
        }
    }

    public int getGeneroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Genero> rt = cq.from(Genero.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
