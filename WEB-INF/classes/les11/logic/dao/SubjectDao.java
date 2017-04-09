package les11.logic.dao;

import java.util.*;
import java.sql.*;

import les11.logic.controller.*;
import les11.logic.dto.*;
import les11.logic.exception.*;
import les11.logic.mysql.*;

public interface SubjectDao {

	// Create a new DB entry as per corresponding received object
	public void create(Subject subject) throws DaoException;

	// Return the object corresponding to the DB entry with received primary 'key'
	public Subject read(int key) throws DaoException;

	// Modify the DB entry as per corresponding received object
	public void update(Subject subject) throws DaoException;

	// Remove the DB entry as per corresponding received object
	public void delete(int key)  throws DaoException;

	// Return a list of objects corresponding to all DB entries
	public List<Subject> getAll() throws DaoException;
	
	// Terminate the connection and all 'PreparedStatements
	public void close();
}
