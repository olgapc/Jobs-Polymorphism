/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itacademy.itproject.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Embeddable
public class HistoricStudentItineraryPK implements Serializable
{
    @Basic(optional = false)
    @Lob
    @Column(name = "student")
    private byte[] student;
    @Basic(optional = false)
    @Lob
    @Column(name = "itinerary")
    private byte[] itinerary;

    public HistoricStudentItineraryPK()
    {
    }

    public HistoricStudentItineraryPK(byte[] student, byte[] itinerary)
    {
        this.student = student;
        this.itinerary = itinerary;
    }

    public byte[] getStudent()
    {
        return student;
    }

    public void setStudent(byte[] student)
    {
        this.student = student;
    }

    public byte[] getItinerary()
    {
        return itinerary;
    }

    public void setItinerary(byte[] itinerary)
    {
        this.itinerary = itinerary;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (student != null ? student.hashCode() : 0);
        hash += (itinerary != null ? itinerary.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricStudentItineraryPK))
        {
            return false;
        }
        HistoricStudentItineraryPK other = (HistoricStudentItineraryPK) object;
        if ((this.student == null && other.student != null) || (this.student != null && !this.student.equals(other.student)))
        {
            return false;
        }
        if ((this.itinerary == null && other.itinerary != null) || (this.itinerary != null && !this.itinerary.equals(other.itinerary)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "containerjpa.HistoricStudentItineraryPK[ student=" + student + ", itinerary=" + itinerary + " ]";
    }

}
