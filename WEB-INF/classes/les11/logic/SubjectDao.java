package les11.logic;

import java.util.*;
import java.sql.*;

public interface SubjectDao {

    // Creates a new DB entry as per corresponding received object
	public void create(Subject subject) throws DaoException;

    // Returns the object corresponding to the DB entry with received primary 'key'
	public Subject read(int key) throws DaoException;

    // Modifies the DB entry as per corresponding received object
	public void update(Subject subject) throws DaoException;

    // Removes the DB entry as per corresponding received object
	public void delete(Subject subject)  throws DaoException;

    // Returns a list of objects corresponding to all DB entries
	public List<Subject> getAll() throws DaoException;
	
	// Terminates the connection and all 'PreparedStatement's
	public void close() throws DaoException;
}
