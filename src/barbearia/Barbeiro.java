package barbearia;

public class Barbeiro extends Pessoa implements Runnable{
	Barbearia b;
	
	public Barbeiro(int id, Barbearia b) {
		super(id);
		this.b = b;
	}
	
	public int get_barbeiroID() {
		return super.getId();
	}

	@Override
	public void run() {
		for(int i = 0; i < 3; i++) {
			try {
				Cliente c = b.proximoCliente();
				Thread.sleep(2000); //tempo de realizaçao do corte
				b.corteTerminado(c);
			}catch(InterruptedException e) {System.out.println("Erro no barbeiro");}
		}
	}
	
}
