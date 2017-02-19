package com.student.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.student.entity.Student;

@Repository
public class StudentDaoImpl implements StudentDao{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void saveStudent(Student student) {
		entityManager.merge(student);
	}

	@Override
	public java.util.List<Student> getStudents(String name) {
		Query query = null;
		if(name.isEmpty() || name == null || name.equals("*")){
			query = entityManager.createQuery("FROM Student");
		}else{
			query = entityManager.createQuery("FROM Student where name like '%"+name+"%'");
		}
		
		java.util.List<Student> list = query.getResultList();
		return list;
	}
	
	@Override
	public Student getStudent(String usn) {
		Student student = entityManager.find(Student.class, usn);
		return student;
	}
}
