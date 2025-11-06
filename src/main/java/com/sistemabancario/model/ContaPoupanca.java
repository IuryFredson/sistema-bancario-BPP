package com.sistemabancario.model;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(String numeroDaConta, Cliente cliente, double saldoInicial) {
        super (numeroDaConta, cliente, saldoInicial);
    }

    @Override
    public saidasDeOperacoes sacar(double valor) {
        if (this.saldo >= valor){
            this.saldo -=  valor;
            return saidasDeOperacoes.OperacaoBemSucedida;
        }
        return saidasDeOperacoes.saldoMenorQueSaque;
    }

	@Override
    public String getDescricao() {
        return "Tipo: Poupança | " + super.getDescricao();
    }
}
