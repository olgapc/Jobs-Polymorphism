/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itacademy.itproject.models;

import com.itacademy.itproject.domain.Exercise;
import com.itacademy.itproject.domain.Student;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "current_delivery_state")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "CurrentDeliveryState.findAll", query = "SELECT c FROM CurrentDeliveryState c")
            , @NamedQuery(name = "CurrentDeliveryState.findByState", query = "SELECT c FROM CurrentDeliveryState c WHERE c.currentDeliveryStatePK.state = :state")
            , @NamedQuery(name = "CurrentDeliveryState.findByDate", query = "SELECT c FROM CurrentDeliveryState c WHERE c.currentDeliveryStatePK.date = :date")
        })
public class CurrentDeliveryState implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CurrentDeliveryStatePK currentDeliveryStatePK;
    @JoinColumn(name = "exercise", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Exercise exercise1;
    @JoinColumn(name = "student", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Student student1;

    public CurrentDeliveryState()
    {
    }

    public CurrentDeliveryState(CurrentDeliveryStatePK currentDeliveryStatePK)
    {
        this.currentDeliveryStatePK = currentDeliveryStatePK;
    }

    public CurrentDeliveryState(byte[] student, byte[] exercise, States state, Date date)
    {
        this.currentDeliveryStatePK = new CurrentDeliveryStatePK(student, exercise, state, date);
    }

    public CurrentDeliveryStatePK getCurrentDeliveryStatePK()
    {
        return currentDeliveryStatePK;
    }

    public void setCurrentDeliveryStatePK(CurrentDeliveryStatePK currentDeliveryStatePK)
    {
        this.currentDeliveryStatePK = currentDeliveryStatePK;
    }

    public Exercise getExercise1()
    {
        return exercise1;
    }

    public void setExercise1(Exercise exercise1)
    {
        this.exercise1 = exercise1;
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
        hash += (currentDeliveryStatePK != null ? currentDeliveryStatePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CurrentDeliveryState))
        {
            return false;
        }
        CurrentDeliveryState other = (CurrentDeliveryState) object;
        if ((this.currentDeliveryStatePK == null && other.currentDeliveryStatePK != null) || (this.currentDeliveryStatePK != null && !this.currentDeliveryStatePK.equals(other.currentDeliveryStatePK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "containerjpa.CurrentDeliveryState[ currentDeliveryStatePK=" + currentDeliveryStatePK + " ]";
    }

}
