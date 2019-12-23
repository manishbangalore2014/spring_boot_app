
package com.journaldev.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.journaldev.hibernate.model.Employee;
import com.journaldev.hibernate.util.HibernateUtil;

public class HibernateGetVsLoad {

	public static void main(String[] args) {
		
		//Prep Work
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		/*
		 * //Get Example Employee emp = (Employee) session.get(Employee.class, new
		 * Integer(100)); System.out.println("Employee get called");
		 * System.out.println("Employee ID= "+emp.getId());
		 * System.out.println("Employee Get Details:: "+emp+"\n");
		 * 
		 * //load Example Employee emp1 = (Employee) session.load(Employee.class, new
		 * Integer(200)); System.out.println("Employee load called");
		 * System.out.println("Employee ID= "+emp1.getId());
		 * System.out.println("Employee load Details:: "+emp1+"\n");
		 */

		//Get Example
		try{
		Employee emp = (Employee) session.get(Employee.class, new Integer(200));
		System.out.println("Employee get called");
		if(emp != null){
		System.out.println("Employee GET ID= "+emp.getId());
		System.out.println("Employee Get Details:: "+emp+"\n");
		}
		}catch(Exception e){
			e.printStackTrace();
		}

		//load Example
		try{
		Employee emp1 = (Employee) session.load(Employee.class, new Integer(100));
		System.out.println("Employee load called");
		System.out.println("Employee LOAD ID= "+emp1.getId());
		System.out.println("Employee load Details:: "+emp1+"\n");
		}catch(Exception e){
			e.printStackTrace();
		}

		
		
		//Close resources
		tx.commit();
		sessionFactory.close();
	}
}
