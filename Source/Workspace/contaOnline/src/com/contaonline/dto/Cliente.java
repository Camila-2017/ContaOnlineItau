package com.contaonline.dto;

/* Declaração da classe - Objeto de transferência entre as camandas */
public class Cliente {

	private String cpf;
	private String nome;
	private String endereco;
	private ContaCorrente cc;
	
	
	public ContaCorrente getCc() {
		return cc;
	}
	public void setCc(ContaCorrente cc) {
		this.cc = cc;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
}
