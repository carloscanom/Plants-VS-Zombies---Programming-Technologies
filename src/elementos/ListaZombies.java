package elementos;

public class ListaZombies {
	
	private Zombie [] lista;
	private int num;
	private int tam;
	
	public ListaZombies() {
		tam = 10;
		lista = new Zombie [tam];
		num = 0;
	}
	
	
	public void anyadir(Zombie objeto){
		if(num == tam){
			tam *= 2;
			Zombie[] aux = new Zombie[tam];
			for(int i = 0; i < num; i++){
				aux[i] = lista[i];
			}
			lista = new Zombie[tam];
			for (int i = 0; i < num; i++){
				lista[i] = aux[i];
			}
		}
		lista[num] = objeto;
		num++;
	}
	
	public void eliminar(Zombie objeto){
		int i = 0;
		boolean encontrado = false;
		while(i < num && !encontrado){
			if (lista[i] == objeto){
				encontrado = true;
				for(int j = i; j < num - 1; j++){
					lista[j] = lista[j+1];
				}
				num--;
			}
			else i++;
		}
}


	public int getNum() {
		return num;
	}


	public Zombie getZombieInPos(int i){
		return lista[i];
	}


	public void update() {
		for(int i = 0; i < num; i++){
			lista[i].update();
		}		
	}
	
	public String info(){
        String aux="";
        
        for(int i =0; i< num; i++)
        aux += lista[i].info()+"|";
        
        return aux;
    }
	
	

}