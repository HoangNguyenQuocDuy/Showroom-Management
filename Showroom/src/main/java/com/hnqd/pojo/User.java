/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByImage", query = "SELECT u FROM User u WHERE u.image = :image"),
    @NamedQuery(name = "User.findByRoleName", query = "SELECT u FROM User u WHERE u.roleName = :roleName")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "image")
    private String image;
    @Size(max = 255)
    @Column(name = "roleName")
    private String roleName;
    @OneToMany(mappedBy = "customerId")
    private Set<Booking> bookingSet;
    @OneToMany(mappedBy = "staffId")
    private Set<Booking> bookingSet1;
    @OneToMany(mappedBy = "staffId")
    private Set<Invoicemaintenance> invoicemaintenanceSet;
    @OneToMany(mappedBy = "customerId")
    private Set<Invoicebuy> invoicebuySet;
    @OneToMany(mappedBy = "staffId")
    private Set<Invoicebuy> invoicebuySet1;
    @OneToMany(mappedBy = "userId")
    private Set<Maintenance> maintenanceSet;
    @JoinColumn(name = "showroom_id", referencedColumnName = "id")
    @ManyToOne
    private Showroom showroomId;
    @OneToMany(mappedBy = "customerId")
    private Set<Rental> rentalSet;
    @OneToMany(mappedBy = "staffId")
    private Set<Rental> rentalSet1;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @XmlTransient
    public Set<Booking> getBookingSet() {
        return bookingSet;
    }

    public void setBookingSet(Set<Booking> bookingSet) {
        this.bookingSet = bookingSet;
    }

    @XmlTransient
    public Set<Booking> getBookingSet1() {
        return bookingSet1;
    }

    public void setBookingSet1(Set<Booking> bookingSet1) {
        this.bookingSet1 = bookingSet1;
    }

    @XmlTransient
    public Set<Invoicemaintenance> getInvoicemaintenanceSet() {
        return invoicemaintenanceSet;
    }

    public void setInvoicemaintenanceSet(Set<Invoicemaintenance> invoicemaintenanceSet) {
        this.invoicemaintenanceSet = invoicemaintenanceSet;
    }

    @XmlTransient
    public Set<Invoicebuy> getInvoicebuySet() {
        return invoicebuySet;
    }

    public void setInvoicebuySet(Set<Invoicebuy> invoicebuySet) {
        this.invoicebuySet = invoicebuySet;
    }

    @XmlTransient
    public Set<Invoicebuy> getInvoicebuySet1() {
        return invoicebuySet1;
    }

    public void setInvoicebuySet1(Set<Invoicebuy> invoicebuySet1) {
        this.invoicebuySet1 = invoicebuySet1;
    }

    @XmlTransient
    public Set<Maintenance> getMaintenanceSet() {
        return maintenanceSet;
    }

    public void setMaintenanceSet(Set<Maintenance> maintenanceSet) {
        this.maintenanceSet = maintenanceSet;
    }

    public Showroom getShowroomId() {
        return showroomId;
    }

    public void setShowroomId(Showroom showroomId) {
        this.showroomId = showroomId;
    }

    @XmlTransient
    public Set<Rental> getRentalSet() {
        return rentalSet;
    }

    public void setRentalSet(Set<Rental> rentalSet) {
        this.rentalSet = rentalSet;
    }

    @XmlTransient
    public Set<Rental> getRentalSet1() {
        return rentalSet1;
    }

    public void setRentalSet1(Set<Rental> rentalSet1) {
        this.rentalSet1 = rentalSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hnqd.pojo.User[ id=" + id + " ]";
    }
    
}
