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
import modelo.Classy;
import modelo.Forum;

/**
 *
 * @author Maria Paula
 */
@WebServlet(name = "ControleForum", urlPatterns = {"/ControleForum"})
public class ControleForum extends HttpServlet {

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
            if ("Postar".equals(acao)) {
                int id_user = Integer.parseInt(request.getParameter("id"));
                int classy_token = Integer.parseInt(request.getParameter("id_classy"));
                
                Classy classyId = new Classy();
                classyId.setToken(classy_token);
                
                AdminDAO adao = new AdminDAO();
                Admin admin = new Admin();
                admin.setId(id_user);
                Admin adminBuscar = adao.consultarPorId(admin);
                
                Forum forum = new Forum();
                forum.setPostagem(request.getParameter("txtPost"));
                forum.setAssunto(request.getParameter("txtAssunto"));
                forum.setUser_id(id_user);
                forum.setUser_nome(adminBuscar.getNome());
                forum.setClassy_token(classy_token);
                
                ForumDAO fdao = new ForumDAO();
                
                System.out.println("seila" + forum.getPostagem());
                
                try {
                fdao.postar(forum);
                System.out.println("postou");
                ClassyDAO cdao = new ClassyDAO();
                Classy classy = new Classy();
                classy = cdao.consultarPorId(classyId); 
    
                ArrayList<Forum> todosPost = new ArrayList<Forum>();
                todosPost = fdao.consultarTodos(forum);

                request.setAttribute("posts", todosPost);
                request.setAttribute("classy", classy);
                request.getRequestDispatcher("adminForum.jsp").forward(request, response);
                
                } catch (Exception e){
                    System.out.println(e);
                    request.setAttribute("title", "Problemas para postar sua mensagem");
                    request.setAttribute("mensagem", "Tente novamente mais tarde.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            } else if ("Publicar".equals(acao)) {
                int id_user = Integer.parseInt(request.getParameter("id"));
                int classy_token = Integer.parseInt(request.getParameter("id_classy"));
                
                Classy classyId = new Classy();
                classyId.setToken(classy_token);
                
                AlunoDAO adao = new AlunoDAO();
                Aluno aluno = new Aluno();
                aluno.setId(id_user);
                Aluno alunoBuscar = adao.consultarPorId(aluno);
                
                Forum forum = new Forum();
                forum.setPostagem(request.getParameter("txtPost"));
                forum.setAssunto(request.getParameter("txtAssunto"));
                forum.setUser_id(id_user);
                forum.setUser_nome(alunoBuscar.getNome());
                forum.setClassy_token(classy_token);
                
                ForumDAO fdao = new ForumDAO();
                
                System.out.println("seila" + forum.getPostagem());
                
                try {
                fdao.postar(forum);
                System.out.println("postou");
                ClassyDAO cdao = new ClassyDAO();
                Classy classy = new Classy();
                classy = cdao.consultarPorId(classyId); 
    
                ArrayList<Forum> todosPost = new ArrayList<Forum>();
                todosPost = fdao.consultarTodos(forum);

                request.setAttribute("posts", todosPost);
                request.setAttribute("classy", classy);
                request.setAttribute("aluno", alunoBuscar);
                request.getRequestDispatcher("userForum.jsp").forward(request, response);
                
                } catch (Exception e){
                    System.out.println(e);
                    request.setAttribute("title", "Problemas para postar sua mensagem");
                    request.setAttribute("mensagem", "Tente novamente mais tarde.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }
            else if ("Excluir".equals(acao)) {
                Forum forum = new Forum();
                int id = Integer.parseInt(request.getParameter("id"));
                int id_classy = Integer.parseInt(request.getParameter("id_classy"));
                
                forum.setId(id);
                ForumDAO adao = new ForumDAO();
                
                try {
                adao.apagar(forum);     
                request.setAttribute("title", "Mensagem apagada com sucesso!");
                request.setAttribute("mensagem", "Voltando ao fórum");
                request.setAttribute("classy", id_classy);
                request.setAttribute("tipo", "Forum");
                request.getRequestDispatcher("success.jsp").forward(request, response);
                } catch (Exception e){
                    System.out.println(e);
                    request.setAttribute("title", "Problemas para apagar esta mensagem");
                    request.setAttribute("mensagem", "Tente novamente mais tarde.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            } else if ("Apagar".equals(acao)) {
                Forum forum = new Forum();
                int id = Integer.parseInt(request.getParameter("id"));
                int id_classy = Integer.parseInt(request.getParameter("id_classy"));
                int id_aluno = Integer.parseInt(request.getParameter("id_aluno"));
                
                forum.setId(id);
                ForumDAO adao = new ForumDAO();     
                
                try {
                adao.apagar(forum);     
                request.setAttribute("title", "Mensagem apagada com sucesso!");
                request.setAttribute("mensagem", "Voltando ao fórum");
                request.setAttribute("classy", id_classy);
                request.setAttribute("aluno", id_aluno);
                request.setAttribute("tipo", "ForumUser");
                request.getRequestDispatcher("success.jsp").forward(request, response);
                } catch (Exception e){
                    System.out.println(e);
                    request.setAttribute("title", "Problemas para apagar esta mensagem");
                    request.setAttribute("mensagem", "Tente novamente mais tarde.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
//            request.setAttribute("erro", e);
//            request.getRequestDispatcher("erro.jsp").forward(request, response);
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
