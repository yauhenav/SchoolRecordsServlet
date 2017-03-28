package les11.logic.mysql;

import java.sql.*;
import java.util.*;

import les11.logic.controller.*;
import les11.logic.dao.*;
import les11.logic.dto.*;
import les11.logic.exception.*;

public class MySqlStudentDao implements StudentDao {
    private Connection connection;
	public final static String SQL_CREATE = "INSERT INTO daotrain.STUDENT (ID, NAME, SURNAME) VALUES (?, ?, ?)";
	public final static String SQL_READ = "SELECT ID, NAME, SURNAME FROM daotrain.STUDENT WHERE ID = ?";
	public final static String SQL_UPDATE = "UPDATE daotrain.STUDENT SET NAME = ?, SURNAME = ? WHERE ID = ?";
	public final static String SQL_DELETE = "DELETE FROM daotrain.STUDENT WHERE ID = ?";
	public final static String SQL_GETALL = "SELECT ID, NAME, SURNAME FROM daotrain.STUDENT";
	
	public PreparedStatement psCreateStud = null;
	public PreparedStatement psReadStud = null;
	public PreparedStatement psUpdStud = null;
	public PreparedStatement psDelStud = null;
	public PreparedStatement psGetAllStud = null;
	public ResultSet rsReadStud = null;
	public ResultSet rsGetAllStud = null;
	
	// Constructor
	public MySqlStudentDao(Connection connection) throws DaoException {
		try {
		this.connection = connection;
		psCreateStud = connection.prepareStatement(SQL_CREATE);
		psReadStud = connection.prepareStatement(SQL_READ);
		psUpdStud = connection.prepareStatement(SQL_UPDATE);
		psDelStud = connection.prepareStatement(SQL_DELETE);
		psGetAllStud = connection.prepareStatement(SQL_GETALL);
		} catch (SQLException exc) {
			throw new DaoException ("Exception for DAO");
		}
    }
	
    // Creates a new DB entry as per corresponding received object
    @Override 
    public void create(Student student) throws DaoException {
		try {
			psCreateStud.setInt(1, student.getId());
			psCreateStud.setString(2, student.getName());
			psCreateStud.setString(3, student.getSurname());
			psCreateStud.execute();
		} catch (SQLException exc) {
			throw new DaoException ("Exception for DAO");
		} 
	}
    
    // Returns the object corresponding to the DB entry with received primary 'key'
	@Override 
    public Student read(int key) throws DaoException {
		try {
			psReadStud.setInt(1, key);
			rsReadStud = psReadStud.executeQuery();
			rsReadStud.next();
			Student tempStud0 = new Student();
			tempStud0.setId(rsReadStud.getInt("ID"));
			tempStud0.setName(rsReadStud.getString("NAME"));
			tempStud0.setSurname(rsReadStud.getString("SURNAME"));
			return tempStud0;
		} catch (SQLException exc) {
			throw new DaoException ("Exception for DAO");
		}
		finally {
			try {
				if (rsReadStud != null) {
					rsReadStud.close();
				} else {
					System.err.println ("RS set of table results was not created");
				}
			} catch (SQLException exc) {
				throw new DaoException ("Exception for DAO");
			}
		}
	}	
	
    // Modifies the DB entry as per corresponding received object
	@Override 
    public void update(Student student) throws DaoException {
		try {
			psUpdStud.setString(1, student.getName());
			psUpdStud.setString(2, student.getSurname());
			psUpdStud.setInt(3, student.getId());
			psUpdStud.execute();
		} catch (SQLException exc) {
			throw new DaoException ("Exception for DAO");
		} 
	}

    // Removes the DB entry as per corresponding received object
    @Override 
    public void delete(int key) throws DaoException {
		try {
			psDelStud.setInt (1, key);
			psDelStud.execute();
		} catch (SQLException exc) {
			throw new DaoException ("Exception for DAO");
		} 
	}

    // Returns a list of objects corresponding to all DB entries
    @Override
    public List<Student> getAll() throws DaoException {
		try {
			rsGetAllStud = psGetAllStud.executeQuery();        
			List<Student> list = new ArrayList<Student>();
			while (rsGetAllStud.next()) {
				Student tempStud1 = new Student();
				tempStud1.setId(rsGetAllStud.getInt("ID"));
				tempStud1.setName(rsGetAllStud.getString("NAME"));
				tempStud1.setSurname(rsGetAllStud.getString("SURNAME"));
				list.add(tempStud1);
			}
			return list;
		} catch (SQLException exc) {
			throw new DaoException ("Exception for DAO");
		}
		finally {
			try {
				if (rsGetAllStud != null) {
					rsGetAllStud.close();
				} else {
					System.err.println ("RS set of table results was not created");
				}
			} catch (SQLException exc) {
					throw new DaoException ("Exception for DAO");
			}
		}
	}
	
	// Terminates the connection and all 'PreparedStatement's
	public void close() throws DaoException {
		try {
			if (psCreateStud != null) {
				psCreateStud.close();
				} else {
					System.err.println ("PS statement was not created");
				}
			if (psReadStud != null) {
				psReadStud.close();
				} else {
					System.err.println ("PS statement was not created");
				}
			if (psUpdStud != null) {
				psUpdStud.close();
				} else {
					System.err.println ("PS statement was not created");
				}
			if (psDelStud != null) {
				psDelStud.close();
				} else {
					System.err.println ("PS statement was not created");
				}
			if (psGetAllStud != null) {
				psGetAllStud.close();
				} else {
					System.err.println ("PS statement was not created");
				}
			if (connection != null) {
				connection.close();
				} else {
					System.err.println ("Connection was not established");
				}
		} catch (SQLException exc) {
			throw new DaoException ("Exception for DAO");
		}
	}
}
