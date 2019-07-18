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
import com.itacademy.itproject.domain.Student;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import com.itacademy.itproject.domain.Itinerary;
import com.itacademy.itproject.domain.User;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class UserJpaController implements Serializable
{
    public UserJpaController(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create(User user) throws PreexistingEntityException, Exception
    {
        if (user.getStudentCollection() == null)
        {
            user.setStudentCollection(new ArrayList<Student>());
        }
        if (user.getItineraryCollection() == null)
        {
            user.setItineraryCollection(new ArrayList<Itinerary>());
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Student> attachedStudentCollection = new ArrayList<Student>();
            for (Student studentCollectionStudentToAttach : user.getStudentCollection())
            {
                studentCollectionStudentToAttach = em.getReference(studentCollectionStudentToAttach.getClass(), studentCollectionStudentToAttach.getId());
                attachedStudentCollection.add(studentCollectionStudentToAttach);
            }
            user.setStudentCollection(attachedStudentCollection);
            Collection<Itinerary> attachedItineraryCollection = new ArrayList<Itinerary>();
            for (Itinerary itineraryCollectionItineraryToAttach : user.getItineraryCollection())
            {
                itineraryCollectionItineraryToAttach = em.getReference(itineraryCollectionItineraryToAttach.getClass(), itineraryCollectionItineraryToAttach.getId());
                attachedItineraryCollection.add(itineraryCollectionItineraryToAttach);
            }
            user.setItineraryCollection(attachedItineraryCollection);
            em.persist(user);
            for (Student studentCollectionStudent : user.getStudentCollection())
            {
                User oldInterviewTeacherOfStudentCollectionStudent = studentCollectionStudent.getInterviewTeacher();
                studentCollectionStudent.setInterviewTeacher(user);
                studentCollectionStudent = em.merge(studentCollectionStudent);
                if (oldInterviewTeacherOfStudentCollectionStudent != null)
                {
                    oldInterviewTeacherOfStudentCollectionStudent.getStudentCollection().remove(studentCollectionStudent);
                    oldInterviewTeacherOfStudentCollectionStudent = em.merge(oldInterviewTeacherOfStudentCollectionStudent);
                }
            }
            for (Itinerary itineraryCollectionItinerary : user.getItineraryCollection())
            {
                User oldTeacherOfItineraryCollectionItinerary = itineraryCollectionItinerary.getTeacher();
                itineraryCollectionItinerary.setTeacher(user);
                itineraryCollectionItinerary = em.merge(itineraryCollectionItinerary);
                if (oldTeacherOfItineraryCollectionItinerary != null)
                {
                    oldTeacherOfItineraryCollectionItinerary.getItineraryCollection().remove(itineraryCollectionItinerary);
                    oldTeacherOfItineraryCollectionItinerary = em.merge(oldTeacherOfItineraryCollectionItinerary);
                }
            }
            em.getTransaction().commit();
        }
        catch (Exception ex)
        {
            if (findUser(user.getId()) != null)
            {
                throw new PreexistingEntityException("User " + user + " already exists.", ex);
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

    public void edit(User user) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            User persistentUser = em.find(User.class, user.getId());
            Collection<Student> studentCollectionOld = persistentUser.getStudentCollection();
            Collection<Student> studentCollectionNew = user.getStudentCollection();
            Collection<Itinerary> itineraryCollectionOld = persistentUser.getItineraryCollection();
            Collection<Itinerary> itineraryCollectionNew = user.getItineraryCollection();
            List<String> illegalOrphanMessages = null;
            for (Itinerary itineraryCollectionOldItinerary : itineraryCollectionOld)
            {
                if (!itineraryCollectionNew.contains(itineraryCollectionOldItinerary))
                {
                    if (illegalOrphanMessages == null)
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Itinerary " + itineraryCollectionOldItinerary + " since its teacher field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null)
            {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Student> attachedStudentCollectionNew = new ArrayList<Student>();
            for (Student studentCollectionNewStudentToAttach : studentCollectionNew)
            {
                studentCollectionNewStudentToAttach = em.getReference(studentCollectionNewStudentToAttach.getClass(), studentCollectionNewStudentToAttach.getId());
                attachedStudentCollectionNew.add(studentCollectionNewStudentToAttach);
            }
            studentCollectionNew = attachedStudentCollectionNew;
            user.setStudentCollection(studentCollectionNew);
            Collection<Itinerary> attachedItineraryCollectionNew = new ArrayList<Itinerary>();
            for (Itinerary itineraryCollectionNewItineraryToAttach : itineraryCollectionNew)
            {
                itineraryCollectionNewItineraryToAttach = em.getReference(itineraryCollectionNewItineraryToAttach.getClass(), itineraryCollectionNewItineraryToAttach.getId());
                attachedItineraryCollectionNew.add(itineraryCollectionNewItineraryToAttach);
            }
            itineraryCollectionNew = attachedItineraryCollectionNew;
            user.setItineraryCollection(itineraryCollectionNew);
            user = em.merge(user);
            for (Student studentCollectionOldStudent : studentCollectionOld)
            {
                if (!studentCollectionNew.contains(studentCollectionOldStudent))
                {
                    studentCollectionOldStudent.setInterviewTeacher(null);
                    studentCollectionOldStudent = em.merge(studentCollectionOldStudent);
                }
            }
            for (Student studentCollectionNewStudent : studentCollectionNew)
            {
                if (!studentCollectionOld.contains(studentCollectionNewStudent))
                {
                    User oldInterviewTeacherOfStudentCollectionNewStudent = studentCollectionNewStudent.getInterviewTeacher();
                    studentCollectionNewStudent.setInterviewTeacher(user);
                    studentCollectionNewStudent = em.merge(studentCollectionNewStudent);
                    if (oldInterviewTeacherOfStudentCollectionNewStudent != null && !oldInterviewTeacherOfStudentCollectionNewStudent.equals(user))
                    {
                        oldInterviewTeacherOfStudentCollectionNewStudent.getStudentCollection().remove(studentCollectionNewStudent);
                        oldInterviewTeacherOfStudentCollectionNewStudent = em.merge(oldInterviewTeacherOfStudentCollectionNewStudent);
                    }
                }
            }
            for (Itinerary itineraryCollectionNewItinerary : itineraryCollectionNew)
            {
                if (!itineraryCollectionOld.contains(itineraryCollectionNewItinerary))
                {
                    User oldTeacherOfItineraryCollectionNewItinerary = itineraryCollectionNewItinerary.getTeacher();
                    itineraryCollectionNewItinerary.setTeacher(user);
                    itineraryCollectionNewItinerary = em.merge(itineraryCollectionNewItinerary);
                    if (oldTeacherOfItineraryCollectionNewItinerary != null && !oldTeacherOfItineraryCollectionNewItinerary.equals(user))
                    {
                        oldTeacherOfItineraryCollectionNewItinerary.getItineraryCollection().remove(itineraryCollectionNewItinerary);
                        oldTeacherOfItineraryCollectionNewItinerary = em.merge(oldTeacherOfItineraryCollectionNewItinerary);
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
                Object id = user.getId();
                if (findUser(id) == null)
                {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
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
            User user;
            try
            {
                user = em.getReference(User.class, id);
                user.getId();
            }
            catch (EntityNotFoundException enfe)
            {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Itinerary> itineraryCollectionOrphanCheck = user.getItineraryCollection();
            for (Itinerary itineraryCollectionOrphanCheckItinerary : itineraryCollectionOrphanCheck)
            {
                if (illegalOrphanMessages == null)
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Itinerary " + itineraryCollectionOrphanCheckItinerary + " in its itineraryCollection field has a non-nullable teacher field.");
            }
            if (illegalOrphanMessages != null)
            {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Student> studentCollection = user.getStudentCollection();
            for (Student studentCollectionStudent : studentCollection)
            {
                studentCollectionStudent.setInterviewTeacher(null);
                studentCollectionStudent = em.merge(studentCollectionStudent);
            }
            em.remove(user);
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

    public List<User> findUserEntities()
    {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult)
    {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult)
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
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

    public User findUser(Object id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(User.class, id);
        }
        finally
        {
            em.close();
        }
    }

    public int getUserCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
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
