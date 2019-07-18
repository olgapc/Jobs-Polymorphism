/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itacademy.itproject.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Embeddable
public class HistoricDeliveryStatePK implements Serializable
{
    @Basic(optional = false)
    @Lob
    @Column(name = "student")
    private byte[] student;
    @Basic(optional = false)
    @Lob
    @Column(name = "exercise")
    private byte[] exercise;
    @Basic(optional = false)
    @Column(name = "state")
    private States state;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public HistoricDeliveryStatePK(byte[] student, byte[] exercise, HistoricDeliveryState.States state, Date date)
    {
    }

    public HistoricDeliveryStatePK(byte[] student, byte[] exercise, States state, Date date)
    {
        this.student = student;
        this.exercise = exercise;
        this.state = state;
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

    public byte[] getExercise()
    {
        return exercise;
    }

    public void setExercise(byte[] exercise)
    {
        this.exercise = exercise;
    }

    public States getState()
    {
        return state;
    }

    public void setState(States state)
    {
        this.state = state;
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
        hash += (exercise != null ? exercise.hashCode() : 0);
        hash += (state != null ? state.hashCode() : 0);
        hash += (date != null ? date.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricDeliveryStatePK))
        {
            return false;
        }
        HistoricDeliveryStatePK other = (HistoricDeliveryStatePK) object;
        if ((this.student == null && other.student != null) || (this.student != null && !this.student.equals(other.student)))
        {
            return false;
        }
        if ((this.exercise == null && other.exercise != null) || (this.exercise != null && !this.exercise.equals(other.exercise)))
        {
            return false;
        }
        if ((this.state == null && other.state != null) || (this.state != null && !this.state.equals(other.state)))
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
        return "containerjpa.HistoricDeliveryStatePK[ student=" + student + ", exercise=" + exercise + ", state=" + state + ", date=" + date + " ]";
    }

}
