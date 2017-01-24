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
 * Servlet implementation class Edicao
 */
@WebServlet("/editar")
public class Edicao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edicao() {
        super();

    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Cliente c = new Cliente();
			
			/* Obtendo todas as informações da tela para passar para o BO */
			c.setCpf(request.getParameter("cpf"));
			c.setEndereco(request.getParameter("endereco"));
			c.setNome(request.getParameter("nome"));
			
			ClienteBO bo = new ClienteBO();
			bo.editar(c);
			request.setAttribute("msg", "Conta alterada com sucesso");
		} catch (Exception e) {
			request.setAttribute("msg", "Erro ao alterar conta corrente");
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(request,response);	
	}

}
