package les11.web;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

import les11.logic.controller.*;
import les11.logic.dao.*;
import les11.logic.dto.*;
import les11.logic.exception.*;
import les11.logic.mysql.*;

public class daoAppServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Service mngob = new Service();
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter pw = resp.getWriter();

            // Display all students
            if (req.getParameter("show_all_students") != null) {
                pw.println("<B>Here's a list of all students in the DB</B>");
                pw.println("<table border=1>");
                List<Student> lst = mngob.displayAllStudents();
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
                pw.println("<B>Here goes one student from selected key</B>");
                pw.println("<table border=1>");
                String paramVal = req.getParameter("show_student_ID");
                int key = Integer.parseInt(paramVal);
                Student element = mngob.displayOneStudent(key);
                pw.println("<tr>");
                pw.println("<td>" + element + "</td>");
                pw.println("</tr>");
                pw.println("</table>");
            }

            // Add new student
            else if (req.getParameter("new_student_ID") != null) {
                pw.println("<B>Addition of new student</B>");
                pw.println("Go to home page and press button in Show all students section to check if student was added");
                String idValue = req.getParameter("new_student_ID");
                int id = Integer.parseInt(idValue);
                String nameValue = req.getParameter("new_student_name");
                String surnameValue = req.getParameter("new_student_surname");
                mngob.addStudent(id, nameValue, surnameValue);
            }

            // Update existing student
            else if(req.getParameter("update_student_ID") != null) {
                pw.println("<B>Update of existing student</B>");
                pw.println("Go to home page and press button in Show all students section to check if student was updated");
                String idValue = req.getParameter("update_student_ID");
                int id = Integer.parseInt(idValue);
                String nameValue = req.getParameter("update_student_name");
                String surnameValue = req.getParameter("update_student_surname");
                mngob.updateStudent(id, nameValue, surnameValue);
            }

            // Delete specified student
            else if(req.getParameter("delete_student_ID") != null) {
                pw.println("<B>Deletion of existing student</B>");
                pw.println("Go to home page and press button in Show all students section to check if student was deleted");
                String idValue = req.getParameter("delete_student_ID");
                int id = Integer.parseInt(idValue);
                mngob.deleteStudent(id);
            }

            // Display one specified subject
            else if (req.getParameter("show_subject_ID") != null) {
                pw.println("<B>Here goes the subject from selected key</B>");
                pw.println("<table border=1>");
                String paramVal = req.getParameter("show_subject_ID");
                int key = Integer.parseInt(paramVal);
                Subject element = mngob.displayOneSubject(key);
                pw.println("<tr>");
                pw.println("<td>" + element + "</td>");
                //pw.println(element.toString());
                pw.println("</tr>");
                pw.println("</table>");
            }

            // Display all subjects
            else if (req.getParameter("show_all_subjects") != null) {
                pw.println("<B>Here's a list of all subjects in the DB</B>");
                pw.println("<table border=1>");
                List<Subject> lst = mngob.displayAllSubjects();
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
                pw.println("<B>Addition of new subject</B>");
                pw.println("Go to home page and press button in Show all subjects section to check if subject was added");
                String idValue = req.getParameter("new_subject_ID");
                int id = Integer.parseInt(idValue);
                String descriptionValue = req.getParameter("new_subject_description");
                mngob.addSubject(id, descriptionValue);
            }

            // Update existing subject
            else if(req.getParameter("update_subject_ID") != null) {
                pw.println("<B>Update of existing subject</B>");
                pw.println("Go to home page and press button in Show all subjects section to check if subject was updated");
                String idValue = req.getParameter("update_subject_ID");
                int id = Integer.parseInt(idValue);
                String descriptionValue = req.getParameter("update_subject_description");
                mngob.updateSubject(id, descriptionValue);
            }

            // Delete specified subject
            else if(req.getParameter("delete_subject_ID") != null) {
                pw.println("<B>Deletion of existing subject</B>");
                pw.println("Go to home page and press button in Show all subjects section to check if subject was deleted");
                String idValue = req.getParameter("delete_subject_ID");
                int id = Integer.parseInt(idValue);
                mngob.deleteSubject(id);
            }

            // Show one specified mark
            else if (req.getParameter("show_mark_ID") != null) {
                pw.println("<B>Here goes the mark from selected key</B>");
                pw.println("<table border=1>");
                String paramVal = req.getParameter("show_mark_ID");
                int key = Integer.parseInt(paramVal);
                Mark element = mngob.displayOneMark(key);
                pw.println("<tr>");
                pw.println("<td>" + element + "</td>");
                pw.println("</tr>");
                pw.println("</table>");
            }

            // Show all marks
            else if (req.getParameter("show_all_marks") != null) {
                pw.println("<B>Here's a list of all marks in the DB</B>");
                pw.println("<table border=1>");
                List<Mark> lst = mngob.displayAllMarks();
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
                pw.println("<B>Here's a list of all marks of one specified student</B>");
                pw.println("<table border=1>");
                String paramVal = req.getParameter("show_all_marks_1stud");
                int key = Integer.parseInt(paramVal);
                List<Mark> lst1 = mngob.displayMarksOneStud(key);
                Iterator<Mark> itrmar1 = lst1.iterator();
                while (itrmar1.hasNext()) {
                    pw.println("<tr>");
                    Mark element = itrmar1.next();
                    pw.println("<td>" + element.toString() + "</td>");
                    pw.println("</tr>");
                }
            }

            // Add new mark
            else if (req.getParameter("new_mark_ID") != null) {
                pw.println("<B>Addition of new mark</B>");
                pw.println("Go to home page and press button in Show all marks section to check if mark was added");
                String idValue = req.getParameter("new_mark_ID");
                int id = Integer.parseInt(idValue);
                String valueValue = req.getParameter("new_mark_value");
                int value = Integer.parseInt(valueValue);
                String studentIdValue = req.getParameter("new_mark_studentId");
                int studentId = Integer.parseInt(studentIdValue);
                String subjectIdValue = req.getParameter("new_mark_subjectId");
                int subjectId = Integer.parseInt(subjectIdValue);
                mngob.addMark (id, value, studentId, subjectId);
            }

            // Update existing mark
            else if (req.getParameter("update_mark_ID") != null) {
                pw.println("<B>Update of a mark</B>");
                pw.println("Go to home page and press button in Show all marks section to check if mark was updated");
                String idValue = req.getParameter("update_mark_ID");
                int id = Integer.parseInt(idValue);
                String valueValue = req.getParameter("update_mark_value");
                int value = Integer.parseInt(valueValue);
                String studentIdValue = req.getParameter("update_mark_studentId");
                int studentId = Integer.parseInt(studentIdValue);
                String subjectIdValue = req.getParameter("update_mark_subjectId");
                int subjectId = Integer.parseInt(subjectIdValue);
                mngob.updateMark (id, value, studentId, subjectId);
            }

            // Delete specified mark
            else if(req.getParameter("delete_mark_ID") != null) {
                pw.println("<B>Deletion of existing mark</B>");
                pw.println("Go to home page and press button in Show all marks section to check if mark was deleted");
                String idValue = req.getParameter("delete_mark_ID");
                int id = Integer.parseInt(idValue);
                mngob.deleteMark(id);
            }

            // Close all 'PreparedStatement' objects and the connection
            else if (req.getParameter("close_everything") != null) {
                pw.println("<B>All prepared statements and connections are closed</B>");
                mngob.closeEverything();
            }
        } catch (DaoException exc) {
            exc.printStackTrace();
        }
    }
}
