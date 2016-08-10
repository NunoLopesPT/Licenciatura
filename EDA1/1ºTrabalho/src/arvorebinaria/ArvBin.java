package arvorebinaria;

public class ArvBin<E extends Comparable<? super E>> {
	NoABP<E> raiz = new NoABP<E>(null,null,null);
	
	public boolean isEmpty() {
		return raiz.getElemento() == null;
	}

	public boolean contains(E x) {
		return contains(x, raiz);
	}
	
	private boolean contains(E x, NoABP<E> no) {
		if (no.getElemento() == null) {
			return false;
		}
		
		if (no.getElemento().compareTo(x) == 0) {
			return true;
		}
		
		if (no.getElemento().compareTo(x) < 0) {
			return contains(x, no.getDir());
		}
		
		if (no.getElemento().compareTo(x) > 0) {
			return contains(x, no.getEsq());
		}
		
		return false;
	}
	
	public E find(E x) {
		return find(x, raiz);
	}

	private E find(E x, NoABP<E> no) {
		if (no.getElemento() == null) {
			return null;
		}
		
		if (no.getElemento().compareTo(x) == 0) {
			return no.getElemento();
		}
		
		if (no.getElemento().compareTo(x) < 0) {
			return find(x, no.getDir());
		}
		
		if (no.getElemento().compareTo(x) > 0) {
			return find(x, no.getEsq());
		}
		
		return no.getElemento();
	}
	
	public E findMin() {
		return findMin(raiz);
	}
	
	private E findMin(NoABP<E> no) {
		if (no.getEsq() == null) {
			return no.getElemento();
		}
		
		if (no.getEsq().getElemento() != null) {
			return findMin(no.getEsq());
		}
		
		return (E) no.getElemento();
	}

	public E findMax() {
		return findMax(raiz);
	}
	
	private E findMax(NoABP<E> no) {
		if (no.getDir() == null) {
			return no.getElemento();
		}
		
		if (no.getDir().getElemento() != null) {
			return findMax(no.getDir());
		}
		
		return (E) no.getElemento();
	}
   
	public void insere(E x) {
			insere(x,raiz);
	}
   
	private NoABP<E> insere(E x, NoABP<E> n) {
		if (n.getElemento() == null) {
			n.setElemento(x);
			n.setDir(new NoABP<E>(null,null,null));
			n.setEsq(new NoABP<E>(null,null,null));
			return n;
		}
		else if ((n.getElemento()).compareTo(x)<0) {
			insere(x, n.getDir());
		}
		else if ((n.getElemento()).compareTo(x)>0) {
			insere(x, n.getEsq());
		}
		else if ((n.getElemento()).compareTo(x)==0) {
			return null;
		}

		return n;
	}
   
   	public void remove(E x) {
		raiz = remove(x,raiz);
	}
   
   	private NoABP<E> remove(E x, NoABP<E> n) {
		if (n == null) {
			return n;
		}
		else if (n.getElemento().compareTo(x)<0) {
			remove(x, n.getDir());
		}
		else if (n.getElemento().compareTo(x)>0) {
			remove(x, n.getEsq());
		}
		else if (n.getEsq().getElemento() != null && n.getDir().getElemento() != null) // Two children
		{
			E min = findMin(n.getDir());
			n.setElemento(min);
			n.setDir(remove(min, n.getDir()));
		}
		else if (n.getEsq().getElemento() == null) {
			n = n.getDir();
			n.setDir(null);
		}
		else {
			n = n.getEsq();
			n.setEsq(null);
		}
		return n;
				
	}
	
	public String printEmOrdem() {
		return printEmOrdem(raiz);
	}
	
	private String printEmOrdem(NoABP<E> no) {
		String a = "";
		
		if (no.getElemento() == null) { 
			return "";
		}
		
		if (no.getEsq() != null) {
			a+= printEmOrdem( no.getEsq() );
		}
		
		a += no.getElemento() + "\n";
		
		if (no.getDir() != null) {
			a+= printEmOrdem( no.getDir() );
		}
		
		return a;
	}

	public String printPosOrdem() {
		return printPosOrdem(raiz);
	}
	
	private String printPosOrdem(NoABP<E> no) {
		String a = "";
		
		if (no.getElemento() == null) { 
			return "";
		}
	  
		if (no.getEsq().getElemento() != null) {
			a+= printPosOrdem( no.getEsq() );
		}
		
		if (no.getDir().getElemento() != null) {
			a+= printPosOrdem( no.getDir() );
		}
		
		a += no.getElemento();
		
		return a;
	}
	
	public String printPreOrdem() {
		return printPreOrdem(raiz);
	}
	
	private String printPreOrdem(NoABP<E> no) {
		String a = "";

		if (no.getElemento() == null) { 
			return "";
		}
	  
		a += no.getElemento();
	  
		if (no.getEsq().getElemento() != null) {
			a+= printPreOrdem( no.getEsq() );
		}

		if (no.getDir().getElemento() != null) {
			a+= printPreOrdem( no.getDir() );
		}

		return a;
	}
}
