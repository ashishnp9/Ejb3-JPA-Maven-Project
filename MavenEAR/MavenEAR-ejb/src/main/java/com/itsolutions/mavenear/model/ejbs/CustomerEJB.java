package com.itsolutions.mavenear.model.ejbs;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.itsolutions.mavenear.model.entities.Customer;

/**
 * Session Bean implementation class CustomerEJB
 */
@Stateless
@LocalBean
public class CustomerEJB implements CustomerEJBLocal{

	@PersistenceContext(unitName="primary")
    EntityManager em;
   
    public List<Customer> customers = new ArrayList<Customer>();
    
    @Override
    public List<Customer> findAllCustomers() {
    	try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
			Root<Customer> customer = query.from(Customer.class);
			query.select(customer);
			return em.createQuery(query).getResultList();
			
		} catch (NoResultException nre) {
			nre.printStackTrace();
			System.out.println("No Result was found");
			return null;
			
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			System.out.println("Ejb Exception occured");
			return null;
		}
		
    }
    
    @Override
	public Customer findCustomerById(int id) {
    	try {
    		CriteriaBuilder cb = em.getCriteriaBuilder();
    		CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
    		Root<Customer> customer = query.from(Customer.class);
    		query.where(cb.equal(customer.get("customerId"), id));
    		return em.createQuery(query).getSingleResult();
    		
    	} catch (NoResultException nre) {
    		return null;
    		
    	} catch ( NonUniqueResultException nure ) {
    		return null;
    	}
		//return em.find(Customer.class, id);
	}

	@Override
	public void createCustomer(Customer customer) {
		em.persist(customer);
		em.flush();
	}

	@Override
	public void updateCustomer(Customer cust) {
		Customer customer = findCustomerById(cust.getCustomerId());
		
		customer.setName(cust.getName());
		customer.setEmail(cust.getEmail());
		customer.setAddress(cust.getAddress());
		
		em.persist(customer);
		em.flush();
	}

	@Override
	public void deleteCustomer(Customer cust) {
		Customer customer = findCustomerById(cust.getCustomerId());
		em.remove(customer);	
	}


}
