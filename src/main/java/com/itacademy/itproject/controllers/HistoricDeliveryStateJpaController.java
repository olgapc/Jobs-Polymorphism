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
import com.itacademy.itproject.models.Exercise;
import com.itacademy.itproject.models.HistoricDeliveryState;
import com.itacademy.itproject.models.HistoricDeliveryStatePK;
import com.itacademy.itproject.models.Student;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class HistoricDeliveryStateJpaController implements Serializable
{
    public HistoricDeliveryStateJpaController(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create(HistoricDeliveryState historicDeliveryState) throws PreexistingEntityException, Exception
    {
        if (historicDeliveryState.getHistoricDeliveryStatePK() == null)
        {
            historicDeliveryState.setHistoricDeliveryStatePK(new HistoricDeliveryStatePK());
        }
        historicDeliveryState.getHistoricDeliveryStatePK().setStudent(historicDeliveryState.getStudent1().getId());
        historicDeliveryState.getHistoricDeliveryStatePK().setExercise(historicDeliveryState.getExercise1().getId());
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Exercise exercise1 = historicDeliveryState.getExercise1();
            if (exercise1 != null)
            {
                exercise1 = em.getReference(exercise1.getClass(), exercise1.getId());
                historicDeliveryState.setExercise1(exercise1);
            }
            Student student1 = historicDeliveryState.getStudent1();
            if (student1 != null)
            {
                student1 = em.getReference(student1.getClass(), student1.getId());
                historicDeliveryState.setStudent1(student1);
            }
            em.persist(historicDeliveryState);
            if (exercise1 != null)
            {
                exercise1.getHistoricDeliveryStateCollection().add(historicDeliveryState);
                exercise1 = em.merge(exercise1);
            }
            if (student1 != null)
            {
                student1.getHistoricDeliveryStateCollection().add(historicDeliveryState);
                student1 = em.merge(student1);
            }
            em.getTransaction().commit();
        }
        catch (Exception ex)
        {
            if (findHistoricDeliveryState(historicDeliveryState.getHistoricDeliveryStatePK()) != null)
            {
                throw new PreexistingEntityException("HistoricDeliveryState " + historicDeliveryState + " already exists.", ex);
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

    public void edit(HistoricDeliveryState historicDeliveryState) throws NonexistentEntityException, Exception
    {
        historicDeliveryState.getHistoricDeliveryStatePK().setStudent(historicDeliveryState.getStudent1().getId());
        historicDeliveryState.getHistoricDeliveryStatePK().setExercise(historicDeliveryState.getExercise1().getId());
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            HistoricDeliveryState persistentHistoricDeliveryState = em.find(HistoricDeliveryState.class, historicDeliveryState.getHistoricDeliveryStatePK());
            Exercise exercise1Old = persistentHistoricDeliveryState.getExercise1();
            Exercise exercise1New = historicDeliveryState.getExercise1();
            Student student1Old = persistentHistoricDeliveryState.getStudent1();
            Student student1New = historicDeliveryState.getStudent1();
            if (exercise1New != null)
            {
                exercise1New = em.getReference(exercise1New.getClass(), exercise1New.getId());
                historicDeliveryState.setExercise1(exercise1New);
            }
            if (student1New != null)
            {
                student1New = em.getReference(student1New.getClass(), student1New.getId());
                historicDeliveryState.setStudent1(student1New);
            }
            historicDeliveryState = em.merge(historicDeliveryState);
            if (exercise1Old != null && !exercise1Old.equals(exercise1New))
            {
                exercise1Old.getHistoricDeliveryStateCollection().remove(historicDeliveryState);
                exercise1Old = em.merge(exercise1Old);
            }
            if (exercise1New != null && !exercise1New.equals(exercise1Old))
            {
                exercise1New.getHistoricDeliveryStateCollection().add(historicDeliveryState);
                exercise1New = em.merge(exercise1New);
            }
            if (student1Old != null && !student1Old.equals(student1New))
            {
                student1Old.getHistoricDeliveryStateCollection().remove(historicDeliveryState);
                student1Old = em.merge(student1Old);
            }
            if (student1New != null && !student1New.equals(student1Old))
            {
                student1New.getHistoricDeliveryStateCollection().add(historicDeliveryState);
                student1New = em.merge(student1New);
            }
            em.getTransaction().commit();
        }
        catch (Exception ex)
        {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0)
            {
                HistoricDeliveryStatePK id = historicDeliveryState.getHistoricDeliveryStatePK();
                if (findHistoricDeliveryState(id) == null)
                {
                    throw new NonexistentEntityException("The historicDeliveryState with id " + id + " no longer exists.");
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

    public void destroy(HistoricDeliveryStatePK id) throws NonexistentEntityException
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            HistoricDeliveryState historicDeliveryState;
            try
            {
                historicDeliveryState = em.getReference(HistoricDeliveryState.class, id);
                historicDeliveryState.getHistoricDeliveryStatePK();
            }
            catch (EntityNotFoundException enfe)
            {
                throw new NonexistentEntityException("The historicDeliveryState with id " + id + " no longer exists.", enfe);
            }
            Exercise exercise1 = historicDeliveryState.getExercise1();
            if (exercise1 != null)
            {
                exercise1.getHistoricDeliveryStateCollection().remove(historicDeliveryState);
                exercise1 = em.merge(exercise1);
            }
            Student student1 = historicDeliveryState.getStudent1();
            if (student1 != null)
            {
                student1.getHistoricDeliveryStateCollection().remove(historicDeliveryState);
                student1 = em.merge(student1);
            }
            em.remove(historicDeliveryState);
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

    public List<HistoricDeliveryState> findHistoricDeliveryStateEntities()
    {
        return findHistoricDeliveryStateEntities(true, -1, -1);
    }

    public List<HistoricDeliveryState> findHistoricDeliveryStateEntities(int maxResults, int firstResult)
    {
        return findHistoricDeliveryStateEntities(false, maxResults, firstResult);
    }

    private List<HistoricDeliveryState> findHistoricDeliveryStateEntities(boolean all, int maxResults, int firstResult)
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HistoricDeliveryState.class));
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

    public HistoricDeliveryState findHistoricDeliveryState(HistoricDeliveryStatePK id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(HistoricDeliveryState.class, id);
        }
        finally
        {
            em.close();
        }
    }

    public int getHistoricDeliveryStateCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HistoricDeliveryState> rt = cq.from(HistoricDeliveryState.class);
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
