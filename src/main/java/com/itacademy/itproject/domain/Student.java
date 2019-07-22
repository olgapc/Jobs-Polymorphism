/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itacademy.itproject.domain;

import com.itacademy.itproject.models.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "student")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
            , @NamedQuery(name = "Student.findByMail", query = "SELECT s FROM Student s WHERE s.mail = :mail")
            , @NamedQuery(name = "Student.findBySex", query = "SELECT s FROM Student s WHERE s.sex = :sex")
            , @NamedQuery(name = "Student.findByConclusion", query = "SELECT s FROM Student s WHERE s.conclusion = :conclusion")
            , @NamedQuery(name = "Student.findByStartDate", query = "SELECT s FROM Student s WHERE s.startDate = :startDate")
            , @NamedQuery(name = "Student.findByDeadline", query = "SELECT s FROM Student s WHERE s.deadline = :deadline")
            , @NamedQuery(name = "Student.findByInterviewResult", query = "SELECT s FROM Student s WHERE s.interviewResult = :interviewResult")
        })
public class Student extends User implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Lob
    @Column(name = "id")
    private byte[] id;
    @Basic(optional = false)
    @Column(name = "mail")
    private String mail;
    @Basic(optional = false)
    @Column(name = "sex")
    private String sex;
    @Basic(optional = false)
    @Column(name = "conclusion")
    private Conclusion conclusion;
    @Basic(optional = false)
    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @Column(name = "deadline")
    @Temporal(TemporalType.DATE)
    private Date deadline;
    @Column(name = "interview_result")
    private String interviewResult;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student1")
    private Collection<StudentAbsences> studentAbsencesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student1")
    private Collection<CurrentDeliveryState> currentDeliveryStateCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student1")
    private Collection<HistoricStudentItinerary> historicStudentItineraryCollection;
    @JoinColumn(name = "interview_teacher", referencedColumnName = "id")
    @ManyToOne
    private User interviewTeacher;
    @JoinColumn(name = "itinerary", referencedColumnName = "id")
    @ManyToOne
    private Itinerary itinerary;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student1")
    private Collection<HistoricDeliveryState> historicDeliveryStateCollection;

    public Student()
    {
    }

    public Student(byte[] id)
    {
        this.id = id;
    }

    public Student(byte[] id, String mail, String sex, Conclusion conclusion, Date startDate, Date deadline)
    {
        this.id = id;
        this.mail = mail;
        this.sex = sex;
        this.conclusion = conclusion;
        this.startDate = startDate;
        this.deadline = deadline;
    }

    public byte[] getId()
    {
        return id;
    }

    public void setId(byte[] id)
    {
        this.id = id;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public Conclusion getConclusion()
    {
        return conclusion;
    }

    public void setConclusion(Conclusion conclusion)
    {
        this.conclusion = conclusion;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getDeadline()
    {
        return deadline;
    }

    public void setDeadline(Date deadline)
    {
        this.deadline = deadline;
    }

    public String getInterviewResult()
    {
        return interviewResult;
    }

    public void setInterviewResult(String interviewResult)
    {
        this.interviewResult = interviewResult;
    }

    @XmlTransient
    public Collection<StudentAbsences> getStudentAbsencesCollection()
    {
        return studentAbsencesCollection;
    }

    public void setStudentAbsencesCollection(Collection<StudentAbsences> studentAbsencesCollection)
    {
        this.studentAbsencesCollection = studentAbsencesCollection;
    }

    @XmlTransient
    public Collection<CurrentDeliveryState> getCurrentDeliveryStateCollection()
    {
        return currentDeliveryStateCollection;
    }

    public void setCurrentDeliveryStateCollection(Collection<CurrentDeliveryState> currentDeliveryStateCollection)
    {
        this.currentDeliveryStateCollection = currentDeliveryStateCollection;
    }

    @XmlTransient
    public Collection<HistoricStudentItinerary> getHistoricStudentItineraryCollection()
    {
        return historicStudentItineraryCollection;
    }

    public void setHistoricStudentItineraryCollection(Collection<HistoricStudentItinerary> historicStudentItineraryCollection)
    {
        this.historicStudentItineraryCollection = historicStudentItineraryCollection;
    }

    public User getInterviewTeacher()
    {
        return interviewTeacher;
    }

    public void setInterviewTeacher(User interviewTeacher)
    {
        this.interviewTeacher = interviewTeacher;
    }

    public Itinerary getItinerary()
    {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary)
    {
        this.itinerary = itinerary;
    }

    @XmlTransient
    public Collection<HistoricDeliveryState> getHistoricDeliveryStateCollection()
    {
        return historicDeliveryStateCollection;
    }

    public void setHistoricDeliveryStateCollection(Collection<HistoricDeliveryState> historicDeliveryStateCollection)
    {
        this.historicDeliveryStateCollection = historicDeliveryStateCollection;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student))
        {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "containerjpa.Student[ id=" + id + " ]";
    }

}
