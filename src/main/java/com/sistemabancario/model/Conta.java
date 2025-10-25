package com.sistemabancario.model;

public abstract class Conta {

    protected String numeroDaConta;
    protected Cliente cliente;
    protected double saldo;

    public Conta(String numeroDaConta, Cliente cliente, double saldoInicial) {
        this.numeroDaConta = numeroDaConta;
        this.cliente = cliente;
        this.saldo = saldoInicial;
    }

    public abstract void depositar(double valor);

    public abstract boolean sacar(double valor);

    public abstract boolean transferir(Conta contaDestino, double valor);

    public double getSaldo() {
        return saldo;
    }

    public String getNumeroDaConta() {
        return numeroDaConta;
    }

    public Cliente getCliente() {
        return cliente;
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
