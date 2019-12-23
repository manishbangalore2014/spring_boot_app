
package com.journaldev.hibernate.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.journaldev.hibernate.model.Employee1;

public class HibernateUtil {

	//XML based configuration
	private static SessionFactory sessionFactory;
	
	/* private static Logger logger = Logger.getLogger(HibernateUtil.class); */
	
	//Annotation based configuration
	private static SessionFactory sessionAnnotationFactory;
	
	//Property based configuration
	private static SessionFactory sessionJavaConfigFactory;

    private static SessionFactory buildSessionFactory() {
        try {
                   	StandardServiceRegistry standardRegistry = 
                    new StandardServiceRegistryBuilder()
        		.configure("hibernate.cfg.xml").build();
                   	
        	Metadata metaData = new MetadataSources(
                           standardRegistry).getMetadataBuilder().build();
        	
        	sessionFactory = metaData.getSessionFactoryBuilder().build();
            
            return sessionFactory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static SessionFactory buildSessionAnnotationFactory() {
    	try {
			
    		StandardServiceRegistry standardRegistry = 
    	            new StandardServiceRegistryBuilder()
    			.configure("hibernate-annotation.cfg.xml").build();
    		
 
			/*
			 * logger.info("Hibernate Configuration loaded");
			 * logger.info("Hibernate serviceRegistry created");
			 */
    		Metadata metaData = new MetadataSources(
    	                   standardRegistry).getMetadataBuilder().build();
    		sessionFactory = metaData.getSessionFactoryBuilder().build();
            
            return sessionFactory;
        	
           
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
	}

    private static SessionFactory buildSessionJavaConfigFactory() {
    	try {
    	Configuration configuration = new Configuration();
		
		//Create Properties, can be read from property files too
		Properties props = new Properties();
		props.put("hibernate.connection.driver_class", "oracle.jdbc.driver.OracleDriver");
		props.put("hibernate.connection.url", "jdbc:oracle:thin:@localhost:1521:xe");
		props.put("hibernate.connection.username", "demoadmin");
		props.put("hibernate.connection.password", "Manish");
		props.put("hibernate.current_session_context_class", "thread");
		
        configuration.setProperties(props);
		
		//we can set mapping file or class with annotation
		//addClass(Employee1.class) will look for resource
		// com/journaldev/hibernate/model/Employee1.hbm.xml (not good)
		configuration.addAnnotatedClass(Employee1.class);
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
    	System.out.println("Hibernate Java Config serviceRegistry created");
    	
    	SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    	
        return sessionFactory;
    	}
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
	}
    
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
	
	public static SessionFactory getSessionAnnotationFactory() {
		if(sessionAnnotationFactory == null) sessionAnnotationFactory = buildSessionAnnotationFactory();
        return sessionAnnotationFactory;
    }
	
	public static SessionFactory getSessionJavaConfigFactory() {
		if(sessionJavaConfigFactory == null) sessionJavaConfigFactory = buildSessionJavaConfigFactory();
        return sessionJavaConfigFactory;
    }
	
}
