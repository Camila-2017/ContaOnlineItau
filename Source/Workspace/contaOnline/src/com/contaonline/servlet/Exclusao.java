package com.contaonline.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.contaonline.bo.ClienteBO;

/**
 * Servlet implementation class Exclusao
 */
@WebServlet("/excluir")
public class Exclusao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Exclusao() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		
		try {
			ClienteBO boCliente = new ClienteBO();
			
			/* Obtendo o CPF do cliente para passar para o BO */
			boCliente.excluir(request.getParameter("cpf"));

			request.setAttribute("msg", "Cliente excluído com sucesso");
		} catch (Exception e) {
			request.setAttribute("msg", "Erro ao excluir conta corrente");
			e.printStackTrace();
		}
		
		dispatcher.forward(request,response);
	}

}
