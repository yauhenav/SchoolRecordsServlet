package les11.logic;

import java.sql.*;
import java.util.*;
import java.io.*;

public interface DaoFactory {

    // Returns an object to manage persistent 'Student' object
    public StudentDao getStudentDao() throws DaoException;

    // Returns an object to manage persistent 'Subject' object
    public SubjectDao getSubjectDao() throws DaoException;
	
	// Returns an object to manage persistent 'Mark' object
	public MarkDao getMarkDao () throws DaoException;
	
	// Closes Connection instance object
	public void close() throws SQLException;
}
