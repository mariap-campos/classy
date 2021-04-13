/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AlunoDAO;
import dao.ClassyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Aluno;
import modelo.Classy;

/**
 *
 * @author Maria Paula
 */
@WebServlet(name = "ControleAluno", urlPatterns = {"/ControleAluno"})
public class ControleAluno extends HttpServlet {

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
            if ("Cadastrar".equals(acao)) {
                int classy_token = Integer.parseInt(request.getParameter("id"));
                Aluno aluno = new Aluno();
                aluno.setNome(request.getParameter("txtNomeAluno"));
                aluno.setRgm(request.getParameter("txtRGM"));
                aluno.setClassy_token(classy_token);
                AlunoDAO adao = new AlunoDAO();
                
                if (adao.getRGM(aluno)) {              
                    request.setAttribute("title", "O aluno com id <span>" + aluno.getRgm() + "</span> já existe!");
                    request.setAttribute("mensagem", "Por favor, escolha outro id.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
        
                } else { 
                    try {
                        
                    adao.cadastrar(aluno);
                    request.setAttribute("title", "Aluno adicionado com sucesso!");
                    request.setAttribute("mensagem", "Clique na aba 'Alunos' para ver todos os alunos dessa turma");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("success.jsp").forward(request, response);
                    
                    } catch (Exception e){
                    System.out.println(e);
                    request.setAttribute("title", "Problemas para realizar o cadastro no banco de dados");
                    request.setAttribute("mensagem", "Tente novamente mais tarde.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
                }
            } else if ("Apagar".equals(acao)) {
                Aluno aluno = new Aluno();
                int id = Integer.parseInt(request.getParameter("id"));
                int classy_id = Integer.parseInt(request.getParameter("classy_id"));
                
                aluno.setId(id);
                AlunoDAO adao = new AlunoDAO();
                
                try {
                adao.apagar(aluno);     
                
                request.setAttribute("title", "Aluno apagado com sucesso!");
                request.setAttribute("mensagem", "Voltando a lista");
                request.setAttribute("classy", classy_id);
                request.setAttribute("tipo", "Aluno");
                request.getRequestDispatcher("success.jsp").forward(request, response);
                
                } catch (Exception e){
                    System.out.println(e);
                    request.setAttribute("title", "Problemas para realizar a exclusão no banco de dados");
                    request.setAttribute("mensagem", "Tente novamente mais tarde.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
            } else if ("abrirForm".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));
          
                Aluno aluno = new Aluno();
                aluno.setId(id);
                
                AlunoDAO cdao = new AlunoDAO();
                Aluno alunoBuscar = new Aluno();
                alunoBuscar = cdao.consultarPorId(aluno);
                   
                request.setAttribute("aluno", alunoBuscar);
                request.getRequestDispatcher("atualizarAluno.jsp").forward(request, response);
            } else if ("Atualizar".equals(acao)) {
                Aluno aluno = new Aluno();
                int id = Integer.parseInt(request.getParameter("id"));
                int id_classy = Integer.parseInt(request.getParameter("id_classy"));
                
                aluno.setId(id);
                aluno.setNome(request.getParameter("txtNomeAluno"));
                aluno.setRgm(request.getParameter("txtRgm"));
                aluno.setSituacao(request.getParameter("txtSituacao"));
               
                
                AlunoDAO cdao = new AlunoDAO();
                
                try {
                cdao.atualizar(aluno);
                
                request.setAttribute("title", "Informações do aluno atualizadas!");
                request.setAttribute("mensagem", "Voltando a página de listagem.");
                request.setAttribute("classy", id_classy);
                request.setAttribute("tipo", "Aluno");
                request.getRequestDispatcher("success.jsp").forward(request, response);
                
                } catch (Exception e){
                    System.out.println(e);
                    request.setAttribute("title", "Problemas para atualizar o cadastro no banco de dados");
                    request.setAttribute("mensagem", "Tente novamente mais tarde.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
            }  else if ("Entrar".equals(acao)) {
                Aluno aluno = new Aluno();
                aluno.setRgm(request.getParameter("campoRGM"));
                
                AlunoDAO adao = new AlunoDAO(); 
                Aluno alunoLogado = adao.efetuarLogin(aluno); 
                System.out.println(alunoLogado.getNome());
                
                if (aluno.getRgm().equals(alunoLogado.getRgm())) {
                    
                    Aluno alunoId = new Aluno();
                    alunoId = adao.getId(alunoLogado);
                    
                    ClassyDAO cdao = new ClassyDAO();
                    Classy classy = new Classy();
                    classy.setToken(alunoLogado.getClassy_token());
                    Classy classyBuscar = cdao.consultarPorId(classy);
                   
                   
                    request.setAttribute("aluno", alunoLogado);
                    request.setAttribute("classy", classyBuscar);
                    request.getRequestDispatcher("userHome.jsp").forward(request, response);
                } else {
                    request.setAttribute("title", "Usuário não existe ou está incorreto!");
                    request.setAttribute("mensagem", "Por favor, tente novamente.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);;
                }
            }  else if ("Editar Dados".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));
          
                Aluno aluno = new Aluno();
                aluno.setId(id);
                
                AlunoDAO cdao = new AlunoDAO();
                Aluno alunoBuscar = new Aluno();
                alunoBuscar = cdao.consultarPorId(aluno);
                   
                request.setAttribute("aluno", alunoBuscar);
                request.getRequestDispatcher("editarDados.jsp").forward(request, response);
            } else if ("Atualizar Dados".equals(acao)) {
                Aluno aluno = new Aluno();
                AlunoDAO cdao = new AlunoDAO();
                int id = Integer.parseInt(request.getParameter("id"));  
                
                aluno.setId(id);
                aluno.setNome(request.getParameter("campoNome"));
                aluno.setImagem(request.getParameter("campoImagem"));

                try {
                        cdao.atualizarDados(aluno);
                        request.setAttribute("mensagem", "Voltando a home");
                        request.setAttribute("title", "Dados atualizados!");
                        request.setAttribute("tipo", "Listar");
                        request.getRequestDispatcher("success.jsp").forward(request, response);
                
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
