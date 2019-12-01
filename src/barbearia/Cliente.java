package barbearia;

import java.util.Random;

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
		for(;;) {			
			try {
				if(b.cortaCabelo(this))
					cabelo_cortado = false;
				
				Thread.sleep(new Random().nextInt(200) + 300); //espera para tentar cortar novamente
			}catch(InterruptedException e) {System.out.println("Erro no cliente");} 
		}
	}

	

}
