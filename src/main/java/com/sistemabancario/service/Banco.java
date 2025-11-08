package com.sistemabancario.service;

import com.sistemabancario.model.Cliente;
import com.sistemabancario.model.Conta;
import com.sistemabancario.model.ContaCorrente;
import com.sistemabancario.model.ContaPoupanca;
import com.sistemabancario.view.ViewConta;
import java.util.ArrayList;
import java.util.List;

public class Banco {

    private final String nomeDoBanco;
    private final List<Conta> todasContas;
    private final List<Cliente> listaDeClientes;
    private final double taxaDeRendimento;
    private final double taxaDeServico;

    public Banco(String nomeDoBanco, double taxaDeRendimento, double taxaDeServico) {
        this.todasContas = new ArrayList<>();
        this.listaDeClientes = new ArrayList<>();
        this.nomeDoBanco = nomeDoBanco;
        this.taxaDeRendimento = taxaDeRendimento;
        this.taxaDeServico = taxaDeServico;
    }

    private Cliente buscarClientePorCpf(String cpf) {
        for (Cliente cliente : this.listaDeClientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    private Cliente criarNovoCliente(String nome, String cpf) {
        Cliente novoCliente = new Cliente(nome, cpf);
        this.listaDeClientes.add(novoCliente);
        return novoCliente;
    }

    public void construirContaCorrente(String nome, String cpf, double saldoInicial){
        String nr = this.gerarNumeroDaConta();

        Cliente cliente = this.buscarClientePorCpf(cpf);
        if (cliente == null) {
            cliente = this.criarNovoCliente(nome, cpf);
        }

        ContaCorrente conta = new ContaCorrente(nr, cliente, saldoInicial, this.taxaDeServico);
        this.todasContas.add(conta);
    }

    public void construirContaPoupanca(String nome, String cpf, double saldoInicial){
        String numeroGerado = this.gerarNumeroDaConta();

        Cliente cliente = this.buscarClientePorCpf(cpf);
        if (cliente == null) {
            cliente = this.criarNovoCliente(nome, cpf);
        }

        ContaPoupanca conta = new ContaPoupanca(numeroGerado, cliente, saldoInicial);
        this.todasContas.add(conta);
    }

    public String getNomeDoBanco() {
        return this.nomeDoBanco;
    }

    public List<ViewConta> getViewTodasContas() {
        List<ViewConta> copiaListaDeContas = new ArrayList<>();
        for (Conta conta : todasContas){
            copiaListaDeContas.add(conta);
        }
        return copiaListaDeContas;
    }

    public double calcularSaldoTotalContas() {
        double saldoTotal = 0.0;
        for (Conta conta : todasContas) {
            saldoTotal += conta.getSaldo();
        }
        return saldoTotal;
    }

    private List<ContaPoupanca> getContasPoupanca() {

        List<ContaPoupanca> copiaContasPoupanca = new ArrayList<>();
        for (Conta conta : todasContas) {
            if (conta instanceof ContaPoupanca contaPoupanca) {
                copiaContasPoupanca.add(contaPoupanca);
            }
        }
        return copiaContasPoupanca;

    }

    public List<ViewConta> getViewContasPoupanca() {
        List<ViewConta> viewContasPoupanca = new ArrayList<>();
        for (ViewConta conta : this.getContasPoupanca()) {
            viewContasPoupanca.add(conta);
        }
        return viewContasPoupanca;
    }

    public double calcularSaldoTotalContasPoupanca() {

        List<ViewConta> contasPoupanca = getViewContasPoupanca();
        double saldoTotal = 0.0;
        for (ViewConta conta : contasPoupanca) {
            saldoTotal += conta.getSaldo();
        }
        return saldoTotal;
    }

    public List<ViewConta> getViewContasCorrente() {

        List<ViewConta> copiaContasCorrente = new ArrayList<>();
        for (Conta conta : todasContas) {
            if (conta instanceof ContaCorrente contaCorrente) {
                copiaContasCorrente.add(contaCorrente);
            }
        }
        return copiaContasCorrente;

    }

    public Conta getContaPeloCodigo(String codigo){

        for (Conta conta : this.todasContas){
            if (conta.getNumeroDaConta().equals(codigo)){ return conta; }
        }
        throw new RuntimeException("Conta não encontrada para o código: " + codigo);

    }

    public double calcularSaldoTotalContasCorrente() {

        List<ViewConta> contasCorrente = getViewContasCorrente();
        double saldoTotal = 0.0;
        for (ViewConta conta : contasCorrente) {
            saldoTotal += conta.getSaldo();
        }
        return saldoTotal;

    }

    public void aplicarTaxaRendimento() {

        for (Conta conta : this.getContasPoupanca()){
            conta.depositar(conta.getSaldo() * this.taxaDeRendimento);
        }

    }

    public double getTaxaRendimento(){
        return this.taxaDeRendimento;
    }

    public double getTaxaServico(){
        return this.taxaDeServico;
    }

    public String gerarNumeroDaConta() {
        return String.valueOf(this.getViewTodasContas().size());
    }
}
