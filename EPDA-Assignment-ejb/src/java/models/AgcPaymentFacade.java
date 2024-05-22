/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author TheOne
 */
@Stateless
public class AgcPaymentFacade extends AbstractFacade<AgcPayment> {

    @PersistenceContext(unitName = "EPDA-Assignment-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AgcPaymentFacade() {
        super(AgcPayment.class);
    }
    
    public List<AgcPayment> findAllPayment() {
        return em.createQuery("SELECT s FROM AgcPayment s", AgcPayment.class).getResultList();
    }
    
    public AgcPayment findPaymentById(Integer id) {
        return em.find(AgcPayment.class, id);
    }
    
    public List<AgcPayment> findByClassId(String classId) {
        return em.createQuery(
            "SELECT p FROM AgcPayment p WHERE p.classId = :classId", AgcPayment.class)
            .setParameter("classId", classId)
            .getResultList();
    }

    public void updateStatus(AgcPayment payment, String status) {
        payment.setStatus(status);
        em.merge(payment);
    }
    
    public List<AgcPayment> findByCustomerId(String customerId) {
        return em.createQuery(
            "SELECT p FROM AgcPayment p WHERE p.customerId = :customerId", AgcPayment.class)
            .setParameter("customerId", customerId)
            .getResultList();
    }
    
    public List<AgcPayment> findAllPaymentDesc() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<AgcPayment> cq = cb.createQuery(AgcPayment.class);
        Root<AgcPayment> rootEntry = cq.from(AgcPayment.class);
        CriteriaQuery<AgcPayment> all = cq.select(rootEntry).orderBy(cb.desc(rootEntry.get("id")));
        TypedQuery<AgcPayment> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
    
    public boolean paymentExists(String customerId, String classId) {
        TypedQuery<AgcPayment> query = em.createQuery(
            "SELECT p FROM AgcPayment p WHERE p.customerId = :customerId AND p.classId = :classId", 
            AgcPayment.class
        );
        query.setParameter("customerId", customerId);
        query.setParameter("classId", classId);
        List<AgcPayment> payments = query.getResultList();
        return !payments.isEmpty();
    }
}
