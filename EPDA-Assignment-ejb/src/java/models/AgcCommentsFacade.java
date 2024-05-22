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
public class AgcCommentsFacade extends AbstractFacade<AgcComments> {

    @PersistenceContext(unitName = "EPDA-Assignment-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AgcCommentsFacade() {
        super(AgcComments.class);
    }
    
    public List<AgcComments> findByCustomerId(String customerId) {
        return em.createQuery(
            "SELECT c FROM AgcComments c WHERE c.customerId = :customerId", AgcComments.class)
            .setParameter("customerId", customerId)
            .getResultList();
    }
    
    public List<AgcComments> findByCustomerIdAndClassId(String customerId, String classId) {
        return em.createQuery(
            "SELECT c FROM AgcComments c WHERE c.customerId = :customerId AND c.classId = :classId", AgcComments.class)
            .setParameter("customerId", customerId)
            .setParameter("classId", classId)
            .getResultList();
    }
    
    public List<AgcComments> findByClassId(String classId) {
        return em.createQuery(
            "SELECT c FROM AgcComments c WHERE c.classId = :classId", AgcComments.class)
            .setParameter("classId", classId)
            .getResultList();
    }
    
    public List<AgcComments> findCommentsByClassId(String classId) {
        TypedQuery<AgcComments> query = em.createQuery("SELECT c FROM AgcComments c WHERE c.classId = :classId ORDER BY c.commentDate DESC", AgcComments.class);
        query.setParameter("classId", classId);
        return query.getResultList();
    }
    
}
