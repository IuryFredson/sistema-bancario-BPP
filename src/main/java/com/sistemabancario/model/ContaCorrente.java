package com.sistemabancario.model;

public class ContaCorrente extends Conta {

    private double taxaDeServico;

    public ContaCorrente(String numeroDaConta, Cliente cliente, double saldoInicial, double inputTaxaDeServico) {
        super(numeroDaConta, cliente, saldoInicial);
        this.taxaDeServico = inputTaxaDeServico;
    }

    public void aplicarTaxaServico() {
        this.saldo -= this.saldo >= this.taxaDeServico ? this.taxaDeServico : null; 
    }

	@Override
    public String getDescricao() {
        return "Tipo: Corrente | " + super.getDescricao();
    }

}
