package les11.logic.controller;

import java.sql.*;
import java.util.*;
import java.io.*;

import les11.logic.dao.*;
import les11.logic.dto.*;
import les11.logic.exception.*;
import les11.logic.mysql.*;

public class Service {
	
	DaoFactory interDaoFact = null;
	StudentDao interDaoStud = null;
	SubjectDao interDaoSub = null;
	MarkDao interDaoMar = null;
	
	// Constructor that establishes connection with the DB & creates required objects
	public Service() {
		try {
			interDaoFact = new MySqlDaoFactory ();
			interDaoStud = interDaoFact.getStudentDao();
			interDaoSub = interDaoFact.getSubjectDao();
			interDaoMar = interDaoFact.getMarkDao();
		} catch (DaoException exc) {
			exc.printStackTrace();
			}
		}
		
	// Method for retrieving and displaying student as per specified ID
	public Student displayOneStudent(int key) throws DaoException {
		Student dummyStud1 = interDaoStud.read(key);
		return dummyStud1;
	}
				
	//Method for retrieving and displaying all students
	public List<Student> displayAllStudents() throws DaoException {
		List<Student> showStuds0 = interDaoStud.getAll();
		return showStuds0;
	}
		
	// Add a new entry into DB as per corresponding received 'Student' object and display the result
	public void addStudent(int id, String name, String surname) throws DaoException {
		Student dummyStud4 = new Student();
		dummyStud4.setId(id);
		dummyStud4.setName(name);
		dummyStud4.setSurname(surname);
		interDaoStud.create(dummyStud4);
	}
	
	// Update DB entry as per specified 'Student' object and display the result
	public void updateStudent(int id, String name, String surname) throws DaoException {
		Student dummyStud2 = new Student();
		dummyStud2.setId(id);
		dummyStud2.setName(name);
		dummyStud2.setSurname(surname);
		interDaoStud.update (dummyStud2);
	}
	
	// Delete DB entry as per specified 'Student' object and display the result
	public void deleteStudent(int id) throws DaoException {
		interDaoStud.delete (id);		
	}
	
	// Retrieve and display subject as per specified ID
	public Subject displayOneSubject(int key) throws DaoException {
		Subject dummySubj1 = interDaoSub.read(key);
		return dummySubj1;
	}
	// Retrieve and display all subjects
	public List<Subject> displayAllSubjects() throws DaoException {
		List<Subject> showSubs0 = interDaoSub.getAll();
		return showSubs0;
	}
	
	// Add a new entry into DB as per corresponding received 'Subject' object and display the result
	public void addSubject(int id, String description) throws DaoException {
		Subject dummySubj3 = new Subject();
		dummySubj3.setId(id);
		dummySubj3.setDescription(description);
		interDaoSub.create(dummySubj3);
	}
	
	// Update DB entry as per specified 'Subject' object and display the result
	public void updateSubject(int id, String description) throws DaoException {
		Subject dummySubj2 = new Subject();
		dummySubj2.setId(id);
		dummySubj2.setDescription(description);
		interDaoSub.update(dummySubj2);
	}
		
	// Delete DB entry as per specified 'Subject' object and display the result
	public void deleteSubject(int id) throws DaoException {
		interDaoSub.delete (id);		
	}	

	// Retrieve and display mark as per specified ID
	public Mark displayOneMark(int key) throws DaoException {
		Mark dummyMar1 = interDaoMar.read(key);
		return dummyMar1;
	}
	
	// Retrieve and display all marks from the DB
	public List<Mark> displayAllMarks() throws DaoException {
		List<Mark> showMarks0 = interDaoMar.getAll();
		return showMarks0;
	}
	
	// Retrieve and display all subjects with all corresponding marks of one student specified by ID
	public List<Mark> displayMarksOneStud (int key) throws DaoException {
		List<Mark> showMarks1Stud = interDaoMar.getAllMarkOneStud(key);
		return showMarks1Stud;
	}
	
	// Add a new entry into DB as per corresponding received 'Mark' object and display the result
	public void addMark(int id, int value, int studentId, int subjectId) throws DaoException {
		Mark dummyMark2 = new Mark();
		dummyMark2.setId(id);
		dummyMark2.setValue(value);
		dummyMark2.setStudentId(studentId);
		dummyMark2.setSubjectId(subjectId);
		interDaoMar.create(dummyMark2);
	}
	
	// Update DB entry as per specified 'Mark' object and display the result
	public void updateMark(int id, int value, int studentId, int subjectId) throws DaoException {
		Mark dummyMar3 = new Mark();
		dummyMar3.setId(id);
		dummyMar3.setValue(value);
		dummyMar3.setStudentId(studentId);
		dummyMar3.setSubjectId(subjectId);
		interDaoMar.update(dummyMar3);
	}
	
	// Delete DB entry as per specified 'Mark' object and display the result
	public void deleteMark(int id) throws DaoException {
		interDaoMar.delete (id);		
	}	
		
	
	// Close all prepared statements and connections
	public void closeEverything() {
		try {
			if (interDaoStud != null) {
				interDaoStud.close();
			} else {
				System.err.println("MySqlStudentDao object was not created");
			}
			if (interDaoSub != null) {
				interDaoSub.close();
			} else {
				System.err.println("MySqlSubjectDao object was not created");
			}
			if (interDaoMar != null) {
				interDaoMar.close();
			} else {
				System.err.println("MySqlMarkDao object was not created");
			}
			if (interDaoFact != null) {
				interDaoFact.close();
			} else {
				System.err.println("MySqlDaoFactory object was not created");
			} 
		} catch (DaoException exc) {
			exc.printStackTrace();
		}
	}
}
/* 			
		
			
		
	
		
		
	
		// Delete DB entry as per specified 'Mark' object and display the result
			System.out.println("Here goes demonstration of a Mark deletion");
			interDaoMar.delete(61);
			List<Mark> showAllMarks4 = interDaoMar.getAll();
			System.out.println ("Here goes a list of all marks of all students for all subjects after deletion");
			Iterator<Mark> itrmar4 = showAllMarks4.iterator();
			while (itrmar4.hasNext()) {
				Mark element = itrmar4.next();
				System.out.println (element);
			}
		} catch (DaoException | SQLException | IOException exc) {
			exc.printStackTrace();
		}
		
		finally {
			try {
				if (interDaoStud != null) {
					interDaoStud.close();
				} else {
					System.err.println("MySqlStudentDao object was not created");
				}
				if (interDaoSub != null) {
					interDaoSub.close();
				} else {
					System.err.println("MySqlSubjectDao object was not created");
				}
				if (interDaoMar != null) {
					interDaoMar.close();
				} else {
					System.err.println("MySqlMarkDao object was not created");
				}
				if (interDaoFact != null) {
					interDaoFact.close();
				} else {
					System.err.println("MySqlDaoFactory object was not created");
				} 
			} catch (DaoException | SQLException exc) {
				exc.printStackTrace();
			}
		}
	} */
