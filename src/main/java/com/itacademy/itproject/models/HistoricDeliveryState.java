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

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Entity
@Table(name = "historic_delivery_state")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "HistoricDeliveryState.findAll", query = "SELECT h FROM HistoricDeliveryState h")
            , @NamedQuery(name = "HistoricDeliveryState.findByState", query = "SELECT h FROM HistoricDeliveryState h WHERE h.historicDeliveryStatePK.state = :state")
            , @NamedQuery(name = "HistoricDeliveryState.findByDate", query = "SELECT h FROM HistoricDeliveryState h WHERE h.historicDeliveryStatePK.date = :date")
        })
public class HistoricDeliveryState implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HistoricDeliveryStatePK historicDeliveryStatePK;
    @JoinColumn(name = "exercise", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Exercise exercise1;
    @JoinColumn(name = "student", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Student student1;

    public HistoricDeliveryState()
    {
    }

    public HistoricDeliveryState(HistoricDeliveryStatePK historicDeliveryStatePK)
    {
        this.historicDeliveryStatePK = historicDeliveryStatePK;
    }

    public HistoricDeliveryState(byte[] student, byte[] exercise, States state, Date date)
    {
        this.historicDeliveryStatePK = new HistoricDeliveryStatePK(student, exercise, state, date);
    }

    public HistoricDeliveryStatePK getHistoricDeliveryStatePK()
    {
        return historicDeliveryStatePK;
    }

    public void setHistoricDeliveryStatePK(HistoricDeliveryStatePK historicDeliveryStatePK)
    {
        this.historicDeliveryStatePK = historicDeliveryStatePK;
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
        hash += (historicDeliveryStatePK != null ? historicDeliveryStatePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricDeliveryState))
        {
            return false;
        }
        HistoricDeliveryState other = (HistoricDeliveryState) object;
        if ((this.historicDeliveryStatePK == null && other.historicDeliveryStatePK != null) || (this.historicDeliveryStatePK != null && !this.historicDeliveryStatePK.equals(other.historicDeliveryStatePK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "containerjpa.HistoricDeliveryState[ historicDeliveryStatePK=" + historicDeliveryStatePK + " ]";
    }
    enum States {ForRevision,Corrected,WithErrors}
}
