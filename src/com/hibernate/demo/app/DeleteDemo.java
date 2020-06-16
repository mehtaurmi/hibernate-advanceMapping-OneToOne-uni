package com.hibernate.demo.app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Student;

public class DeleteDemo {

	public static void main(String[] args) {
		
		//Create a session Factory
		SessionFactory factory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			int instructorId = 1;
			
			//start session
			session.beginTransaction();
			
			//retrieve Instructor object with given id
			Instructor instructor=session.get(Instructor.class, instructorId);
			
			//Delete the instructor
			//this will also delete InstructorDetail object 
			//because of CascadeeType.All
			System.out.println("Found Instructor:"+instructor);
			if(instructor != null)
			{
				session.delete(instructor);
			}
			
			//end transaction and commit it
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}finally {
			factory.close();
		}

	}

}
