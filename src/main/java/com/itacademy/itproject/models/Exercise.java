/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itacademy.itproject.models;

import com.itacademy.itproject.models.HistoricDeliveryState;
import com.itacademy.itproject.models.Itinerary;
import com.itacademy.itproject.models.CurrentDeliveryState;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Entity
@Table(name = "exercise")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "Exercise.findAll", query = "SELECT e FROM Exercise e")
            , @NamedQuery(name = "Exercise.findByName", query = "SELECT e FROM Exercise e WHERE e.name = :name")
            , @NamedQuery(name = "Exercise.findByIsCommon", query = "SELECT e FROM Exercise e WHERE e.isCommon = :isCommon")
            , @NamedQuery(name = "Exercise.findByOrder", query = "SELECT e FROM Exercise e WHERE e.order = :order")
        })
public class Exercise implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Lob
    @Column(name = "id")
    private byte[] id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "is_common")
    private short isCommon;
    @Basic(optional = false)
    @Column(name = "order")
    private int order;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exercise1")
    private Collection<CurrentDeliveryState> currentDeliveryStateCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exercise1")
    private Collection<HistoricDeliveryState> historicDeliveryStateCollection;
    @JoinColumn(name = "itinerary", referencedColumnName = "id")
    @ManyToOne
    private Itinerary itinerary;

    public Exercise()
    {
    }

    public Exercise(byte[] id)
    {
        this.id = id;
    }

    public Exercise(byte[] id, String name, short isCommon, int order)
    {
        this.id = id;
        this.name = name;
        this.isCommon = isCommon;
        this.order = order;
    }

    public byte[] getId()
    {
        return id;
    }

    public void setId(byte[] id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public short getIsCommon()
    {
        return isCommon;
    }

    public void setIsCommon(short isCommon)
    {
        this.isCommon = isCommon;
    }

    public int getOrder()
    {
        return order;
    }

    public void setOrder(int order)
    {
        this.order = order;
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
    public Collection<HistoricDeliveryState> getHistoricDeliveryStateCollection()
    {
        return historicDeliveryStateCollection;
    }

    public void setHistoricDeliveryStateCollection(Collection<HistoricDeliveryState> historicDeliveryStateCollection)
    {
        this.historicDeliveryStateCollection = historicDeliveryStateCollection;
    }

    public Itinerary getItinerary()
    {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary)
    {
        this.itinerary = itinerary;
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
        if (!(object instanceof Exercise))
        {
            return false;
        }
        Exercise other = (Exercise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "containerjpa.Exercise[ id=" + id + " ]";
    }

}
