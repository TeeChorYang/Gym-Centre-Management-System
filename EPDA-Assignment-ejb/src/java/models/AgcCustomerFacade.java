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

/**
 *
 * @author TheOne
 */
@Stateless
public class AgcCustomerFacade extends AbstractFacade<AgcCustomer> {

    @PersistenceContext(unitName = "EPDA-Assignment-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AgcCustomerFacade() {
        super(AgcCustomer.class);
    }
    
    public Long countCustomers() {
        return em.createQuery("SELECT COUNT(c) FROM AgcCustomer c", Long.class).getSingleResult();
    }
    
    public List<AgcCustomer> findAllCustomer() {
        return em.createQuery("SELECT s FROM AgcCustomer s", AgcCustomer.class).getResultList();
    }
    
    public AgcCustomer findCustomerById(String customerId) {
        return em.find(AgcCustomer.class, customerId);
    }
    
}
