/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itacademy.itproject.controllers;

import com.itacademy.itproject.domain.Itinerary;
import com.itacademy.itproject.domain.Student;
import com.itacademy.itproject.domain.User;
import com.itacademy.itproject.exceptions.IllegalOrphanException;
import com.itacademy.itproject.exceptions.NonexistentEntityException;
import com.itacademy.itproject.exceptions.PreexistingEntityException;
import com.itacademy.itproject.models.CurrentDeliveryState;
import com.itacademy.itproject.models.HistoricDeliveryState;
import com.itacademy.itproject.models.HistoricStudentItinerary;
import com.itacademy.itproject.models.StudentAbsences;

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

public class StudentJpaController implements Serializable
{
    public StudentJpaController(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create(Student student) throws PreexistingEntityException, Exception
    {
        if (student.getStudentAbsencesCollection() == null)
        {
            student.setStudentAbsencesCollection(new ArrayList<StudentAbsences>());
        }
        if (student.getCurrentDeliveryStateCollection() == null)
        {
            student.setCurrentDeliveryStateCollection(new ArrayList<CurrentDeliveryState>());
        }
        if (student.getHistoricStudentItineraryCollection() == null)
        {
            student.setHistoricStudentItineraryCollection(new ArrayList<HistoricStudentItinerary>());
        }
        if (student.getHistoricDeliveryStateCollection() == null)
        {
            student.setHistoricDeliveryStateCollection(new ArrayList<HistoricDeliveryState>());
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            User interviewTeacher = student.getInterviewTeacher();
            if (interviewTeacher != null)
            {
                interviewTeacher = em.getReference(interviewTeacher.getClass(), interviewTeacher.getId());
                student.setInterviewTeacher(interviewTeacher);
            }
            Itinerary itinerary = student.getItinerary();
            if (itinerary != null)
            {
                itinerary = em.getReference(itinerary.getClass(), itinerary.getId());
                student.setItinerary(itinerary);
            }
            Collection<StudentAbsences> attachedStudentAbsencesCollection = new ArrayList<StudentAbsences>();
            for (StudentAbsences studentAbsencesCollectionStudentAbsencesToAttach : student.getStudentAbsencesCollection())
            {
                studentAbsencesCollectionStudentAbsencesToAttach = em.getReference(studentAbsencesCollectionStudentAbsencesToAttach.getClass(), studentAbsencesCollectionStudentAbsencesToAttach.getStudentAbsencesPK());
                attachedStudentAbsencesCollection.add(studentAbsencesCollectionStudentAbsencesToAttach);
            }
            student.setStudentAbsencesCollection(attachedStudentAbsencesCollection);
            Collection<CurrentDeliveryState> attachedCurrentDeliveryStateCollection = new ArrayList<CurrentDeliveryState>();
            for (CurrentDeliveryState currentDeliveryStateCollectionCurrentDeliveryStateToAttach : student.getCurrentDeliveryStateCollection())
            {
                currentDeliveryStateCollectionCurrentDeliveryStateToAttach = em.getReference(currentDeliveryStateCollectionCurrentDeliveryStateToAttach.getClass(), currentDeliveryStateCollectionCurrentDeliveryStateToAttach.getCurrentDeliveryStatePK());
                attachedCurrentDeliveryStateCollection.add(currentDeliveryStateCollectionCurrentDeliveryStateToAttach);
            }
            student.setCurrentDeliveryStateCollection(attachedCurrentDeliveryStateCollection);
            Collection<HistoricStudentItinerary> attachedHistoricStudentItineraryCollection = new ArrayList<HistoricStudentItinerary>();
            for (HistoricStudentItinerary historicStudentItineraryCollectionHistoricStudentItineraryToAttach : student.getHistoricStudentItineraryCollection())
            {
                historicStudentItineraryCollectionHistoricStudentItineraryToAttach = em.getReference(historicStudentItineraryCollectionHistoricStudentItineraryToAttach.getClass(), historicStudentItineraryCollectionHistoricStudentItineraryToAttach.getHistoricStudentItineraryPK());
                attachedHistoricStudentItineraryCollection.add(historicStudentItineraryCollectionHistoricStudentItineraryToAttach);
            }
            student.setHistoricStudentItineraryCollection(attachedHistoricStudentItineraryCollection);
            Collection<HistoricDeliveryState> attachedHistoricDeliveryStateCollection = new ArrayList<HistoricDeliveryState>();
            for (HistoricDeliveryState historicDeliveryStateCollectionHistoricDeliveryStateToAttach : student.getHistoricDeliveryStateCollection())
            {
                historicDeliveryStateCollectionHistoricDeliveryStateToAttach = em.getReference(historicDeliveryStateCollectionHistoricDeliveryStateToAttach.getClass(), historicDeliveryStateCollectionHistoricDeliveryStateToAttach.getHistoricDeliveryStatePK());
                attachedHistoricDeliveryStateCollection.add(historicDeliveryStateCollectionHistoricDeliveryStateToAttach);
            }
            student.setHistoricDeliveryStateCollection(attachedHistoricDeliveryStateCollection);
            em.persist(student);
            if (interviewTeacher != null)
            {
                interviewTeacher.getStudentCollection().add(student);
                interviewTeacher = em.merge(interviewTeacher);
            }
            if (itinerary != null)
            {
                itinerary.getStudentCollection().add(student);
                itinerary = em.merge(itinerary);
            }
            for (StudentAbsences studentAbsencesCollectionStudentAbsences : student.getStudentAbsencesCollection())
            {
                Student oldStudent1OfStudentAbsencesCollectionStudentAbsences = studentAbsencesCollectionStudentAbsences.getStudent1();
                studentAbsencesCollectionStudentAbsences.setStudent1(student);
                studentAbsencesCollectionStudentAbsences = em.merge(studentAbsencesCollectionStudentAbsences);
                if (oldStudent1OfStudentAbsencesCollectionStudentAbsences != null)
                {
                    oldStudent1OfStudentAbsencesCollectionStudentAbsences.getStudentAbsencesCollection().remove(studentAbsencesCollectionStudentAbsences);
                    oldStudent1OfStudentAbsencesCollectionStudentAbsences = em.merge(oldStudent1OfStudentAbsencesCollectionStudentAbsences);
                }
            }
            for (CurrentDeliveryState currentDeliveryStateCollectionCurrentDeliveryState : student.getCurrentDeliveryStateCollection())
            {
                Student oldStudent1OfCurrentDeliveryStateCollectionCurrentDeliveryState = currentDeliveryStateCollectionCurrentDeliveryState.getStudent1();
                currentDeliveryStateCollectionCurrentDeliveryState.setStudent1(student);
                currentDeliveryStateCollectionCurrentDeliveryState = em.merge(currentDeliveryStateCollectionCurrentDeliveryState);
                if (oldStudent1OfCurrentDeliveryStateCollectionCurrentDeliveryState != null)
                {
                    oldStudent1OfCurrentDeliveryStateCollectionCurrentDeliveryState.getCurrentDeliveryStateCollection().remove(currentDeliveryStateCollectionCurrentDeliveryState);
                    oldStudent1OfCurrentDeliveryStateCollectionCurrentDeliveryState = em.merge(oldStudent1OfCurrentDeliveryStateCollectionCurrentDeliveryState);
                }
            }
            for (HistoricStudentItinerary historicStudentItineraryCollectionHistoricStudentItinerary : student.getHistoricStudentItineraryCollection())
            {
                Student oldStudent1OfHistoricStudentItineraryCollectionHistoricStudentItinerary = historicStudentItineraryCollectionHistoricStudentItinerary.getStudent1();
                historicStudentItineraryCollectionHistoricStudentItinerary.setStudent1(student);
                historicStudentItineraryCollectionHistoricStudentItinerary = em.merge(historicStudentItineraryCollectionHistoricStudentItinerary);
                if (oldStudent1OfHistoricStudentItineraryCollectionHistoricStudentItinerary != null)
                {
                    oldStudent1OfHistoricStudentItineraryCollectionHistoricStudentItinerary.getHistoricStudentItineraryCollection().remove(historicStudentItineraryCollectionHistoricStudentItinerary);
                    oldStudent1OfHistoricStudentItineraryCollectionHistoricStudentItinerary = em.merge(oldStudent1OfHistoricStudentItineraryCollectionHistoricStudentItinerary);
                }
            }
            for (HistoricDeliveryState historicDeliveryStateCollectionHistoricDeliveryState : student.getHistoricDeliveryStateCollection())
            {
                Student oldStudent1OfHistoricDeliveryStateCollectionHistoricDeliveryState = historicDeliveryStateCollectionHistoricDeliveryState.getStudent1();
                historicDeliveryStateCollectionHistoricDeliveryState.setStudent1(student);
                historicDeliveryStateCollectionHistoricDeliveryState = em.merge(historicDeliveryStateCollectionHistoricDeliveryState);
                if (oldStudent1OfHistoricDeliveryStateCollectionHistoricDeliveryState != null)
                {
                    oldStudent1OfHistoricDeliveryStateCollectionHistoricDeliveryState.getHistoricDeliveryStateCollection().remove(historicDeliveryStateCollectionHistoricDeliveryState);
                    oldStudent1OfHistoricDeliveryStateCollectionHistoricDeliveryState = em.merge(oldStudent1OfHistoricDeliveryStateCollectionHistoricDeliveryState);
                }
            }
            em.getTransaction().commit();
        }
        catch (Exception ex)
        {
            if (findStudent(student.getId()) != null)
            {
                throw new PreexistingEntityException("Student " + student + " already exists.", ex);
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

    public void edit(Student student) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Student persistentStudent = em.find(Student.class, student.getId());
            User interviewTeacherOld = persistentStudent.getInterviewTeacher();
            User interviewTeacherNew = student.getInterviewTeacher();
            Itinerary itineraryOld = persistentStudent.getItinerary();
            Itinerary itineraryNew = student.getItinerary();
            Collection<StudentAbsences> studentAbsencesCollectionOld = persistentStudent.getStudentAbsencesCollection();
            Collection<StudentAbsences> studentAbsencesCollectionNew = student.getStudentAbsencesCollection();
            Collection<CurrentDeliveryState> currentDeliveryStateCollectionOld = persistentStudent.getCurrentDeliveryStateCollection();
            Collection<CurrentDeliveryState> currentDeliveryStateCollectionNew = student.getCurrentDeliveryStateCollection();
            Collection<HistoricStudentItinerary> historicStudentItineraryCollectionOld = persistentStudent.getHistoricStudentItineraryCollection();
            Collection<HistoricStudentItinerary> historicStudentItineraryCollectionNew = student.getHistoricStudentItineraryCollection();
            Collection<HistoricDeliveryState> historicDeliveryStateCollectionOld = persistentStudent.getHistoricDeliveryStateCollection();
            Collection<HistoricDeliveryState> historicDeliveryStateCollectionNew = student.getHistoricDeliveryStateCollection();
            List<String> illegalOrphanMessages = null;
            for (StudentAbsences studentAbsencesCollectionOldStudentAbsences : studentAbsencesCollectionOld)
            {
                if (!studentAbsencesCollectionNew.contains(studentAbsencesCollectionOldStudentAbsences))
                {
                    if (illegalOrphanMessages == null)
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain StudentAbsences " + studentAbsencesCollectionOldStudentAbsences + " since its student1 field is not nullable.");
                }
            }
            for (CurrentDeliveryState currentDeliveryStateCollectionOldCurrentDeliveryState : currentDeliveryStateCollectionOld)
            {
                if (!currentDeliveryStateCollectionNew.contains(currentDeliveryStateCollectionOldCurrentDeliveryState))
                {
                    if (illegalOrphanMessages == null)
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CurrentDeliveryState " + currentDeliveryStateCollectionOldCurrentDeliveryState + " since its student1 field is not nullable.");
                }
            }
            for (HistoricStudentItinerary historicStudentItineraryCollectionOldHistoricStudentItinerary : historicStudentItineraryCollectionOld)
            {
                if (!historicStudentItineraryCollectionNew.contains(historicStudentItineraryCollectionOldHistoricStudentItinerary))
                {
                    if (illegalOrphanMessages == null)
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HistoricStudentItinerary " + historicStudentItineraryCollectionOldHistoricStudentItinerary + " since its student1 field is not nullable.");
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
                    illegalOrphanMessages.add("You must retain HistoricDeliveryState " + historicDeliveryStateCollectionOldHistoricDeliveryState + " since its student1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null)
            {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (interviewTeacherNew != null)
            {
                interviewTeacherNew = em.getReference(interviewTeacherNew.getClass(), interviewTeacherNew.getId());
                student.setInterviewTeacher(interviewTeacherNew);
            }
            if (itineraryNew != null)
            {
                itineraryNew = em.getReference(itineraryNew.getClass(), itineraryNew.getId());
                student.setItinerary(itineraryNew);
            }
            Collection<StudentAbsences> attachedStudentAbsencesCollectionNew = new ArrayList<StudentAbsences>();
            for (StudentAbsences studentAbsencesCollectionNewStudentAbsencesToAttach : studentAbsencesCollectionNew)
            {
                studentAbsencesCollectionNewStudentAbsencesToAttach = em.getReference(studentAbsencesCollectionNewStudentAbsencesToAttach.getClass(), studentAbsencesCollectionNewStudentAbsencesToAttach.getStudentAbsencesPK());
                attachedStudentAbsencesCollectionNew.add(studentAbsencesCollectionNewStudentAbsencesToAttach);
            }
            studentAbsencesCollectionNew = attachedStudentAbsencesCollectionNew;
            student.setStudentAbsencesCollection(studentAbsencesCollectionNew);
            Collection<CurrentDeliveryState> attachedCurrentDeliveryStateCollectionNew = new ArrayList<CurrentDeliveryState>();
            for (CurrentDeliveryState currentDeliveryStateCollectionNewCurrentDeliveryStateToAttach : currentDeliveryStateCollectionNew)
            {
                currentDeliveryStateCollectionNewCurrentDeliveryStateToAttach = em.getReference(currentDeliveryStateCollectionNewCurrentDeliveryStateToAttach.getClass(), currentDeliveryStateCollectionNewCurrentDeliveryStateToAttach.getCurrentDeliveryStatePK());
                attachedCurrentDeliveryStateCollectionNew.add(currentDeliveryStateCollectionNewCurrentDeliveryStateToAttach);
            }
            currentDeliveryStateCollectionNew = attachedCurrentDeliveryStateCollectionNew;
            student.setCurrentDeliveryStateCollection(currentDeliveryStateCollectionNew);
            Collection<HistoricStudentItinerary> attachedHistoricStudentItineraryCollectionNew = new ArrayList<HistoricStudentItinerary>();
            for (HistoricStudentItinerary historicStudentItineraryCollectionNewHistoricStudentItineraryToAttach : historicStudentItineraryCollectionNew)
            {
                historicStudentItineraryCollectionNewHistoricStudentItineraryToAttach = em.getReference(historicStudentItineraryCollectionNewHistoricStudentItineraryToAttach.getClass(), historicStudentItineraryCollectionNewHistoricStudentItineraryToAttach.getHistoricStudentItineraryPK());
                attachedHistoricStudentItineraryCollectionNew.add(historicStudentItineraryCollectionNewHistoricStudentItineraryToAttach);
            }
            historicStudentItineraryCollectionNew = attachedHistoricStudentItineraryCollectionNew;
            student.setHistoricStudentItineraryCollection(historicStudentItineraryCollectionNew);
            Collection<HistoricDeliveryState> attachedHistoricDeliveryStateCollectionNew = new ArrayList<HistoricDeliveryState>();
            for (HistoricDeliveryState historicDeliveryStateCollectionNewHistoricDeliveryStateToAttach : historicDeliveryStateCollectionNew)
            {
                historicDeliveryStateCollectionNewHistoricDeliveryStateToAttach = em.getReference(historicDeliveryStateCollectionNewHistoricDeliveryStateToAttach.getClass(), historicDeliveryStateCollectionNewHistoricDeliveryStateToAttach.getHistoricDeliveryStatePK());
                attachedHistoricDeliveryStateCollectionNew.add(historicDeliveryStateCollectionNewHistoricDeliveryStateToAttach);
            }
            historicDeliveryStateCollectionNew = attachedHistoricDeliveryStateCollectionNew;
            student.setHistoricDeliveryStateCollection(historicDeliveryStateCollectionNew);
            student = em.merge(student);
            if (interviewTeacherOld != null && !interviewTeacherOld.equals(interviewTeacherNew))
            {
                interviewTeacherOld.getStudentCollection().remove(student);
                interviewTeacherOld = em.merge(interviewTeacherOld);
            }
            if (interviewTeacherNew != null && !interviewTeacherNew.equals(interviewTeacherOld))
            {
                interviewTeacherNew.getStudentCollection().add(student);
                interviewTeacherNew = em.merge(interviewTeacherNew);
            }
            if (itineraryOld != null && !itineraryOld.equals(itineraryNew))
            {
                itineraryOld.getStudentCollection().remove(student);
                itineraryOld = em.merge(itineraryOld);
            }
            if (itineraryNew != null && !itineraryNew.equals(itineraryOld))
            {
                itineraryNew.getStudentCollection().add(student);
                itineraryNew = em.merge(itineraryNew);
            }
            for (StudentAbsences studentAbsencesCollectionNewStudentAbsences : studentAbsencesCollectionNew)
            {
                if (!studentAbsencesCollectionOld.contains(studentAbsencesCollectionNewStudentAbsences))
                {
                    Student oldStudent1OfStudentAbsencesCollectionNewStudentAbsences = studentAbsencesCollectionNewStudentAbsences.getStudent1();
                    studentAbsencesCollectionNewStudentAbsences.setStudent1(student);
                    studentAbsencesCollectionNewStudentAbsences = em.merge(studentAbsencesCollectionNewStudentAbsences);
                    if (oldStudent1OfStudentAbsencesCollectionNewStudentAbsences != null && !oldStudent1OfStudentAbsencesCollectionNewStudentAbsences.equals(student))
                    {
                        oldStudent1OfStudentAbsencesCollectionNewStudentAbsences.getStudentAbsencesCollection().remove(studentAbsencesCollectionNewStudentAbsences);
                        oldStudent1OfStudentAbsencesCollectionNewStudentAbsences = em.merge(oldStudent1OfStudentAbsencesCollectionNewStudentAbsences);
                    }
                }
            }
            for (CurrentDeliveryState currentDeliveryStateCollectionNewCurrentDeliveryState : currentDeliveryStateCollectionNew)
            {
                if (!currentDeliveryStateCollectionOld.contains(currentDeliveryStateCollectionNewCurrentDeliveryState))
                {
                    Student oldStudent1OfCurrentDeliveryStateCollectionNewCurrentDeliveryState = currentDeliveryStateCollectionNewCurrentDeliveryState.getStudent1();
                    currentDeliveryStateCollectionNewCurrentDeliveryState.setStudent1(student);
                    currentDeliveryStateCollectionNewCurrentDeliveryState = em.merge(currentDeliveryStateCollectionNewCurrentDeliveryState);
                    if (oldStudent1OfCurrentDeliveryStateCollectionNewCurrentDeliveryState != null && !oldStudent1OfCurrentDeliveryStateCollectionNewCurrentDeliveryState.equals(student))
                    {
                        oldStudent1OfCurrentDeliveryStateCollectionNewCurrentDeliveryState.getCurrentDeliveryStateCollection().remove(currentDeliveryStateCollectionNewCurrentDeliveryState);
                        oldStudent1OfCurrentDeliveryStateCollectionNewCurrentDeliveryState = em.merge(oldStudent1OfCurrentDeliveryStateCollectionNewCurrentDeliveryState);
                    }
                }
            }
            for (HistoricStudentItinerary historicStudentItineraryCollectionNewHistoricStudentItinerary : historicStudentItineraryCollectionNew)
            {
                if (!historicStudentItineraryCollectionOld.contains(historicStudentItineraryCollectionNewHistoricStudentItinerary))
                {
                    Student oldStudent1OfHistoricStudentItineraryCollectionNewHistoricStudentItinerary = historicStudentItineraryCollectionNewHistoricStudentItinerary.getStudent1();
                    historicStudentItineraryCollectionNewHistoricStudentItinerary.setStudent1(student);
                    historicStudentItineraryCollectionNewHistoricStudentItinerary = em.merge(historicStudentItineraryCollectionNewHistoricStudentItinerary);
                    if (oldStudent1OfHistoricStudentItineraryCollectionNewHistoricStudentItinerary != null && !oldStudent1OfHistoricStudentItineraryCollectionNewHistoricStudentItinerary.equals(student))
                    {
                        oldStudent1OfHistoricStudentItineraryCollectionNewHistoricStudentItinerary.getHistoricStudentItineraryCollection().remove(historicStudentItineraryCollectionNewHistoricStudentItinerary);
                        oldStudent1OfHistoricStudentItineraryCollectionNewHistoricStudentItinerary = em.merge(oldStudent1OfHistoricStudentItineraryCollectionNewHistoricStudentItinerary);
                    }
                }
            }
            for (HistoricDeliveryState historicDeliveryStateCollectionNewHistoricDeliveryState : historicDeliveryStateCollectionNew)
            {
                if (!historicDeliveryStateCollectionOld.contains(historicDeliveryStateCollectionNewHistoricDeliveryState))
                {
                    Student oldStudent1OfHistoricDeliveryStateCollectionNewHistoricDeliveryState = historicDeliveryStateCollectionNewHistoricDeliveryState.getStudent1();
                    historicDeliveryStateCollectionNewHistoricDeliveryState.setStudent1(student);
                    historicDeliveryStateCollectionNewHistoricDeliveryState = em.merge(historicDeliveryStateCollectionNewHistoricDeliveryState);
                    if (oldStudent1OfHistoricDeliveryStateCollectionNewHistoricDeliveryState != null && !oldStudent1OfHistoricDeliveryStateCollectionNewHistoricDeliveryState.equals(student))
                    {
                        oldStudent1OfHistoricDeliveryStateCollectionNewHistoricDeliveryState.getHistoricDeliveryStateCollection().remove(historicDeliveryStateCollectionNewHistoricDeliveryState);
                        oldStudent1OfHistoricDeliveryStateCollectionNewHistoricDeliveryState = em.merge(oldStudent1OfHistoricDeliveryStateCollectionNewHistoricDeliveryState);
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
                Object id = student.getId();
                if (findStudent(id) == null)
                {
                    throw new NonexistentEntityException("The student with id " + id + " no longer exists.");
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
            Student student;
            try
            {
                student = em.getReference(Student.class, id);
                student.getId();
            }
            catch (EntityNotFoundException enfe)
            {
                throw new NonexistentEntityException("The student with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<StudentAbsences> studentAbsencesCollectionOrphanCheck = student.getStudentAbsencesCollection();
            for (StudentAbsences studentAbsencesCollectionOrphanCheckStudentAbsences : studentAbsencesCollectionOrphanCheck)
            {
                if (illegalOrphanMessages == null)
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Student (" + student + ") cannot be destroyed since the StudentAbsences " + studentAbsencesCollectionOrphanCheckStudentAbsences + " in its studentAbsencesCollection field has a non-nullable student1 field.");
            }
            Collection<CurrentDeliveryState> currentDeliveryStateCollectionOrphanCheck = student.getCurrentDeliveryStateCollection();
            for (CurrentDeliveryState currentDeliveryStateCollectionOrphanCheckCurrentDeliveryState : currentDeliveryStateCollectionOrphanCheck)
            {
                if (illegalOrphanMessages == null)
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Student (" + student + ") cannot be destroyed since the CurrentDeliveryState " + currentDeliveryStateCollectionOrphanCheckCurrentDeliveryState + " in its currentDeliveryStateCollection field has a non-nullable student1 field.");
            }
            Collection<HistoricStudentItinerary> historicStudentItineraryCollectionOrphanCheck = student.getHistoricStudentItineraryCollection();
            for (HistoricStudentItinerary historicStudentItineraryCollectionOrphanCheckHistoricStudentItinerary : historicStudentItineraryCollectionOrphanCheck)
            {
                if (illegalOrphanMessages == null)
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Student (" + student + ") cannot be destroyed since the HistoricStudentItinerary " + historicStudentItineraryCollectionOrphanCheckHistoricStudentItinerary + " in its historicStudentItineraryCollection field has a non-nullable student1 field.");
            }
            Collection<HistoricDeliveryState> historicDeliveryStateCollectionOrphanCheck = student.getHistoricDeliveryStateCollection();
            for (HistoricDeliveryState historicDeliveryStateCollectionOrphanCheckHistoricDeliveryState : historicDeliveryStateCollectionOrphanCheck)
            {
                if (illegalOrphanMessages == null)
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Student (" + student + ") cannot be destroyed since the HistoricDeliveryState " + historicDeliveryStateCollectionOrphanCheckHistoricDeliveryState + " in its historicDeliveryStateCollection field has a non-nullable student1 field.");
            }
            if (illegalOrphanMessages != null)
            {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            User interviewTeacher = student.getInterviewTeacher();
            if (interviewTeacher != null)
            {
                interviewTeacher.getStudentCollection().remove(student);
                interviewTeacher = em.merge(interviewTeacher);
            }
            Itinerary itinerary = student.getItinerary();
            if (itinerary != null)
            {
                itinerary.getStudentCollection().remove(student);
                itinerary = em.merge(itinerary);
            }
            em.remove(student);
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

    public List<Student> findStudentEntities()
    {
        return findStudentEntities(true, -1, -1);
    }

    public List<Student> findStudentEntities(int maxResults, int firstResult)
    {
        return findStudentEntities(false, maxResults, firstResult);
    }

    private List<Student> findStudentEntities(boolean all, int maxResults, int firstResult)
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Student.class));
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

    public Student findStudent(Object id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(Student.class, id);
        }
        finally
        {
            em.close();
        }
    }

    public int getStudentCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Student> rt = cq.from(Student.class);
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
