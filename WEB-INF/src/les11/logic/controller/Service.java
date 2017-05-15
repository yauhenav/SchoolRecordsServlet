package les11.logic.controller;

import java.util.*;

import les11.logic.dao.*;
import les11.logic.dto.*;
import les11.logic.exception.*;
import les11.logic.mysql.*;

public class Service {

    private DaoFactory interDaoFact = null;
    private StudentDao interDaoStud = null;
    private SubjectDao interDaoSub = null;
    private MarkDao interDaoMar = null;

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

    // Retrieve one 'Student' DTO from the DB as per received ID
    public Student displayOneStudent(int key) throws DaoException {
        Student dummyStud1 = interDaoStud.read(key);
        return dummyStud1;
    }

    //Retrieve all 'Student' DTO's from the DB
    public List<Student> displayAllStudents() throws DaoException {
        List<Student> showStuds0 = interDaoStud.getAll();
        return showStuds0;
    }

    // Add new entry into DB as per received attributes for a 'Student' DTO
    public void addStudent(int id, String name, String surname) throws DaoException {
        Student dummyStud4 = new Student();
        dummyStud4.setId(id);
        dummyStud4.setName(name);
        dummyStud4.setSurname(surname);
        interDaoStud.create(dummyStud4);
    }

    // Update existing DB entry as per received attributes for a 'Student' DTO
    public void updateStudent(int id, String name, String surname) throws DaoException {
        Student dummyStud2 = new Student();
        dummyStud2.setId(id);
        dummyStud2.setName(name);
        dummyStud2.setSurname(surname);
        interDaoStud.update (dummyStud2);
    }

    // Delete existing entry from DB as per received ID for a 'Student' DTO
    public void deleteStudent(int id) throws DaoException {
        interDaoStud.delete (id);
    }

    // Retrieve one 'Subject' DTO from DB as per specified ID
    public Subject displayOneSubject(int key) throws DaoException {
        Subject dummySubj1 = interDaoSub.read(key);
        return dummySubj1;
    }

    // Retrieve all 'Subject' DTO's from the DB
    public List<Subject> displayAllSubjects() throws DaoException {
        List<Subject> showSubs0 = interDaoSub.getAll();
        return showSubs0;
    }

    // Add new entry into DB as per received attributes for a 'Subject' DTO
    public void addSubject(int id, String description) throws DaoException {
        Subject dummySubj3 = new Subject();
        dummySubj3.setId(id);
        dummySubj3.setDescription(description);
        interDaoSub.create(dummySubj3);
    }

    // Update existing DB entry as per received attributes for a 'Subject' DTO
    public void updateSubject(int id, String description) throws DaoException {
        Subject dummySubj2 = new Subject();
        dummySubj2.setId(id);
        dummySubj2.setDescription(description);
        interDaoSub.update(dummySubj2);
    }

    // Delete existing entry from DB as per received ID for a 'Subject' DTO
    public void deleteSubject(int id) throws DaoException {
        interDaoSub.delete (id);
    }

    // Retrieve one 'Mark' DTO from DB as per specified ID
    public Mark displayOneMark(int key) throws DaoException {
        Mark dummyMar1 = interDaoMar.read(key);
        return dummyMar1;
    }

    // Retrieve all 'Mark' DTO's from the DB
    public List<Mark> displayAllMarks() throws DaoException {
        List<Mark> showMarks0 = interDaoMar.getAll();
        return showMarks0;
    }

    // Retrieve all 'Mark' DTO's related to one 'Student' DTO as per received ID
    public List<Mark> displayMarksOneStud (int key) throws DaoException {
        List<Mark> showMarks1Stud = interDaoMar.getAllMarkOneStud(key);
        return showMarks1Stud;
    }

    // Add new entry into DB as per received attributes for a 'Mark' DTO
    public void addMark(int id, int value, int studentId, int subjectId) throws DaoException {
        Mark dummyMark2 = new Mark();
        dummyMark2.setId(id);
        dummyMark2.setValue(value);
        dummyMark2.setStudentId(studentId);
        dummyMark2.setSubjectId(subjectId);
        interDaoMar.create(dummyMark2);
    }

    // Update existing DB entry as per received attributes for a 'Mark' DTO
    public void updateMark(int id, int value, int studentId, int subjectId) throws DaoException {
        Mark dummyMar3 = new Mark();
        dummyMar3.setId(id);
        dummyMar3.setValue(value);
        dummyMar3.setStudentId(studentId);
        dummyMar3.setSubjectId(subjectId);
        interDaoMar.update(dummyMar3);
    }

    // Delete existing entry from DB as per received ID for a 'Mark' DTO
    public void deleteMark(int id) throws DaoException {
        interDaoMar.delete (id);
    }

    // Close all 'PreparedStatement' objects and the 'Connection' object
    public void close() {
        DaoException exc = null;
        try {
            if (interDaoStud != null) {
                try {
                    interDaoStud.close();
                } catch (DaoException e) {
                    exc = e;
                }
            } else {
                System.err.println("MySqlStudentDao object was not created");
            }
            if (interDaoSub != null) {
                try {
                    interDaoSub.close();
                } catch (DaoException e) {
                    exc = e;
                }
            } else {
                System.err.println("MySqlSubjectDao object was not created");
            }
            if (interDaoMar != null) {
                try {
                    interDaoMar.close();
                } catch (DaoException e) {
                    exc = e;
                }
            } else {
                System.err.println("MySqlMarkDao object was not created");
            }
            if (interDaoFact != null) {
                try {
                    interDaoFact.close();
                } catch (DaoException e) {
                    exc = e;
                }
            } else {
                System.err.println("MySqlDaoFactory object was not created");
            }
        } finally {
            if (exc != null) {
                System.out.println("Error was caught while attempting to close all PreparesStatement's in all MySql*Dao classes");
                exc.printStackTrace();
            } else {
                System.out.println("No errors was caught while attempting to close all PreparedStatement's in all MySql*Dao classes");
            }
        }
    }
}
