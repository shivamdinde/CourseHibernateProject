package day6.main;

import java.io.Serializable;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import day6.beans.CourseProject;
import day6.utils.HibernateUtils;

public class CourseHibernateCRUDMain {
 private static Scanner sc = new Scanner(System.in);
	private static void createNewCourse() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Course Id:");
		int courseId = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Course Title:");
		String Title = sc.nextLine();
		System.out.println("Enter Course Duration:");
		int Duration = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Course Provider:");
		String Provider = sc.nextLine();
		System.out.println("Enter Course Fees");
		int Fees = sc.nextInt();
		sc.nextLine();
		
		CourseProject cst = new CourseProject(courseId,Title,Duration,Provider,Fees);
		
		Transaction transaction = session.beginTransaction();
			session.save(cst);
		transaction.commit();
		session.close();
		factory.close();
		System.out.println("New Course Created.");
	}
	private static void showOneCourse() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		
		Class<CourseProject> entityClassType = CourseProject.class;
		
		System.out.println("Enter Course Id to Display:");
		int id = sc.nextInt();
		
		Serializable entityId = id;
		
		CourseProject foundCourse = session.load(entityClassType, entityId);
		System.out.println(foundCourse);
		session.close();
		factory.close();
	}
	private static void updateCourse() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Class<CourseProject> entityClassType = CourseProject.class;
		
		System.out.println("Enter Course Id to Display:");
		int id = sc.nextInt();
		
		Serializable entityId = id;
		CourseProject foundCourse = session.load(entityClassType, entityId);
		
		Transaction tx = session.beginTransaction();
		
		System.out.println("Enter Title to Update:");
		String Title = sc.nextLine();
		System.out.println("Enter Duration to Update:");
		int duration = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Provider to Update:");
		String Provider = sc.nextLine();
		System.out.println("Enter Fees to Update:");
		int Fees = sc.nextInt();
		sc.nextLine();
		
		foundCourse.setTitle(Title);
		foundCourse.setDuration(duration);
		foundCourse.setProvider(Provider);
		foundCourse.setFees(Fees);
		tx.commit();
		
		session.close();
		factory.close();
		System.out.println("Course Record Updated");
	}
	private static void deleteCourse() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Class<CourseProject> entityClassType = CourseProject.class;
		
		System.out.println("Enter Course Id to Display:");
		int id = sc.nextInt();
		
		Serializable entityId = id;
		CourseProject foundCourse = session.load(entityClassType, entityId);
		
		Transaction tx = session.beginTransaction();
		session.delete(foundCourse);
		tx.commit();
		
		session.close();
		factory.close();
		System.out.println("Course Record Deleted");
	}
	private static void showCourseAgain() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Class<CourseProject> entityClassType = CourseProject.class;
		
		System.out.println("Enter Course Id to Display:");
		int id = sc.nextInt();
		
		Serializable entityId = id;
		CourseProject foundCourse = session.load(entityClassType, entityId);
		
		System.out.println(foundCourse.getCourseId());
		System.out.println(foundCourse.getTitle());
		System.out.println(foundCourse.getDuration());
		System.out.println(foundCourse.getProvider());
		System.out.println(foundCourse.getFees());
	}
	
	public static void main(String[] args) {
		
		//createNewCourse();
		//updateCourse();
		//deleteCourse();
		//showCourseAgain();
		
		
		int choice;
		
		do {
			System.out.println("1.Create New Course\n2.Update Course\n3.Delete Course\n4.Show Course Again");
			choice = sc.nextInt();
			switch(choice) {
			case 0:
				break;
				
			case 1:{
				createNewCourse();
				break;
			}
			case 2:{
				updateCourse();
				break;
			}
			case 3:{
				deleteCourse();
				break;
			}	
			case 4:{
				showCourseAgain();
				break;
			}
			default:
				break;
			}
		}
		while(choice!=0);
	}

}
