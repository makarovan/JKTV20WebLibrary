/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Author;
import entity.Book;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.UserFacade;
import session.UserRolesFacade;

/**
 *
 * @author pupil
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/editUser", "/updateUser"})
public class AdminServlet extends HttpServlet {
    @EJB private UserRolesFacade userRolesFacade;
    @EJB private UserFacade userFacade;
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
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "У вас нет прав. Войдите с правами менеджера");
            request.getRequestDispatcher("/showLogin.jsp").forward(request, response);
            return;
        }
        User authUser = (User) session.getAttribute("authUser");
        if(authUser == null){
            request.setAttribute("info", "У вас нет прав. Войдите с правами менеджера");
            request.getRequestDispatcher("/showLogin.jsp").forward(request, response);
            return;
        }
        request.setAttribute("auth", "true");
        if(!userRolesFacade.isRole("MANAGER", authUser)){
            request.setAttribute("info", "У вас нет прав. Войдите с правами менеджера");
            request.getRequestDispatcher("/showLogin.jsp").forward(request, response);
            return;
        }
        String path = request.getServletPath();
        switch (path){
            case "/editUser":
                List<User> users = userFacade.findAll();
                request.setAttribute("users", users);
                request.getRequestDispatcher("/editUser.jsp").forward(request, response);
                break;
            case "/updateUser":
                //получаем данные из формы
                String caption = request.getParameter("caption");
                String year = request.getParameter("publiserYear");
                String quantity = request.getParameter("quantity");
                String[] bookAuthors = request.getParameterValues("authors");
                //добавляем данные
                Book book = new Book();
                List<Author> listAuthors = new ArrayList<>();
                for (int i = 0; i < bookAuthors.length; i++) {//ERROR
                    String authorId = bookAuthors[i];
                    listAuthors.add(authorFacade.find(Long.parseLong(authorId)));
                    
                }
                book.setAuthor(listAuthors);
                book.setCaption(caption);
                book.setQuantity(Integer.parseInt(quantity));
                book.setCount(Integer.parseInt(quantity));
                book.setPublishedYear(Integer.parseInt(year));
                bookFacade.create(book);
                request.getRequestDispatcher("/addBook.jsp").forward(request, response);
                break;
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
