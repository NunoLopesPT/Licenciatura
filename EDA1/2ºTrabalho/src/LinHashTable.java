public class LinHashTable<T> extends HashTable<T>{
	public LinHashTable() {
		super();
	}
	
	public LinHashTable(int n) {
		super(n);
	}

	private static final long FNV_64_INIT = 0xcbf29ce484222325L;
	private static final long FNV_64_PRIME = 0x100000001b3L;
	
	protected int SDBMHash(T str) {	
		long hash = FNV_64_INIT;
		final int len = ((String)str).length();

		for (int i = 0; i < len; i++) {
			hash ^= ((String)str).charAt(i);
			hash *= FNV_64_PRIME;
		}
	  
		if (hash<0) {
			hash = -hash;
		}

		return (int)(hash % TabelaElementos.length);
	}
	
	public int procPos(T x) {
		int colisoes = 0;
		int posActual = SDBMHash(x);

		while (TabelaElementos[posActual] != null && !TabelaElementos[posActual].elemento.equals(x)) {
			colisoes += 1;
			posActual += colisoes; // f(i) = i

			if (posActual >= TabelaElementos.length) {
				posActual = posActual % TabelaElementos.length;
			}
		}
		
		return posActual % TabelaElementos.length;
	}
}
