package com.sistemabancario;

import com.sistemabancario.model.Cliente;
import com.sistemabancario.model.ContaCorrente;
import com.sistemabancario.model.ContaPoupanca;
import com.sistemabancario.reporting.Relatorio;
import com.sistemabancario.service.Banco;
import java.util.Scanner;

public class Main{

    public enum entradas{
        

        criarNovaConta(1),
        exibirTodasContas(2),
        ExibirTotalTodasContas(3),
        Depositar(4),
        Sacar(5),
        Transferir(6),
        TodasCorrentes(7),
        TotalCorrentes(8),
        TodasPoupanca(9),
        TotalPoupanca(10);

        private final int valor;
        private entradas(int valor){
            this.valor = valor;
        }


    }

    public static void bemVindo(){
        System.out.println("-=Bem vindo ao Sistema TSIBank=-");
    }

    public static void exibirInstrucoes(){
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("O que deseja fazer? \n");

        System.out.println("-= Opções sobre todas as contas =-");
        System.out.println("1- Criar nova conta");
        System.out.println("2- Exibir todas as contas");
        System.out.println("3- Exibir total de todas as contas\n");
        
        System.out.println("-= Opções sobre uma conta específica =-");
        System.out.println("4- Depositar");
        System.out.println("5- Sacar");
        System.out.println("6- Transferir");

        System.out.println("-= Opções sobre as Contas Corrente =-");
        System.out.println("7- Exibir todas as contas corrente");
        System.out.println("8- Exibir o saldo total de todas as contas corrente\n");
        
        System.out.println("-= Opções sobre as Contas Poupança =-");
        System.out.println("9- Exibir todas as contas poupança");
        System.out.println("10- Exibir o saldo total de todas as contas poupança");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");

        System.out.println("OBS: Neste sistema, você tem o controle do tempo! Faça o tempo passar digitando 'd' + quantidadeDeDias.\n");
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner ler = new Scanner(System.in);
        Banco TSIbank = new Banco("TSIBank", 0.15, 0.05);
        Relatorio relatorio = new Relatorio(TSIbank);

        bemVindo();
        while (true){
            exibirInstrucoes();
            int opcao = ler.nextInt();
            
            if (opcao == entradas.criarNovaConta.valor){
                System.out.println("-=Primeiro nos forneca as informações sobre o cliente=-");
                System.out.println("Digite o nome do Cliente: ");
                String nome = ler.nextLine();

                System.out.println("Digite o cpf do Cliente:");
                String cpf = ler.nextLine();

                Cliente cliente = new Cliente(nome, cpf);

                System.out.println("Qual o tipo da conta que deseja criar?");
                System.out.println("1- Conta Corrente");
                System.out.println("2- Conta Poupança");
                int tipoDaConta = ler.nextInt();

                System.out.println("Qual o saldo inicial da conta?");
                Double saldoInicial = ler.nextDouble();

                if (tipoDaConta == 1){
                    ContaCorrente conta = new ContaCorrente(TSIbank.gerarNumeroDaConta(), cliente, saldoInicial, TSIbank.getTaxaServico());
                }
                else if (tipoDaConta == 2){
                    ContaPoupanca conta = new ContaPoupanca(TSIbank.gerarNumeroDaConta(), cliente, saldoInicial);
                }



            }
        }

    }
}