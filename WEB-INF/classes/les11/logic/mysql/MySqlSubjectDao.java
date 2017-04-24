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
	
	// Create a new DB entry as per corresponding received object
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

	// Return the object corresponding to the DB entry with received primary 'key'
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
	
	// Modify the DB entry as per corresponding received object
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
	
	// Remove the DB entry as per corresponding received object
	@Override
	public void delete(int key) throws DaoException {
		try {
			psDelSubj.setInt(1, key);
			psDelSubj.execute();
		} catch (SQLException exc) {
			throw new DaoException ("Exception for DAO");
		}
	}
	
	// Return a list of objects corresponding to all DB entries
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
	
	// Terminate 'PreparedStatement' object received as an argument
	private void closePs(PreparedStatement dummyPs) throws SQLException {
		if (dummyPs != null) {
			dummyPs.close();
			throw new SQLException(); // Uncomment this line to test exception handling
		} else {
			System.err.println ("PS statement was not created");
		}
	}

	/*
	// Terminate all 'PreparedStatement' objects - Version 1
	@Override
	public void close() throws DaoException {
		PreparedStatement[] array = {psCreateSubj, psReadSubj, psUpdSubj, psDelSubj, psGetAllSubj};
		int x =0;
		while (x<5) {
			try {
				closePs(array[x]);
			} catch (SQLException exc) {
				System.out.println("attempt " + x); // Uncomment this line to test exception handling
				throw new DaoException("Exception for Dao");
			} finally {
				x++;
				continue;
			}
		}
	}
	*/
	

	// Terminate all 'PreparedStatement' objects - Version 2
	public void close() throws DaoException {
		try {
			closePs(psCreateSubj);
		} catch (SQLException exc) {
			System.out.println("1th exc thrown");
			throw new DaoException("Exception for Dao");
		}
		finally {
			try {
				closePs(psReadSubj);
			} catch (SQLException exc) {
				System.out.println("2th exc thrown");
				throw new DaoException("Exception for Dao");
			}
			finally {
				try {
					closePs(psUpdSubj);
				} catch (SQLException exc) {
					System.out.println("3th exc thrown");
					throw new DaoException("Exception for Dao");
				}
				finally {
					try {
						closePs(psDelSubj);
					} catch (SQLException exc) {
						System.out.println("4th exc thrown");
						throw new DaoException("Exception for Dao");
						
					}
					finally {
						try {
							closePs(psGetAllSubj);
						} catch (SQLException exc) {
							System.out.println("5th exc thrown");
							throw new DaoException("Exception for Dao");
							
						}
						finally {
							System.out.println("Attempts were undertaken to close all 'PreparedStatement's in MySqlSubject Dao");
						}
					}
				}
			}
		}
	}
}

