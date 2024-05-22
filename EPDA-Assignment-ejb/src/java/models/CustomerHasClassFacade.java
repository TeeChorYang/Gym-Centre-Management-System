/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
public class CustomerHasClassFacade extends AbstractFacade<CustomerHasClass> {

    @PersistenceContext(unitName = "EPDA-Assignment-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerHasClassFacade() {
        super(CustomerHasClass.class);
    }
    
    public List<CustomerHasClass> findByClassId(String classId) {
        return em.createQuery(
            "SELECT chc FROM CustomerHasClass chc WHERE chc.classId = :classId", CustomerHasClass.class)
            .setParameter("classId", classId)
            .getResultList();
    }
    
    public CustomerHasClass findByCustomerIdAndClassId(String customerId, String classId) {
        try {
            return em.createQuery(
                "SELECT chc FROM CustomerHasClass chc WHERE chc.customerId = :customerId AND chc.classId = :classId", CustomerHasClass.class)
                .setParameter("customerId", customerId)
                .setParameter("classId", classId)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public List<AgcCustomer> findCustomerIdsByClassId(String classId) {
        TypedQuery<CustomerHasClass> query = em.createQuery(
            "SELECT chc FROM CustomerHasClass chc WHERE chc.classId = :classId", CustomerHasClass.class);
        query.setParameter("classId", classId);
        List<CustomerHasClass> customerHasClasses = query.getResultList();

        List<AgcCustomer> customers = new ArrayList<>();
        for (CustomerHasClass chc : customerHasClasses) {
            AgcCustomer customer = em.find(AgcCustomer.class, chc.getCustomerId());
            if (customer != null) {
                customers.add(customer);
            }
        }

        return customers;
    }
    
    public long countByCustomerId(String customerId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CustomerHasClass> root = cq.from(CustomerHasClass.class);
        cq.select(cb.count(root));
        cq.where(cb.equal(root.get("customerId"), customerId));
        return em.createQuery(cq).getSingleResult();
    }
    
    public List<CustomerHasClass> findByCustomerId(String customerId) {
        return em.createQuery(
            "SELECT c FROM CustomerHasClass c WHERE c.customerId = :customerId", CustomerHasClass.class)
            .setParameter("customerId", customerId)
            .getResultList();
    }
}
