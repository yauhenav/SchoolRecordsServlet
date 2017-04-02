package les11.logic.mysql;

import java.sql.*;
import java.util.*;

import les11.logic.controller.*;
import les11.logic.dao.*;
import les11.logic.dto.*;
import les11.logic.exception.*;

public class MySqlSubjectDao implements SubjectDao {
    private Connection connection;
	private final static String SQL_CREATE = "INSERT INTO daotrain.SUBJECT (ID, DESCRIPTION) VALUES (?, ?)";
	private final static String SQL_READ = "SELECT ID, DESCRIPTION FROM daotrain.SUBJECT WHERE ID = ?";
	private final static String SQL_UPDATE = "UPDATE daotrain.SUBJECT SET DESCRIPTION = ? WHERE ID = ?";
	private final static String SQL_DELETE = "DELETE FROM daotrain.SUBJECT WHERE ID = ?";
	private final static String SQL_GETALL = "SELECT ID, DESCRIPTION FROM daotrain.SUBJECT";
	
	private PreparedStatement psCreateSubj = null;
	private PreparedStatement psReadSubj = null;
	private PreparedStatement psUpdSubj = null;
	private PreparedStatement psDelSubj = null;
	private PreparedStatement psGetAllSubj = null;
	private ResultSet rsReadSubj = null;
	private ResultSet rsGetAllSubj = null;
	
	// Constructor
	public MySqlSubjectDao(Connection connection) throws DaoException {
        try {
			this.connection = connection;
			psCreateSubj = connection.prepareStatement(SQL_CREATE);
			psReadSubj = connection.prepareStatement(SQL_READ);
			psUpdSubj = connection.prepareStatement(SQL_UPDATE);
			psDelSubj = connection.prepareStatement(SQL_DELETE);
			psGetAllSubj = connection.prepareStatement (SQL_GETALL);
		} catch (SQLException exc) {
			throw new DaoException ("Exception for DAO");
		}			
    }
	
    // Creates a new DB entry as per corresponding received object
    @Override
    public void create(Subject subject) throws DaoException {
		try {
			psCreateSubj.setInt(1, subject.getId());
			psCreateSubj.setString(2, subject.getDescription());
			psCreateSubj.execute();
		} catch (SQLException exc) {
			throw new DaoException ("Excepion for DAO");
		}
    }

    // Returns the object corresponding to the DB entry with received primary 'key'
    @Override
    public Subject read(int key) throws DaoException {
		try {
			psReadSubj.setInt(1, key);
			rsReadSubj = psReadSubj.executeQuery();
			rsReadSubj.next();
			Subject tempSubj0 = new Subject();
			tempSubj0.setId(rsReadSubj.getInt("ID"));
			tempSubj0.setDescription(rsReadSubj.getString("DESCRIPTION"));
			return tempSubj0;
		} catch (SQLException exc) {
			throw new DaoException ("Exception for Dao");
		}
		finally {
			try {
				if (rsReadSubj != null) {
					rsReadSubj.close();
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
    public void update(Subject subject) throws DaoException {
		try {
			psUpdSubj.setString(1, subject.getDescription());
			psUpdSubj.setInt(2, subject.getId());
			psUpdSubj.execute();
		} catch (SQLException exc) {
			throw new DaoException ("Exception for DAO");
		}
	}
	
    // Removes the DB entry as per corresponding received object
    @Override
    public void delete(int key) throws DaoException {
		try {
			psDelSubj.setInt(1, key);
			psDelSubj.execute();
		} catch (SQLException exc) {
			throw new DaoException ("Exception for DAO");
		}
	}
	
    // Returns a list of objects corresponding to all DB entries
    @Override
    public List<Subject> getAll() throws DaoException {
		try {
			rsGetAllSubj = psGetAllSubj.executeQuery();
			List<Subject> list = new ArrayList<Subject>();
			while (rsGetAllSubj.next()) {
				Subject tempSubj1 = new Subject();
				tempSubj1.setId(rsGetAllSubj.getInt("ID"));
				tempSubj1.setDescription(rsGetAllSubj.getString("DESCRIPTION"));
				list.add(tempSubj1);
			}
			return list;
		} catch (SQLException exc) {
			throw new DaoException ("Exception for DAO");
		}
		finally {
			try {
				if (rsGetAllSubj != null) {
					rsGetAllSubj.close();
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
			if (psCreateSubj != null) {
				psCreateSubj.close();
				throw new DaoException("error in psCreateSubj.close()");
				} else {
					System.err.println ("PS statement was not created");
				}
				} catch (DaoException e) {		
					System.err.println ("this error caught in psCreateSub.close() line");
					e.printStackTrace();
				} catch (SQLException exc) {
				exc.printStackTrace();
			}
		try {
			if (psReadSubj != null) {
				psReadSubj.close();
				} else {
					System.err.println ("PS statement was not created");
				}
			} catch (SQLException exc) {
				throw new DaoException ("Exception for DAQ");
			}
		try {
			if (psUpdSubj != null) {
				psUpdSubj.close();
				} else {
					System.err.println ("PS statement was not created");
				}
			} catch (SQLException exc) {
				throw new DaoException ("Exception for DAQ");
			}
		try {
			if (psDelSubj != null) {
				psDelSubj.close();
				} else {
					System.err.println ("PS statement was not created");
				}
			} catch (SQLException exc) {
				throw new DaoException ("Exception for DAQ");
			}
		try {
			if (psGetAllSubj != null) {
				psGetAllSubj.close();
				} else {
					System.err.println ("PS statement was not created");
				}
			} catch (SQLException exc) {
				throw new DaoException ("Exception for DAQ");
			}
		try {
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
