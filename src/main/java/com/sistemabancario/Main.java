package com.sistemabancario;

import com.sistemabancario.model.Conta;
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
        exibirTodasCorrentes(7),
        TotalCorrentes(8),
        exibirTodasPoupanca(9),
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

    public static void IOcriarConta(Banco TSIbank) throws CloneNotSupportedException{
        Scanner lerCriandoConta = new Scanner(System.in);
        System.out.println("-=Primeiro nos forneca as informações sobre o cliente=-");
        System.out.println("Digite o nome do Cliente: ");
        String nome = lerCriandoConta.nextLine();

        System.out.println("Digite o cpf do Cliente:");
        String cpf = lerCriandoConta.nextLine();

        System.out.println("Qual o tipo da conta que deseja criar?");
        System.out.println("1- Conta Corrente");
        System.out.println("2- Conta Poupança");
        int tipoDaConta = lerCriandoConta.nextInt();

        System.out.println("Qual o saldo inicial da conta?");
        Double saldoInicial = lerCriandoConta.nextDouble();

        if (tipoDaConta == 1){
            TSIbank.construirContaCorrente(nome, cpf, saldoInicial);
        }
        else if (tipoDaConta == 2){
            TSIbank.construirContaPoupanca(nome, cpf, saldoInicial);
        }

        System.out.println("--> Sua conta foi criada!\n");
        lerCriandoConta.close();
    }

    public static void IOdepositar(Banco TSIbank, Relatorio relatorio){
        Scanner lerDepositar = new Scanner(System.in);
        relatorio.exibirTodasContas();
        System.out.println("Em qual conta você quer depositar? Digite o número da conta: ");
        String numeroDaConta = lerDepositar.nextLine();
        System.out.println("Quanto você quer depositar?");
        double valorParaDepositar = lerDepositar.nextDouble();
        TSIbank.getContaPeloCodigo(numeroDaConta).depositar(valorParaDepositar);
        System.out.println("Valor depositado com sucesso!");
        lerDepositar.close();
    }

    public static void IOsacar(Banco TSIbank, Relatorio relatorio){
        Scanner lerSacar = new Scanner(System.in);
        relatorio.exibirTodasContas();
        System.out.println("De qual conta você quer sacar? Digite o número da conta: ");
        String numeroDaConta = lerSacar.nextLine();
        System.out.println("Quanto você quer sacar?");
        double valorParaSacar = lerSacar.nextDouble();
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
            default -> {
            }
        }
        lerSacar.close();
    }

    public static void IOtransferir(Banco TSIbank, Relatorio relatorio){
        Scanner lerTransferir = new Scanner(System.in);
        relatorio.exibirTodasContas();
        System.out.println("De qual conta você quer transferir? Digite o número da conta: ");
        String numeroDaContaTransferir = lerTransferir.nextLine();
        System.out.println("Para qual conta você quer transferir? Digite o número da conta: ");
        String numeroDaContaAlvo = lerTransferir.nextLine();
        System.out.println("Quanto deseja transferir? ");
        double valorParaTransferir = lerTransferir.nextDouble();

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
        lerTransferir.close();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner ler = new Scanner(System.in);
        Banco TSIbank = new Banco("TSIBank", 0.15, 0.05);
        Relatorio relatorio = new Relatorio(TSIbank);

        bemVindo();
        while (true){
            exibirInstrucoes();
            int opcao = ler.nextInt();
            
            if (opcao == entradas.criarNovaConta.valor){ IOcriarConta(TSIbank); }
            else if (opcao == entradas.exibirTodasContas.valor){ relatorio.exibirTodasContas(); }
            else if (opcao == entradas.ExibirTotalTodasContas.valor){ relatorio.exibirSaldoTotalContas(); }
            else if (opcao == entradas.Depositar.valor){ IOdepositar(TSIbank, relatorio); }
            else if (opcao == entradas.Sacar.valor){ IOsacar(TSIbank, relatorio); }
            else if (opcao == entradas.Transferir.valor){ IOtransferir(TSIbank, relatorio); }
            else if (opcao == entradas.exibirTodasCorrentes.valor) { relatorio.exibirContasCorrente(); }
            else if (opcao == entradas.TotalCorrentes.valor) { relatorio.exibirTotalContasCorrente();}
            else if (opcao == entradas.exibirTodasPoupanca.valor) { relatorio.exibirContasPoupanca();}
            else if (opcao == entradas.TotalPoupanca.valor) { relatorio.exibirTotalContasPoupanca();}
        }
    }
}