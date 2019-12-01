package barbearia;

import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		// TODO
		// * essa classe vai ser a main do trabalho
		// * transformar barbearia num monitor
		// * cliente e barbeiro em loop tentando chamar as funçoes do monitor
		
		//converte os parametros do programa para inteiro 
		int num_barbeiros = Integer.parseInt(args[0]);
		int num_cadeiras = Integer.parseInt(args[1]);
		int total_clientes = Integer.parseInt(args[2]);
		
		//cria um monitor com os parametros do programa
		Barbearia monitor = new Barbearia(num_barbeiros, num_cadeiras, total_clientes);
		
		
		//cria uma lista de barbeiros 
		LinkedList<Barbeiro> lista_de_barbeiros = new LinkedList<Barbeiro>();
		for(int i = 1; i < num_barbeiros+1; i++) {
			Barbeiro barbeiro = new Barbeiro(i, monitor);
			lista_de_barbeiros.add(barbeiro);
			
		}
		
		
		//cria o barbeiro e o cliente e rodam como threads
		Cliente regata = new Cliente(20, monitor);
		Cliente leo = new Cliente(30, monitor);
		
		Thread t1 = new Thread(lista_de_barbeiros.getFirst());
		Thread t2 = new Thread(regata);
		Thread t3 = new Thread(leo);
		t1.start();
		t2.start();
		t3.start();
		
		
		
		
		
		
		
		
		
		
		
		
//		System.out.println("roberval: " + roberval.get_barbeiroID() + "\nregata: " + regata.get_clienteID());
//		System.out.println("barbeiros: " + num_barbeiros + "\n" + "cadeiras: " + num_cadeiras + "\n" + "total clientes: " + total_clientes );
	}

}
