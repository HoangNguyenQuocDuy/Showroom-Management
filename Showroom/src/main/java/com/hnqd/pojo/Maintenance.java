package com.hnqd.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "maintenance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Maintenance.findAll", query = "SELECT m FROM Maintenance m"),
    @NamedQuery(name = "Maintenance.findById", query = "SELECT m FROM Maintenance m WHERE m.id = :id"),
    @NamedQuery(name = "Maintenance.findByStatus", query = "SELECT m FROM Maintenance m WHERE m.status = :status"),
    @NamedQuery(name = "Maintenance.findByCreatedDate", query = "SELECT m FROM Maintenance m WHERE m.createdDate = :createdDate"),
    @NamedQuery(name = "Maintenance.findByUpdatedDate", query = "SELECT m FROM Maintenance m WHERE m.updatedDate = :updatedDate"),
    @NamedQuery(name = "Maintenance.findByTime", query = "SELECT m FROM Maintenance m WHERE m.time = :time")})
public class Maintenance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "content")
    private String content;
    @Size(max = 255)
    @Column(name = "status")
    private String status;
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @JoinColumn(name = "showroom_id", referencedColumnName = "id")
    @ManyToOne
    private Showroom showroomId;
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    @ManyToOne
    private User staffId;
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne
    private User customerId;

    public Maintenance() {
    }

    public Maintenance(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Showroom getShowroomId() {
        return showroomId;
    }

    public void setShowroomId(Showroom showroomId) {
        this.showroomId = showroomId;
    }

    public User getStaffId() {
        return staffId;
    }

    public void setStaffId(User staffId) {
        this.staffId = staffId;
    }

    public User getCustomerId() {
        return customerId;
    }

    public void setCustomerId(User customerId) {
        this.customerId = customerId;
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
        if (!(object instanceof Maintenance)) {
            return false;
        }
        Maintenance other = (Maintenance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hnqd.pojo.Maintenance[ id=" + id + " ]";
    }
    
}