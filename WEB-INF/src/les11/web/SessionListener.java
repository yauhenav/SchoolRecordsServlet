package les11.web;

import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSession;

import les11.logic.controller.*;

/**
 * Created by yauhenav on 9.5.17.
 */
public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent e) {
        HttpSession sessObj = e.getSession();
        Service sesMngObj = new Service();
        sessObj.setAttribute("sessionObject", sesMngObj);
    }
    public void sessionDestroyed(HttpSessionEvent e) {
        HttpSession sessObj = e.getSession();
        Service sesMngObj = (Service) sessObj.getAttribute("sessionObject");
        sesMngObj.close();
        sessObj.removeAttribute("sessionObject");
    }
}
