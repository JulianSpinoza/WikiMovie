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
import jpa.wikioscars.Reparto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import jpa.wikioscars.Actor;

/**
 *
 * @author Yabito123
 */
public class ActorJpaController implements Serializable {

    public ActorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Actor actor) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (actor.getRepartoList() == null) {
            actor.setRepartoList(new ArrayList<Reparto>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Reparto> attachedRepartoList = new ArrayList<Reparto>();
            for (Reparto repartoListRepartoToAttach : actor.getRepartoList()) {
                repartoListRepartoToAttach = em.getReference(repartoListRepartoToAttach.getClass(), repartoListRepartoToAttach.getRepartoPK());
                attachedRepartoList.add(repartoListRepartoToAttach);
            }
            actor.setRepartoList(attachedRepartoList);
            em.persist(actor);
            for (Reparto repartoListReparto : actor.getRepartoList()) {
                Actor oldActorOfRepartoListReparto = repartoListReparto.getActor();
                repartoListReparto.setActor(actor);
                repartoListReparto = em.merge(repartoListReparto);
                if (oldActorOfRepartoListReparto != null) {
                    oldActorOfRepartoListReparto.getRepartoList().remove(repartoListReparto);
                    oldActorOfRepartoListReparto = em.merge(oldActorOfRepartoListReparto);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findActor(actor.getIdActor()) != null) {
                throw new PreexistingEntityException("Actor " + actor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Actor actor) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Actor persistentActor = em.find(Actor.class, actor.getIdActor());
            List<Reparto> repartoListOld = persistentActor.getRepartoList();
            List<Reparto> repartoListNew = actor.getRepartoList();
            List<String> illegalOrphanMessages = null;
            for (Reparto repartoListOldReparto : repartoListOld) {
                if (!repartoListNew.contains(repartoListOldReparto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reparto " + repartoListOldReparto + " since its actor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Reparto> attachedRepartoListNew = new ArrayList<Reparto>();
            for (Reparto repartoListNewRepartoToAttach : repartoListNew) {
                repartoListNewRepartoToAttach = em.getReference(repartoListNewRepartoToAttach.getClass(), repartoListNewRepartoToAttach.getRepartoPK());
                attachedRepartoListNew.add(repartoListNewRepartoToAttach);
            }
            repartoListNew = attachedRepartoListNew;
            actor.setRepartoList(repartoListNew);
            actor = em.merge(actor);
            for (Reparto repartoListNewReparto : repartoListNew) {
                if (!repartoListOld.contains(repartoListNewReparto)) {
                    Actor oldActorOfRepartoListNewReparto = repartoListNewReparto.getActor();
                    repartoListNewReparto.setActor(actor);
                    repartoListNewReparto = em.merge(repartoListNewReparto);
                    if (oldActorOfRepartoListNewReparto != null && !oldActorOfRepartoListNewReparto.equals(actor)) {
                        oldActorOfRepartoListNewReparto.getRepartoList().remove(repartoListNewReparto);
                        oldActorOfRepartoListNewReparto = em.merge(oldActorOfRepartoListNewReparto);
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
                Integer id = actor.getIdActor();
                if (findActor(id) == null) {
                    throw new NonexistentEntityException("The actor with id " + id + " no longer exists.");
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
            Actor actor;
            try {
                actor = em.getReference(Actor.class, id);
                actor.getIdActor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Reparto> repartoListOrphanCheck = actor.getRepartoList();
            for (Reparto repartoListOrphanCheckReparto : repartoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Actor (" + actor + ") cannot be destroyed since the Reparto " + repartoListOrphanCheckReparto + " in its repartoList field has a non-nullable actor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(actor);
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

    public List<Actor> findActorEntities() {
        return findActorEntities(true, -1, -1);
    }

    public List<Actor> findActorEntities(int maxResults, int firstResult) {
        return findActorEntities(false, maxResults, firstResult);
    }

    private List<Actor> findActorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Actor.class));
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

    public Actor findActor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Actor.class, id);
        } finally {
            em.close();
        }
    }

    public int getActorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Actor> rt = cq.from(Actor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
