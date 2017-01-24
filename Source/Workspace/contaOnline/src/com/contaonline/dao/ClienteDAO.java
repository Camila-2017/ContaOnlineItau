package com.contaonline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.contaonline.dto.Cliente;
import com.contaonline.dto.ContaCorrente;

/* Classe Data Access Object - Acesso ao banco de dados  */
public class ClienteDAO {
	
	Connection con;
	
	public ClienteDAO(Connection conexao ) {
		con = conexao;
	}
	
	/* Método responsável por realizar a exclusão do cliente por CPF no banco de dados */
	public void excluir(String cpf) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from contacorrente where cpf = ? ");
		
		/* Informar qual a posicao e qual campo deve ser inserido no where */
		PreparedStatement ps = con.prepareStatement(sql.toString());
		ps.setString(1, cpf);

		ps.executeUpdate();
		
		sql = new StringBuffer();
		sql.append("delete from clientes where cpf = ? ");
		
		ps = con.prepareStatement(sql.toString());
		ps.setString(1, cpf);

		ps.executeUpdate();				
	}

	/* Método responsável por realizar a busca do cliente por CPF no banco de dados */
	public Cliente buscarPorCPF(String cpf) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select c.*, cc.* from clientes c, contacorrente cc where c.cpf = ? and c.cpf = cc.cpf");
		Cliente c = null;
		ContaCorrente cc = null;
		
		/* Informar qual a posicao e qual campo deve ser inserido no where */
		PreparedStatement ps = con.prepareStatement(sql.toString());
		ps.setString(1, cpf);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			cc = new ContaCorrente();
			c = new Cliente();
			c.setCpf(rs.getString("cpf"));
			c.setEndereco(rs.getString("endereco"));
			c.setNome(rs.getString("nome"));
			cc.setAgencia(rs.getString("agencia"));
			cc.setConta(rs.getString("conta"));
			c.setCc(cc);
		}
		
		rs.close();
		
		return c;
	}
	
	/* Método responsável por realizar a atualização dos dados do cliente no banco de dados */
	public void editar(Cliente c) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("update clientes  set nome = ?, endereco = ? where cpf = ? ");
		
		/* Informar qual a posicao e qual campo deve ser inserido no where */
		PreparedStatement ps = con.prepareStatement(sql.toString());
		ps.setString(1, c.getNome());
		ps.setString(2, c.getEndereco());
		ps.setString(3,  c.getCpf());
		
		ps.executeUpdate();
	}
	
	/* Método responsável por realizar a inclusão dos dados do cliente no banco de dados */
	public void incluir (Cliente c) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into clientes values (?, ?, ?) ");
		
		/* Informar qual a posicao e qual campo deve ser inserido no where */
		PreparedStatement ps = con.prepareStatement(sql.toString());
		ps.setString(1,  c.getCpf());
		ps.setString(2, c.getNome());
		ps.setString(3, c.getEndereco());

		ps.executeUpdate();
		
		sql = new StringBuffer();
		sql.append("insert into contacorrente values (?, ?, ?) ");
		
		ps = con.prepareStatement(sql.toString());
		ps.setString(1,  c.getCpf());
		ps.setString(2, c.getCc().getAgencia());
		ps.setString(3, c.getCc().getConta());

		ps.executeUpdate();		
		
	}
}
