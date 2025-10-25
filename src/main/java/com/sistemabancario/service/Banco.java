package com.sistemabancario.service;

import com.sistemabancario.model.Conta;
import com.sistemabancario.model.ContaCorrente;
import com.sistemabancario.model.ContaPoupanca;
import java.util.ArrayList;
import java.util.Comparator;


public class Banco {

    private String nomeDoBanco;
    private ArrayList<Conta> todasContas;

    public Banco(String nomeDoBanco) {
        this.todasContas = new ArrayList<>();
        this.nomeDoBanco = nomeDoBanco;
    }

    public String getNomeDoBanco() {
        return nomeDoBanco;
    }

    public ArrayList<Conta> getTodasContas() {
        return todasContas;
    }

    public double calcularSaldoTotalContas() {

        double saldoTotal = 0.0;
        for (Conta conta : todasContas) {
            saldoTotal += conta.getSaldo();
        }
        return saldoTotal;

    }

    public ArrayList<ContaPoupanca> getContasPoupanca() {

        ArrayList<ContaPoupanca> contasPoupanca = new ArrayList<>();
        for (Conta conta : todasContas) {
            if (conta instanceof ContaPoupanca contaPoupanca) {
                contasPoupanca.add(contaPoupanca);
            }
        }
        return contasPoupanca;

    }

    public double calcularSaldoTotalContasPoupanca() {

        ArrayList<ContaPoupanca> contasPoupanca = getContasPoupanca();
        double saldoTotal = 0.0;
        for (ContaPoupanca conta : contasPoupanca) {
            saldoTotal += conta.getSaldo();
        }
        return saldoTotal;

    }

    public ArrayList<ContaCorrente> getContasCorrente() {

        ArrayList<ContaCorrente> contasCorrente = new ArrayList<>();
        for (Conta conta : todasContas) {
            if (conta instanceof ContaCorrente contaCorrente) {
                contasCorrente.add(contaCorrente);
            }
        }
        return contasCorrente;

    }

    public double calcularSaldoTotalContasCorrente() {

        ArrayList<ContaCorrente> contasCorrente = getContasCorrente();
        double saldoTotal = 0.0;
        for (ContaCorrente conta : contasCorrente) {
            saldoTotal += conta.getSaldo();
        }
        return saldoTotal;

    }

    public ArrayList<Conta> listarContasPorSaldoDescendente() {

        ArrayList<Conta> contasOrdemDescendente = new ArrayList<>(todasContas);
        contasOrdemDescendente.sort(Comparator.comparingDouble(Conta::getSaldo).reversed());
        return contasOrdemDescendente;

    }

}
