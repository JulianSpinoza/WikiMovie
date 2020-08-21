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
import jpa.wikioscars.Categoria;

/**
 *
 * @author Yabito123
 */
public class CategoriaJpaController implements Serializable {

    public CategoriaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Categoria categoria) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (categoria.getGeneroList() == null) {
            categoria.setGeneroList(new ArrayList<Genero>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Genero> attachedGeneroList = new ArrayList<Genero>();
            for (Genero generoListGeneroToAttach : categoria.getGeneroList()) {
                generoListGeneroToAttach = em.getReference(generoListGeneroToAttach.getClass(), generoListGeneroToAttach.getGeneroPK());
                attachedGeneroList.add(generoListGeneroToAttach);
            }
            categoria.setGeneroList(attachedGeneroList);
            em.persist(categoria);
            for (Genero generoListGenero : categoria.getGeneroList()) {
                Categoria oldCategoriaOfGeneroListGenero = generoListGenero.getCategoria();
                generoListGenero.setCategoria(categoria);
                generoListGenero = em.merge(generoListGenero);
                if (oldCategoriaOfGeneroListGenero != null) {
                    oldCategoriaOfGeneroListGenero.getGeneroList().remove(generoListGenero);
                    oldCategoriaOfGeneroListGenero = em.merge(oldCategoriaOfGeneroListGenero);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCategoria(categoria.getIdCategoria()) != null) {
                throw new PreexistingEntityException("Categoria " + categoria + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Categoria categoria) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Categoria persistentCategoria = em.find(Categoria.class, categoria.getIdCategoria());
            List<Genero> generoListOld = persistentCategoria.getGeneroList();
            List<Genero> generoListNew = categoria.getGeneroList();
            List<String> illegalOrphanMessages = null;
            for (Genero generoListOldGenero : generoListOld) {
                if (!generoListNew.contains(generoListOldGenero)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Genero " + generoListOldGenero + " since its categoria field is not nullable.");
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
            categoria.setGeneroList(generoListNew);
            categoria = em.merge(categoria);
            for (Genero generoListNewGenero : generoListNew) {
                if (!generoListOld.contains(generoListNewGenero)) {
                    Categoria oldCategoriaOfGeneroListNewGenero = generoListNewGenero.getCategoria();
                    generoListNewGenero.setCategoria(categoria);
                    generoListNewGenero = em.merge(generoListNewGenero);
                    if (oldCategoriaOfGeneroListNewGenero != null && !oldCategoriaOfGeneroListNewGenero.equals(categoria)) {
                        oldCategoriaOfGeneroListNewGenero.getGeneroList().remove(generoListNewGenero);
                        oldCategoriaOfGeneroListNewGenero = em.merge(oldCategoriaOfGeneroListNewGenero);
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
                String id = categoria.getIdCategoria();
                if (findCategoria(id) == null) {
                    throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Categoria categoria;
            try {
                categoria = em.getReference(Categoria.class, id);
                categoria.getIdCategoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Genero> generoListOrphanCheck = categoria.getGeneroList();
            for (Genero generoListOrphanCheckGenero : generoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Categoria (" + categoria + ") cannot be destroyed since the Genero " + generoListOrphanCheckGenero + " in its generoList field has a non-nullable categoria field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(categoria);
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

    public List<Categoria> findCategoriaEntities() {
        return findCategoriaEntities(true, -1, -1);
    }

    public List<Categoria> findCategoriaEntities(int maxResults, int firstResult) {
        return findCategoriaEntities(false, maxResults, firstResult);
    }

    private List<Categoria> findCategoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categoria.class));
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

    public Categoria findCategoria(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categoria> rt = cq.from(Categoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
