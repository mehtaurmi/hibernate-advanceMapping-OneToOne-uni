package com.hibernate.demo.app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {
		
		//Create a session Factory
		SessionFactory factory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//create two objects
			/*Instructor instructor=new Instructor("Mary","Deo","mary@gmail.com");
			InstructorDetail instructorDetail=new InstructorDetail("www.mary.com/youtube","cooking");*/
			
			Instructor instructor=new Instructor("John","Martin","john@gmail.com");
			InstructorDetail instructorDetail=new InstructorDetail("www.john.com/youtube","swimming");
			
			//associate two objects
			instructor.setInstructorDetail(instructorDetail);
			
			//start session
			session.beginTransaction();
			
			//Save the instructor
			//this will also save InstructorDetail object 
			//because of CascadeeType.All
			System.out.println("Savind Instructor:"+instructor);
			session.save(instructor);
			
			//end transaction and commit it
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}finally {
			factory.close();
		}

	}

}
