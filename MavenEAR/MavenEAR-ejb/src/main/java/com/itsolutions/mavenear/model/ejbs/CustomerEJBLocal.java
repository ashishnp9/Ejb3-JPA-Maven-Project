package com.itsolutions.mavenear.model.ejbs;

import java.util.List;

import javax.ejb.Local;

import com.itsolutions.mavenear.model.entities.Customer;

@Local
public interface CustomerEJBLocal {

	/**
	 * 
	 * @return Returns the list of customers
	 */
	public List<Customer> findAllCustomers();
	
	/**
	 * Finds the customer by id
	 * @param id
	 * @return returns the Customer
	 */
	public Customer findCustomerById(int id);
	
	/**
	 * Creates a customer in the database
	 * @param customer
	 */
	public void createCustomer(Customer customer);
	
	/**
	 * deletes the customer from the database
	 * @param customer
	 */
	public void deleteCustomer(Customer customer);
	
	/**
	 * updates the customer in the database
	 * @param customer
	 */
	public void updateCustomer(Customer customer);
	
	
}
