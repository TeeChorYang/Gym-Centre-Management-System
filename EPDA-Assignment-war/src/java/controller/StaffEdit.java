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
import models.AgcStaff;
import models.AgcStaffFacade;

/**
 *
 * @author TheOne
 */
@WebServlet(name = "StaffEdit", urlPatterns = {"/StaffEdit"})
public class StaffEdit extends HttpServlet {

    @EJB
    private AgcStaffFacade agcStaffFacade;

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

        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String isAdmin = request.getParameter("is_admin");


        try (PrintWriter out = response.getWriter()) {
            try{
                AgcStaff c = agcStaffFacade.find(id); 
                if (password != null && !password.trim().isEmpty()) {
                    if (!password.equals(confirmPassword)){
                        throw new Exception("Password and confirm password do not match");
                    }
                    c.setPassword(password);
                }
                c.setName(name);
                c.setEmail(email);
                c.setPhone(phone);
                c.setIs_admin(Boolean.parseBoolean(isAdmin));

                agcStaffFacade.edit(c);
                HttpSession s = request.getSession(false);
                s.setAttribute("successMessage", "Successfully updated staff information.");
                response.sendRedirect("StaffEdit?id=" + id);
            }catch(Exception e){
                HttpSession s = request.getSession(false);
                s.setAttribute("errorMessage", "Invalid Input: " + e.getMessage());
                AgcStaff c = (AgcStaff)s.getAttribute("LoginUser");
                s.setAttribute("user", c);
                response.sendRedirect("StaffEdit?id=" + id); 
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        AgcStaff staff = agcStaffFacade.find(id);
        request.setAttribute("staff", staff);
        request.getRequestDispatcher("staff-edit.jsp").forward(request, response);
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
