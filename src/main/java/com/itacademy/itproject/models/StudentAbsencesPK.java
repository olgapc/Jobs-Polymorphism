/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itacademy.itproject.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Embeddable
public class StudentAbsencesPK implements Serializable
{
    @Basic(optional = false)
    @Lob
    @Column(name = "student")
    private byte[] student;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public StudentAbsencesPK()
    {
    }

    public StudentAbsencesPK(byte[] student, Date date)
    {
        this.student = student;
        this.date = date;
    }

    public byte[] getStudent()
    {
        return student;
    }

    public void setStudent(byte[] student)
    {
        this.student = student;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (student != null ? student.hashCode() : 0);
        hash += (date != null ? date.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentAbsencesPK))
        {
            return false;
        }
        StudentAbsencesPK other = (StudentAbsencesPK) object;
        if ((this.student == null && other.student != null) || (this.student != null && !this.student.equals(other.student)))
        {
            return false;
        }
        if ((this.date == null && other.date != null) || (this.date != null && !this.date.equals(other.date)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "containerjpa.StudentAbsencesPK[ student=" + student + ", date=" + date + " ]";
    }

}
