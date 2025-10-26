package com.sistemabancario.model;

public class ContaPoupanca extends Conta {

    private double taxaDeRendimento;

    public ContaPoupanca(String numeroDaConta, Cliente cliente, double saldoInicial, double inputTaxaDeRendimento) {
        super (numeroDaConta, cliente, saldoInicial);
        this.taxaDeRendimento = inputTaxaDeRendimento;
    }

    public void aplicarTaxaRendimento() {
        this.saldo += taxaDeRendimento * this.saldo;
    }

	@Override
    public String getDescricao() {
        return "Tipo: Poupança | " + super.getDescricao();
    }
}
