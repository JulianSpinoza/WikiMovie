/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.Persistence;

import Servlets.exceptions.IllegalOrphanException;
import Servlets.exceptions.NonexistentEntityException;
import Servlets.exceptions.PreexistingEntityException;
import Servlets.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.wikioscars.Genero;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import jpa.wikioscars.Pelicula;
import jpa.wikioscars.Reparto;

/**
 *
 * @author Yabito123
 */
public class PeliculaJpaController implements Serializable {

    public PeliculaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pelicula pelicula) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (pelicula.getGeneroList() == null) {
            pelicula.setGeneroList(new ArrayList<Genero>());
        }
        if (pelicula.getRepartoList() == null) {
            pelicula.setRepartoList(new ArrayList<Reparto>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Genero> attachedGeneroList = new ArrayList<Genero>();
            for (Genero generoListGeneroToAttach : pelicula.getGeneroList()) {
                generoListGeneroToAttach = em.getReference(generoListGeneroToAttach.getClass(), generoListGeneroToAttach.getGeneroPK());
                attachedGeneroList.add(generoListGeneroToAttach);
            }
            pelicula.setGeneroList(attachedGeneroList);
            List<Reparto> attachedRepartoList = new ArrayList<Reparto>();
            for (Reparto repartoListRepartoToAttach : pelicula.getRepartoList()) {
                repartoListRepartoToAttach = em.getReference(repartoListRepartoToAttach.getClass(), repartoListRepartoToAttach.getRepartoPK());
                attachedRepartoList.add(repartoListRepartoToAttach);
            }
            pelicula.setRepartoList(attachedRepartoList);
            em.persist(pelicula);
            for (Genero generoListGenero : pelicula.getGeneroList()) {
                Pelicula oldPeliculaOfGeneroListGenero = generoListGenero.getPelicula();
                generoListGenero.setPelicula(pelicula);
                generoListGenero = em.merge(generoListGenero);
                if (oldPeliculaOfGeneroListGenero != null) {
                    oldPeliculaOfGeneroListGenero.getGeneroList().remove(generoListGenero);
                    oldPeliculaOfGeneroListGenero = em.merge(oldPeliculaOfGeneroListGenero);
                }
            }
            for (Reparto repartoListReparto : pelicula.getRepartoList()) {
                Pelicula oldPeliculaOfRepartoListReparto = repartoListReparto.getPelicula();
                repartoListReparto.setPelicula(pelicula);
                repartoListReparto = em.merge(repartoListReparto);
                if (oldPeliculaOfRepartoListReparto != null) {
                    oldPeliculaOfRepartoListReparto.getRepartoList().remove(repartoListReparto);
                    oldPeliculaOfRepartoListReparto = em.merge(oldPeliculaOfRepartoListReparto);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPelicula(pelicula.getIdPelicula()) != null) {
                throw new PreexistingEntityException("Pelicula " + pelicula + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pelicula pelicula) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Pelicula persistentPelicula = em.find(Pelicula.class, pelicula.getIdPelicula());
            List<Genero> generoListOld = persistentPelicula.getGeneroList();
            List<Genero> generoListNew = pelicula.getGeneroList();
            List<Reparto> repartoListOld = persistentPelicula.getRepartoList();
            List<Reparto> repartoListNew = pelicula.getRepartoList();
            List<String> illegalOrphanMessages = null;
            for (Genero generoListOldGenero : generoListOld) {
                if (!generoListNew.contains(generoListOldGenero)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Genero " + generoListOldGenero + " since its pelicula field is not nullable.");
                }
            }
            for (Reparto repartoListOldReparto : repartoListOld) {
                if (!repartoListNew.contains(repartoListOldReparto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reparto " + repartoListOldReparto + " since its pelicula field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Genero> attachedGeneroListNew = new ArrayList<Genero>();
            for (Genero generoListNewGeneroToAttach : generoListNew) {
                generoListNewGeneroToAttach = em.getReference(generoListNewGeneroToAttach.getClass(), generoListNewGeneroToAttach.getGeneroPK());
                attachedGeneroListNew.add(generoListNewGeneroToAttach);
            }
            generoListNew = attachedGeneroListNew;
            pelicula.setGeneroList(generoListNew);
            List<Reparto> attachedRepartoListNew = new ArrayList<Reparto>();
            for (Reparto repartoListNewRepartoToAttach : repartoListNew) {
                repartoListNewRepartoToAttach = em.getReference(repartoListNewRepartoToAttach.getClass(), repartoListNewRepartoToAttach.getRepartoPK());
                attachedRepartoListNew.add(repartoListNewRepartoToAttach);
            }
            repartoListNew = attachedRepartoListNew;
            pelicula.setRepartoList(repartoListNew);
            pelicula = em.merge(pelicula);
            for (Genero generoListNewGenero : generoListNew) {
                if (!generoListOld.contains(generoListNewGenero)) {
                    Pelicula oldPeliculaOfGeneroListNewGenero = generoListNewGenero.getPelicula();
                    generoListNewGenero.setPelicula(pelicula);
                    generoListNewGenero = em.merge(generoListNewGenero);
                    if (oldPeliculaOfGeneroListNewGenero != null && !oldPeliculaOfGeneroListNewGenero.equals(pelicula)) {
                        oldPeliculaOfGeneroListNewGenero.getGeneroList().remove(generoListNewGenero);
                        oldPeliculaOfGeneroListNewGenero = em.merge(oldPeliculaOfGeneroListNewGenero);
                    }
                }
            }
            for (Reparto repartoListNewReparto : repartoListNew) {
                if (!repartoListOld.contains(repartoListNewReparto)) {
                    Pelicula oldPeliculaOfRepartoListNewReparto = repartoListNewReparto.getPelicula();
                    repartoListNewReparto.setPelicula(pelicula);
                    repartoListNewReparto = em.merge(repartoListNewReparto);
                    if (oldPeliculaOfRepartoListNewReparto != null && !oldPeliculaOfRepartoListNewReparto.equals(pelicula)) {
                        oldPeliculaOfRepartoListNewReparto.getRepartoList().remove(repartoListNewReparto);
                        oldPeliculaOfRepartoListNewReparto = em.merge(oldPeliculaOfRepartoListNewReparto);
                    }
                }
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
                Integer id = pelicula.getIdPelicula();
                if (findPelicula(id) == null) {
                    throw new NonexistentEntityException("The pelicula with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Pelicula pelicula;
            try {
                pelicula = em.getReference(Pelicula.class, id);
                pelicula.getIdPelicula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pelicula with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Genero> generoListOrphanCheck = pelicula.getGeneroList();
            for (Genero generoListOrphanCheckGenero : generoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pelicula (" + pelicula + ") cannot be destroyed since the Genero " + generoListOrphanCheckGenero + " in its generoList field has a non-nullable pelicula field.");
            }
            List<Reparto> repartoListOrphanCheck = pelicula.getRepartoList();
            for (Reparto repartoListOrphanCheckReparto : repartoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pelicula (" + pelicula + ") cannot be destroyed since the Reparto " + repartoListOrphanCheckReparto + " in its repartoList field has a non-nullable pelicula field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(pelicula);
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

    public List<Pelicula> findPeliculaEntities() {
        return findPeliculaEntities(true, -1, -1);
    }

    public List<Pelicula> findPeliculaEntities(int maxResults, int firstResult) {
        return findPeliculaEntities(false, maxResults, firstResult);
    }

    private List<Pelicula> findPeliculaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pelicula.class));
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

    public Pelicula findPelicula(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pelicula.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeliculaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pelicula> rt = cq.from(Pelicula.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
