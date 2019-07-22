/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itacademy.itproject.models;

import com.itacademy.itproject.domain.Itinerary;
import com.itacademy.itproject.domain.Student;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "historic_student_itinerary")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "HistoricStudentItinerary.findAll", query = "SELECT h FROM HistoricStudentItinerary h")
            , @NamedQuery(name = "HistoricStudentItinerary.findByEndDate", query = "SELECT h FROM HistoricStudentItinerary h WHERE h.endDate = :endDate")
        })
public class HistoricStudentItinerary implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HistoricStudentItineraryPK historicStudentItineraryPK;
    @Basic(optional = false)
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @JoinColumn(name = "itinerary", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Itinerary itinerary1;
    @JoinColumn(name = "student", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Student student1;

    public HistoricStudentItinerary()
    {
    }

    public HistoricStudentItinerary(HistoricStudentItineraryPK historicStudentItineraryPK)
    {
        this.historicStudentItineraryPK = historicStudentItineraryPK;
    }

    public HistoricStudentItinerary(HistoricStudentItineraryPK historicStudentItineraryPK, Date endDate)
    {
        this.historicStudentItineraryPK = historicStudentItineraryPK;
        this.endDate = endDate;
    }

    public HistoricStudentItinerary(byte[] student, byte[] itinerary)
    {
        this.historicStudentItineraryPK = new HistoricStudentItineraryPK(student, itinerary);
    }

    public HistoricStudentItineraryPK getHistoricStudentItineraryPK()
    {
        return historicStudentItineraryPK;
    }

    public void setHistoricStudentItineraryPK(HistoricStudentItineraryPK historicStudentItineraryPK)
    {
        this.historicStudentItineraryPK = historicStudentItineraryPK;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public Itinerary getItinerary1()
    {
        return itinerary1;
    }

    public void setItinerary1(Itinerary itinerary1)
    {
        this.itinerary1 = itinerary1;
    }

    public Student getStudent1()
    {
        return student1;
    }

    public void setStudent1(Student student1)
    {
        this.student1 = student1;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (historicStudentItineraryPK != null ? historicStudentItineraryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricStudentItinerary))
        {
            return false;
        }
        HistoricStudentItinerary other = (HistoricStudentItinerary) object;
        if ((this.historicStudentItineraryPK == null && other.historicStudentItineraryPK != null) || (this.historicStudentItineraryPK != null && !this.historicStudentItineraryPK.equals(other.historicStudentItineraryPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "containerjpa.HistoricStudentItinerary[ historicStudentItineraryPK=" + historicStudentItineraryPK + " ]";
    }

}
