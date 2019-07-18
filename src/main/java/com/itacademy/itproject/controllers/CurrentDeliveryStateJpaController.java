/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itacademy.itproject.controllers;

import com.itacademy.itproject.domain.Exercise;
import com.itacademy.itproject.domain.Student;
import com.itacademy.itproject.exceptions.NonexistentEntityException;
import com.itacademy.itproject.exceptions.PreexistingEntityException;
import com.itacademy.itproject.models.CurrentDeliveryState;
import com.itacademy.itproject.models.CurrentDeliveryStatePK;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class CurrentDeliveryStateJpaController implements Serializable
{
    public CurrentDeliveryStateJpaController(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create(CurrentDeliveryState currentDeliveryState) throws PreexistingEntityException, Exception
    {
        if (currentDeliveryState.getCurrentDeliveryStatePK() == null)
        {
            currentDeliveryState.setCurrentDeliveryStatePK(new CurrentDeliveryStatePK());
        }
        currentDeliveryState.getCurrentDeliveryStatePK().setStudent(currentDeliveryState.getStudent1().getId());
        currentDeliveryState.getCurrentDeliveryStatePK().setExercise(currentDeliveryState.getExercise1().getId());
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Exercise exercise1 = currentDeliveryState.getExercise1();
            if (exercise1 != null)
            {
                exercise1 = em.getReference(exercise1.getClass(), exercise1.getId());
                currentDeliveryState.setExercise1(exercise1);
            }
            Student student1 = currentDeliveryState.getStudent1();
            if (student1 != null)
            {
                student1 = em.getReference(student1.getClass(), student1.getId());
                currentDeliveryState.setStudent1(student1);
            }
            em.persist(currentDeliveryState);
            if (exercise1 != null)
            {
                exercise1.getCurrentDeliveryStateCollection().add(currentDeliveryState);
                exercise1 = em.merge(exercise1);
            }
            if (student1 != null)
            {
                student1.getCurrentDeliveryStateCollection().add(currentDeliveryState);
                student1 = em.merge(student1);
            }
            em.getTransaction().commit();
        }
        catch (Exception ex)
        {
            if (findCurrentDeliveryState(currentDeliveryState.getCurrentDeliveryStatePK()) != null)
            {
                throw new PreexistingEntityException("CurrentDeliveryState " + currentDeliveryState + " already exists.", ex);
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

    public void edit(CurrentDeliveryState currentDeliveryState) throws NonexistentEntityException, Exception
    {
        currentDeliveryState.getCurrentDeliveryStatePK().setStudent(currentDeliveryState.getStudent1().getId());
        currentDeliveryState.getCurrentDeliveryStatePK().setExercise(currentDeliveryState.getExercise1().getId());
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            CurrentDeliveryState persistentCurrentDeliveryState = em.find(CurrentDeliveryState.class, currentDeliveryState.getCurrentDeliveryStatePK());
            Exercise exercise1Old = persistentCurrentDeliveryState.getExercise1();
            Exercise exercise1New = currentDeliveryState.getExercise1();
            Student student1Old = persistentCurrentDeliveryState.getStudent1();
            Student student1New = currentDeliveryState.getStudent1();
            if (exercise1New != null)
            {
                exercise1New = em.getReference(exercise1New.getClass(), exercise1New.getId());
                currentDeliveryState.setExercise1(exercise1New);
            }
            if (student1New != null)
            {
                student1New = em.getReference(student1New.getClass(), student1New.getId());
                currentDeliveryState.setStudent1(student1New);
            }
            currentDeliveryState = em.merge(currentDeliveryState);
            if (exercise1Old != null && !exercise1Old.equals(exercise1New))
            {
                exercise1Old.getCurrentDeliveryStateCollection().remove(currentDeliveryState);
                exercise1Old = em.merge(exercise1Old);
            }
            if (exercise1New != null && !exercise1New.equals(exercise1Old))
            {
                exercise1New.getCurrentDeliveryStateCollection().add(currentDeliveryState);
                exercise1New = em.merge(exercise1New);
            }
            if (student1Old != null && !student1Old.equals(student1New))
            {
                student1Old.getCurrentDeliveryStateCollection().remove(currentDeliveryState);
                student1Old = em.merge(student1Old);
            }
            if (student1New != null && !student1New.equals(student1Old))
            {
                student1New.getCurrentDeliveryStateCollection().add(currentDeliveryState);
                student1New = em.merge(student1New);
            }
            em.getTransaction().commit();
        }
        catch (Exception ex)
        {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0)
            {
                CurrentDeliveryStatePK id = currentDeliveryState.getCurrentDeliveryStatePK();
                if (findCurrentDeliveryState(id) == null)
                {
                    throw new NonexistentEntityException("The currentDeliveryState with id " + id + " no longer exists.");
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

    public void destroy(CurrentDeliveryStatePK id) throws NonexistentEntityException
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            CurrentDeliveryState currentDeliveryState;
            try
            {
                currentDeliveryState = em.getReference(CurrentDeliveryState.class, id);
                currentDeliveryState.getCurrentDeliveryStatePK();
            }
            catch (EntityNotFoundException enfe)
            {
                throw new NonexistentEntityException("The currentDeliveryState with id " + id + " no longer exists.", enfe);
            }
            Exercise exercise1 = currentDeliveryState.getExercise1();
            if (exercise1 != null)
            {
                exercise1.getCurrentDeliveryStateCollection().remove(currentDeliveryState);
                exercise1 = em.merge(exercise1);
            }
            Student student1 = currentDeliveryState.getStudent1();
            if (student1 != null)
            {
                student1.getCurrentDeliveryStateCollection().remove(currentDeliveryState);
                student1 = em.merge(student1);
            }
            em.remove(currentDeliveryState);
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

    public List<CurrentDeliveryState> findCurrentDeliveryStateEntities()
    {
        return findCurrentDeliveryStateEntities(true, -1, -1);
    }

    public List<CurrentDeliveryState> findCurrentDeliveryStateEntities(int maxResults, int firstResult)
    {
        return findCurrentDeliveryStateEntities(false, maxResults, firstResult);
    }

    private List<CurrentDeliveryState> findCurrentDeliveryStateEntities(boolean all, int maxResults, int firstResult)
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CurrentDeliveryState.class));
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

    public CurrentDeliveryState findCurrentDeliveryState(CurrentDeliveryStatePK id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(CurrentDeliveryState.class, id);
        }
        finally
        {
            em.close();
        }
    }

    public int getCurrentDeliveryStateCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CurrentDeliveryState> rt = cq.from(CurrentDeliveryState.class);
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
