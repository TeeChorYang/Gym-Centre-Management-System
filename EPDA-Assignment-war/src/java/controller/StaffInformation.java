/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
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
@WebServlet(name = "StaffInformation", urlPatterns = {"/StaffInformation"})
public class StaffInformation extends HttpServlet {

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

        HttpSession s = request.getSession(false);

        if (s == null || s.getAttribute("LoginUser") == null) {
            response.sendRedirect("login.jsp"); 
            return;
        }
        AgcStaff currentUser = (AgcStaff) s.getAttribute("LoginUser");

        if (!currentUser.getIs_admin().equals(true) && !currentUser.getId().equals("admin")) {
            response.sendRedirect("staff-error.jsp");
            return;
        }
    
        String searchQuery = request.getParameter("search");
        List<AgcStaff> staffList;
        if (searchQuery != null && !searchQuery.isEmpty()) {
            staffList = agcStaffFacade.findAllStaff().stream()
                .filter(staff -> staff.getName().toLowerCase().contains(searchQuery.toLowerCase())
                    || staff.getId().toLowerCase().contains(searchQuery.toLowerCase())
                    || staff.getEmail().toLowerCase().contains(searchQuery.toLowerCase())
                    || staff.getPhone().toLowerCase().contains(searchQuery.toLowerCase()))
                .collect(Collectors.toList());
        } else {
            staffList = agcStaffFacade.findAllStaff();
        }

        request.setAttribute("staffList", staffList);

        request.getRequestDispatcher("staff-information.jsp").include(request, response);
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
