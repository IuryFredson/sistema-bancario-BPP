package com.sistemabancario.service;

import com.sistemabancario.model.Conta;
import com.sistemabancario.model.ContaCorrente;
import com.sistemabancario.model.ContaPoupanca;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Banco {

    private final String nomeDoBanco;
    private final List<Conta> todasContas;

    public Banco(String nomeDoBanco) {
        this.todasContas = new ArrayList<>();
        this.nomeDoBanco = nomeDoBanco;
    }

    public String getNomeDoBanco() {
        String cloneNome = nomeDoBanco;
        return cloneNome;
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

}
