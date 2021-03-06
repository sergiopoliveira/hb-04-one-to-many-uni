package com.sergio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sergio.hibernate.demo.entity.Course;
import com.sergio.hibernate.demo.entity.Instructor;
import com.sergio.hibernate.demo.entity.InstructorDetail;
import com.sergio.hibernate.demo.entity.Review;

public class DeleteCourseAndReviewsDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .addAnnotatedClass(Course.class)
								 .addAnnotatedClass(Review.class)
								 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		// use the session object to save Java object
		try {
			
			session.beginTransaction();
		
			// get the course
			int theId = 10;
			Course tempCourse = session.get(Course.class, theId);
			
			// print the course
			System.out.println(tempCourse);
			
			//delete the course..
			System.out.println("Deleting the course...");
			session.delete(tempCourse);
			
			// print the course reviews
			System.out.println(tempCourse.getReviews());
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} 
		finally {
			
			//add clean up code
			session.close();
			factory.close();
		}
	}

}
