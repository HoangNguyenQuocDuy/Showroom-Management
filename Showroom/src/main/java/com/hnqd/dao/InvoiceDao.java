/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.dao;

import com.hnqd.dto.StatistisResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DELL
 */
@Repository
@Transactional
public class InvoiceDao {

    @Autowired
    private LocalSessionFactoryBean factory;

    public List<StatistisResponse> getMonthlyStatsByYear(int year) {
        List<StatistisResponse> monthlyStatsList = new ArrayList<>();

        for (int month = 1; month <= 12; month++) {
            StatistisResponse monthlyStats = new StatistisResponse();
            monthlyStats.setYear(year);
            monthlyStats.setMonth(month);
            if (countInvoiceBuyByMonth(year, month) != null) {
                monthlyStats.setNumberOfInvoices(countInvoiceBuyByMonth(year, month));
            }
            if (calculateRevenueByMonth(year, month) != null) {
                monthlyStats.setRevenue(calculateRevenueByMonth(year, month));
            }
            monthlyStatsList.add(monthlyStats);
        }

        return monthlyStatsList;
    }

    public Long countInvoiceBuyByMonth(int year, int month) {
        Session s = factory.getObject().getCurrentSession();
        Query query = s.createQuery("SELECT COUNT(invoiceBuy.id) "
                + "FROM Invoicebuy invoiceBuy WHERE YEAR(invoiceBuy.createdAt) = :year "
                + "AND MONTH(invoiceBuy.createdAt) = :month");
        query.setParameter("year", year);
        query.setParameter("month", month);
        return (Long) query.getSingleResult();
    }

    public BigDecimal calculateRevenueByMonth(int year, int month) {
        Session session = factory.getObject().getCurrentSession();
        Query query = session.createQuery("SELECT SUM(invoiceBuy.amount) "
                + "FROM Invoicebuy invoiceBuy WHERE YEAR(invoiceBuy.createdAt) = :year "
                + "AND MONTH(invoiceBuy.createdAt) = :month");
        query.setParameter("year", year);
        query.setParameter("month", month);
        BigDecimal invoiceBuyAmount = (BigDecimal) query.getSingleResult();

        query = session.createQuery("SELECT SUM(invoiceMaintenance.amount) "
                + "FROM Invoicemaintenance invoiceMaintenance "
                + "WHERE YEAR(invoiceMaintenance.createdAt) = :year "
                + "AND MONTH(invoiceMaintenance.createdAt) = :month");
        query.setParameter("year", year);
        query.setParameter("month", month);
        BigDecimal invoiceMaintenanceAmount = (BigDecimal) query.getSingleResult();  

        query = session.createQuery("SELECT SUM(invoiceRent.amount) "
                + "FROM Invoicerent invoiceRent "
                + "WHERE YEAR(invoiceRent.createdAt) = :year "
                + "AND MONTH(invoiceRent.createdAt) = :month");
        query.setParameter("year", year);
        query.setParameter("month", month);
        BigDecimal invoiceRentAmount = (BigDecimal) query.getSingleResult();

        if (invoiceBuyAmount != null) {
            if (invoiceMaintenanceAmount != null) {
                invoiceBuyAmount.add(invoiceMaintenanceAmount);
            }
            if (invoiceRentAmount != null) {
                invoiceBuyAmount.add(invoiceBuyAmount);
            }
        }
        
        return invoiceBuyAmount;
    }

}
