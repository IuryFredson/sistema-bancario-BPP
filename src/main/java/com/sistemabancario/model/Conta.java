package com.sistemabancario.model;

public abstract class Conta implements Cloneable{

    @Override
    public Conta clone() throws CloneNotSupportedException {
        return (Conta) super.clone();
    }

    protected String numeroDaConta;
    protected Cliente cliente;
    protected double saldo;

    public Conta(String numeroDaConta, Cliente cliente, double saldoInicial) {
        this.numeroDaConta = numeroDaConta;
        this.cliente = cliente;
        this.saldo = saldoInicial;
    }

    public void depositar(double valor){
        this.saldo += valor;
    }

    protected abstract saidasDeOperacoes sacar(double valor);

    public boolean transferir(Conta contaDestino, double valor){
        if (this.saldo >= valor){
            this.saldo -= valor;
            contaDestino.depositar(valor);
            return true;
        }
        return false;
    }

    public double getSaldo() { 
        return this.saldo;
    }

    public String getNumeroDaConta() {
        return this.numeroDaConta;
    }

    public Cliente getCliente() throws CloneNotSupportedException {
        return cliente.clone();
    }
    
    public enum saidasDeOperacoes{
        OperacaoBemSucedida,
        saldoMenorQueSaque,
        saldoMenorQueTarifa;
    }

    public String getDescricao() {

        String nomeCliente;

        if (cliente != null) {
            nomeCliente = cliente.getNome();

        } else {
            nomeCliente = "Desconhecido";
        }

        return String.format(

            "Cliente: %-20s | Conta: %-10s | Saldo: R$ %.2f",
            nomeCliente,
            numeroDaConta,
            saldo

        );

    }

}
