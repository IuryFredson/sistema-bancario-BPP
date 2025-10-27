package com.sistemabancario.reporting;

import com.sistemabancario.model.Conta;
import com.sistemabancario.service.Banco;
import java.util.List;



public class Relatorio {
    private final Banco banco;

    public Relatorio(Banco banco) {
        this.banco = banco;
    }

    public void exibirTotalContasPoupanca() {

        int totalContasPoupanca = this.banco.getContasPoupanca().size();
        double saldoTotalContasPoupanca = this.banco.calcularSaldoTotalContasPoupanca();

        System.out.println(

            "Relatório de todas as Contas Poupança cadastradas no banco: " +
            this.banco.getNomeDoBanco()

        );

        if (totalContasPoupanca == 0){
            System.out.println("Nenhuma conta poupança cadastrada.");
            System.out.println("Portanto o Saldo Total é: R$ 0.0 \n");
            return;
        }

        System.out.println("Total de Contas Poupança cadastradas: " + totalContasPoupanca);
        System.out.println("Saldo Total das Contas Poupança: R$ " + saldoTotalContasPoupanca + "\n");

    }

    public void exibirTotalContasCorrente() {

        int todasContasCorrente = this.banco.getContasCorrente().size();
        double saldoTotalContasCorrente = this.banco.calcularSaldoTotalContasCorrente();

        System.out.println(

            "Relatório de todas as Contas Corrente cadastradas no banco: " +
            this.banco.getNomeDoBanco()

        );

        if (todasContasCorrente == 0) {
            System.out.println("Nenhuma conta poupança cadastrada.");
            System.out.println("Portanto o Saldo Total é: R$ 0.0 \n");
            return;
        }

        System.out.println("Total de Contas Corrente cadastradas: " + todasContasCorrente);
        System.out.println("Saldo Total das Contas Corrente: R$ " + saldoTotalContasCorrente + "\n");

    }

    public void exibirTotalContas() {

        int totalDeContas = this.banco.getTodasContas().size();
        double saldoTotalDeContas = this.banco.calcularSaldoTotalContas();

        System.out.println(

            "Relatório geral de todas as contas cadastradas no banco: " +
            this.banco.getNomeDoBanco()

        );
        System.out.println("Total de Contas cadastradas: " + totalDeContas);
        System.out.println("Saldo Total de Todas as Contas: R$ " + saldoTotalDeContas + "\n");

    }

    public void exibirTodasContas() {

        List<Conta> contasOrdemDescendente = this.banco.listarContasPorSaldoDescendente();

        System.out.println(

            "Exibindo todas as contas cadastradas no banco " +
            this.banco.getNomeDoBanco() +
            " mostradas em ordem descendente de saldo:"

        );

        if (contasOrdemDescendente.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }

        for (Conta conta : contasOrdemDescendente) {
            System.out.println(conta.getDescricao());
        }

        System.out.println();
    }

}

