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
		try {
			Management mngob = new Management();
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter pw = resp.getWriter();
			
			/* String paramName = null;
			String paramVal  = null;
			
			Enumeration<String> enmr=req.getParameterNames();
			while(enmr.hasMoreElements()) {
				paramName=(String)enmr.nextElement(); // parameter name
				paramVal=req.getParameter(paramName); // parameter value
			} */
			
			
			if (req.getParameter("show_student_ID") != null) {
				pw.println("<B>Here goes one student from selected key</B>");
				pw.println("<table border=1>");
				String paramVal = req.getParameter("show_student_ID");
				int key = Integer.parseInt(paramVal);
				Student element = mngob.displayOneStudent(key);
				pw.println("<tr>");
				pw.println("<td>" + element + "</td>");
				//pw.println(element.toString());
				pw.println("</tr>");
				pw.println("</table>");
			}
		} catch (Exception e) {
				throw new ServletException(e);
		}
				
    }
}
				
				
				
			        
		/* PrintWriter pw = resp.getWriter();
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
	//}
} */