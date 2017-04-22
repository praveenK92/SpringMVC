package com.mkyong;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.mkyong.stock.StudentModel;
import com.mkyong.stock.SubjectModel;

public class App {
	public static void main(String[] args) {
		System.out.println("Hibernate one to one (Annotation)");
		ApplicationContext ac=new AnnotationConfigApplicationContext(HibernateConfiguration.class);
		SessionFactory sessionFactory =ac.getBean(SessionFactory.class);
		Session session=sessionFactory.openSession();
		
		HibernateTemplate htemplate=ac.getBean(HibernateTemplate.class);

		session.beginTransaction();

		/*Stock stock = new Stock();

		stock.setStockCode("7052");
		stock.setStockName("PADINI");

		StockDetail stockDetail = new StockDetail();
		stockDetail.setCompName("PADINI Holding Malaysia");
		stockDetail.setCompDesc("one stop shopping");
		stockDetail.setRemark("vinci vinci");
		stockDetail.setListedDate(new Date());

		stock.setStockDetail(stockDetail);
		stockDetail.setStock(stock);

		session.save(stock);*/
		
		List<StudentModel> s1=(List<StudentModel>)session.createQuery("SELECT s FROM StudentModel s").list();
		int i=1;
		for(StudentModel s:s1){
			//s.setName("pikachu"+i++);
			//s.setRollNo(i++);
			//session.save(s);
			System.out.println(s);
		}
		
		StudentModel s11=new StudentModel();
		s11.setName("Madara");
		s11.setSubject("Chemistry");
		i=(int)session.createQuery("SELECT MAX(s.rollNo) FROM StudentModel s").list().get(0);
		s11.setRollNo(i+1);
		List<SubjectModel> subList=new ArrayList<>();
		for(int j=0;j<3;j++){
			subList.add(new SubjectModel(s11.getRollNo(),"subj"+j,40+(j+1)*10,s11));
		}
		System.out.println(s11.getRollNo());
		s11.setSubjectList(subList);
		/*for(SubjectModel s:subList){
			session.saveOrUpdate(s);
		}*/
		session.saveOrUpdate(s11);
		
		session.getTransaction().commit();
		System.out.println("Done");
	}
	
	public static void storeStudent(){
	}
	public static HibernateTemplate getHibernateTemplate(SessionFactory s){
		return new HibernateTemplate(s);
	}
}
