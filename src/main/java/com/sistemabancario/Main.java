package com.sistemabancario;

import com.sistemabancario.model.Conta;
import com.sistemabancario.reporting.Relatorio;
import com.sistemabancario.service.Banco;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner ler = new Scanner(System.in);
        Banco TSIbank = new Banco("TSIBank", 0.05, 0.05);
        Relatorio relatorio = new Relatorio(TSIbank);

        System.out.println("-=Bem vindo ao Sistema TSIBank=-");
        while (true){
            exibirInstrucoes();
            int opcao = ler.nextInt();
            ler.nextLine();
            
            if (opcao == entradas.criarNovaConta.valor){ IOcriarConta(TSIbank, ler); }
            else if (opcao == entradas.exibirTodasContas.valor){ relatorio.exibirTodasContas(); }
            else if (opcao == entradas.ExibirTotalTodasContas.valor){ relatorio.exibirSaldoTotalContas(); }
            else if (opcao == entradas.AplicarRendimento.valor) { TSIbank.aplicarTaxaRendimento(); }
            else if (opcao == entradas.Depositar.valor){ IOdepositar(TSIbank, relatorio, ler); }
            else if (opcao == entradas.Sacar.valor){ IOsacar(TSIbank, relatorio, ler); }
            else if (opcao == entradas.Transferir.valor){ IOtransferir(TSIbank, relatorio, ler); }
            else if (opcao == entradas.exibirTodasCorrentes.valor) { relatorio.exibirContasCorrente(); }
            else if (opcao == entradas.TotalCorrentes.valor) { relatorio.exibirTotalContasCorrente(); }
            else if (opcao == entradas.exibirTodasPoupanca.valor) { relatorio.exibirContasPoupanca(); }
            else if (opcao == entradas.TotalPoupanca.valor) { relatorio.exibirTotalContasPoupanca(); }
            
        }
    }

    public static void IOcriarConta(Banco TSIbank, Scanner ler) throws CloneNotSupportedException{
        System.out.println("-=Primeiro nos forneca as informações sobre o cliente=-");
        System.out.println("Digite o nome do Cliente: ");
        String nome = ler.nextLine();
        System.out.println("Digite o cpf do Cliente:");
        String cpf = ler.nextLine();

        try {
            System.out.println("Qual o tipo da conta que deseja criar?");
            System.out.println("1- Conta Corrente");
            System.out.println("2- Conta Poupança");
            int tipoDaConta = ler.nextInt();
            ler.nextLine();
            System.out.println("Qual o saldo inicial da conta?");
            Double saldoInicial = ler.nextDouble();
            ler.nextLine();

            if (tipoDaConta == 1){
                TSIbank.construirContaCorrente(nome, cpf, saldoInicial);
            }
            else if (tipoDaConta == 2){
                TSIbank.construirContaPoupanca(nome, cpf, saldoInicial);
            }

            System.out.println("--> Sua conta foi criada!\n");
        }
        catch (InputMismatchException e) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("ERRO: Valor inserido inválido.");
            System.out.println("Tente novamente.");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
            ler.nextLine();
        }
    }

    public static void IOdepositar(Banco TSIbank, Relatorio relatorio, Scanner ler){
        try {
            relatorio.exibirTodasContas();
            System.out.println("Em qual conta você quer depositar? Digite o número da conta: ");
            String numeroDaConta = ler.nextLine();
            System.out.println("Quanto você quer depositar?");
            double valorParaDepositar = ler.nextDouble();
            ler.nextLine();
            TSIbank.getContaPeloCodigo(numeroDaConta).depositar(valorParaDepositar);
            System.out.println("Valor depositado com sucesso!");
        } catch( InputMismatchException e){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("ERRO: Valor inserido inválido.");
            System.out.println("Tente novamente.");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
        } catch (RuntimeException e) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("ERRO: " + e.getMessage());
            System.out.println("Tente novamente.");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
        } 
    }

    public static void IOsacar(Banco TSIbank, Relatorio relatorio, Scanner ler){
        try {
            relatorio.exibirTodasContas();
            System.out.println("De qual conta você quer sacar? Digite o número da conta: ");
            String numeroDaConta = ler.nextLine();
            System.out.println("Quanto você quer sacar?");
            double valorParaSacar = ler.nextDouble();
            ler.nextLine();
            Conta conta = TSIbank.getContaPeloCodigo(numeroDaConta);
            switch (conta.sacar(valorParaSacar)) {
            case OperacaoBemSucedida -> System.out.println("Operação bem sucedida!");
            case saldoMenorQueSaque -> {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("ATENÇÃO! Não foi possível realizar o saque pois o saldo da conta é menor do que o valor desejado.");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
            case saldoMenorQueTarifa -> {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("ATENÇÃO! Não foi possível realizar o saque, pois a conta não tem saldo o suficiente para pagar a taxa de saque.");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        }  
        } catch (InputMismatchException e){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("ERRO: Valor inserido inválido.");
            System.out.println("Tente novamente.");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
        } catch (RuntimeException e) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("ERRO: " + e.getMessage());
            System.out.println("Tente novamente.");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
        }
    }

    public static void IOtransferir(Banco TSIbank, Relatorio relatorio, Scanner ler){
        relatorio.exibirTodasContas();
        System.out.println("De qual conta você quer transferir? Digite o número da conta: ");
        String numeroDaContaTransferir = ler.nextLine();
        System.out.println("Para qual conta você quer transferir? Digite o número da conta: ");
        String numeroDaContaAlvo = ler.nextLine();
        System.out.println("Quanto deseja transferir? ");
        double valorParaTransferir = ler.nextDouble();
        ler.nextLine();

        try {
            Conta contaTransferir = TSIbank.getContaPeloCodigo(numeroDaContaTransferir);
            Conta contaAlvo = TSIbank.getContaPeloCodigo(numeroDaContaAlvo);

            if (contaTransferir.transferir(contaAlvo, valorParaTransferir)){
                System.out.println("Operação bem sucedida!");
            } 
            else {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("ATENÇÃO! Não foi possível fazer a transferência pois o saldo da conta é menor do que o valor desejado.");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        } catch (InputMismatchException e){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("ERRO: Valor inserido inválido.");
            System.out.println("Tente novamente.");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
        } catch (RuntimeException e) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("ERRO: " + e.getMessage());
            System.out.println("Tente novamente.");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
        }
    }

    public static void exibirInstrucoes(){
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("O que deseja fazer? \n");

        System.out.println("-= Opções sobre todas as contas =-");
        System.out.println("1- Criar nova conta");
        System.out.println("2- Exibir todas as contas");
        System.out.println("3- Exibir total de todas as contas");
        System.out.println("4- Aplicar taxa de rendimento para todas as contas poupança\n");
        
        System.out.println("-= Opções sobre uma conta específica =-");
        System.out.println("5- Depositar");
        System.out.println("6- Sacar");
        System.out.println("7- Transferir\n");

        System.out.println("-= Opções sobre as Contas Corrente =-");
        System.out.println("8- Exibir todas as contas corrente");
        System.out.println("9- Exibir o saldo total de todas as contas corrente\n");
        
        System.out.println("-= Opções sobre as Contas Poupança =-");
        System.out.println("10- Exibir todas as contas poupança");
        System.out.println("11- Exibir o saldo total de todas as contas poupança");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");
    }

    public enum entradas{
        criarNovaConta(1),
        exibirTodasContas(2),
        ExibirTotalTodasContas(3),
        AplicarRendimento(4),
        Depositar(5),
        Sacar(6),
        Transferir(7),
        exibirTodasCorrentes(8),
        TotalCorrentes(9),
        exibirTodasPoupanca(10),
        TotalPoupanca(11);

        private final int valor;
        private entradas(int valor){
            this.valor = valor;
        }
    }
}