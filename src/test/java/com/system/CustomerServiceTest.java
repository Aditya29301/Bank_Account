package com.system;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.system.dao.CustomerRepository;
import com.system.entity.Customer;
import com.system.service.CustomerService;

class CustomerServiceTest {

	@InjectMocks
	private CustomerService customerservice;
	
	@Mock
	private CustomerRepository customerrepository;
	
	@BeforeEach
	public void setup()
	{
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testGetAllCustomers()
	{
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1,"Aditya","Bangalore",null));
		customers.add(new Customer(2,"Aadi","Bareilly",null));
		
		when(customerrepository.findAll()).thenReturn(customers);
		
		List<Customer> result = customerservice.getAllCustomers();
		
		assertEquals(2,result.size());
		assertEquals(1,result.get(0).getCustomerid());
		assertEquals("Aditya",result.get(0).getCustomername());
		assertEquals("Bangalore",result.get(0).getCustomerAddress());
		assertEquals(2,result.get(1).getCustomerid());
		assertEquals("Aadi",result.get(1).getCustomername());
		assertEquals("Bareilly",result.get(1).getCustomerAddress());

		
	}
	
	@Test
	public void getCustomerById()
	{
		Customer c = new Customer(1,"Aditya","Bangalore",null);
		when(customerrepository.findById(1)).thenReturn(Optional.of(c));
		assertEquals("Aditya",c.getCustomername());
	}
	
	@Test
	public void testAddCustomer()
	{
		Customer c = new Customer(3,"Aditya","Kodathi",null);
		when(customerrepository.save(c)).thenReturn(c);
		assertEquals(3,c.getCustomerid());
		assertEquals("Aditya",c.getCustomername());
	}
	
	@Test
	public void testDelereCustomerById()
	{
		Customer c = new Customer(4,"Aadi","India",null);
		customerrepository.save(c);
		customerrepository.deleteById(c.getCustomerid());
		
		Optional<Customer> oc = customerrepository.findById(c.getCustomerid());
		Assertions.assertEquals(true, oc.isEmpty());
	}
	
	@Test
	public void testUpdateCustomer()
	{
		Customer c = new Customer(1,"Aditya","Bareilly",null);
		customerrepository.save(c);
		c.setCustomerid(c.getCustomerid());
		c.setCustomername("Aadi");
		c.setCustomerAddress("Kodathi");
		customerrepository.save(c);
		when(customerrepository.findById(c.getCustomerid())).thenReturn(Optional.of(c));
		assertEquals("Aadi",c.getCustomername());
		assertEquals("Kodathi",c.getCustomerAddress());
		
	}
	
	@Test
	public void testDelete()
	{
		Customer c1 = new Customer(3,"Aditya","Bareilly",null);
		Customer c2 = new Customer(4,"Aadi","Bangalore",null);
		
		customerrepository.save(c1);
		customerrepository.save(c2);
		customerrepository.deleteAll();
		
		List<Customer> list = (List<Customer>) customerrepository.findAll();
		Assertions.assertEquals(true,list.isEmpty());
	}
	

}
