/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Servicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.PaqueteServicio;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Leandro
 */
public class PaqueteServicioJpaController implements Serializable {

    public PaqueteServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public PaqueteServicioJpaController(){
    emf = Persistence.createEntityManagerFactory("TpFinal3PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PaqueteServicio paqueteServicio) {
        if (paqueteServicio.getListaServicio() == null) {
            paqueteServicio.setListaServicio(new ArrayList<Servicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Servicio> attachedListaServicio = new ArrayList<Servicio>();
            for (Servicio listaServicioServicioToAttach : paqueteServicio.getListaServicio()) {
                listaServicioServicioToAttach = em.getReference(listaServicioServicioToAttach.getClass(), listaServicioServicioToAttach.getCodigo_servicio());
                attachedListaServicio.add(listaServicioServicioToAttach);
            }
            paqueteServicio.setListaServicio(attachedListaServicio);
            em.persist(paqueteServicio);
            for (Servicio listaServicioServicio : paqueteServicio.getListaServicio()) {
                listaServicioServicio.getListaPaquetes().add(paqueteServicio);
                listaServicioServicio = em.merge(listaServicioServicio);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PaqueteServicio paqueteServicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PaqueteServicio persistentPaqueteServicio = em.find(PaqueteServicio.class, paqueteServicio.getCodigo_paquete());
            List<Servicio> listaServicioOld = persistentPaqueteServicio.getListaServicio();
            List<Servicio> listaServicioNew = paqueteServicio.getListaServicio();
            List<Servicio> attachedListaServicioNew = new ArrayList<Servicio>();
            for (Servicio listaServicioNewServicioToAttach : listaServicioNew) {
                listaServicioNewServicioToAttach = em.getReference(listaServicioNewServicioToAttach.getClass(), listaServicioNewServicioToAttach.getCodigo_servicio());
                attachedListaServicioNew.add(listaServicioNewServicioToAttach);
            }
            listaServicioNew = attachedListaServicioNew;
            paqueteServicio.setListaServicio(listaServicioNew);
            paqueteServicio = em.merge(paqueteServicio);
            for (Servicio listaServicioOldServicio : listaServicioOld) {
                if (!listaServicioNew.contains(listaServicioOldServicio)) {
                    listaServicioOldServicio.getListaPaquetes().remove(paqueteServicio);
                    listaServicioOldServicio = em.merge(listaServicioOldServicio);
                }
            }
            for (Servicio listaServicioNewServicio : listaServicioNew) {
                if (!listaServicioOld.contains(listaServicioNewServicio)) {
                    listaServicioNewServicio.getListaPaquetes().add(paqueteServicio);
                    listaServicioNewServicio = em.merge(listaServicioNewServicio);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = paqueteServicio.getCodigo_paquete();
                if (findPaqueteServicio(id) == null) {
                    throw new NonexistentEntityException("The paqueteServicio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PaqueteServicio paqueteServicio;
            try {
                paqueteServicio = em.getReference(PaqueteServicio.class, id);
                paqueteServicio.getCodigo_paquete();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paqueteServicio with id " + id + " no longer exists.", enfe);
            }
            List<Servicio> listaServicio = paqueteServicio.getListaServicio();
            for (Servicio listaServicioServicio : listaServicio) {
                listaServicioServicio.getListaPaquetes().remove(paqueteServicio);
                listaServicioServicio = em.merge(listaServicioServicio);
            }
            em.remove(paqueteServicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PaqueteServicio> findPaqueteServicioEntities() {
        return findPaqueteServicioEntities(true, -1, -1);
    }

    public List<PaqueteServicio> findPaqueteServicioEntities(int maxResults, int firstResult) {
        return findPaqueteServicioEntities(false, maxResults, firstResult);
    }

    private List<PaqueteServicio> findPaqueteServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PaqueteServicio.class));
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

    public PaqueteServicio findPaqueteServicio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PaqueteServicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaqueteServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PaqueteServicio> rt = cq.from(PaqueteServicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
