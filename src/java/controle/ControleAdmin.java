/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AdminDAO;
import dao.ClassyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Admin;
import modelo.Classy;
import util.ConverteDate;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import util.randomToken;

/**
 *
 * @author Maria Paula
 */
@WebServlet(name = "ControleAdmin", urlPatterns = {"/ControleAdmin"})
public class ControleAdmin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String acao = request.getParameter("acao");
            if ("Cadastrar".equals(acao)) {
                modelo.Admin admin = new modelo.Admin();
                admin.setUsuario(request.getParameter("campoUsuario"));
                admin.setNome(request.getParameter("campoNome"));
                admin.setSenha(request.getParameter("campoSenha"));
                admin.setEmail(request.getParameter("campoEmail"));
                admin.setImagem(request.getParameter("campoImagem"));
                
                AdminDAO adao = new AdminDAO();
                try {
                    
                    if (adao.getUsuario(admin)) {
                        
                    request.setAttribute("title", "O usuário <span>" + admin.getUsuario() + "</span> já existe!");
                    request.setAttribute("mensagem", "Por favor, escolha outro usuário");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    
                    } else {
                        
                    adao.cadastrar(admin);
                    Admin adminId = new Admin();
                    adminId = adao.getId(admin);

                    modelo.Classy classy = new modelo.Classy();
                    classy.setNome(request.getParameter("campoClassy"));
                    classy.setNome_instituicao(request.getParameter("campoInsti"));
                    classy.setId_admin(adminId.getId());

                    ConverteDate conversor = new ConverteDate();
                    Date data1 = conversor.getDate(request.getParameter("campoInicio"));
                    Date data2 = conversor.getDate(request.getParameter("campoFinal"));

                    classy.setData_inicio(data1);
                    classy.setData_final(data2);

                    classy.setMaterias(request.getParameter("campoMaterias"));

                    dao.ClassyDAO cdao = new dao.ClassyDAO();
                    cdao.cadastrar(classy);
                    System.out.println("Admin cadastrado com Sucesso!");

                    request.setAttribute("title", "Seu cadastro foi realizado com sucesso!");
                    request.setAttribute("mensagem", "Voltando a página inicial para que você possa efetuar o login!");
                    request.setAttribute("tipo", "Cadastrar");
                    request.getRequestDispatcher("success.jsp").forward(request, response);
                    
                    }
                } catch (Exception e){
                    System.out.println(e);
                    request.setAttribute("title", "Problemas para realizar o cadastro no banco de dados");
                    request.setAttribute("mensagem", "Tente novamente mais tarde.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }  else if ("Entrar".equals(acao)) {
                modelo.Admin admin = new modelo.Admin();
                admin.setUsuario(request.getParameter("campoUsuario"));
                admin.setSenha(request.getParameter("campoSenha"));
                
                AdminDAO adao = new AdminDAO(); 
                Admin adminLogado = adao.efetuarLogin(admin);     
                
                if (admin.getUsuario().equals(adminLogado.getUsuario()) && admin.getSenha().equals(adminLogado.getSenha())) {
                    
                    Admin adminId = new Admin();
                    adminId = adao.getId(adminLogado);
                    
                    Classy classy = new Classy();
                    classy.setId_admin(adminId.getId());
                
                    ClassyDAO cdao = new ClassyDAO();
                    ArrayList<Classy> todosClassys = new ArrayList<Classy>();
                    todosClassys = cdao.consultarTodos(classy);
                    
                    request.setAttribute("classys", todosClassys);
                    HttpSession sessao = request.getSession();                     
                    sessao.setAttribute("admin",adminLogado);  
                    request.getRequestDispatcher("listarClassy.jsp").forward(request, response);
                } else {
                    request.setAttribute("title", "Usuário não existe ou está incorreto!");
                    request.setAttribute("mensagem", "Por favor, tente novamente.");
                    request.setAttribute("tipo", "Login");
                    request.getRequestDispatcher("error.jsp").forward(request, response);;
                }
            } else if ("Voltar ao menu".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));
               
                Classy classy = new Classy();
                classy.setId_admin(id);
                
                ClassyDAO cdao = new ClassyDAO();
                ArrayList<Classy> todosClassys = new ArrayList<Classy>();
                todosClassys = cdao.consultarTodos(classy);
                    
                request.setAttribute("classys", todosClassys);
                request.getRequestDispatcher("listarClassy.jsp").forward(request, response);
            }  else if ("Enviar email".equals(acao)) {
                Admin admin = new Admin();
                admin.setUsuario(request.getParameter("campoUsuario"));
                randomToken random = new randomToken(); 
                
                String novaSenha = random.randomString();
                admin.setSenha(novaSenha);
                
                AdminDAO adao = new AdminDAO();
                adao.updatePassword(admin);
                Admin adminId = adao.getId(admin);
                Admin adminBuscar = adao.consultarPorId(adminId);
                    
                request.setAttribute("senha", novaSenha);
                request.setAttribute("email", adminBuscar.getEmail());
                request.setAttribute("title", "Enviamos sua nova senha no seu email!");
                request.setAttribute("mensagem", "Por favor, verifique sua caixa de entrada.");
                request.setAttribute("tipo", "Email");
                request.getRequestDispatcher("success.jsp").forward(request, response);
            }  else if ("Editar Dados".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));
          
                Admin admin = new Admin();
                admin.setId(id);
                
                AdminDAO cdao = new AdminDAO();
                Admin adminBuscar = new Admin();
                adminBuscar = cdao.consultarPorId(admin); 
                   
                request.setAttribute("admin", adminBuscar);
                request.getRequestDispatcher("atualizarAdmin.jsp").forward(request, response);
            } else if ("Atualizar".equals(acao)) {
                Admin admin = new Admin();
                AdminDAO cdao = new AdminDAO();
                int id = Integer.parseInt(request.getParameter("id"));  
                
                admin.setId(id);
                admin.setNome(request.getParameter("campoNome"));
                admin.setUsuario(request.getParameter("campoUsuario"));
                admin.setImagem(request.getParameter("campoImagem"));
                admin.setEmail(request.getParameter("campoEmail"));
               
               Admin adminUser = cdao.consultarPorId(admin);
                
                try {
                    
                    if (cdao.getUsuario(admin) && !adminUser.getUsuario().equals(admin.getUsuario()) ) {
      
                        request.setAttribute("title", "O usuário <span>" + admin.getUsuario() + "</span> já existe!");
                        request.setAttribute("mensagem", "Por favor, escolha outro usuário");
                        request.setAttribute("tipo", "Listar");
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                    
                    } else {
                        cdao.atualizar(admin);
                        HttpSession sessao = request.getSession();                     
                        sessao.setAttribute("admin",admin); 
                        request.setAttribute("title", "Dados atualizados!");
                        request.setAttribute("mensagem", "Voltando a home");
                        request.setAttribute("tipo", "home");
                        request.getRequestDispatcher("success.jsp").forward(request, response);
                    }
                
                } catch (Exception e){
                    System.out.println(e);
                    request.setAttribute("title", "Problemas para atualizar o cadastro no banco de dados");
                    request.setAttribute("mensagem", "Tente novamente mais tarde.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
            } 
        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("ERROOOOOOOOOO", e);
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
