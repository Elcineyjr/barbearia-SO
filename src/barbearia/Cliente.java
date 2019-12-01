package barbearia;

import javax.activity.InvalidActivityException;

public class Cliente extends Pessoa implements Runnable{
	Barbearia b;
	public boolean cabelo_cortado = false;
	
	public Cliente(int id, Barbearia b) {
		super(id);
		this.b = b;
	}
	
	public int get_clienteID() {
		return super.getId();
	}

	@Override
	public void run() {
		for(int i = 0; i < 3; i++) {			
			try {
				
				System.out.println(this.id + "tentando entrar na barbearia");
				b.cortaCabelo(this);
				
				cabelo_cortado = false;
				
				Thread.sleep(5000); //espera para tentar cortar novamente
			}catch(InterruptedException e) {System.out.println("Erro no cliente");} 
		}
	}

	

}
