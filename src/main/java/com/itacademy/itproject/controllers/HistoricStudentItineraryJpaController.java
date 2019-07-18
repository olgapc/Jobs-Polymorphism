/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itacademy.itproject.controllers;

import com.itacademy.itproject.exceptions.NonexistentEntityException;
import com.itacademy.itproject.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.itacademy.itproject.models.HistoricStudentItinerary;
import com.itacademy.itproject.models.HistoricStudentItineraryPK;
import com.itacademy.itproject.models.Itinerary;
import com.itacademy.itproject.models.Student;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class HistoricStudentItineraryJpaController implements Serializable
{
    public HistoricStudentItineraryJpaController(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create(HistoricStudentItinerary historicStudentItinerary) throws PreexistingEntityException, Exception
    {
        if (historicStudentItinerary.getHistoricStudentItineraryPK() == null)
        {
            historicStudentItinerary.setHistoricStudentItineraryPK(new HistoricStudentItineraryPK());
        }
        historicStudentItinerary.getHistoricStudentItineraryPK().setItinerary(historicStudentItinerary.getItinerary1().getId());
        historicStudentItinerary.getHistoricStudentItineraryPK().setStudent(historicStudentItinerary.getStudent1().getId());
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Itinerary itinerary1 = historicStudentItinerary.getItinerary1();
            if (itinerary1 != null)
            {
                itinerary1 = em.getReference(itinerary1.getClass(), itinerary1.getId());
                historicStudentItinerary.setItinerary1(itinerary1);
            }
            Student student1 = historicStudentItinerary.getStudent1();
            if (student1 != null)
            {
                student1 = em.getReference(student1.getClass(), student1.getId());
                historicStudentItinerary.setStudent1(student1);
            }
            em.persist(historicStudentItinerary);
            if (itinerary1 != null)
            {
                itinerary1.getHistoricStudentItineraryCollection().add(historicStudentItinerary);
                itinerary1 = em.merge(itinerary1);
            }
            if (student1 != null)
            {
                student1.getHistoricStudentItineraryCollection().add(historicStudentItinerary);
                student1 = em.merge(student1);
            }
            em.getTransaction().commit();
        }
        catch (Exception ex)
        {
            if (findHistoricStudentItinerary(historicStudentItinerary.getHistoricStudentItineraryPK()) != null)
            {
                throw new PreexistingEntityException("HistoricStudentItinerary " + historicStudentItinerary + " already exists.", ex);
            }
            throw ex;
        }
        finally
        {
            if (em != null)
            {
                em.close();
            }
        }
    }

    public void edit(HistoricStudentItinerary historicStudentItinerary) throws NonexistentEntityException, Exception
    {
        historicStudentItinerary.getHistoricStudentItineraryPK().setItinerary(historicStudentItinerary.getItinerary1().getId());
        historicStudentItinerary.getHistoricStudentItineraryPK().setStudent(historicStudentItinerary.getStudent1().getId());
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            HistoricStudentItinerary persistentHistoricStudentItinerary = em.find(HistoricStudentItinerary.class, historicStudentItinerary.getHistoricStudentItineraryPK());
            Itinerary itinerary1Old = persistentHistoricStudentItinerary.getItinerary1();
            Itinerary itinerary1New = historicStudentItinerary.getItinerary1();
            Student student1Old = persistentHistoricStudentItinerary.getStudent1();
            Student student1New = historicStudentItinerary.getStudent1();
            if (itinerary1New != null)
            {
                itinerary1New = em.getReference(itinerary1New.getClass(), itinerary1New.getId());
                historicStudentItinerary.setItinerary1(itinerary1New);
            }
            if (student1New != null)
            {
                student1New = em.getReference(student1New.getClass(), student1New.getId());
                historicStudentItinerary.setStudent1(student1New);
            }
            historicStudentItinerary = em.merge(historicStudentItinerary);
            if (itinerary1Old != null && !itinerary1Old.equals(itinerary1New))
            {
                itinerary1Old.getHistoricStudentItineraryCollection().remove(historicStudentItinerary);
                itinerary1Old = em.merge(itinerary1Old);
            }
            if (itinerary1New != null && !itinerary1New.equals(itinerary1Old))
            {
                itinerary1New.getHistoricStudentItineraryCollection().add(historicStudentItinerary);
                itinerary1New = em.merge(itinerary1New);
            }
            if (student1Old != null && !student1Old.equals(student1New))
            {
                student1Old.getHistoricStudentItineraryCollection().remove(historicStudentItinerary);
                student1Old = em.merge(student1Old);
            }
            if (student1New != null && !student1New.equals(student1Old))
            {
                student1New.getHistoricStudentItineraryCollection().add(historicStudentItinerary);
                student1New = em.merge(student1New);
            }
            em.getTransaction().commit();
        }
        catch (Exception ex)
        {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0)
            {
                HistoricStudentItineraryPK id = historicStudentItinerary.getHistoricStudentItineraryPK();
                if (findHistoricStudentItinerary(id) == null)
                {
                    throw new NonexistentEntityException("The historicStudentItinerary with id " + id + " no longer exists.");
                }
            }
            throw ex;
        }
        finally
        {
            if (em != null)
            {
                em.close();
            }
        }
    }

    public void destroy(HistoricStudentItineraryPK id) throws NonexistentEntityException
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            HistoricStudentItinerary historicStudentItinerary;
            try
            {
                historicStudentItinerary = em.getReference(HistoricStudentItinerary.class, id);
                historicStudentItinerary.getHistoricStudentItineraryPK();
            }
            catch (EntityNotFoundException enfe)
            {
                throw new NonexistentEntityException("The historicStudentItinerary with id " + id + " no longer exists.", enfe);
            }
            Itinerary itinerary1 = historicStudentItinerary.getItinerary1();
            if (itinerary1 != null)
            {
                itinerary1.getHistoricStudentItineraryCollection().remove(historicStudentItinerary);
                itinerary1 = em.merge(itinerary1);
            }
            Student student1 = historicStudentItinerary.getStudent1();
            if (student1 != null)
            {
                student1.getHistoricStudentItineraryCollection().remove(historicStudentItinerary);
                student1 = em.merge(student1);
            }
            em.remove(historicStudentItinerary);
            em.getTransaction().commit();
        }
        finally
        {
            if (em != null)
            {
                em.close();
            }
        }
    }

    public List<HistoricStudentItinerary> findHistoricStudentItineraryEntities()
    {
        return findHistoricStudentItineraryEntities(true, -1, -1);
    }

    public List<HistoricStudentItinerary> findHistoricStudentItineraryEntities(int maxResults, int firstResult)
    {
        return findHistoricStudentItineraryEntities(false, maxResults, firstResult);
    }

    private List<HistoricStudentItinerary> findHistoricStudentItineraryEntities(boolean all, int maxResults, int firstResult)
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HistoricStudentItinerary.class));
            Query q = em.createQuery(cq);
            if (!all)
            {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        }
        finally
        {
            em.close();
        }
    }

    public HistoricStudentItinerary findHistoricStudentItinerary(HistoricStudentItineraryPK id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(HistoricStudentItinerary.class, id);
        }
        finally
        {
            em.close();
        }
    }

    public int getHistoricStudentItineraryCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HistoricStudentItinerary> rt = cq.from(HistoricStudentItinerary.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        }
        finally
        {
            em.close();
        }
    }

}
