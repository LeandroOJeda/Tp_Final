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
import logica.PaqueteServicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Servicio;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Leandro
 */
public class ServicioJpaController implements Serializable {

    public ServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public ServicioJpaController(){
    emf = Persistence.createEntityManagerFactory("TpFinal3PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicio servicio) {
        if (servicio.getListaPaquetes() == null) {
            servicio.setListaPaquetes(new ArrayList<PaqueteServicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PaqueteServicio> attachedListaPaquetes = new ArrayList<PaqueteServicio>();
            for (PaqueteServicio listaPaquetesPaqueteServicioToAttach : servicio.getListaPaquetes()) {
                listaPaquetesPaqueteServicioToAttach = em.getReference(listaPaquetesPaqueteServicioToAttach.getClass(), listaPaquetesPaqueteServicioToAttach.getCodigo_paquete());
                attachedListaPaquetes.add(listaPaquetesPaqueteServicioToAttach);
            }
            servicio.setListaPaquetes(attachedListaPaquetes);
            em.persist(servicio);
            for (PaqueteServicio listaPaquetesPaqueteServicio : servicio.getListaPaquetes()) {
                listaPaquetesPaqueteServicio.getListaServicio().add(servicio);
                listaPaquetesPaqueteServicio = em.merge(listaPaquetesPaqueteServicio);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicio servicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio persistentServicio = em.find(Servicio.class, servicio.getCodigo_servicio());
            List<PaqueteServicio> listaPaquetesOld = persistentServicio.getListaPaquetes();
            List<PaqueteServicio> listaPaquetesNew = servicio.getListaPaquetes();
            List<PaqueteServicio> attachedListaPaquetesNew = new ArrayList<PaqueteServicio>();
            for (PaqueteServicio listaPaquetesNewPaqueteServicioToAttach : listaPaquetesNew) {
                listaPaquetesNewPaqueteServicioToAttach = em.getReference(listaPaquetesNewPaqueteServicioToAttach.getClass(), listaPaquetesNewPaqueteServicioToAttach.getCodigo_paquete());
                attachedListaPaquetesNew.add(listaPaquetesNewPaqueteServicioToAttach);
            }
            listaPaquetesNew = attachedListaPaquetesNew;
            servicio.setListaPaquetes(listaPaquetesNew);
            servicio = em.merge(servicio);
            for (PaqueteServicio listaPaquetesOldPaqueteServicio : listaPaquetesOld) {
                if (!listaPaquetesNew.contains(listaPaquetesOldPaqueteServicio)) {
                    listaPaquetesOldPaqueteServicio.getListaServicio().remove(servicio);
                    listaPaquetesOldPaqueteServicio = em.merge(listaPaquetesOldPaqueteServicio);
                }
            }
            for (PaqueteServicio listaPaquetesNewPaqueteServicio : listaPaquetesNew) {
                if (!listaPaquetesOld.contains(listaPaquetesNewPaqueteServicio)) {
                    listaPaquetesNewPaqueteServicio.getListaServicio().add(servicio);
                    listaPaquetesNewPaqueteServicio = em.merge(listaPaquetesNewPaqueteServicio);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = servicio.getCodigo_servicio();
                if (findServicio(id) == null) {
                    throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.");
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
            Servicio servicio;
            try {
                servicio = em.getReference(Servicio.class, id);
                servicio.getCodigo_servicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.", enfe);
            }
            List<PaqueteServicio> listaPaquetes = servicio.getListaPaquetes();
            for (PaqueteServicio listaPaquetesPaqueteServicio : listaPaquetes) {
                listaPaquetesPaqueteServicio.getListaServicio().remove(servicio);
                listaPaquetesPaqueteServicio = em.merge(listaPaquetesPaqueteServicio);
            }
            em.remove(servicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicio> findServicioEntities() {
        return findServicioEntities(true, -1, -1);
    }

    public List<Servicio> findServicioEntities(int maxResults, int firstResult) {
        return findServicioEntities(false, maxResults, firstResult);
    }

    private List<Servicio> findServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicio.class));
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

    public Servicio findServicio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicio> rt = cq.from(Servicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
