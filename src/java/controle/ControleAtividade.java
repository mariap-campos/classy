/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.ConverteDate;

/**
 *
 * @author Maria Paula
 */
@WebServlet(name = "ControleAtividade", urlPatterns = {"/ControleAtividade"})
public class ControleAtividade extends HttpServlet {

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
            if ("Cadastrar".equals(acao)) {
                int classy_token = Integer.parseInt(request.getParameter("id"));
                
                modelo.Atividade ativ = new modelo.Atividade();
                ativ.setNome(request.getParameter("txtNome"));
                ativ.setDescricao(request.getParameter("txtDesc"));
                ativ.setProfessor(request.getParameter("txtProfessor"));
                ativ.setMateria(request.getParameter("txtMateria"));
                ativ.setClassy_token(classy_token);
                
                ConverteDate conversor = new ConverteDate();
                Date data1 = conversor.getDate(request.getParameter("txtData"));
               
                ativ.setData_entrega(data1);
               
                
                dao.AtividadeDAO adao = new dao.AtividadeDAO();
                adao.cadastrar(ativ);
                System.out.println("Atividade cadastrada com Sucesso!");
                
                request.setAttribute("title", "Atividade criada com sucesso!");
                request.setAttribute("mensagem", "Voltando a home do seu classy.");
                request.setAttribute("tipo", "Voltar");
                request.getRequestDispatcher("success.jsp").forward(request, response);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ControleAtividade.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ControleAtividade.class.getName()).log(Level.SEVERE, null, ex);
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
