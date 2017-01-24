package com.contaonline.bo;

import java.sql.Connection;
import java.util.Random;

import com.contaonline.dao.ClienteDAO;
import com.contaonline.dao.Connector;
import com.contaonline.dto.Cliente;
import com.contaonline.dto.ContaCorrente;

/* Classe Business Object - Contém as Regras de Negócio  */
public class ClienteBO {
	
	/* Método para excluir o cliente por CPF */
	public void excluir(String cpf) throws Exception {
		
		Connection con = Connector.getConnection();
		ClienteDAO dao = new ClienteDAO(con);
		dao.excluir(cpf);
		con.close();		
	}

	/* Método para buscar o cliente por CPF */
	public Cliente buscarPorCPF(String cpf) throws Exception {

		Connection con = Connector.getConnection();
		ClienteDAO dao = new ClienteDAO(con);
		Cliente c = dao.buscarPorCPF(cpf);
		con.close();
		
		return c;
	}
	
	/* Método para buscar atualizar as informações cliente */
	public void editar(Cliente c) throws Exception {
		
		ClienteDAO dao = new ClienteDAO(Connector.getConnection());
		dao.editar(c);	
	}
	
	/* Método para incluir as informações do cliente */
	public void incluir (Cliente c) throws Exception {
		
		ContaCorrente cc = new ContaCorrente();
		int numero;
		int digito;
		
		/* Para cada cliente inserido, deverá ser gerado uma conta corrente aleatória */
		Random r = new Random();
		
		numero = r.nextInt(99999);
		digito = r.nextInt(9);
		
		/* Fixo a informação da agência. Todos serão da mesma agência */
		cc.setAgencia("2412");
		cc.setConta(numero + "-" + digito);
		
		c.setCc(cc);
		
		ClienteDAO dao = new ClienteDAO(Connector.getConnection());
		dao.incluir(c);
	}
}
