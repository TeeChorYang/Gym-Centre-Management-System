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

/**
 *
 * @author TheOne
 */
@Stateless
public class AgcFeedbackFacade extends AbstractFacade<AgcFeedback> {

    @PersistenceContext(unitName = "EPDA-Assignment-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AgcFeedbackFacade() {
        super(AgcFeedback.class);
    }
    
    public List<AgcFeedback> findByCustomerId(String customerId) {
        return em.createQuery(
            "SELECT f FROM AgcFeedback f WHERE f.customerId = :customerId", AgcFeedback.class)
            .setParameter("customerId", customerId)
            .getResultList();
    }
    
    public List<AgcFeedback> findByClassId(String classId) {
        return em.createQuery(
            "SELECT f FROM AgcFeedback f WHERE f.classId = :classId", AgcFeedback.class)
            .setParameter("classId", classId)
            .getResultList();
    }
    
    public List<AgcFeedback> findFeedbacksByCustomerIdAndClassId(String customerId, String classId) {
        TypedQuery<AgcFeedback> query = em.createQuery("SELECT f FROM AgcFeedback f WHERE f.customerId = :customerId AND f.classId = :classId ORDER BY f.feedbackDate DESC", AgcFeedback.class);
        query.setParameter("customerId", customerId);
        query.setParameter("classId", classId);
        return query.getResultList();
    }
    
}
