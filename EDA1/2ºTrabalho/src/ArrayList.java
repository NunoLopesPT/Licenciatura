
public class ArrayList<E> {
	E[] array;
	private int tamanho;
	
	@SuppressWarnings("unchecked")
	public ArrayList(int tamanho){
		if(tamanho < 0) {
			throw new IllegalArgumentException();
		}

		this.tamanho = tamanho;
		array = (E[]) new Object[tamanho];
	}
	
	public int size(){
		return tamanho;
	}
	
	public void clear(){
		for(int i = 0; i < tamanho; i++) {
			array[i] = null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void ExpandSize(int size){
		if(size > tamanho) {
			E[] arrayt = array;
			array = (E[]) new Object[size];

			for(int i = 0; i < tamanho; i++) {
				array[i] = arrayt[i];
			}
		}

		tamanho += size-tamanho;
	}
	
	public void insert(E x){
		insert(x, tamanho);
		return;
	}
	
	public void insert(E x, int index){
		if(index > tamanho || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		ExpandSize(tamanho+1);

		for(int i = index; i > index; i--) {
			array[i] = array[i-1];
		}

		array[index] = x;
	}
	
	
	public boolean contains(E x){
		for(int i = 0; i < tamanho; i++) {
			if(x.equals(array[i])) {
				return true;
			}
		}

		return false;
	}
	public int procurar(E x){
		for (int i = 0; i < tamanho; i++) {
			if (x.equals(array[i])) {
				return i;
			}
		}

		return -1;
	}
	
	
	public void set(E x, int index){
		array[index] = x;
	}
	
	public E get(int index){
		return array[index];
	}
	
	public String toString(){
		String a = "[";

		for(int i = 0; i < tamanho ; i++) {
			a += array[i] + ", ";
		}

		return a.substring(0, a.length()-2) + "]";
	}
	
}
