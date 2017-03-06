package les11.logic;

import java.util.*;
import java.sql.*;

public interface StudentDao {

    // Creates a new DB entry as per corresponding received object
	public void create(Student student) throws DaoException;

    // Returns the object corresponding to the DB entry with received primary 'key'
	public Student read(int key) throws DaoException;

    // Modifies the DB entry as per corresponding received object
	public void update(Student student) throws DaoException;

    // Removes the DB entry as per corresponding received object
	public void delete(Student student) throws DaoException;

    // Returns a list of objects corresponding to all DB entries
	public List<Student> getAll() throws DaoException;	
	
	// Terminates the connection and all 'PreparedStatement's
	public void close() throws DaoException;
}
