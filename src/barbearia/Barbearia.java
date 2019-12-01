package barbearia;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import com.sun.swing.internal.plaf.synth.resources.synth;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Barbearia { 
	int total_barbeiros;
	int total_cadeiras;
	int total_clientes;
	int cadeiras_disponiveis;
	int barbeiros_disponiveis;
	LinkedList<Cliente> clientes_esperando;
	LinkedList<Barbeiro> barbeiros;
	
//	TODO funçoes do monitor (synchronized)
//	public boolean cortaCabelo(Cliente c) {}
//	public Cliente proximoCliente() {}
//	public void corteTerminado(Cliente c){}
	
	public Barbearia(int n, int m, int total_clientes) {
		this.total_barbeiros = n;
		this.total_cadeiras = m;
		this.total_clientes = total_clientes;
		
		this.cadeiras_disponiveis = m + n; //considera tantos as cadeiras de espera quanto as de barbeiro 
		this.barbeiros_disponiveis = n;
		this.clientes_esperando = new LinkedList<Cliente>();
		this.barbeiros = new LinkedList<Barbeiro>();
	}
	
	public void adicionaBarbeiros(LinkedList<Barbeiro> lista) {
		this.barbeiros = (LinkedList<Barbeiro>) lista.clone();
	}
	
	public synchronized boolean cortaCabelo(Cliente c) throws InterruptedException { 
		//TODO pensar se vale a pena transformar tudo em pessoa e ai vai ser uma fila só
		
		if(cadeiras_disponiveis > 0) { //TODO verificar caso o cliente seja o primeiro a sentar
			
			
			this.clientes_esperando.add(c); //adiciona o cliente na fila pra corte
			cadeiras_disponiveis--;
			
			//TODO acordar barbeiro
			notifyAll();
			
			System.out.println(c.get_clienteID() + " sentou!");
			
			//bloqueia o cliente q conseguiu entrar na barbearia
			while(!c.cabelo_cortado) {
				wait(); //this.wait(); 					
			}
				
			
			System.out.println(c.get_clienteID() + " saiu!");
			cadeiras_disponiveis++;
			return true;
		}														
		System.out.println(c.get_clienteID() + " NAO sentou!");
		return false;
	}

	public synchronized void acordaBarbeiros() {
		barbeiros.notifyAll();
	}
	
	
	// Seleciona o próximo cliente (dentro desta chamada o
	// barbeiro pode dormir esperando um cliente).
	public synchronized Cliente proximoCliente() throws InterruptedException {
		Cliente primeiro = null;
		
		while(primeiro == null) {			
			try{
				primeiro = clientes_esperando.removeFirst();
			}catch(NoSuchElementException e) {primeiro = null;}
			
			
			if(primeiro == null) { //caso nao tenha cliente ainda o barbeiro pode domir
				wait();
				continue;
			}
		}
		
		
		return primeiro; 
	}

	
	
	// O barbeiro acorda o cliente que está na sua cadeira
	// e espera que ele saia da barbearia
	// (tome cuidado para acordar o cliente certo).
	public synchronized void corteTerminado(Cliente c) throws InterruptedException { 
		System.out.println(c.get_clienteID() + " cortando cabelo...");
		System.out.println(c.get_clienteID() + " cabelo cortado!!!");
		
		c.cabelo_cortado = true;
		notifyAll(); //acorda todos os clientes
	}

}
