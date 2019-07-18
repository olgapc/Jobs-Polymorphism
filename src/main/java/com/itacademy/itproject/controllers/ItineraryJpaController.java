/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itacademy.itproject.controllers;

import com.itacademy.itproject.exceptions.IllegalOrphanException;
import com.itacademy.itproject.exceptions.NonexistentEntityException;
import com.itacademy.itproject.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.itacademy.itproject.models.User;
import com.itacademy.itproject.models.HistoricStudentItinerary;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import com.itacademy.itproject.models.Student;
import com.itacademy.itproject.models.Exercise;
import com.itacademy.itproject.models.Itinerary;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class ItineraryJpaController implements Serializable
{
    public ItineraryJpaController(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create(Itinerary itinerary) throws PreexistingEntityException, Exception
    {
        if (itinerary.getHistoricStudentItineraryCollection() == null)
        {
            itinerary.setHistoricStudentItineraryCollection(new ArrayList<HistoricStudentItinerary>());
        }
        if (itinerary.getStudentCollection() == null)
        {
            itinerary.setStudentCollection(new ArrayList<Student>());
        }
        if (itinerary.getExerciseCollection() == null)
        {
            itinerary.setExerciseCollection(new ArrayList<Exercise>());
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            User teacher = itinerary.getTeacher();
            if (teacher != null)
            {
                teacher = em.getReference(teacher.getClass(), teacher.getId());
                itinerary.setTeacher(teacher);
            }
            Collection<HistoricStudentItinerary> attachedHistoricStudentItineraryCollection = new ArrayList<HistoricStudentItinerary>();
            for (HistoricStudentItinerary historicStudentItineraryCollectionHistoricStudentItineraryToAttach : itinerary.getHistoricStudentItineraryCollection())
            {
                historicStudentItineraryCollectionHistoricStudentItineraryToAttach = em.getReference(historicStudentItineraryCollectionHistoricStudentItineraryToAttach.getClass(), historicStudentItineraryCollectionHistoricStudentItineraryToAttach.getHistoricStudentItineraryPK());
                attachedHistoricStudentItineraryCollection.add(historicStudentItineraryCollectionHistoricStudentItineraryToAttach);
            }
            itinerary.setHistoricStudentItineraryCollection(attachedHistoricStudentItineraryCollection);
            Collection<Student> attachedStudentCollection = new ArrayList<Student>();
            for (Student studentCollectionStudentToAttach : itinerary.getStudentCollection())
            {
                studentCollectionStudentToAttach = em.getReference(studentCollectionStudentToAttach.getClass(), studentCollectionStudentToAttach.getId());
                attachedStudentCollection.add(studentCollectionStudentToAttach);
            }
            itinerary.setStudentCollection(attachedStudentCollection);
            Collection<Exercise> attachedExerciseCollection = new ArrayList<Exercise>();
            for (Exercise exerciseCollectionExerciseToAttach : itinerary.getExerciseCollection())
            {
                exerciseCollectionExerciseToAttach = em.getReference(exerciseCollectionExerciseToAttach.getClass(), exerciseCollectionExerciseToAttach.getId());
                attachedExerciseCollection.add(exerciseCollectionExerciseToAttach);
            }
            itinerary.setExerciseCollection(attachedExerciseCollection);
            em.persist(itinerary);
            if (teacher != null)
            {
                teacher.getItineraryCollection().add(itinerary);
                teacher = em.merge(teacher);
            }
            for (HistoricStudentItinerary historicStudentItineraryCollectionHistoricStudentItinerary : itinerary.getHistoricStudentItineraryCollection())
            {
                Itinerary oldItinerary1OfHistoricStudentItineraryCollectionHistoricStudentItinerary = historicStudentItineraryCollectionHistoricStudentItinerary.getItinerary1();
                historicStudentItineraryCollectionHistoricStudentItinerary.setItinerary1(itinerary);
                historicStudentItineraryCollectionHistoricStudentItinerary = em.merge(historicStudentItineraryCollectionHistoricStudentItinerary);
                if (oldItinerary1OfHistoricStudentItineraryCollectionHistoricStudentItinerary != null)
                {
                    oldItinerary1OfHistoricStudentItineraryCollectionHistoricStudentItinerary.getHistoricStudentItineraryCollection().remove(historicStudentItineraryCollectionHistoricStudentItinerary);
                    oldItinerary1OfHistoricStudentItineraryCollectionHistoricStudentItinerary = em.merge(oldItinerary1OfHistoricStudentItineraryCollectionHistoricStudentItinerary);
                }
            }
            for (Student studentCollectionStudent : itinerary.getStudentCollection())
            {
                Itinerary oldItineraryOfStudentCollectionStudent = studentCollectionStudent.getItinerary();
                studentCollectionStudent.setItinerary(itinerary);
                studentCollectionStudent = em.merge(studentCollectionStudent);
                if (oldItineraryOfStudentCollectionStudent != null)
                {
                    oldItineraryOfStudentCollectionStudent.getStudentCollection().remove(studentCollectionStudent);
                    oldItineraryOfStudentCollectionStudent = em.merge(oldItineraryOfStudentCollectionStudent);
                }
            }
            for (Exercise exerciseCollectionExercise : itinerary.getExerciseCollection())
            {
                Itinerary oldItineraryOfExerciseCollectionExercise = exerciseCollectionExercise.getItinerary();
                exerciseCollectionExercise.setItinerary(itinerary);
                exerciseCollectionExercise = em.merge(exerciseCollectionExercise);
                if (oldItineraryOfExerciseCollectionExercise != null)
                {
                    oldItineraryOfExerciseCollectionExercise.getExerciseCollection().remove(exerciseCollectionExercise);
                    oldItineraryOfExerciseCollectionExercise = em.merge(oldItineraryOfExerciseCollectionExercise);
                }
            }
            em.getTransaction().commit();
        }
        catch (Exception ex)
        {
            if (findItinerary(itinerary.getId()) != null)
            {
                throw new PreexistingEntityException("Itinerary " + itinerary + " already exists.", ex);
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

    public void edit(Itinerary itinerary) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Itinerary persistentItinerary = em.find(Itinerary.class, itinerary.getId());
            User teacherOld = persistentItinerary.getTeacher();
            User teacherNew = itinerary.getTeacher();
            Collection<HistoricStudentItinerary> historicStudentItineraryCollectionOld = persistentItinerary.getHistoricStudentItineraryCollection();
            Collection<HistoricStudentItinerary> historicStudentItineraryCollectionNew = itinerary.getHistoricStudentItineraryCollection();
            Collection<Student> studentCollectionOld = persistentItinerary.getStudentCollection();
            Collection<Student> studentCollectionNew = itinerary.getStudentCollection();
            Collection<Exercise> exerciseCollectionOld = persistentItinerary.getExerciseCollection();
            Collection<Exercise> exerciseCollectionNew = itinerary.getExerciseCollection();
            List<String> illegalOrphanMessages = null;
            for (HistoricStudentItinerary historicStudentItineraryCollectionOldHistoricStudentItinerary : historicStudentItineraryCollectionOld)
            {
                if (!historicStudentItineraryCollectionNew.contains(historicStudentItineraryCollectionOldHistoricStudentItinerary))
                {
                    if (illegalOrphanMessages == null)
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HistoricStudentItinerary " + historicStudentItineraryCollectionOldHistoricStudentItinerary + " since its itinerary1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null)
            {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (teacherNew != null)
            {
                teacherNew = em.getReference(teacherNew.getClass(), teacherNew.getId());
                itinerary.setTeacher(teacherNew);
            }
            Collection<HistoricStudentItinerary> attachedHistoricStudentItineraryCollectionNew = new ArrayList<HistoricStudentItinerary>();
            for (HistoricStudentItinerary historicStudentItineraryCollectionNewHistoricStudentItineraryToAttach : historicStudentItineraryCollectionNew)
            {
                historicStudentItineraryCollectionNewHistoricStudentItineraryToAttach = em.getReference(historicStudentItineraryCollectionNewHistoricStudentItineraryToAttach.getClass(), historicStudentItineraryCollectionNewHistoricStudentItineraryToAttach.getHistoricStudentItineraryPK());
                attachedHistoricStudentItineraryCollectionNew.add(historicStudentItineraryCollectionNewHistoricStudentItineraryToAttach);
            }
            historicStudentItineraryCollectionNew = attachedHistoricStudentItineraryCollectionNew;
            itinerary.setHistoricStudentItineraryCollection(historicStudentItineraryCollectionNew);
            Collection<Student> attachedStudentCollectionNew = new ArrayList<Student>();
            for (Student studentCollectionNewStudentToAttach : studentCollectionNew)
            {
                studentCollectionNewStudentToAttach = em.getReference(studentCollectionNewStudentToAttach.getClass(), studentCollectionNewStudentToAttach.getId());
                attachedStudentCollectionNew.add(studentCollectionNewStudentToAttach);
            }
            studentCollectionNew = attachedStudentCollectionNew;
            itinerary.setStudentCollection(studentCollectionNew);
            Collection<Exercise> attachedExerciseCollectionNew = new ArrayList<Exercise>();
            for (Exercise exerciseCollectionNewExerciseToAttach : exerciseCollectionNew)
            {
                exerciseCollectionNewExerciseToAttach = em.getReference(exerciseCollectionNewExerciseToAttach.getClass(), exerciseCollectionNewExerciseToAttach.getId());
                attachedExerciseCollectionNew.add(exerciseCollectionNewExerciseToAttach);
            }
            exerciseCollectionNew = attachedExerciseCollectionNew;
            itinerary.setExerciseCollection(exerciseCollectionNew);
            itinerary = em.merge(itinerary);
            if (teacherOld != null && !teacherOld.equals(teacherNew))
            {
                teacherOld.getItineraryCollection().remove(itinerary);
                teacherOld = em.merge(teacherOld);
            }
            if (teacherNew != null && !teacherNew.equals(teacherOld))
            {
                teacherNew.getItineraryCollection().add(itinerary);
                teacherNew = em.merge(teacherNew);
            }
            for (HistoricStudentItinerary historicStudentItineraryCollectionNewHistoricStudentItinerary : historicStudentItineraryCollectionNew)
            {
                if (!historicStudentItineraryCollectionOld.contains(historicStudentItineraryCollectionNewHistoricStudentItinerary))
                {
                    Itinerary oldItinerary1OfHistoricStudentItineraryCollectionNewHistoricStudentItinerary = historicStudentItineraryCollectionNewHistoricStudentItinerary.getItinerary1();
                    historicStudentItineraryCollectionNewHistoricStudentItinerary.setItinerary1(itinerary);
                    historicStudentItineraryCollectionNewHistoricStudentItinerary = em.merge(historicStudentItineraryCollectionNewHistoricStudentItinerary);
                    if (oldItinerary1OfHistoricStudentItineraryCollectionNewHistoricStudentItinerary != null && !oldItinerary1OfHistoricStudentItineraryCollectionNewHistoricStudentItinerary.equals(itinerary))
                    {
                        oldItinerary1OfHistoricStudentItineraryCollectionNewHistoricStudentItinerary.getHistoricStudentItineraryCollection().remove(historicStudentItineraryCollectionNewHistoricStudentItinerary);
                        oldItinerary1OfHistoricStudentItineraryCollectionNewHistoricStudentItinerary = em.merge(oldItinerary1OfHistoricStudentItineraryCollectionNewHistoricStudentItinerary);
                    }
                }
            }
            for (Student studentCollectionOldStudent : studentCollectionOld)
            {
                if (!studentCollectionNew.contains(studentCollectionOldStudent))
                {
                    studentCollectionOldStudent.setItinerary(null);
                    studentCollectionOldStudent = em.merge(studentCollectionOldStudent);
                }
            }
            for (Student studentCollectionNewStudent : studentCollectionNew)
            {
                if (!studentCollectionOld.contains(studentCollectionNewStudent))
                {
                    Itinerary oldItineraryOfStudentCollectionNewStudent = studentCollectionNewStudent.getItinerary();
                    studentCollectionNewStudent.setItinerary(itinerary);
                    studentCollectionNewStudent = em.merge(studentCollectionNewStudent);
                    if (oldItineraryOfStudentCollectionNewStudent != null && !oldItineraryOfStudentCollectionNewStudent.equals(itinerary))
                    {
                        oldItineraryOfStudentCollectionNewStudent.getStudentCollection().remove(studentCollectionNewStudent);
                        oldItineraryOfStudentCollectionNewStudent = em.merge(oldItineraryOfStudentCollectionNewStudent);
                    }
                }
            }
            for (Exercise exerciseCollectionOldExercise : exerciseCollectionOld)
            {
                if (!exerciseCollectionNew.contains(exerciseCollectionOldExercise))
                {
                    exerciseCollectionOldExercise.setItinerary(null);
                    exerciseCollectionOldExercise = em.merge(exerciseCollectionOldExercise);
                }
            }
            for (Exercise exerciseCollectionNewExercise : exerciseCollectionNew)
            {
                if (!exerciseCollectionOld.contains(exerciseCollectionNewExercise))
                {
                    Itinerary oldItineraryOfExerciseCollectionNewExercise = exerciseCollectionNewExercise.getItinerary();
                    exerciseCollectionNewExercise.setItinerary(itinerary);
                    exerciseCollectionNewExercise = em.merge(exerciseCollectionNewExercise);
                    if (oldItineraryOfExerciseCollectionNewExercise != null && !oldItineraryOfExerciseCollectionNewExercise.equals(itinerary))
                    {
                        oldItineraryOfExerciseCollectionNewExercise.getExerciseCollection().remove(exerciseCollectionNewExercise);
                        oldItineraryOfExerciseCollectionNewExercise = em.merge(oldItineraryOfExerciseCollectionNewExercise);
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
                Object id = itinerary.getId();
                if (findItinerary(id) == null)
                {
                    throw new NonexistentEntityException("The itinerary with id " + id + " no longer exists.");
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
            Itinerary itinerary;
            try
            {
                itinerary = em.getReference(Itinerary.class, id);
                itinerary.getId();
            }
            catch (EntityNotFoundException enfe)
            {
                throw new NonexistentEntityException("The itinerary with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<HistoricStudentItinerary> historicStudentItineraryCollectionOrphanCheck = itinerary.getHistoricStudentItineraryCollection();
            for (HistoricStudentItinerary historicStudentItineraryCollectionOrphanCheckHistoricStudentItinerary : historicStudentItineraryCollectionOrphanCheck)
            {
                if (illegalOrphanMessages == null)
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Itinerary (" + itinerary + ") cannot be destroyed since the HistoricStudentItinerary " + historicStudentItineraryCollectionOrphanCheckHistoricStudentItinerary + " in its historicStudentItineraryCollection field has a non-nullable itinerary1 field.");
            }
            if (illegalOrphanMessages != null)
            {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            User teacher = itinerary.getTeacher();
            if (teacher != null)
            {
                teacher.getItineraryCollection().remove(itinerary);
                teacher = em.merge(teacher);
            }
            Collection<Student> studentCollection = itinerary.getStudentCollection();
            for (Student studentCollectionStudent : studentCollection)
            {
                studentCollectionStudent.setItinerary(null);
                studentCollectionStudent = em.merge(studentCollectionStudent);
            }
            Collection<Exercise> exerciseCollection = itinerary.getExerciseCollection();
            for (Exercise exerciseCollectionExercise : exerciseCollection)
            {
                exerciseCollectionExercise.setItinerary(null);
                exerciseCollectionExercise = em.merge(exerciseCollectionExercise);
            }
            em.remove(itinerary);
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

    public List<Itinerary> findItineraryEntities()
    {
        return findItineraryEntities(true, -1, -1);
    }

    public List<Itinerary> findItineraryEntities(int maxResults, int firstResult)
    {
        return findItineraryEntities(false, maxResults, firstResult);
    }

    private List<Itinerary> findItineraryEntities(boolean all, int maxResults, int firstResult)
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Itinerary.class));
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

    public Itinerary findItinerary(Object id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(Itinerary.class, id);
        }
        finally
        {
            em.close();
        }
    }

    public int getItineraryCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Itinerary> rt = cq.from(Itinerary.class);
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
