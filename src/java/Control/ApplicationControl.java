/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.MemberModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ApplicationControl", urlPatterns = {"/ApplicationControl"})
public class ApplicationControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String Application = request.getParameter("Application");
            RequestDispatcher rd = null;

            HttpSession session = request.getSession();
            String user = request.getParameter("user");
            String password = request.getParameter("password");

            if ("Login".equals(Application)) {
                if (user == null) {
                    rd = request.getRequestDispatcher("login.jsp");
                } else {
                    LoginModel loginModel = new LoginModel(user, password);
                    boolean result = loginModel.getResult();
                    if (result) {
                        session.setAttribute("user", user);
                        rd = request.getRequestDispatcher("Welcome/welcome.jsp");
                    } else {
                        rd = request.getRequestDispatcher("member.html");                               //"Welcome/loginfailure.jsp");
                    }
                }

            } else if ("Logout".equals(Application)) {
                user = (String) session.getAttribute("user");
                session.invalidate();
                request.setAttribute("user", user);
                rd = request.getRequestDispatcher("Welcome/logout.jsp");

            } else if ("Shopping".equals(Application)) {
                rd = request.getRequestDispatcher("Shopping/shopping_form.jsp");
            } else if ("Member".equals(Application)) {

                String id=request.getParameter("id");
                String email=request.getParameter("email");
                String pwd=request.getParameter("pwd");
                String first_name=request.getParameter("first_name");
                String last_name=request.getParameter("last_name");
                String birthday=request.getParameter("birthday");
                String registered_day=request.getParameter("registered_day");
                String sex=request.getParameter("sex");
                String camp=request.getParameter("camp");
                String phone=request.getParameter("phone");
                String add_1=request.getParameter("add_1");
                String add_2=request.getParameter("add_2");
                String add_3=request.getParameter("add_3");
                String result ="";
                MemberModel member = new MemberModel(id, email, pwd, first_name, last_name, birthday, registered_day, sex, camp, phone, add_1, add_2, add_3);
                result = member.AddMember();
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------"+result);
            
            }
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
