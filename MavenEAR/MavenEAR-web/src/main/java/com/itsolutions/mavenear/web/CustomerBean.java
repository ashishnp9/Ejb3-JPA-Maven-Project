package com.itsolutions.mavenear.web;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.itsolutions.mavenear.model.ejbs.CustomerEJBLocal;
import com.itsolutions.mavenear.model.entities.Customer;
import com.itsolutions.maventest.utilities.MessageUtil;

@ManagedBean(name="customerBean")
@SessionScoped
public class CustomerBean {

	@EJB
	CustomerEJBLocal customerEjb;	
	
	private List<Customer> customerList = new ArrayList<Customer>();
	private Customer customer = new Customer();
	
	/**
	 * This method calls deleteCustomer method of CustomerEjb
	 * to delete the Customer Entity
	 * @param customer
	 * @return index to navigate to index.xhtml
	 */
	public String deleteCustomer(Customer customer) {
		
		try {
			customerEjb.deleteCustomer(customer);
			MessageUtil.addSuccessMessage("Post was successfully deleted.");
		} catch (Exception e) {
			MessageUtil.addErrorMessage("Could not delete Customer.");
		}
	    
	    return "index";
	}
	
	/**
	 * 
	 * @return index to navigate to index.xhtml page
	 */
	public String createCustomer() {
		try {
			customerEjb.createCustomer(this.customer);
			MessageUtil.addSuccessMessage("Post was successfully created.");
		} catch (Exception e) {
			MessageUtil.addErrorMessage("Post could not be saved. A Persisting error occured.");
		}
	    
	    return "index";
	}
	
	/**
	 * 
	 * @return view to navigate to view.xhtml page
	 */
	public String updateCustomer() {
		try {
			customerEjb.updateCustomer(this.customer);
			MessageUtil.addSuccessMessage("Customer with id " + this.customer.getCustomerId() + " was successfully updated.");
			
		} catch (Exception e) {
			MessageUtil.addErrorMessage("Customer with id " + this.customer.getCustomerId() + " could not be saved. An update error occured.");
		}
		
		return "view";
	}
	
	/**
	 * This function loads the customers.
	 */
	public void loadCustomers() {
		this.customerList = customerEjb.findAllCustomers();
	}
	
	/**
	 * Finds the customer by id.
	 */
	public void findCustomer() {
		this.customer = customerEjb.findCustomerById(this.customer.getCustomerId());
	}
	
	/**
	 * Clears the current customer bean
	 */
	public void clearCustomer() {
		this.customer = new Customer();
	}
	
	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
