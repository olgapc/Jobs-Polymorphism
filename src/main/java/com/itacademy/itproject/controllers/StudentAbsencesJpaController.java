/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itacademy.itproject.controllers;

import com.itacademy.itproject.domain.Student;
import com.itacademy.itproject.exceptions.NonexistentEntityException;
import com.itacademy.itproject.exceptions.PreexistingEntityException;
import com.itacademy.itproject.models.StudentAbsences;
import com.itacademy.itproject.models.StudentAbsencesPK;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class StudentAbsencesJpaController implements Serializable
{
    public StudentAbsencesJpaController(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create(StudentAbsences studentAbsences) throws PreexistingEntityException, Exception
    {
        if (studentAbsences.getStudentAbsencesPK() == null)
        {
            studentAbsences.setStudentAbsencesPK(new StudentAbsencesPK());
        }
        studentAbsences.getStudentAbsencesPK().setStudent(studentAbsences.getStudent1().getId());
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Student student1 = studentAbsences.getStudent1();
            if (student1 != null)
            {
                student1 = em.getReference(student1.getClass(), student1.getId());
                studentAbsences.setStudent1(student1);
            }
            em.persist(studentAbsences);
            if (student1 != null)
            {
                student1.getStudentAbsencesCollection().add(studentAbsences);
                student1 = em.merge(student1);
            }
            em.getTransaction().commit();
        }
        catch (Exception ex)
        {
            if (findStudentAbsences(studentAbsences.getStudentAbsencesPK()) != null)
            {
                throw new PreexistingEntityException("StudentAbsences " + studentAbsences + " already exists.", ex);
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

    public void edit(StudentAbsences studentAbsences) throws NonexistentEntityException, Exception
    {
        studentAbsences.getStudentAbsencesPK().setStudent(studentAbsences.getStudent1().getId());
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            StudentAbsences persistentStudentAbsences = em.find(StudentAbsences.class, studentAbsences.getStudentAbsencesPK());
            Student student1Old = persistentStudentAbsences.getStudent1();
            Student student1New = studentAbsences.getStudent1();
            if (student1New != null)
            {
                student1New = em.getReference(student1New.getClass(), student1New.getId());
                studentAbsences.setStudent1(student1New);
            }
            studentAbsences = em.merge(studentAbsences);
            if (student1Old != null && !student1Old.equals(student1New))
            {
                student1Old.getStudentAbsencesCollection().remove(studentAbsences);
                student1Old = em.merge(student1Old);
            }
            if (student1New != null && !student1New.equals(student1Old))
            {
                student1New.getStudentAbsencesCollection().add(studentAbsences);
                student1New = em.merge(student1New);
            }
            em.getTransaction().commit();
        }
        catch (Exception ex)
        {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0)
            {
                StudentAbsencesPK id = studentAbsences.getStudentAbsencesPK();
                if (findStudentAbsences(id) == null)
                {
                    throw new NonexistentEntityException("The studentAbsences with id " + id + " no longer exists.");
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

    public void destroy(StudentAbsencesPK id) throws NonexistentEntityException
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            StudentAbsences studentAbsences;
            try
            {
                studentAbsences = em.getReference(StudentAbsences.class, id);
                studentAbsences.getStudentAbsencesPK();
            }
            catch (EntityNotFoundException enfe)
            {
                throw new NonexistentEntityException("The studentAbsences with id " + id + " no longer exists.", enfe);
            }
            Student student1 = studentAbsences.getStudent1();
            if (student1 != null)
            {
                student1.getStudentAbsencesCollection().remove(studentAbsences);
                student1 = em.merge(student1);
            }
            em.remove(studentAbsences);
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

    public List<StudentAbsences> findStudentAbsencesEntities()
    {
        return findStudentAbsencesEntities(true, -1, -1);
    }

    public List<StudentAbsences> findStudentAbsencesEntities(int maxResults, int firstResult)
    {
        return findStudentAbsencesEntities(false, maxResults, firstResult);
    }

    private List<StudentAbsences> findStudentAbsencesEntities(boolean all, int maxResults, int firstResult)
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StudentAbsences.class));
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

    public StudentAbsences findStudentAbsences(StudentAbsencesPK id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(StudentAbsences.class, id);
        }
        finally
        {
            em.close();
        }
    }

    public int getStudentAbsencesCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StudentAbsences> rt = cq.from(StudentAbsences.class);
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
