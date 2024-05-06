/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.pojo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "rental")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rental.findAll", query = "SELECT r FROM Rental r"),
    @NamedQuery(name = "Rental.findById", query = "SELECT r FROM Rental r WHERE r.id = :id"),
    @NamedQuery(name = "Rental.findByStatus", query = "SELECT r FROM Rental r WHERE r.status = :status"),
    @NamedQuery(name = "Rental.findByStartDate", query = "SELECT r FROM Rental r WHERE r.startDate = :startDate"),
    @NamedQuery(name = "Rental.findByEndDate", query = "SELECT r FROM Rental r WHERE r.endDate = :endDate"),
    @NamedQuery(name = "Rental.findByCreatedAt", query = "SELECT r FROM Rental r WHERE r.createdAt = :createdAt"),
    @NamedQuery(name = "Rental.findByUpdatedAt", query = "SELECT r FROM Rental r WHERE r.updatedAt = :updatedAt")})
public class Rental implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "status")
    private String status;
    @Column(name = "startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(mappedBy = "rentalId")
    private Set<Invoicerent> invoicerentSet;
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne
    private User customerId;
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    @ManyToOne
    private User staffId;
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    @ManyToOne
    private Vehicle vehicleId;

    public Rental() {
    }

    public Rental(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date time) {
        this.startDate = time;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date time) {
        this.endDate = time;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @XmlTransient
    public Set<Invoicerent> getInvoicerentSet() {
        return invoicerentSet;
    }

    public void setInvoicerentSet(Set<Invoicerent> invoicerentSet) {
        this.invoicerentSet = invoicerentSet;
    }

    public User getCustomerId() {
        return customerId;
    }

    public void setCustomerId(User customerId) {
        this.customerId = customerId;
    }

    public User getStaffId() {
        return staffId;
    }

    public void setStaffId(User staffId) {
        this.staffId = staffId;
    }

    public Vehicle getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Vehicle vehicleId) {
        this.vehicleId = vehicleId;
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
        if (!(object instanceof Rental)) {
            return false;
        }
        Rental other = (Rental) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hnqd.pojo.Rental[ id=" + id + " ]";
    }

}
