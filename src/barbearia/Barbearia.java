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
	
	
	public Barbearia(int n, int m, int total_clientes) {
		this.total_barbeiros = n;
		this.total_cadeiras = m;
		this.total_clientes = total_clientes;
		
		this.cadeiras_disponiveis = m + n; //considera tantos as cadeiras de espera quanto as de barbeiro 
		this.barbeiros_disponiveis = n;
		this.clientes_esperando = new LinkedList<Cliente>();
	}
	
	
	
	public synchronized boolean cortaCabelo(Cliente c) throws InterruptedException { 
		if(cadeiras_disponiveis == 0) {
			System.out.println("Cliente " + c.get_clienteID() + " tentou entrar na barbearia, mas está lotada... indo dar uma voltinha");
			return false;
		}
		
		//adiciona o cliente na fila pra corte
		this.clientes_esperando.add(c); 
		cadeiras_disponiveis--;
		
		System.out.println("Cliente " + c.get_clienteID() + " esperando corte...");
		
		
		notifyAll(); //acorda barbeiros
		
		
		//bloqueia o cliente q esta na barbearia mas nao teve o cabelo cortado ainda
		while(!c.cabelo_cortado) 
			wait(); 
		

		cadeiras_disponiveis++;
		
		return true;
	}

	
	
	// Seleciona o próximo cliente (dentro desta chamada o
	// barbeiro pode dormir esperando um cliente).
	public Cliente proximoCliente() throws InterruptedException {
		if(!clientes_esperando.isEmpty()) {
			return clientes_esperando.removeFirst();
		}
		return null; 
	}

	
	
	// O barbeiro acorda o cliente que está na sua cadeira
	// e espera que ele saia da barbearia
	// (tome cuidado para acordar o cliente certo).
	public synchronized void corteTerminado(Cliente c) throws InterruptedException { 
		c.cabelo_cortado = true;
		notifyAll(); 
		System.out.println("Cliente " + c.get_clienteID() + " terminou o corte.. saindo da barbearia!");
	}

}
