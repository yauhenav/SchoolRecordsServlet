package les11.web;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import les11.logic.*;

public class daoAppServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter pw = resp.getWriter();
        pw.println("<B>Here's a list of all students in the DB</B>");
        pw.println("<table border=1>");
        try {
            Management mngob = new Management();
			List<Student> lst = mngob.displayAllStudents();
			Iterator<Student> itrstud0 = lst.iterator();
			while (itrstud0.hasNext()) {
				pw.println("<tr>");
				Student element = itrstud0.next();
				 pw.println("<td>" + element.toString() + "</td>");
				 pw.println("</tr>");
			}
       } catch (Exception e) {
            throw new ServletException(e);
        }
        pw.println("</table>");
    }
}