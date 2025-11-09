# Projeto: Sistema Bancário Simples (Boas Práticas de Programação - Unidade II)

Este projeto simula um sistema bancário básico para controle de contas e operações financeiras, conforme especificado na disciplina de Boas Práticas de Programação.

## 1. Integrantes da Equipe

- Iury Fredson Germano Miranda.

- Theo Henrique da Silva Borges.

- Sabrina da Silva Barbosa.


## 2. Breve Explicação sobre o Sistema

O sistema foi desenvolvido em Java e segue uma arquitetura em camadas para aplicar a **Separação de Preocupações**:

- **`model`**: Contém as classes de dados (`Cliente`, `Conta`, `ContaCorrente`, `ContaPoupanca`).

- **`service`**: Contém a lógica de negócio e regras (`Banco`).

- **`reporting`**: Contém a lógica de geração de relatórios (`Relatorio`).

- **`view`**: Contém as interfaces (`ViewCliente`, `ViewConta`) que desacoplam as camadas (Inversão de Dependência).

- **`Main`**: Atua como a camada de "Apresentação" ou "Controlador", lendo as entradas do console e orquestrando as operações.


O sistema cumpre todos os 9 requisitos funcionais, permitindo ao usuário criar clientes e contas (corrente/poupança), realizar operações (depósito, saque, transferência) e gerar relatórios consolidados.

## 3. Instruções para Compilar o Sistema (Via Terminal)

1. Clone o repositório:

    ```
    git clone https://github.com/IuryFredson/sistema-bancario-BPP
    cd sistema-bancario-BPP/
    ```

2. Crie um diretório para os arquivos compilados (ex: `bin`):

    ```
    mkdir bin
    ```

3. Compile todos os arquivos `.java`, direcionando os `.class` para o diretório `bin`:

    ```
    javac -d bin -cp src/main/java src/main/java/com/sistemabancario/*.java src/main/java/com/sistemabancario/model/*.java src/main/java/com/sistemabancario/service/*.java src/main/java/com/sistemabancario/reporting/*.java src/main/java/com/sistemabancario/view/*.java
    ```


## 4. Instruções para Executar o Sistema

1. No diretório raiz do projeto (o mesmo onde está a pasta `bin`), execute o comando `java`.

2. Use a flag `-cp` (classpath) para indicar onde estão os arquivos `.class` (dentro da pasta `bin`).

3. Especifique o nome completo da classe que contém o método `main`: `com.sistemabancario.Main`.

    ```
    java -cp bin com.sistemabancario.Main
    ```

1. O menu interativo do sistema será iniciado no terminal.