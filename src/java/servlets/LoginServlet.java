/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Book;
import entity.Reader;
import entity.Role;
import entity.User;
import entity.UserRoles;
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
import session.BookFacade;
import session.ReaderFacade;
import session.RoleFacade;
import session.UserFacade;
import session.UserRolesFacade;

/**
 *
 * @author pupil
 */
@WebServlet(name = "LoginServlet", loadOnStartup = 1, urlPatterns = {"/index.jsp","/index", "/showLogin", "/login", "/logout"})
public class LoginServlet extends HttpServlet {
    @EJB private UserFacade userFacade;
    @EJB private ReaderFacade readerFacade;
    @EJB private RoleFacade roleFacade;
    @EJB private UserRolesFacade userRolesFacade;
    @EJB private BookFacade bookFacade;
    
    @Override
    public void init() throws ServletException {
        super.init(); 
        
        
        List<User> users = userFacade.findAll();
        if(users.isEmpty()){//creating admin
            User user = new User();
            user.setLogin("admin");
            user.setPassword("12345");
            Reader reader = new Reader();
            reader.setFirstname("name");
            reader.setLastname("lastname");
            reader.setPhone("1234567");
            readerFacade.create(reader);
            user.setReader(reader);
            userFacade.create(user);
            Role role = new Role();
            UserRoles userRoles = new UserRoles();
            role.setRoleName("ADMINISTRATOR");//ROLES SHOULD BE WRITTEN IN CAPS
            userRoles.setRole(role);
            userRoles = new UserRoles();
            userRoles.setUser(user);
            roleFacade.create(role);
            userRolesFacade.create(userRoles);
            role = new Role();
            role.setRoleName("MANAGER");
            userRoles.setRole(role);
            userRoles = new UserRoles();
            userRoles.setUser(user);
            roleFacade.create(role);
            userRolesFacade.create(userRoles);
            role = new Role();
            role.setRoleName("READER");
            userRoles.setRole(role);
            userRoles = new UserRoles();
            userRoles.setUser(user);
            roleFacade.create(role);
            userRolesFacade.create(userRoles);
        }
    }

    
    
    
    
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
        String path = request.getServletPath();
        switch (path) {
           case "/index.jsp": 
           case "/index":
                List<Book> books = bookFacade.findAll();
                request.setAttribute("books", books);
                request.getRequestDispatcher("/listBooks.jsp").forward(request, response);
                break;
            case "/showLogin": //pattern, на него срабатывает сервлет
                request.getRequestDispatcher("/showLogin.jsp").forward(request, response);
                break;
            case "/login": //обрабатываем запрос
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                //аутентификация
                User authUser = userFacade.find(login);
                if(authUser == null){
                    request.setAttribute("info", "Нет такого пользователя или неправильный пароль");
                    request.getRequestDispatcher("/showLogin").forward(request, response);
                }
                //авторизация
                if(!password.equals(authUser.getPassword())){
                    request.setAttribute("info", "Нет такого пользователя или неправильный пароль");
                    request.getRequestDispatcher("/showLogin").forward(request, response);
                }
                HttpSession session = request.getSession();
                session.setAttribute("authUser", authUser);
                String info = authUser.getReader().getFirstname() +", здравствуйте!";
                request.setAttribute("info", info);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/logout":
                
                request.getRequestDispatcher("/index.jsp").forward(request, response);
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
