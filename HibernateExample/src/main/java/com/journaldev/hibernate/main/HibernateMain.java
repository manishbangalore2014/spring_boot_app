
package com.journaldev.hibernate.main;

import java.util.Date;

import org.hibernate.Session;

import com.journaldev.hibernate.model.Employee;
import com.journaldev.hibernate.model.Employee1;
import com.journaldev.hibernate.model.Employee_renamed;
import com.journaldev.hibernate.util.HibernateUtil;

public class HibernateMain {

	public static void main(String[] args) {
		Employee_renamed emp = new Employee_renamed();
		emp.setName("Pankaj6");
		emp.setRole("CEO26");
		emp.setInsertTime(new Date());
		
		//Get Session
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		//start transaction
		session.beginTransaction();
		
		//Save the Model object
		session.save(emp);
		
		//Commit transaction
		session.getTransaction().commit();
		
		System.out.println("Employee ID="+emp.getId());
		
		//terminate session factory, otherwise program won't end
		HibernateUtil.getSessionFactory().close();
	}

}
