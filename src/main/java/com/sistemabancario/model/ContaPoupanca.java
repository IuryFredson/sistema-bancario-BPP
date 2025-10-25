package com.sistemabancario.model;

public class ContaPoupanca extends Conta {

    private double taxaDeRendimento;

    public ContaPoupanca(String numeroDaConta, Cliente cliente, double saldoInicial) {
        //TODO
    }

    public void aplicarTaxaRendimento(double taxaPercentual) {
    }

	@Override
    public String getDescricao() {
        return "Tipo: Poupança | " + super.getDescricao();
    }
}
