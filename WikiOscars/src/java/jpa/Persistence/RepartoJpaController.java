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
import jpa.wikioscars.Actor;
import jpa.wikioscars.Pelicula;
import jpa.wikioscars.Reparto;
import jpa.wikioscars.RepartoPK;

/**
 *
 * @author Yabito123
 */
public class RepartoJpaController implements Serializable {

    public RepartoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reparto reparto) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (reparto.getRepartoPK() == null) {
            reparto.setRepartoPK(new RepartoPK());
        }
        reparto.getRepartoPK().setActoridActor(reparto.getActor().getIdActor());
        reparto.getRepartoPK().setPeliculaidPelicula(reparto.getPelicula().getIdPelicula());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Actor actor = reparto.getActor();
            if (actor != null) {
                actor = em.getReference(actor.getClass(), actor.getIdActor());
                reparto.setActor(actor);
            }
            Pelicula pelicula = reparto.getPelicula();
            if (pelicula != null) {
                pelicula = em.getReference(pelicula.getClass(), pelicula.getIdPelicula());
                reparto.setPelicula(pelicula);
            }
            em.persist(reparto);
            if (actor != null) {
                actor.getRepartoList().add(reparto);
                actor = em.merge(actor);
            }
            if (pelicula != null) {
                pelicula.getRepartoList().add(reparto);
                pelicula = em.merge(pelicula);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findReparto(reparto.getRepartoPK()) != null) {
                throw new PreexistingEntityException("Reparto " + reparto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reparto reparto) throws NonexistentEntityException, RollbackFailureException, Exception {
        reparto.getRepartoPK().setActoridActor(reparto.getActor().getIdActor());
        reparto.getRepartoPK().setPeliculaidPelicula(reparto.getPelicula().getIdPelicula());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Reparto persistentReparto = em.find(Reparto.class, reparto.getRepartoPK());
            Actor actorOld = persistentReparto.getActor();
            Actor actorNew = reparto.getActor();
            Pelicula peliculaOld = persistentReparto.getPelicula();
            Pelicula peliculaNew = reparto.getPelicula();
            if (actorNew != null) {
                actorNew = em.getReference(actorNew.getClass(), actorNew.getIdActor());
                reparto.setActor(actorNew);
            }
            if (peliculaNew != null) {
                peliculaNew = em.getReference(peliculaNew.getClass(), peliculaNew.getIdPelicula());
                reparto.setPelicula(peliculaNew);
            }
            reparto = em.merge(reparto);
            if (actorOld != null && !actorOld.equals(actorNew)) {
                actorOld.getRepartoList().remove(reparto);
                actorOld = em.merge(actorOld);
            }
            if (actorNew != null && !actorNew.equals(actorOld)) {
                actorNew.getRepartoList().add(reparto);
                actorNew = em.merge(actorNew);
            }
            if (peliculaOld != null && !peliculaOld.equals(peliculaNew)) {
                peliculaOld.getRepartoList().remove(reparto);
                peliculaOld = em.merge(peliculaOld);
            }
            if (peliculaNew != null && !peliculaNew.equals(peliculaOld)) {
                peliculaNew.getRepartoList().add(reparto);
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
                RepartoPK id = reparto.getRepartoPK();
                if (findReparto(id) == null) {
                    throw new NonexistentEntityException("The reparto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RepartoPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Reparto reparto;
            try {
                reparto = em.getReference(Reparto.class, id);
                reparto.getRepartoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reparto with id " + id + " no longer exists.", enfe);
            }
            Actor actor = reparto.getActor();
            if (actor != null) {
                actor.getRepartoList().remove(reparto);
                actor = em.merge(actor);
            }
            Pelicula pelicula = reparto.getPelicula();
            if (pelicula != null) {
                pelicula.getRepartoList().remove(reparto);
                pelicula = em.merge(pelicula);
            }
            em.remove(reparto);
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

    public List<Reparto> findRepartoEntities() {
        return findRepartoEntities(true, -1, -1);
    }

    public List<Reparto> findRepartoEntities(int maxResults, int firstResult) {
        return findRepartoEntities(false, maxResults, firstResult);
    }

    private List<Reparto> findRepartoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reparto.class));
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

    public Reparto findReparto(RepartoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reparto.class, id);
        } finally {
            em.close();
        }
    }

    public int getRepartoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reparto> rt = cq.from(Reparto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
