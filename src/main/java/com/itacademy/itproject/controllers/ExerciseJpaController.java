/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itacademy.itproject.controllers;

import com.itacademy.itproject.domain.Exercise;
import com.itacademy.itproject.domain.Itinerary;
import com.itacademy.itproject.exceptions.IllegalOrphanException;
import com.itacademy.itproject.exceptions.NonexistentEntityException;
import com.itacademy.itproject.exceptions.PreexistingEntityException;
import com.itacademy.itproject.models.CurrentDeliveryState;
import com.itacademy.itproject.models.HistoricDeliveryState;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExerciseJpaController implements Serializable
{
    public ExerciseJpaController(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create(Exercise exercise) throws PreexistingEntityException, Exception
    {
        if (exercise.getCurrentDeliveryStateCollection() == null)
        {
            exercise.setCurrentDeliveryStateCollection(new ArrayList<CurrentDeliveryState>());
        }
        if (exercise.getHistoricDeliveryStateCollection() == null)
        {
            exercise.setHistoricDeliveryStateCollection(new ArrayList<HistoricDeliveryState>());
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Itinerary itinerary = exercise.getItinerary();
            if (itinerary != null)
            {
                itinerary = em.getReference(itinerary.getClass(), itinerary.getId());
                exercise.setItinerary(itinerary);
            }
            Collection<CurrentDeliveryState> attachedCurrentDeliveryStateCollection = new ArrayList<CurrentDeliveryState>();
            for (CurrentDeliveryState currentDeliveryStateCollectionCurrentDeliveryStateToAttach : exercise.getCurrentDeliveryStateCollection())
            {
                currentDeliveryStateCollectionCurrentDeliveryStateToAttach = em.getReference(currentDeliveryStateCollectionCurrentDeliveryStateToAttach.getClass(), currentDeliveryStateCollectionCurrentDeliveryStateToAttach.getCurrentDeliveryStatePK());
                attachedCurrentDeliveryStateCollection.add(currentDeliveryStateCollectionCurrentDeliveryStateToAttach);
            }
            exercise.setCurrentDeliveryStateCollection(attachedCurrentDeliveryStateCollection);
            Collection<HistoricDeliveryState> attachedHistoricDeliveryStateCollection = new ArrayList<HistoricDeliveryState>();
            for (HistoricDeliveryState historicDeliveryStateCollectionHistoricDeliveryStateToAttach : exercise.getHistoricDeliveryStateCollection())
            {
                historicDeliveryStateCollectionHistoricDeliveryStateToAttach = em.getReference(historicDeliveryStateCollectionHistoricDeliveryStateToAttach.getClass(), historicDeliveryStateCollectionHistoricDeliveryStateToAttach.getHistoricDeliveryStatePK());
                attachedHistoricDeliveryStateCollection.add(historicDeliveryStateCollectionHistoricDeliveryStateToAttach);
            }
            exercise.setHistoricDeliveryStateCollection(attachedHistoricDeliveryStateCollection);
            em.persist(exercise);
            if (itinerary != null)
            {
                itinerary.getExerciseCollection().add(exercise);
                itinerary = em.merge(itinerary);
            }
            for (CurrentDeliveryState currentDeliveryStateCollectionCurrentDeliveryState : exercise.getCurrentDeliveryStateCollection())
            {
                Exercise oldExercise1OfCurrentDeliveryStateCollectionCurrentDeliveryState = currentDeliveryStateCollectionCurrentDeliveryState.getExercise1();
                currentDeliveryStateCollectionCurrentDeliveryState.setExercise1(exercise);
                currentDeliveryStateCollectionCurrentDeliveryState = em.merge(currentDeliveryStateCollectionCurrentDeliveryState);
                if (oldExercise1OfCurrentDeliveryStateCollectionCurrentDeliveryState != null)
                {
                    oldExercise1OfCurrentDeliveryStateCollectionCurrentDeliveryState.getCurrentDeliveryStateCollection().remove(currentDeliveryStateCollectionCurrentDeliveryState);
                    oldExercise1OfCurrentDeliveryStateCollectionCurrentDeliveryState = em.merge(oldExercise1OfCurrentDeliveryStateCollectionCurrentDeliveryState);
                }
            }
            for (HistoricDeliveryState historicDeliveryStateCollectionHistoricDeliveryState : exercise.getHistoricDeliveryStateCollection())
            {
                Exercise oldExercise1OfHistoricDeliveryStateCollectionHistoricDeliveryState = historicDeliveryStateCollectionHistoricDeliveryState.getExercise1();
                historicDeliveryStateCollectionHistoricDeliveryState.setExercise1(exercise);
                historicDeliveryStateCollectionHistoricDeliveryState = em.merge(historicDeliveryStateCollectionHistoricDeliveryState);
                if (oldExercise1OfHistoricDeliveryStateCollectionHistoricDeliveryState != null)
                {
                    oldExercise1OfHistoricDeliveryStateCollectionHistoricDeliveryState.getHistoricDeliveryStateCollection().remove(historicDeliveryStateCollectionHistoricDeliveryState);
                    oldExercise1OfHistoricDeliveryStateCollectionHistoricDeliveryState = em.merge(oldExercise1OfHistoricDeliveryStateCollectionHistoricDeliveryState);
                }
            }
            em.getTransaction().commit();
        }
        catch (Exception ex)
        {
            if (findExercise(exercise.getId()) != null)
            {
                throw new PreexistingEntityException("Exercise " + exercise + " already exists.", ex);
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

    public void edit(Exercise exercise) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Exercise persistentExercise = em.find(Exercise.class, exercise.getId());
            Itinerary itineraryOld = persistentExercise.getItinerary();
            Itinerary itineraryNew = exercise.getItinerary();
            Collection<CurrentDeliveryState> currentDeliveryStateCollectionOld = persistentExercise.getCurrentDeliveryStateCollection();
            Collection<CurrentDeliveryState> currentDeliveryStateCollectionNew = exercise.getCurrentDeliveryStateCollection();
            Collection<HistoricDeliveryState> historicDeliveryStateCollectionOld = persistentExercise.getHistoricDeliveryStateCollection();
            Collection<HistoricDeliveryState> historicDeliveryStateCollectionNew = exercise.getHistoricDeliveryStateCollection();
            List<String> illegalOrphanMessages = null;
            for (CurrentDeliveryState currentDeliveryStateCollectionOldCurrentDeliveryState : currentDeliveryStateCollectionOld)
            {
                if (!currentDeliveryStateCollectionNew.contains(currentDeliveryStateCollectionOldCurrentDeliveryState))
                {
                    if (illegalOrphanMessages == null)
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CurrentDeliveryState " + currentDeliveryStateCollectionOldCurrentDeliveryState + " since its exercise1 field is not nullable.");
                }
            }
            for (HistoricDeliveryState historicDeliveryStateCollectionOldHistoricDeliveryState : historicDeliveryStateCollectionOld)
            {
                if (!historicDeliveryStateCollectionNew.contains(historicDeliveryStateCollectionOldHistoricDeliveryState))
                {
                    if (illegalOrphanMessages == null)
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HistoricDeliveryState " + historicDeliveryStateCollectionOldHistoricDeliveryState + " since its exercise1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null)
            {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (itineraryNew != null)
            {
                itineraryNew = em.getReference(itineraryNew.getClass(), itineraryNew.getId());
                exercise.setItinerary(itineraryNew);
            }
            Collection<CurrentDeliveryState> attachedCurrentDeliveryStateCollectionNew = new ArrayList<CurrentDeliveryState>();
            for (CurrentDeliveryState currentDeliveryStateCollectionNewCurrentDeliveryStateToAttach : currentDeliveryStateCollectionNew)
            {
                currentDeliveryStateCollectionNewCurrentDeliveryStateToAttach = em.getReference(currentDeliveryStateCollectionNewCurrentDeliveryStateToAttach.getClass(), currentDeliveryStateCollectionNewCurrentDeliveryStateToAttach.getCurrentDeliveryStatePK());
                attachedCurrentDeliveryStateCollectionNew.add(currentDeliveryStateCollectionNewCurrentDeliveryStateToAttach);
            }
            currentDeliveryStateCollectionNew = attachedCurrentDeliveryStateCollectionNew;
            exercise.setCurrentDeliveryStateCollection(currentDeliveryStateCollectionNew);
            Collection<HistoricDeliveryState> attachedHistoricDeliveryStateCollectionNew = new ArrayList<HistoricDeliveryState>();
            for (HistoricDeliveryState historicDeliveryStateCollectionNewHistoricDeliveryStateToAttach : historicDeliveryStateCollectionNew)
            {
                historicDeliveryStateCollectionNewHistoricDeliveryStateToAttach = em.getReference(historicDeliveryStateCollectionNewHistoricDeliveryStateToAttach.getClass(), historicDeliveryStateCollectionNewHistoricDeliveryStateToAttach.getHistoricDeliveryStatePK());
                attachedHistoricDeliveryStateCollectionNew.add(historicDeliveryStateCollectionNewHistoricDeliveryStateToAttach);
            }
            historicDeliveryStateCollectionNew = attachedHistoricDeliveryStateCollectionNew;
            exercise.setHistoricDeliveryStateCollection(historicDeliveryStateCollectionNew);
            exercise = em.merge(exercise);
            if (itineraryOld != null && !itineraryOld.equals(itineraryNew))
            {
                itineraryOld.getExerciseCollection().remove(exercise);
                itineraryOld = em.merge(itineraryOld);
            }
            if (itineraryNew != null && !itineraryNew.equals(itineraryOld))
            {
                itineraryNew.getExerciseCollection().add(exercise);
                itineraryNew = em.merge(itineraryNew);
            }
            for (CurrentDeliveryState currentDeliveryStateCollectionNewCurrentDeliveryState : currentDeliveryStateCollectionNew)
            {
                if (!currentDeliveryStateCollectionOld.contains(currentDeliveryStateCollectionNewCurrentDeliveryState))
                {
                    Exercise oldExercise1OfCurrentDeliveryStateCollectionNewCurrentDeliveryState = currentDeliveryStateCollectionNewCurrentDeliveryState.getExercise1();
                    currentDeliveryStateCollectionNewCurrentDeliveryState.setExercise1(exercise);
                    currentDeliveryStateCollectionNewCurrentDeliveryState = em.merge(currentDeliveryStateCollectionNewCurrentDeliveryState);
                    if (oldExercise1OfCurrentDeliveryStateCollectionNewCurrentDeliveryState != null && !oldExercise1OfCurrentDeliveryStateCollectionNewCurrentDeliveryState.equals(exercise))
                    {
                        oldExercise1OfCurrentDeliveryStateCollectionNewCurrentDeliveryState.getCurrentDeliveryStateCollection().remove(currentDeliveryStateCollectionNewCurrentDeliveryState);
                        oldExercise1OfCurrentDeliveryStateCollectionNewCurrentDeliveryState = em.merge(oldExercise1OfCurrentDeliveryStateCollectionNewCurrentDeliveryState);
                    }
                }
            }
            for (HistoricDeliveryState historicDeliveryStateCollectionNewHistoricDeliveryState : historicDeliveryStateCollectionNew)
            {
                if (!historicDeliveryStateCollectionOld.contains(historicDeliveryStateCollectionNewHistoricDeliveryState))
                {
                    Exercise oldExercise1OfHistoricDeliveryStateCollectionNewHistoricDeliveryState = historicDeliveryStateCollectionNewHistoricDeliveryState.getExercise1();
                    historicDeliveryStateCollectionNewHistoricDeliveryState.setExercise1(exercise);
                    historicDeliveryStateCollectionNewHistoricDeliveryState = em.merge(historicDeliveryStateCollectionNewHistoricDeliveryState);
                    if (oldExercise1OfHistoricDeliveryStateCollectionNewHistoricDeliveryState != null && !oldExercise1OfHistoricDeliveryStateCollectionNewHistoricDeliveryState.equals(exercise))
                    {
                        oldExercise1OfHistoricDeliveryStateCollectionNewHistoricDeliveryState.getHistoricDeliveryStateCollection().remove(historicDeliveryStateCollectionNewHistoricDeliveryState);
                        oldExercise1OfHistoricDeliveryStateCollectionNewHistoricDeliveryState = em.merge(oldExercise1OfHistoricDeliveryStateCollectionNewHistoricDeliveryState);
                    }
                }
            }
            em.getTransaction().commit();
        }
        catch (Exception ex)
        {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0)
            {
                Object id = exercise.getId();
                if (findExercise(id) == null)
                {
                    throw new NonexistentEntityException("The exercise with id " + id + " no longer exists.");
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

    public void destroy(Object id) throws IllegalOrphanException, NonexistentEntityException
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Exercise exercise;
            try
            {
                exercise = em.getReference(Exercise.class, id);
                exercise.getId();
            }
            catch (EntityNotFoundException enfe)
            {
                throw new NonexistentEntityException("The exercise with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<CurrentDeliveryState> currentDeliveryStateCollectionOrphanCheck = exercise.getCurrentDeliveryStateCollection();
            for (CurrentDeliveryState currentDeliveryStateCollectionOrphanCheckCurrentDeliveryState : currentDeliveryStateCollectionOrphanCheck)
            {
                if (illegalOrphanMessages == null)
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Exercise (" + exercise + ") cannot be destroyed since the CurrentDeliveryState " + currentDeliveryStateCollectionOrphanCheckCurrentDeliveryState + " in its currentDeliveryStateCollection field has a non-nullable exercise1 field.");
            }
            Collection<HistoricDeliveryState> historicDeliveryStateCollectionOrphanCheck = exercise.getHistoricDeliveryStateCollection();
            for (HistoricDeliveryState historicDeliveryStateCollectionOrphanCheckHistoricDeliveryState : historicDeliveryStateCollectionOrphanCheck)
            {
                if (illegalOrphanMessages == null)
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Exercise (" + exercise + ") cannot be destroyed since the HistoricDeliveryState " + historicDeliveryStateCollectionOrphanCheckHistoricDeliveryState + " in its historicDeliveryStateCollection field has a non-nullable exercise1 field.");
            }
            if (illegalOrphanMessages != null)
            {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Itinerary itinerary = exercise.getItinerary();
            if (itinerary != null)
            {
                itinerary.getExerciseCollection().remove(exercise);
                itinerary = em.merge(itinerary);
            }
            em.remove(exercise);
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

    public List<Exercise> findExerciseEntities()
    {
        return findExerciseEntities(true, -1, -1);
    }

    public List<Exercise> findExerciseEntities(int maxResults, int firstResult)
    {
        return findExerciseEntities(false, maxResults, firstResult);
    }

    private List<Exercise> findExerciseEntities(boolean all, int maxResults, int firstResult)
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Exercise.class));
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

    public Exercise findExercise(Object id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(Exercise.class, id);
        }
        finally
        {
            em.close();
        }
    }

    public int getExerciseCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Exercise> rt = cq.from(Exercise.class);
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
