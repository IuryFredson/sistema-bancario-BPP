package com.sistemabancario.service;

import com.sistemabancario.model.Cliente;
import com.sistemabancario.model.Conta;
import com.sistemabancario.model.ContaCorrente;
import com.sistemabancario.model.ContaPoupanca;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Banco {

    private final String nomeDoBanco;
    private final List<Conta> todasContas;
    private final double taxaDeRendimento;
    private final double taxaDeServico;

    public Banco(String nomeDoBanco, double taxaDeRendimento, double taxaDeServico) {
        this.todasContas = new ArrayList<>();
        this.nomeDoBanco = nomeDoBanco;
        this.taxaDeRendimento = taxaDeRendimento;
        this.taxaDeServico = taxaDeServico;
    }

    public void construirContaCorrente(String nome, String cpf, double saldoInicial) throws CloneNotSupportedException{
        String nr = this.gerarNumeroDaConta();
        Cliente cliente = new Cliente(nome, cpf);
        ContaCorrente conta = new ContaCorrente(nr, cliente, saldoInicial, this.taxaDeServico);
        this.todasContas.add(conta);
    }

    public void construirContaPoupanca(String nome, String cpf, double saldoInicial) throws CloneNotSupportedException{
        String numeroGerado = this.gerarNumeroDaConta();
        Cliente cliente = new Cliente(nome, cpf);
        ContaPoupanca conta = new ContaPoupanca(numeroGerado, cliente, saldoInicial);
        this.todasContas.add(conta);
    }

    public String getNomeDoBanco() {
        return this.nomeDoBanco;
    }

    public List<Conta> getTodasContas() throws CloneNotSupportedException {
        List<Conta> copiaListaDeContas = new ArrayList<>();
        for (Conta conta : todasContas){
            copiaListaDeContas.add((Conta) conta.clone());
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

    public List<ContaPoupanca> getContasPoupanca() {

        List<ContaPoupanca> contasPoupanca = new ArrayList<>();
        for (Conta conta : todasContas) {
            if (conta instanceof ContaPoupanca contaPoupanca) {
                contasPoupanca.add(contaPoupanca);
            }
        }
        return contasPoupanca;

    }

    public double calcularSaldoTotalContasPoupanca() {

        List<ContaPoupanca> contasPoupanca = getContasPoupanca();
        double saldoTotal = 0.0;

        for (ContaPoupanca conta : contasPoupanca) {
            saldoTotal += conta.getSaldo();
        }
        return saldoTotal;

    }

    public List<ContaCorrente> getContasCorrente() {

        List<ContaCorrente> contasCorrente = new ArrayList<>();
        for (Conta conta : todasContas) {
            if (conta instanceof ContaCorrente contaCorrente) {
                contasCorrente.add(contaCorrente);
            }
        }
        return contasCorrente;

    }

    public Conta getContaPeloCodigo(String codigo){
        for (Conta conta : this.todasContas){
            if (conta.getNumeroDaConta().equals(codigo)){ return conta; }
        }
        throw new RuntimeException("Conta não encontrada para o código: " + codigo);
    }

    public double calcularSaldoTotalContasCorrente() {

        List<ContaCorrente> contasCorrente = getContasCorrente();
        double saldoTotal = 0.0;

        for (ContaCorrente conta : contasCorrente) {
            saldoTotal += conta.getSaldo();
        }
        return saldoTotal;

    }

    public List<Conta> listarContasPorSaldoDescendente() {

        List<Conta> contasOrdemDescendente = new ArrayList<>(todasContas);
        contasOrdemDescendente.sort(Comparator.comparingDouble(Conta::getSaldo).reversed());
        return contasOrdemDescendente;

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

    public String gerarNumeroDaConta() throws CloneNotSupportedException{
        return String.valueOf(this.getTodasContas().size());
    }

}
