package les11.logic.mysql;

import java.sql.*;
import java.util.*;

import les11.logic.service.*;
import les11.logic.dao.*;
import les11.logic.dto.*;
import les11.logic.exception.*;

public class MySqlMarkDao implements MarkDao {
    private final static String SQL_CREATE = "INSERT INTO daotrain.MARK (ID, VALUE, STUDENT_ID, SUBJECT_ID) VALUES (?, ?, ?, ?)";
    private final static String SQL_READ = "SELECT VALUE, STUDENT_ID, SUBJECT_ID FROM daotrain.MARK WHERE ID = ?";
    private final static String SQL_UPDATE = "UPDATE daotrain.MARK SET VALUE = ?, STUDENT_ID = ?, SUBJECT_ID = ? WHERE ID = ?";
    private final static String SQL_DELETE = "DELETE FROM daotrain.MARK WHERE ID = ?";
    private final static String SQL_GETALL = "SELECT ID, VALUE, STUDENT_ID, SUBJECT_ID FROM daotrain.MARK";
    private final static String SQL_GETALL_ONE_STUDENT = "SELECT ID, VALUE, SUBJECT_ID FROM daotrain.MARK WHERE STUDENT_ID = ?";

    private PreparedStatement psCreateMark = null;
    private PreparedStatement psReadMark = null;
    private PreparedStatement psUpdMark = null;
    private PreparedStatement psDelMark = null;
    private PreparedStatement psGetAllMark = null;
    private PreparedStatement psGetAllMarkOneStud = null;

    // Constructor
    public MySqlMarkDao(Connection connection) throws DaoException {
        try {
            psCreateMark = connection.prepareStatement(SQL_CREATE);
            psReadMark = connection.prepareStatement(SQL_READ);
            psUpdMark = connection.prepareStatement(SQL_UPDATE);
            psDelMark = connection.prepareStatement(SQL_DELETE);
            psGetAllMark = connection.prepareStatement(SQL_GETALL);
            psGetAllMarkOneStud = connection.prepareStatement(SQL_GETALL_ONE_STUDENT);
        } catch (SQLException exc) {
            throw new DaoException ("Exception for DAO", exc);
        }
    }

    // Create a new DB entry as per corresponding received object
    @Override
    public void create(Mark mark) throws DaoException {
        try {
            psCreateMark.setInt(1, mark.getId());
            psCreateMark.setInt(2, mark.getValue());
            psCreateMark.setInt(3, mark.getStudentId());
            psCreateMark.setInt(4, mark.getSubjectId());
            psCreateMark.execute();
        } catch (SQLException exc) {
            throw new DaoException ("Exception for DAO", exc);
        }
    }

    // Return the object corresponding to the DB entry with received primary 'key'
    @Override
    public Mark read(Mark mark) throws DaoException {
        ResultSet rsReadMark = null;
        try {
            psReadMark.setInt(1, mark.getId());
            rsReadMark = psReadMark.executeQuery();
            rsReadMark.next();
            mark.setValue(rsReadMark.getInt("VALUE"));
            mark.setStudentId(rsReadMark.getInt("STUDENT_ID"));
            mark.setSubjectId(rsReadMark.getInt("SUBJECT_ID"));
            return mark;
        } catch (SQLException exc) {
            throw new DaoException ("Exception for DAO", exc);
        }
        finally {
            try {
                if (rsReadMark != null) {
                    rsReadMark.close();
                } else {
                    System.err.println ("RS set of table results was not created");
                }
            } catch (SQLException exc) {
                throw new DaoException ("Exception for DAO", exc);
            }
        }
    }

    // Modify the DB entry as per corresponding received object
    @Override
    public void update(Mark mark) throws DaoException {
        try {
            psUpdMark.setInt(1, mark.getValue());
            psUpdMark.setInt(2, mark.getStudentId());
            psUpdMark.setInt(3, mark.getSubjectId());
            psUpdMark.setInt(4, mark.getId());
            psUpdMark.execute();
        } catch (SQLException exc) {
            throw new DaoException ("Exception for DAO", exc);
        }
    }

    // Remove the DB entry as per corresponding received object
    @Override
    public void delete(Mark mark) throws DaoException {
        try {
            psDelMark.setInt(1, mark.getId());
            psDelMark.execute();
        } catch (SQLException exc) {
            throw new DaoException ("Exception for DAO", exc);
        }
    }

    // Return a list of objects corresponding to all DB entries
    @Override
    public List<Mark> getAll() throws DaoException {
        ResultSet rsGetAllMark = null;
        try {
            rsGetAllMark = psGetAllMark.executeQuery();
            List<Mark> lst = new ArrayList<Mark>();
            while (rsGetAllMark.next()) {
                Mark tempMark1 = new Mark(0,0,0,0);
                tempMark1.setId(rsGetAllMark.getInt("ID"));
                tempMark1.setValue(rsGetAllMark.getInt("VALUE"));
                tempMark1.setStudentId(rsGetAllMark.getInt("STUDENT_ID"));
                tempMark1.setSubjectId(rsGetAllMark.getInt("SUBJECT_ID"));
                lst.add(tempMark1);
            }
            return lst;
        } catch (SQLException exc) {
            throw new DaoException ("Exception for DAO", exc);
        }
        finally {
            try {
                if (rsGetAllMark != null) {
                    rsGetAllMark.close();
                } else {
                    System.err.println ("RS set of table results was not created");
                }
            } catch (SQLException exc) {
                    throw new DaoException ("Exception for DAO", exc);
            }
        }
    }

    // Return a list of Marks of one Student as per received primary 'key'
    @Override
    public List<Mark> getAllMarkOneStud (Mark mark) throws DaoException {
        ResultSet rsGetAllMarkOneStud = null;
        try {
            psGetAllMarkOneStud.setInt(1, mark.getStudentId());
            rsGetAllMarkOneStud = psGetAllMarkOneStud.executeQuery();
            List<Mark> lst = new ArrayList<Mark>();
            while (rsGetAllMarkOneStud.next()) {
                mark.setId(rsGetAllMarkOneStud.getInt("ID"));
                mark.setValue(rsGetAllMarkOneStud.getInt("VALUE"));
                mark.setSubjectId(rsGetAllMarkOneStud.getInt("SUBJECT_ID"));
                lst.add(mark);
            }
            return lst;
        } catch (SQLException exc) {
            throw new DaoException ("Exception for DAO", exc);
        }
        finally {
            try {
                if (rsGetAllMarkOneStud != null) {
                    rsGetAllMarkOneStud.close();
                } else {
                    System.err.println ("RS set of table results was not created");
                }
            } catch (SQLException exc) {
                    throw new DaoException ("Exception for DAO", exc);
            }
        }
    }

    // Terminate 'PreparedStatement' object received as an argument
    private void closePs(PreparedStatement dummyPs) throws DaoException {
        if (dummyPs != null) {
            try {
                dummyPs.close();
                //throw new SQLException(); // Uncomment this line to test exception handling
            } catch (SQLException exc) {
                throw new DaoException("Exception for Dao", exc);
            }
        } else {
            System.err.println ("PS statement was not created");
        }
    }

    // Terminate all 'PreparedStatement' objects
    @Override
    public void close() throws DaoException {
        DaoException exc = null;
        try {
            try {
                this.closePs(psCreateMark);
            } catch (DaoException e) {
                exc = e;
            }
            try {
                this.closePs(psReadMark);
            } catch (DaoException e) {
                exc.printStackTrace();
            }
            try {
                this.closePs(psUpdMark);
            } catch (DaoException e) {
                exc = e;
            }
            try {
                this.closePs(psDelMark);
            } catch (DaoException e) {
                exc = e;
            }
            try {
                this.closePs(psGetAllMark);
            } catch (DaoException e) {
                exc = e;
            }
            try {
                this.closePs(psGetAllMarkOneStud);
            } catch (DaoException e) {
                exc = e;
            }
        } finally {
            if (exc != null) {
                throw exc;
            }
        }
    }
}
