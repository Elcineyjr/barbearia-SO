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
		for(;;) {
			try {
				Cliente c;
				
				//bloco sincronizado para q apenas um barbeiro acesse por vez
				synchronized (b) { 
					boolean dormiu = false; //evita prints descenessarios sobre barbeiro dormir/acordar
					while((c = b.proximoCliente()) == null) {
						//barbeiro pode ser acordado varias vezes pelo notifyAll e logo depois ir dormir pois nao devia ter sido acordado,
						//porem só vai avisar q ta dormindo na primeira vez
						if(!dormiu) System.out.println("Barbeiro " + this.id + " indo dormir um pouco... não há clientes na barbearia...");
						
						b.wait();
						dormiu = true; 
					}
					
					//caso barbeiro nao chegou a dormir, nao precisa printar q ele acordou
					if(dormiu) System.out.println("Barbeiro " + this.id + " acordou! Começando os trabalhos!");
					dormiu = false;
					
					System.out.println("Cliente " + c.get_clienteID() + " cortando cabelo com Barbeiro " + this.id);
				}
				
				
				Thread.sleep(4000); //tempo de realizaçao do corte //TODO variar tempo
				
				b.corteTerminado(c);
			}catch(InterruptedException e) {System.out.println("Erro no barbeiro");}
		}
	}
	
}
