/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AdminDAO;
import dao.AlunoDAO;
import dao.ClassyDAO;
import dao.ForumDAO;
import dao.ProvaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Admin;
import modelo.Aluno;
import modelo.Atividade;
import modelo.Classy;
import modelo.Forum;
import modelo.Prova;

/**
 *
 * @author Maria Paula
 */
@WebServlet(name = "ControleTabs", urlPatterns = {"/ControleTabs"})
public class ControleTabs extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String acao = request.getParameter("acao");
            if ("Home".equals(acao)) {
                Classy classyId = new Classy();
                int id = Integer.parseInt(request.getParameter("id"));
                classyId.setToken(id);
                
                ClassyDAO cdao = new ClassyDAO();
                Classy classy = new Classy();
                classy = cdao.consultarPorId(classyId); 
                
                AdminDAO adao = new AdminDAO();
                Admin adminId = new Admin();
                adminId.setId(classy.getId_admin());
                
                Admin admin = adao.consultarPorId(adminId);
                
                request.setAttribute("classy", classy);
                request.setAttribute("admin", admin);
                request.getRequestDispatcher("adminHome.jsp").forward(request, response);
            } else if ("Atividades".equals(acao)) {
                    int classy_token = Integer.parseInt(request.getParameter("id"));
                    Classy classy = new Classy();
                    classy.setToken(classy_token);
                    
                    Atividade ativ = new Atividade();
                    ativ.setClassy_token(classy_token);
                
                    dao.AtividadeDAO adao = new dao.AtividadeDAO();
                    ArrayList<Atividade> todosAtividade = new ArrayList<Atividade>();
                    todosAtividade = adao.consultarTodos(ativ);
                    
                    ClassyDAO cdao = new ClassyDAO();
                    Classy classyBuscar = new Classy();
                    classyBuscar = cdao.consultarPorId(classy);
                    
                    request.setAttribute("classy", classyBuscar);
                    request.setAttribute("atividades", todosAtividade);
                    request.setAttribute("isAdmin", true);
                    request.getRequestDispatcher("adminAtividade.jsp").forward(request, response);
            } else if ("Forum".equals(acao)) {
                Classy classyId = new Classy();
                int id = Integer.parseInt(request.getParameter("id"));
                classyId.setToken(id);
                
                ClassyDAO cdao = new ClassyDAO();
                Classy classy = new Classy();
                classy = cdao.consultarPorId(classyId); 
                
                Forum forum = new Forum();
                forum.setClassy_token(id);
                
                ForumDAO fdao = new ForumDAO();
                ArrayList<Forum> todosPost = new ArrayList<Forum>();
                todosPost = fdao.consultarTodos(forum);


                request.setAttribute("posts", todosPost);
                request.setAttribute("classy", classy);
                request.getRequestDispatcher("adminForum.jsp").forward(request, response);
            } else if ("Alunos".equals(acao)) {
                Classy classyId = new Classy();
                int id = Integer.parseInt(request.getParameter("id"));
                classyId.setToken(id);
                
                ClassyDAO cdao = new ClassyDAO();
                Classy classy = new Classy();
                classy = cdao.consultarPorId(classyId); 
                
                Aluno forum = new Aluno();
                forum.setClassy_token(id);
                
                AlunoDAO fdao = new AlunoDAO();
                ArrayList<Aluno> todosAlunos = new ArrayList<Aluno>();
                todosAlunos = fdao.consultarTodos(forum);


                request.setAttribute("alunos", todosAlunos);
                request.setAttribute("classy", classy);
                request.getRequestDispatcher("adminAluno.jsp").forward(request, response);
            } else if ("Provas".equals(acao)) {
                Classy classyId = new Classy();
                int id = Integer.parseInt(request.getParameter("id"));
                classyId.setToken(id);
                
                ClassyDAO cdao = new ClassyDAO();
                Classy classy = new Classy();
                classy = cdao.consultarPorId(classyId); 
                
                Prova prova = new Prova();
                prova.setToken_classy(id);
                
                ProvaDAO fdao = new ProvaDAO();
                ArrayList<Prova> todosProva = new ArrayList<Prova>();
                todosProva = fdao.consultarTodos(prova);


                request.setAttribute("provas", todosProva);
                request.setAttribute("classy", classy);
                request.getRequestDispatcher("adminProva.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("erro", e);
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
