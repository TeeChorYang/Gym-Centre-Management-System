/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.AgcClass;
import models.AgcClassFacade;
import models.AgcTrainer;
import models.AgcTrainerFacade;

/**
 *
 * @author TheOne
 */
@WebServlet(name = "StaffClassEdit", urlPatterns = {"/StaffClassEdit"})
public class StaffClassEdit extends HttpServlet {

    @EJB
    private AgcTrainerFacade agcTrainerFacade;

    @EJB
    private AgcClassFacade agcClassFacade;

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
        String name = request.getParameter("name");
        String trainer = request.getParameter("trainer");
        String priceStr = request.getParameter("price");
        Double price = null;
        String description = request.getParameter("description");


           try (PrintWriter out = response.getWriter()) {
               try {
                   if (priceStr != null) {
                       price = Double.parseDouble(priceStr);
                   }

                   AgcClass c = agcClassFacade.find(id);

                   c.setName(name);
                   c.setTrainerId(trainer);
                   c.setPrice(price);
                   c.setDescription(description);

                   agcClassFacade.edit(c);
                   HttpSession s = request.getSession(false);
                   s.setAttribute("successMessage", "Successfully updated class information.");
                   response.sendRedirect("StaffClassEdit?id=" + id);
               } catch (NumberFormatException e) {
                   HttpSession s = request.getSession(false);
                   s.setAttribute("errorMessage", "Invalid price: " + priceStr);
                   response.sendRedirect("StaffClassEdit?id=" + id);
               } catch (Exception e) {
                   HttpSession s = request.getSession(false);
                   s.setAttribute("errorMessage", "Invalid Input: " + e.getMessage());
                   response.sendRedirect("StaffClassEdit?id=" + id);
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
        AgcClass customer = agcClassFacade.find(id);
        request.setAttribute("agcClass", customer);

        // Fetch the trainers and set them as a request attribute
        List<AgcTrainer> trainers = agcTrainerFacade.findAll();
        request.setAttribute("trainers", trainers);

        request.getRequestDispatcher("staff-class-edit.jsp").forward(request, response);
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
