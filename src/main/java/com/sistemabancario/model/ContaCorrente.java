package com.sistemabancario.model;

public class ContaCorrente extends Conta {

    private final double taxaDeServico;

    public ContaCorrente(String numeroDaConta, Cliente cliente, double saldoInicial, double taxaDeServico) {
        super(numeroDaConta, cliente, saldoInicial);
        this.taxaDeServico = taxaDeServico;
    }

    @Override
    public saidasDeOperacoes sacar(double valor) {
        if (this.saldo < valor){
            return saidasDeOperacoes.saldoMenorQueSaque;
        }
        else if (this.saldo < valor + (valor * this.taxaDeServico)){
            return saidasDeOperacoes.saldoMenorQueTarifa;
        }
        this.saldo -= valor;
        this.saldo -= valor * this.taxaDeServico;
        return saidasDeOperacoes.OperacaoBemSucedida;
    }

	@Override
    public String getDescricao() {
        return "Tipo: Corrente | " + super.getDescricao();
    }

}
