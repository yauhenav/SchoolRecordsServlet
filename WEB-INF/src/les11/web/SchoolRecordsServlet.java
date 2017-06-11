package les11.web;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import les11.logic.service.*;
import les11.logic.dto.*;
import les11.logic.exception.*;

public class SchoolRecordsServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        try {
            HttpSession sessObj = req.getSession(true);
            Service sesMngObj = (Service) sessObj.getAttribute("sessionObject");
            resp.setContentType("text/html;charset=utf-8");
            pw = resp.getWriter();
            String action = req.getParameter("action");

            switch (action) {
                case "Show all students":
                    executeShowAllStudents(pw, sesMngObj, sessObj);
                    break;
                case "Show student":
                    executeShowOneStudent(pw, sesMngObj, req);
                    break;
                case "Add new student":
                    executeAddNewStudent(pw, sesMngObj, req);
                    break;
                case "Update student":
                    executeUpdateStudent(pw, sesMngObj, req);
                    break;
                case "Delete student":
                    executeDeleteStudent(pw, sesMngObj, req);
                    break;
                case "Show subject":
                    executeShowOneSubject(pw, sesMngObj, req);
                    break;
                case "Show all subjects":
                    executeShowAllSubjects(pw, sesMngObj);
                    break;
                case "Add new subject":
                    executeAddNewSubject(pw, sesMngObj, req);
                    break;
                case "Update subject":
                    executeUpdateExistingSubject(pw, sesMngObj, req);
                    break;
                case "Delete subject":
                    executeDeleteSpecifiedSubject(pw, sesMngObj, req);
                    break;
                case "Show mark":
                    executeShowOneSpecifiedMark(pw, sesMngObj, req);
                    break;
                case "Show all marks":
                    executeShowAllMarks(pw, sesMngObj);
                    break;
                case "Show student's marks":
                    executeShowMarksOneStudent(pw, sesMngObj, req);
                    break;
                case "Add new mark":
                    executeAddNewMark(pw, sesMngObj, req);
                    break;
                case "Update existing mark":
                    executeUpdateExistingMark(pw, sesMngObj, req);
                    break;
                case "Delete mark":
                    executeDeleteSpecifiedMark(pw, sesMngObj, req);
                    break;
                case "Close everything":
                    executeCloseEverything(pw, sessObj);
                    break;
                default:
                    pw.println("Your request doesn't match any available action");
            }
        } catch (DaoException | ServiceException exc) {
            exc.printStackTrace();
            pw.println("An error occurred while trying to process your request");
        }
    }

    /**
     * Display all students
     */
    private void executeShowAllStudents(PrintWriter pw, Service sesMngObj, HttpSession sessObj) throws DaoException, ServiceException {
        pw.println("<B>Here's a list of all students in the DB</B>");
        pw.println("<table border=1>");
        //Uncomment the line below to print session number to web-page for test purposes
        pw.println(sessObj);
        //Uncomment the line below to print session number to web server console for test purposes
        System.out.println(sessObj);
        List<Student> lst = sesMngObj.displayAllStudents();
        Iterator<Student> iterator = lst.iterator();
        while (iterator.hasNext()) {
            pw.println("<tr>");
            Student element = iterator.next();
            pw.println("<td>" + element.toString() + "</td>");
            pw.println("</tr>");
        }
    }

    /**
     * Display one specified student
     */
    private void executeShowOneStudent (PrintWriter pw, Service sesMngObj, HttpServletRequest req) throws DaoException, ServiceException {
        String paramVal = req.getParameter("show_student_ID");
        if (paramVal.matches("[0-9]+")) {
            int key = Integer.parseInt(paramVal);
            Student student = new Student(key);
            student = sesMngObj.displayOneStudent(student);
            pw.println("<B>Here goes one student from selected key</B>");
            pw.println("<table border=1>");
            pw.println("<tr>");
            pw.println("<td>" + student + "</td>");
            pw.println("</tr>");
            pw.println("</table>");
        } else {
            pw.println("<font color=\"red\">You've entered invalid ID value, " +
                    "go back and enter a valid ID</font>");
        }
    }

    /**
     * Add new student
     */
    private void executeAddNewStudent (PrintWriter pw, Service sesMngObj, HttpServletRequest req) throws DaoException, ServiceException {
        String idValue = req.getParameter("new_student_ID");
        String nameValue = req.getParameter("new_student_name");
        String surnameValue = req.getParameter("new_student_surname");
        if (idValue.matches("[0-9]+") && nameValue.matches("[A-Za-z]+")
                && surnameValue.matches("[A-Za-z]+")) {
            int id = Integer.parseInt(idValue);
            Student student = new Student(id, nameValue, surnameValue);
            sesMngObj.addStudent(student);
            pw.println("<B>New student has been added</B>");
            pw.println("Go to home page and press button in Show all students " +
                    "section to check if student was added");
        } else {
            pw.println("<font color=\"red\">You've entered incorrect name or surname, " +
                    "go back and enter a valid name</font>");
        }
    }

    /**
     * Update existing student
     */
    private void executeUpdateStudent (PrintWriter pw, Service sesMngObj, HttpServletRequest req) throws DaoException, ServiceException {
        String idValue = req.getParameter("update_student_ID");
        String nameValue = req.getParameter("update_student_name");
        String surnameValue = req.getParameter("update_student_surname");
        if (idValue.matches("[0-9]+") && nameValue.matches("[A-Za-z]+")
                && surnameValue.matches("[A-Za-z]+")) {
            int id = Integer.parseInt(idValue);
            Student student = new Student(id, nameValue, surnameValue);
            sesMngObj.updateStudent(student);
            pw.println("<B>Update of existing student</B>");
            pw.println("Go to home page and press button in Show all students " +
                    "section to check if student was updated");
        } else {
            pw.println("<font color=\"red\">You've entered incorrect name or surname, " +
                    "go back and enter a valid name</font>");
        }
    }

    /**
     * Delete specified student
     */
    private void executeDeleteStudent (PrintWriter pw, Service sesMngObj, HttpServletRequest req) throws DaoException, ServiceException {
        String idValue = req.getParameter("delete_student_ID");
        if (idValue.matches("[0-9]+")) {
            int id = Integer.parseInt(idValue);
            Student student = new Student (id);
            sesMngObj.deleteStudent(student);
            pw.println("<B>Deletion of existing student</B>");
            pw.println("Go to home page and press button in Show all students " +
                    "section to check if student was deleted");
        } else {
            pw.println("<font color=\"red\">You've entered invalid ID value, " +
                    "go back and enter a valid ID</font>");
        }
    }

    /**
     * Display one specified subject
     */
    private void executeShowOneSubject (PrintWriter pw, Service sesMngObj, HttpServletRequest req) throws DaoException, ServiceException {
        String paramVal = req.getParameter("show_subject_ID");
        if (paramVal.matches("[0-9]+")) {
            int key = Integer.parseInt(paramVal);
            Subject subject = new Subject(key);
            subject = sesMngObj.displayOneSubject(subject);
            pw.println("<B>Here goes the subject from selected key</B>");
            pw.println("<table border=1>");
            pw.println("<tr>");
            pw.println("<td>" + subject + "</td>");
            pw.println("</tr>");
            pw.println("</table>");
        } else {
            pw.println("<font color=\"red\">You've entered invalid ID value, " +
                    "go back and enter a valid ID</font>");
        }
    }

    /**
     * Display all subjects
     */
    private void executeShowAllSubjects (PrintWriter pw, Service sesMngObj) throws DaoException, ServiceException {
        pw.println("<B>Here's a list of all subjects in the DB</B>");
        pw.println("<table border=1>");
        List<Subject> lst = sesMngObj.displayAllSubjects();
        Iterator<Subject> iterator = lst.iterator();
        while (iterator.hasNext()) {
            pw.println("<tr>");
            Subject element = iterator.next();
            pw.println("<td>" + element + "</td>");
            pw.println("</tr>");
        }
    }

    /**
     * Add new subject
     */
    private void executeAddNewSubject (PrintWriter pw, Service sesMngObj, HttpServletRequest req) throws DaoException, ServiceException {
        String idValue = req.getParameter("new_subject_ID");
        String descriptionValue = req.getParameter("new_subject_description");
        if (idValue.matches("[0-9]+") && descriptionValue.matches("[A-Za-z]+")) {
            int id = Integer.parseInt(idValue);
            Subject subject = new Subject(id, descriptionValue);
            sesMngObj.addSubject(subject);
            pw.println("<B>New subject has been added</B>");
            pw.println("Go to home page and press button in Show all subjects " +
                    "section to check if subject was added");
        } else {
            pw.println("<font color=\"red\">You've entered incorrect description, " +
                    "go back and enter a valid description</font>");
        }
    }

    /**
     * Update existing subject
     */
    private void executeUpdateExistingSubject (PrintWriter pw, Service sesMngObj, HttpServletRequest req) throws DaoException, ServiceException {
        String idValue = req.getParameter("update_subject_ID");
        String descriptionValue = req.getParameter("update_subject_description");
        if (idValue.matches("[0-9]+") && descriptionValue.matches("[A-Za-z]+")) {
            int id = Integer.parseInt(idValue);
            Subject subject = new Subject(id, descriptionValue);
            sesMngObj.updateSubject(subject);
            pw.println("<B>Update of existing subject</B>");
            pw.println("Go to home page and press button in Show all subjects " +
                    "section to check if subject was updated");
        } else {
            pw.println("<font color=\"red\">You've entered incorrect description, " +
                    "go back and enter a valid one</font>");
        }
    }

    /**
     * Delete specified subject
     */
    private void executeDeleteSpecifiedSubject (PrintWriter pw, Service sesMngObj, HttpServletRequest req) throws DaoException, ServiceException {
        String idValue = req.getParameter("delete_subject_ID");
        if (idValue.matches("[0-9]+")) {
            int id = Integer.parseInt(idValue);
            Subject subject = new Subject(id);
            sesMngObj.deleteSubject(subject);
            pw.println("<B>Deletion of existing subject</B>");
            pw.println("Go to home page and press button in Show all subjects " +
                    "section to check if subject was deleted");
        } else {
            pw.println("<font color=\"red\">You've entered invalid ID value, " +
                    "go back and enter a valid ID</font>");
        }
    }

    /**
     * Show one specified mark
     */
    private void executeShowOneSpecifiedMark (PrintWriter pw, Service sesMngObj, HttpServletRequest req) throws DaoException, ServiceException {
        String paramVal = req.getParameter("show_mark_ID");
        if (paramVal.matches("[0-9]+")) {
            int id = Integer.parseInt(paramVal);
            Mark mark = new Mark (id);
            mark = sesMngObj.displayOneMark(mark);
            pw.println("<B>Here goes the mark from selected key</B>");
            pw.println("<table border=1>");
            pw.println("<tr>");
            pw.println("<td>" + mark + "</td>");
            pw.println("</tr>");
            pw.println("</table>");
        } else {
            pw.println("<font color=\"red\">You've entered invalid ID value, " +
                    "go back and enter a valid ID</font>");
        }
    }

    /**
     * Show all marks
     */
    private void executeShowAllMarks (PrintWriter pw, Service sesMngObj) throws DaoException, ServiceException {
        pw.println("<B>Here's a list of all marks in the DB</B>");
        pw.println("<table border=1>");
        List<Mark> lst = sesMngObj.displayAllMarks();
        Iterator<Mark> iterator = lst.iterator();
        while (iterator.hasNext()) {
            pw.println("<tr>");
            Mark mark = iterator.next();
            pw.println("<td>" + mark + "</td>");
            pw.println("</tr>");
        }
    }

    /**
     * Display all marks of one student
     */
    private void executeShowMarksOneStudent (PrintWriter pw, Service sesMngObj, HttpServletRequest req) throws DaoException, ServiceException {
        String paramVal = req.getParameter("show_all_marks_1stud");
        if (paramVal.matches("[0-9]+")) {
            int studentId = Integer.parseInt(paramVal);
            Mark mark = new Mark(0, 0, studentId, 0);
            List<Mark> lst = sesMngObj.displayMarksOneStud(mark);
            pw.println("<B>Here's a list of all marks of one specified student</B>");
            pw.println("<table border=1>");
            Iterator<Mark> iterator = lst.iterator();
            while (iterator.hasNext()) {
                pw.println("<tr>");
                mark = iterator.next();
                pw.println("<td>" + mark + "</td>");
                pw.println("</tr>");
            }
        } else {
            pw.println("<font color=\"red\">You've entered invalid ID value, " +
                    "go back and enter a valid ID</font>");
        }
    }

    /**
     * Add new mark
     */
    private void executeAddNewMark (PrintWriter pw, Service sesMngObj, HttpServletRequest req) throws DaoException, ServiceException {
        String idValue = req.getParameter("new_mark_ID");
        String valueValue = req.getParameter("new_mark_value");
        String studentIdValue = req.getParameter("new_mark_studentId");
        String subjectIdValue = req.getParameter("new_mark_subjectId");
        if (idValue.matches("[0-9]+") && valueValue.matches("[0-9]+")
                && studentIdValue.matches("[0-9]+")
                && subjectIdValue.matches("[0-9]+")) {
            int id = Integer.parseInt(idValue);
            int value = Integer.parseInt(valueValue);
            int studentId = Integer.parseInt(studentIdValue);
            int subjectId = Integer.parseInt(subjectIdValue);
            Mark mark = new Mark (id, value, studentId, subjectId);
            sesMngObj.addMark (mark);
            pw.println("<B>Addition of new mark</B>");
            pw.println("Go to home page and press button in Show all marks " +
                    "section to check if mark was added");
        } else {
            pw.println("<font color=\"red\">You've entered incorrect mark id " +
                    "or mark value, or student id, or subject id, go back " +
                    "and enter a valid values</font>");
        }
    }

    /**
     * Update existing mark
     */
    private void executeUpdateExistingMark (PrintWriter pw, Service sesMngObj, HttpServletRequest req) throws DaoException, ServiceException {
        String idValue = req.getParameter("update_mark_ID");
        String valueValue = req.getParameter("update_mark_value");
        String studentIdValue = req.getParameter("update_mark_studentId");
        String subjectIdValue = req.getParameter("update_mark_subjectId");
        if (idValue.matches("[0-9]+") && valueValue.matches("[0-9]+")
                && studentIdValue.matches("[0-9]+")
                && subjectIdValue.matches("[0-9]+")) {
            int id = Integer.parseInt(idValue);
            int value = Integer.parseInt(valueValue);
            int studentId = Integer.parseInt(studentIdValue);
            int subjectId = Integer.parseInt(subjectIdValue);
            Mark mark = new Mark(id, value, studentId, subjectId);
            sesMngObj.updateMark (mark);
            pw.println("<B>Update of a mark</B>");
            pw.println("Go to home page and press button in Show all marks section " +
                    "to check if mark was updated");
        } else {
            pw.println("<font color=\"red\">You've entered incorrect mark id " +
                    "or mark value, or student id, or subject id, " +
                    "go back and enter a valid values</font>");
        }
    }

    /**
     * Delete specified mark
     */
    private void executeDeleteSpecifiedMark (PrintWriter pw, Service sesMngObj, HttpServletRequest req) throws DaoException, ServiceException {
        String idValue = req.getParameter("delete_mark_ID");
        if (idValue.matches("[0-9]+")) {
            int id = Integer.parseInt(idValue);
            Mark mark = new Mark (id);
            sesMngObj.deleteMark(mark);
            pw.println("<B>Deletion of existing mark</B>");
            pw.println("Go to home page and press button in Show all marks " +
                    "section to check if mark was deleted");
        } else {
            pw.println("<font color=\"red\">You've entered invalid ID value, " +
                    "go back and enter a valid ID</font>");
        }
    }

    /**
     * Close the session; all 'PreparedStatement' objects and the connection will be closed by MySesListener
     */
    private void executeCloseEverything (PrintWriter pw, HttpSession sessObj) throws DaoException {
        pw.println("<B>This session has been closed, resulting in closure of all prepared statements, and connection</B>");
        sessObj.invalidate();
    }
}
