package barbearia;

import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		
		//converte os parametros do programa para inteiro 
		int num_barbeiros = Integer.parseInt(args[0]);
		int num_cadeiras = Integer.parseInt(args[1]);
		int total_clientes = Integer.parseInt(args[2]);
		
		//cria um monitor com os parametros do programa
		Barbearia monitor = new Barbearia(num_barbeiros, num_cadeiras, total_clientes);
		
		
		//cria uma lista de barbeiros 
		LinkedList<Barbeiro> lista_de_barbeiros = new LinkedList<Barbeiro>();
		
		//adiciona na lista a quantidade de barbeiros passados para o programa 
		for(int i = 1; i < num_barbeiros+1; i++) {
			Barbeiro barbeiro = new Barbeiro(i, monitor);
			lista_de_barbeiros.add(barbeiro);
		}
		
		//cria uma lista de barbeiros 
		LinkedList<Cliente> lista_de_clientes = new LinkedList<Cliente>();
		
		//adiciona na lista a quantidade de clientes passados para o programa 
		for(int i = 1; i < total_clientes+1; i++) {
			Cliente cliente = new Cliente(i, monitor);
			lista_de_clientes.add(cliente);
		}
		
		//cria uma thread para cada barbeiro 
		for(int i = 0; i < num_barbeiros; i++) {
			Thread t = new Thread(lista_de_barbeiros.get(i));
			t.start();
		}
		
		//cria uma thread para cada cliente
		for(int i = 0; i < total_clientes; i++) {
			Thread t = new Thread(lista_de_clientes.get(i));
			t.start();
		}
	}

}
