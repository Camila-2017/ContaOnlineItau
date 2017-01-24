package com.contaonline.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.contaonline.bo.ClienteBO;
import com.contaonline.dto.Cliente;

/**
 * Servlet implementation class Busca
 */
@WebServlet("/buscar")
public class Busca extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Busca() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		
		try {
			ClienteBO boCliente = new ClienteBO();
			Cliente c = boCliente.buscarPorCPF(request.getParameter("cpf"));
			
			/* Se encontrar o CPF informado, redireciona para edição (1)
			 * Se não encontrar o CPF informado, redireciona para a inclusão (2) */
			Integer tipo = c == null ? new Integer(2) : new Integer(1);
			
			request.setAttribute("tipo", tipo);
			request.setAttribute("cliente", c);
			dispatcher = getServletContext().getRequestDispatcher("/edit.jsp");
		} catch (Exception e) {
			request.setAttribute("msg", "Erro buscar conta corrente");
			e.printStackTrace();
		}
		
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
