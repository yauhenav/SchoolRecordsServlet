package les11.web;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import les11.logic.controller.*;
import les11.logic.dto.*;
import les11.logic.exception.*;

public class daoAppServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Service sesMngObj;
            HttpSession sessObj = req.getSession(false);
            if (sessObj == null) {
                sessObj = req.getSession(true);
                sesMngObj = new Service();
                sessObj.setAttribute("sessionObject", sesMngObj);
            } else {
                sesMngObj = (Service) sessObj.getAttribute("sessionObject");
            }

            resp.setContentType("text/html;charset=utf-8");
            PrintWriter pw = resp.getWriter();

            // Display all students
            if (req.getParameter("show_all_students") != null) {
                pw.println("<B>Here's a list of all students in the DB</B>");
                pw.println("<table border=1>");
                //Uncomment the line below to print session number to web-page for test purposes
                pw.println(sessObj);
                //Uncomment the line below to print session number to web server console for test purposes
                System.out.println(sessObj);
                List<Student> lst = sesMngObj.displayAllStudents();
                Iterator<Student> itrstud0 = lst.iterator();
                while (itrstud0.hasNext()) {
                    pw.println("<tr>");
                    Student element = itrstud0.next();
                    pw.println("<td>" + element.toString() + "</td>");
                    pw.println("</tr>");
                }
            }

            // Display one specified student
            else if (req.getParameter("show_student_ID") != null) {
                String paramVal = req.getParameter("show_student_ID");
                if (paramVal.matches("[0-9]+")) {
                    int key = Integer.parseInt(paramVal);
                    Student element = sesMngObj.displayOneStudent(key);
                    pw.println("<B>Here goes one student from selected key</B>");
                    pw.println("<table border=1>");
                    pw.println("<tr>");
                    pw.println("<td>" + element + "</td>");
                    pw.println("</tr>");
                    pw.println("</table>");
                } else {
                    pw.println("<font color=\"red\">You've entered invalid ID value, " +
                            "go back and enter a valid ID</font>");
                }
            }

            // Add new student
            else if (req.getParameter("new_student_ID") != null) {
                String idValue = req.getParameter("new_student_ID");
                String nameValue = req.getParameter("new_student_name");
                String surnameValue = req.getParameter("new_student_surname");
                if (idValue.matches("[0-9]+") && nameValue.matches("[A-Za-z]+")
                        && surnameValue.matches("[A-Za-z]+")) {
                    int id = Integer.parseInt(idValue);
                    sesMngObj.addStudent(id, nameValue, surnameValue);
                    pw.println("<B>New student has been added</B>");
                    pw.println("Go to home page and press button in Show all students " +
                            "section to check if student was added");
                    } else {
                    pw.println("<font color=\"red\">You've entered incorrect name or surname, " +
                            "go back and enter a valid name</font>");
                }
            }

            // Update existing student
            else if(req.getParameter("update_student_ID") != null) {
                String idValue = req.getParameter("update_student_ID");
                String nameValue = req.getParameter("update_student_name");
                String surnameValue = req.getParameter("update_student_surname");
                if (idValue.matches("[0-9]+") && nameValue.matches("[A-Za-z]+")
                        && surnameValue.matches("[A-Za-z]+")) {
                    int id = Integer.parseInt(idValue);
                    sesMngObj.updateStudent(id, nameValue, surnameValue);
                    pw.println("<B>Update of existing student</B>");
                    pw.println("Go to home page and press button in Show all students " +
                            "section to check if student was updated");
                } else {
                    pw.println("<font color=\"red\">You've entered incorrect name or surname, " +
                            "go back and enter a valid name</font>");
                }
            }

            // Delete specified student
            else if(req.getParameter("delete_student_ID") != null) {
                String idValue = req.getParameter("delete_student_ID");
                if (idValue.matches("[0-9]+")) {
                    int id = Integer.parseInt(idValue);
                    sesMngObj.deleteStudent(id);
                    pw.println("<B>Deletion of existing student</B>");
                    pw.println("Go to home page and press button in Show all students " +
                            "section to check if student was deleted");
                } else {
                    pw.println("<font color=\"red\">You've entered invalid ID value, " +
                            "go back and enter a valid ID</font>");
                }
            }

            // Display one specified subject
            else if (req.getParameter("show_subject_ID") != null) {
                String paramVal = req.getParameter("show_subject_ID");
                if (paramVal.matches("[0-9]+")) {
                    int key = Integer.parseInt(paramVal);
                    Subject element = sesMngObj.displayOneSubject(key);
                    pw.println("<B>Here goes the subject from selected key</B>");
                    pw.println("<table border=1>");
                    pw.println("<tr>");
                    pw.println("<td>" + element + "</td>");
                    pw.println("</tr>");
                    pw.println("</table>");
                } else {
                    pw.println("<font color=\"red\">You've entered invalid ID value, " +
                            "go back and enter a valid ID</font>");
                }
            }

            // Display all subjects
            else if (req.getParameter("show_all_subjects") != null) {
                pw.println("<B>Here's a list of all subjects in the DB</B>");
                pw.println("<table border=1>");
                List<Subject> lst = sesMngObj.displayAllSubjects();
                Iterator<Subject> itrsub0 = lst.iterator();
                while (itrsub0.hasNext()) {
                    pw.println("<tr>");
                    Subject element = itrsub0.next();
                    pw.println("<td>" + element.toString() + "</td>");
                    pw.println("</tr>");
                }
            }

            // Add new subject
            else if (req.getParameter("new_subject_ID") != null) {
                String idValue = req.getParameter("new_subject_ID");
                String descriptionValue = req.getParameter("new_subject_description");
                if (idValue.matches("[0-9]+") && descriptionValue.matches("[A-Za-z]+")) {
                    int id = Integer.parseInt(idValue);
                    sesMngObj.addSubject(id, descriptionValue);
                    pw.println("<B>New subject has been added</B>");
                    pw.println("Go to home page and press button in Show all subjects " +
                            "section to check if subject was added");
                } else {
                    pw.println("<font color=\"red\">You've entered incorrect description, " +
                            "go back and enter a valid description</font>");
                }
            }

            // Update existing subject
            else if(req.getParameter("update_subject_ID") != null) {
                String idValue = req.getParameter("update_subject_ID");
                String descriptionValue = req.getParameter("update_subject_description");
                if (idValue.matches("[0-9]+") && descriptionValue.matches("[A-Za-z]+")) {
                    int id = Integer.parseInt(idValue);
                    sesMngObj.updateSubject(id, descriptionValue);
                    pw.println("<B>Update of existing subject</B>");
                    pw.println("Go to home page and press button in Show all subjects " +
                            "section to check if subject was updated");
                } else {
                    pw.println("<font color=\"red\">You've entered incorrect description, " +
                            "go back and enter a valid one</font>");
                }
            }

            // Delete specified subject
            else if(req.getParameter("delete_subject_ID") != null) {
                String idValue = req.getParameter("delete_subject_ID");
                if (idValue.matches("[0-9]+")) {
                    int id = Integer.parseInt(idValue);
                    sesMngObj.deleteSubject(id);
                    pw.println("<B>Deletion of existing subject</B>");
                    pw.println("Go to home page and press button in Show all subjects " +
                            "section to check if subject was deleted");
                } else {
                    pw.println("<font color=\"red\">You've entered invalid ID value, " +
                            "go back and enter a valid ID</font>");
                }
            }

            // Show one specified mark
            else if (req.getParameter("show_mark_ID") != null) {
                String paramVal = req.getParameter("show_mark_ID");
                if (paramVal.matches("[0-9]+")) {
                    int key = Integer.parseInt(paramVal);
                    Mark element = sesMngObj.displayOneMark(key);
                    pw.println("<B>Here goes the mark from selected key</B>");
                    pw.println("<table border=1>");
                    pw.println("<tr>");
                    pw.println("<td>" + element + "</td>");
                    pw.println("</tr>");
                    pw.println("</table>");
                } else {
                    pw.println("<font color=\"red\">You've entered invalid ID value, " +
                            "go back and enter a valid ID</font>");
                }
            }

            // Show all marks
            else if (req.getParameter("show_all_marks") != null) {
                pw.println("<B>Here's a list of all marks in the DB</B>");
                pw.println("<table border=1>");
                List<Mark> lst = sesMngObj.displayAllMarks();
                Iterator<Mark> itrmar0 = lst.iterator();
                while (itrmar0.hasNext()) {
                    pw.println("<tr>");
                    Mark element = itrmar0.next();
                    pw.println("<td>" + element.toString() + "</td>");
                    pw.println("</tr>");
                }
            }

            // Display all marks of one student
            else if (req.getParameter("show_all_marks_1stud") != null) {
                String paramVal = req.getParameter("show_all_marks_1stud");
                if (paramVal.matches("[0-9]+")) {
                    int key = Integer.parseInt(paramVal);
                    List<Mark> lst1 = sesMngObj.displayMarksOneStud(key);
                    pw.println("<B>Here's a list of all marks of one specified student</B>");
                    pw.println("<table border=1>");
                    Iterator<Mark> itrmar1 = lst1.iterator();
                    while (itrmar1.hasNext()) {
                        pw.println("<tr>");
                        Mark element = itrmar1.next();
                        pw.println("<td>" + element.toString() + "</td>");
                        pw.println("</tr>");
                    }
                } else {
                    pw.println("<font color=\"red\">You've entered invalid ID value, " +
                            "go back and enter a valid ID</font>");
                }
            }

            // Add new mark
            else if (req.getParameter("new_mark_ID") != null) {
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
                    sesMngObj.addMark (id, value, studentId, subjectId);
                    pw.println("<B>Addition of new mark</B>");
                    pw.println("Go to home page and press button in Show all marks " +
                            "section to check if mark was added");
                } else {
                    pw.println("<font color=\"red\">You've entered incorrect mark id " +
                            "or mark value, or student id, or subject id, go back " +
                            "and enter a valid values</font>");
                }
            }

            // Update existing mark
            else if (req.getParameter("update_mark_ID") != null) {
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
                    sesMngObj.updateMark (id, value, studentId, subjectId);
                    pw.println("<B>Update of a mark</B>");
                    pw.println("Go to home page and press button in Show all marks section " +
                            "to check if mark was updated");
                } else {
                    pw.println("<font color=\"red\">You've entered incorrect mark id " +
                            "or mark value, or student id, or subject id, " +
                            "go back and enter a valid values</font>");
                }
            }

            // Delete specified mark
            else if(req.getParameter("delete_mark_ID") != null) {
                String idValue = req.getParameter("delete_mark_ID");
                if (idValue.matches("[0-9]+")) {
                    int id = Integer.parseInt(idValue);
                    sesMngObj.deleteMark(id);
                    pw.println("<B>Deletion of existing mark</B>");
                    pw.println("Go to home page and press button in Show all marks " +
                            "section to check if mark was deleted");
                } else {
                    pw.println("<font color=\"red\">You've entered invalid ID value, " +
                            "go back and enter a valid ID</font>");
                }
            }

            // Close all 'PreparedStatement' objects, and the connection, and the session
            else if (req.getParameter("close_everything") != null) {
                pw.println("<B>All prepared statements, and connection, " +
                        "and session have been closed</B>");
                sesMngObj.closeEverything();
                sessObj.removeAttribute("sessionObject");
                sessObj.invalidate();
            }
        } catch (DaoException exc) {
            exc.printStackTrace();
        }
    }
}
