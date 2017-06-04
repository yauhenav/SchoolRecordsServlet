package les11.logic.dao;

import java.util.*;
import java.sql.*;

import les11.logic.controller.*;
import les11.logic.dto.*;
import les11.logic.exception.*;
import les11.logic.mysql.*;

public interface StudentDao {

    // Create a new DB entry as per corresponding received object
    void create(Student student) throws DaoException;

    // Return the object corresponding to the DB entry with received primary 'key'
    Student read(Student student) throws DaoException;

    // Modify the DB entry as per corresponding received object
    void update(Student student) throws DaoException;

    // Remove the DB entry as per corresponding received object
    void delete(Student student) throws DaoException;

    // Return a list of objects corresponding to all DB entries
    List<Student> getAll() throws DaoException;

    // Terminate the connection and all 'PreparedStatement's
    void close() throws DaoException;
}
