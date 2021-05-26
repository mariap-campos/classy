/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AdminDAO;
import dao.ClassyDAO;
import dao.ForumDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Admin;
import modelo.Classy;
import modelo.Forum;
import util.ConverteDate;
import util.SeparateSubject;

/**
 *
 * @author Maria Paula
 */
@WebServlet(name = "ControleClassy", urlPatterns = {"/ControleClassy"})
public class ControleClassy extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String acao = request.getParameter("acao");
            if ("Entrar".equals(acao)) {
                Classy classyId = new Classy();
                int id = Integer.parseInt(request.getParameter("id"));
                classyId.setToken(id);
                
                ClassyDAO cdao = new ClassyDAO();
                Classy classy = new Classy();

                classy = cdao.consultarPorId(classyId); 
                Admin adminId = new Admin();
                adminId.setId(classy.getId_admin());
                
                SeparateSubject separator = new SeparateSubject();
                String[] materias = separator.splitSubjects(classy.getMaterias());
                
                HttpSession sessao = request.getSession();                     
                sessao.setAttribute("classy",classy); 
                sessao.setAttribute("materias",materias); 
                request.getRequestDispatcher("adminHome.jsp").forward(request, response);
            } else if ("Novo Classy +".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Classy classy = new Classy();
                classy.setId_admin(id);
                request.setAttribute("classy", classy);
                request.getRequestDispatcher("novoClassy.jsp").forward(request, response);
            } else if ("Criar Classy".equals(acao)) {
                int id_admin = Integer.parseInt(request.getParameter("id"));
                
                modelo.Classy classy = new modelo.Classy();
                classy.setNome(request.getParameter("campoClassy"));
                classy.setNome_instituicao(request.getParameter("campoInsti"));
                classy.setId_admin(id_admin);
                
                ConverteDate conversor = new ConverteDate();
                Date data1 = conversor.getDate(request.getParameter("campoInicio"));
                Date data2 = conversor.getDate(request.getParameter("campoFinal"));
               
                classy.setData_inicio(data1);
                classy.setData_final(data2);
                
                classy.setMaterias(request.getParameter("campoMaterias"));
                
                dao.ClassyDAO cdao = new dao.ClassyDAO();
                
                try {
                cdao.cadastrar(classy);
                request.setAttribute("title", "Classy criado com sucesso!");
                request.setAttribute("mensagem", "Voltando a página de listagem de seus classys.");
                request.setAttribute("tipo", "Listar");
                request.getRequestDispatcher("success.jsp").forward(request, response);
                
                } catch (Exception e){
                    System.out.println(e);
                    request.setAttribute("title", "Problemas para realizar o cadastro no banco de dados");
                    request.setAttribute("mensagem", "Tente novamente mais tarde.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            } else if ("abrirAtualizar".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));
          
                Classy classy = new Classy();
                classy.setToken(id);
                
                dao.ClassyDAO cdao = new dao.ClassyDAO();
                Classy classyBuscar = new Classy();
                classyBuscar = cdao.consultarPorId(classy);
                   
                request.setAttribute("classy", classyBuscar);
                request.getRequestDispatcher("atualizarClassy.jsp").forward(request, response);
            } else if ("Atualizar".equals(acao)) {
                modelo.Classy classy = new modelo.Classy();
                int id = Integer.parseInt(request.getParameter("id"));
                
                classy.setToken(id);
                classy.setNome(request.getParameter("campoClassy"));
                classy.setNome_instituicao(request.getParameter("campoInsti"));
                classy.setMaterias(request.getParameter("campoMaterias"));
                
                ConverteDate conversor = new ConverteDate();
                Date data1 = conversor.getDate(request.getParameter("campoInicio"));
                Date data2 = conversor.getDate(request.getParameter("campoFinal"));
                
                
               
                classy.setData_inicio(data1);
                classy.setData_final(data2);
                
                dao.ClassyDAO cdao = new dao.ClassyDAO();
                
                try {
                cdao.atualizar(classy);
                SeparateSubject separator = new SeparateSubject();
                String[] materias = separator.splitSubjects(classy.getMaterias());
                HttpSession sessao = request.getSession();                     
                sessao.setAttribute("materias",materias); 
                
                request.setAttribute("title", "Informações do Classy atualizado!");
                request.setAttribute("mensagem", "Voltando a página de listagem de seus classys.");
                request.setAttribute("tipo", "Listar");
                request.getRequestDispatcher("success.jsp").forward(request, response);
                } catch (Exception e){
                    System.out.println(e);
                    request.setAttribute("title", "Problemas para atualizar o cadastro no banco de dados");
                    request.setAttribute("mensagem", "Tente novamente mais tarde.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            } else if ("Apagar".equals(acao)) {
                modelo.Classy classy = new modelo.Classy();
                int id = Integer.parseInt(request.getParameter("id"));
                
                classy.setToken(id);
                dao.ClassyDAO cdao = new dao.ClassyDAO();
                Classy classyBuscar = cdao.consultarPorId(classy);
                
                Date data_final = classyBuscar.getData_final();
                
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
                LocalDateTime now = LocalDateTime.now(); 
                
                ConverteDate conversor = new ConverteDate();
                Date agora = conversor.getDate(dtf.format(now));
                
                if (agora.after(data_final)) {
                    
                    try {
                    cdao.apagar(classy);                
                    request.setAttribute("title", "Classy apagado com sucesso!");
                    request.setAttribute("mensagem", "Voltando a página de listagem de seus classys.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("success.jsp").forward(request, response);
                    } catch (Exception e){
                    System.out.println(e);
                    request.setAttribute("title", "Problemas para excluir o cadastro no banco de dados");
                    request.setAttribute("mensagem", "Tente novamente mais tarde.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
                } else {
                   request.setAttribute("title", "O semestre desse classy ainda não acabou!");
                    request.setAttribute("mensagem", "Se deseja apagá-lo. Favor ajustar data de fim de semestre.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            } else if ("Publicar no Forum".equals(acao)) {
                
                HttpSession sessao = request.getSession();
                Classy classy = (Classy)sessao.getAttribute("classy");

                Forum forum = new Forum();
                forum.setClassy_token(classy.getToken());
                
                ForumDAO fdao = new ForumDAO();
                ArrayList<Forum> todosPost = new ArrayList<Forum>();
                todosPost = fdao.consultarTodos(forum);


                request.setAttribute("posts", todosPost);
                request.getRequestDispatcher("adminForum.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ControleClassy.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ControleClassy.class.getName()).log(Level.SEVERE, null, ex);
        }
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
