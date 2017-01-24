package com.contaonline.bo;

import java.sql.Connection;
import java.util.Random;

import com.contaonline.dao.ClienteDAO;
import com.contaonline.dao.Connector;
import com.contaonline.dto.Cliente;
import com.contaonline.dto.ContaCorrente;

/* Classe Business Object - Cont�m as Regras de Neg�cio  */
public class ClienteBO {
	
	/* M�todo para excluir o cliente por CPF */
	public void excluir(String cpf) throws Exception {
		
		Connection con = Connector.getConnection();
		ClienteDAO dao = new ClienteDAO(con);
		dao.excluir(cpf);
		con.close();		
	}

	/* M�todo para buscar o cliente por CPF */
	public Cliente buscarPorCPF(String cpf) throws Exception {

		Connection con = Connector.getConnection();
		ClienteDAO dao = new ClienteDAO(con);
		Cliente c = dao.buscarPorCPF(cpf);
		con.close();
		
		return c;
	}
	
	/* M�todo para buscar atualizar as informa��es cliente */
	public void editar(Cliente c) throws Exception {
		
		ClienteDAO dao = new ClienteDAO(Connector.getConnection());
		dao.editar(c);	
	}
	
	/* M�todo para incluir as informa��es do cliente */
	public void incluir (Cliente c) throws Exception {
		
		ContaCorrente cc = new ContaCorrente();
		int numero;
		int digito;
		
		/* Para cada cliente inserido, dever� ser gerado uma conta corrente aleat�ria */
		Random r = new Random();
		
		numero = r.nextInt(99999);
		digito = r.nextInt(9);
		
		/* Fixo a informa��o da ag�ncia. Todos ser�o da mesma ag�ncia */
		cc.setAgencia("2412");
		cc.setConta(numero + "-" + digito);
		
		c.setCc(cc);
		
		ClienteDAO dao = new ClienteDAO(Connector.getConnection());
		dao.incluir(c);
	}
}
