

public class Elemento<T> {
	
	public T elemento;
	public boolean ativo;
	public boolean prefixo = true;
	
	public Elemento(T elemento, boolean prefixo){
		this.elemento = elemento;
		ativo = true;

		if(!prefixo && this.prefixo) {
			this.prefixo = prefixo;
		}
	}
	
	public String toString(){
		return "" + elemento;
	}

}
