package com.sistemabancario.model;

public class Cliente implements Cloneable{

    @Override
    public Cliente clone() throws CloneNotSupportedException {
        return (Cliente) super.clone();
    }

    private String nome;
    private String cpf;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        String cloneNome = this.nome;
        return cloneNome;
    }

    public String getCpf() {
        String cloneCpf = this.cpf;
        return cloneCpf;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}

