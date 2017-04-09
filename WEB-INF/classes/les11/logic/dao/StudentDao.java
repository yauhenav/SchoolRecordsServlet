package les11.logic.dao;

import java.util.*;
import java.sql.*;

import les11.logic.controller.*;
import les11.logic.dto.*;
import les11.logic.exception.*;
import les11.logic.mysql.*;

public interface StudentDao {

	// Create a new DB entry as per corresponding received object
	public void create(Student student) throws DaoException;

	// Return the object corresponding to the DB entry with received primary 'key'
	public Student read(int key) throws DaoException;

	// Modify the DB entry as per corresponding received object
	public void update(Student student) throws DaoException;

	// Remove the DB entry as per corresponding received object
	public void delete(int key) throws DaoException;

	// Return a list of objects corresponding to all DB entries
	public List<Student> getAll() throws DaoException;	
	
	// Terminate the connection and all 'PreparedStatement's
	public void close();
}
