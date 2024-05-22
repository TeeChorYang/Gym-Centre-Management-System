/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.AgcCustomer;
import models.AgcCustomerFacade;
import models.AgcStaff;
import models.AgcStaffFacade;
import models.AgcTrainer;
import models.AgcTrainerFacade;

/**
 *
 * @author TheOne
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @EJB
    private AgcStaffFacade agcStaffFacade;

    @EJB
    private AgcTrainerFacade agcTrainerFacade;

    @EJB
    private AgcCustomerFacade agcCustomerFacade;
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String id = request.getParameter("username");
        String password = request.getParameter("password");

        try (PrintWriter out = response.getWriter()) {
            try{
                AgcCustomer customer = agcCustomerFacade.find(id);
                AgcTrainer trainer = agcTrainerFacade.find(id);
                AgcStaff staff = agcStaffFacade.find(id);

                if (customer != null) {
                    if (!password.equals(customer.getPassword())) {
                        throw new Exception("Incorrect password.");
                    }
                    if (!customer.getIs_approve()) {
                        throw new Exception("Your account is not approved yet.");
                    }
                    HttpSession s = request.getSession();
                    s.setAttribute("LoginUser", customer);
                    request.getRequestDispatcher("CustomerHome").include(request,response);

                } else if (trainer != null) {
                    if (!password.equals(trainer.getPassword())) {
                        throw new Exception("Incorrect password.");
                    }
                    if (!trainer.getIs_approve()) {
                        throw new Exception("Your account is not approved yet.");
                    }
                    HttpSession s = request.getSession();
                    s.setAttribute("LoginUser", trainer);
                    request.getRequestDispatcher("TrainerHome").include(request,response);

                } else if (staff != null) {
                    if (!password.equals(staff.getPassword())) {
                        throw new Exception("Incorrect password.");
                    }
                    HttpSession s = request.getSession();
                    s.setAttribute("LoginUser", staff);
                    request.getRequestDispatcher("StaffHome").include(request,response);

                } else if ("admin".equals(id) && "Password".equals(password)) {
                    HttpSession s = request.getSession();
                    AgcStaff admin = new AgcStaff();
                    admin.setId("admin");
                    admin.setPassword("Password");
                    admin.setName("Admin");
                    admin.setEmail("admin@agc.com");
                    admin.setPhone("0121231234");
                    admin.setIs_admin(true);
                    s.setAttribute("LoginUser", admin);
                    request.getRequestDispatcher("StaffHome").include(request,response);

                }else {
                    throw new Exception("User not found.");
                }
            }catch(Exception e){
                request.setAttribute("errorMessage",e.getMessage());
                request.getRequestDispatcher("login.jsp").include(request,response);
            }
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
