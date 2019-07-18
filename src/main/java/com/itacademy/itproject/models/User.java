/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itacademy.itproject.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import com.itacademy.itproject.models.Itinerary;
import com.itacademy.itproject.models.Student;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
    , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
    , @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name")
    , @NamedQuery(name = "User.findBySurnames", query = "SELECT u FROM User u WHERE u.surnames = :surnames")
    , @NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.role = :role")
})
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Lob
    @Column(name = "id")
    private byte[] id;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "surnames")
    private String surnames;
    @Basic(optional = false)
    @Column(name = "role")
    private String role;
    @OneToMany(mappedBy = "interviewTeacher")
    private Collection<Student> studentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private Collection<Itinerary> itineraryCollection;

    public User()
    {
    }

    public User(byte[] id)
    {
        this.id = id;
    }

    public User(byte[] id, String username, String password, String name, String surnames, String role)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surnames = surnames;
        this.role = role;
    }

    public byte[] getId()
    {
        return id;
    }

    public void setId(byte[] id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurnames()
    {
        return surnames;
    }

    public void setSurnames(String surnames)
    {
        this.surnames = surnames;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
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
    public Collection<Itinerary> getItineraryCollection()
    {
        return itineraryCollection;
    }

    public void setItineraryCollection(Collection<Itinerary> itineraryCollection)
    {
        this.itineraryCollection = itineraryCollection;
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
        if (!(object instanceof User))
        {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "containerjpa.User[ id=" + id + " ]";
    }

}
