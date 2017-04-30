package les11.logic.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import les11.logic.controller.*;
import les11.logic.dao.*;
import les11.logic.exception.*;
import les11.logic.mysql.*;

public interface DaoFactory {

    // Return an object to manage persistent 'Student' object
    public StudentDao getStudentDao() throws DaoException;

    // Return an object to manage persistent 'Subject' object
    public SubjectDao getSubjectDao() throws DaoException;

    // Return an object to manage persistent 'Mark' object
    public MarkDao getMarkDao () throws DaoException;

    // Close Connection instance object
    public void close() throws DaoException;
}
