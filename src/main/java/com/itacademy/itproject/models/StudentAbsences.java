/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itacademy.itproject.models;

import com.itacademy.itproject.domain.Student;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "student_absences")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "StudentAbsences.findAll", query = "SELECT s FROM StudentAbsences s")
            , @NamedQuery(name = "StudentAbsences.findByDate", query = "SELECT s FROM StudentAbsences s WHERE s.studentAbsencesPK.date = :date")
        })
public class StudentAbsences implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StudentAbsencesPK studentAbsencesPK;
    @JoinColumn(name = "student", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Student student1;

    public StudentAbsences()
    {
    }

    public StudentAbsences(StudentAbsencesPK studentAbsencesPK)
    {
        this.studentAbsencesPK = studentAbsencesPK;
    }

    public StudentAbsences(byte[] student, Date date)
    {
        this.studentAbsencesPK = new StudentAbsencesPK(student, date);
    }

    public StudentAbsencesPK getStudentAbsencesPK()
    {
        return studentAbsencesPK;
    }

    public void setStudentAbsencesPK(StudentAbsencesPK studentAbsencesPK)
    {
        this.studentAbsencesPK = studentAbsencesPK;
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
        hash += (studentAbsencesPK != null ? studentAbsencesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentAbsences))
        {
            return false;
        }
        StudentAbsences other = (StudentAbsences) object;
        if ((this.studentAbsencesPK == null && other.studentAbsencesPK != null) || (this.studentAbsencesPK != null && !this.studentAbsencesPK.equals(other.studentAbsencesPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "containerjpa.StudentAbsences[ studentAbsencesPK=" + studentAbsencesPK + " ]";
    }

}
