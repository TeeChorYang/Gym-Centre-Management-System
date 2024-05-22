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
import javax.persistence.criteria.Root;

/**
 *
 * @author TheOne
 */
@Stateless
public class AgcClassFacade extends AbstractFacade<AgcClass> {

    @PersistenceContext(unitName = "EPDA-Assignment-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AgcClassFacade() {
        super(AgcClass.class);
    }
    
    public Long countClasses() {
        return em.createQuery("SELECT COUNT(cl) FROM AgcClass cl", Long.class).getSingleResult();
    }
    
    public List<AgcClass> findAllClass() {
        return em.createQuery("SELECT s FROM AgcClass s", AgcClass.class).getResultList();
    }
    
    public AgcClass findClassById(String id) {
        return em.find(AgcClass.class, id);
    }
    
    public List<AgcClass> findByTrainerId(String trainerId) {
        return em.createQuery(
            "SELECT a FROM AgcClass a WHERE a.trainerId = :trainerId", AgcClass.class)
            .setParameter("trainerId", trainerId)
            .getResultList();
    }
    
    public boolean idExists(String id) {
        return em.find(AgcClass.class, id) != null;
    }
    
    public long countByTrainerId(String trainerId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<AgcClass> root = cq.from(AgcClass.class);
        cq.select(cb.count(root));
        cq.where(cb.equal(root.get("trainerId"), trainerId));
        return em.createQuery(cq).getSingleResult();
    }
}
