package elementos;

public class ListaPlantas {
	
	private Plant [] lista;
	private int num;
	private int tam;
	
	public ListaPlantas() {
		tam = 10;
		lista = new Plant [tam];
		num = 0;
	}
	
	public void anyadir(Plant objeto){
		if(num == tam){
			tam *= 2;
			Plant[] aux = new Plant[tam];
			for(int i = 0; i < num; i++){
				aux[i] = lista[i];
			}
			lista = new Plant[tam];
			for (int i = 0; i < num; i++){
				lista[i] = aux[i];
			}
		}
		lista[num] = objeto;
		num++;
	}
	
	public void eliminar(Plant objeto){
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

	public int getNum() {
		return num;
	}
	
	public Plant getPlantAtIndex (int i){
		return lista[i];
	}
	
	
	

}
