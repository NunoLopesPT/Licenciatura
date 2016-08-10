package arvorebinaria;

public class NoABP<E> {
	private E elemento;
	
	private NoABP<E> esq;
	private NoABP<E> dir;
	
	public NoABP(E elemento, NoABP<E> esq, NoABP<E> dir){
		this.elemento = elemento;
		this.dir = dir;
		this.esq = esq;
	}

	public E getElemento() {
		return elemento;
	}

	public void setElemento(E elemento) {
		this.elemento = elemento;
	}

	public NoABP<E> getEsq() {
		return esq;
	}

	public void setEsq(NoABP<E> esq) {
		this.esq = esq;
	}

	public NoABP<E> getDir() {
		return dir;
	}

	public void setDir(NoABP<E> dir) {
		this.dir = dir;
	}
	
	
}
