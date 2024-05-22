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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

/**
 *
 * @author TheOne
 */
@Stateless
public class AgcTrainerFacade extends AbstractFacade<AgcTrainer> {

    @PersistenceContext(unitName = "EPDA-Assignment-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AgcTrainerFacade() {
        super(AgcTrainer.class);
    }
    
    public Long countTrainers() {
        return em.createQuery("SELECT COUNT(t) FROM AgcTrainer t", Long.class).getSingleResult();
    }
    
    public List<AgcTrainer> findAllTrainer() {
        return em.createQuery("SELECT s FROM AgcTrainer s", AgcTrainer.class).getResultList();
    }
    
    // In AgcTrainerFacade.java
    public long countCustomersByTrainerId(String trainerId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<AgcClass> root = cq.from(AgcClass.class);
        cq.select(root.get("id")); 
        cq.where(cb.equal(root.get("trainerId"), trainerId));
        List<String> classIds = em.createQuery(cq).getResultList();

     
        long totalCustomers = 0;
        for (String classId : classIds) {
            CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
            Root<CustomerHasClass> countRoot = countQuery.from(CustomerHasClass.class);
            countQuery.select(cb.count(countRoot));
            countQuery.where(cb.equal(countRoot.get("classId"), classId)); 
            totalCustomers += em.createQuery(countQuery).getSingleResult();
        }

        return totalCustomers;
    }
}
