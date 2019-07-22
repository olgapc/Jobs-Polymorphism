/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itacademy.itproject.domain;

import com.itacademy.itproject.models.HistoricStudentItinerary;
import com.itacademy.itproject.models.Itineraries;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "itinerary")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "Itinerary.findAll", query = "SELECT i FROM Itinerary i")
            , @NamedQuery(name = "Itinerary.findByName", query = "SELECT i FROM Itinerary i WHERE i.name = :name")
        })
public class Itinerary implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Lob
    @Column(name = "id")
    private byte[] id;
    @Basic(optional = false)
    @Column(name = "name")
    private Itineraries name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itinerary1")
    private Collection<HistoricStudentItinerary> historicStudentItineraryCollection;
    @OneToMany(mappedBy = "itinerary")
    private Collection<Student> studentCollection;
    @OneToMany(mappedBy = "itinerary")
    private Collection<Exercise> exerciseCollection;
    @JoinColumn(name = "teacher", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User teacher;

    public Itinerary()
    {
    }

    public Itinerary(byte[] id)
    {
        this.id = id;
    }

    public Itinerary(byte[] id, Itineraries name)
    {
        this.id = id;
        this.name = name;
    }

    public byte[] getId()
    {
        return id;
    }

    public void setId(byte[] id)
    {
        this.id = id;
    }

    public Itineraries getName()
    {
        return name;
    }

    public void setName(Itineraries name)
    {
        this.name = name;
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

    @XmlTransient
    public Collection<Student> getStudentCollection()
    {
        return studentCollection;
    }

    public void setStudentCollection(Collection<Student> studentCollection)
    {
        this.studentCollection = studentCollection;
    }

    @XmlTransient
    public Collection<Exercise> getExerciseCollection()
    {
        return exerciseCollection;
    }

    public void setExerciseCollection(Collection<Exercise> exerciseCollection)
    {
        this.exerciseCollection = exerciseCollection;
    }

    public User getTeacher()
    {
        return teacher;
    }

    public void setTeacher(User teacher)
    {
        this.teacher = teacher;
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
        if (!(object instanceof Itinerary))
        {
            return false;
        }
        Itinerary other = (Itinerary) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "containerjpa.Itinerary[ id=" + id + " ]";
    }


}
