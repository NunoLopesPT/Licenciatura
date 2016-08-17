public abstract class HashTable<T> {
	Elemento<T>[] TabelaElementos;
	int ocupados = 0;

	public HashTable() {
		this(11);
	}
	
	@SuppressWarnings("unchecked")
	public HashTable(int n) {
		n = NextPrime(n);
		TabelaElementos = (Elemento<T>[]) new Elemento[n];
	}
	
	public int ocupados() {
		int ocupados = 0;

		for (int i = 0; i<TabelaElementos.length ; i++) {
			if (TabelaElementos[i] != null) {
				ocupados++;
			}
		}
		return ocupados;
	}
	
	public float factorCarga() {
		return TabelaElementos.length - ocupados();
	}
	
	protected abstract int procPos(T s);
	
	@SuppressWarnings("unchecked")
	public void alocarTabela(int dim) {
		TabelaElementos = (Elemento<T>[])new Elemento[dim];
	}
	
	public void tornarVazia() {
		for (int i = 0; i<TabelaElementos.length; i++) {
			if (TabelaElementos[i] != null) {
				TabelaElementos[i] = null;
			}
		}
	}
	//esvazia a tabela em uso;
	
	public Elemento<T> procurar(T x) {
		if (TabelaElementos[procPos(x)] == null) {
			return null;
		}

		return TabelaElementos[procPos(x)];
	}

	//retorna o elemento que esta na tabela , se x não está lá
	//retorna null
	public boolean contains(T x) {
		if (TabelaElementos[procPos(x)] == null) {
			return false;
		}

		return true;
	}
	
	public void remove(T x) {
		TabelaElementos[procPos(x)] = null;
	}
	//remove o elemento da tabela
	
	public void insere (T x, boolean palavra) {
		if (!contains(x)) {
			TabelaElementos[procPos(x)] = new Elemento<T>(x,palavra);
			
			if (ocupados > TabelaElementos.length / 2) {
				rehash();
			}
			
			ocupados++;
		}
	}
	
	public int NextPrime(int n) {
		for (int i=2; i<n; i++) {
			if (n%i==0) {
				return NextPrime(n+1);
			}
		}

		return n;
	}
	
	public long SDBMHash(String str) {
		long hash = 0;

		for (int i = 0; i < str.length(); i++) {
			hash = str.charAt(i) + (hash << 6) + (hash << 16) - hash;
		}

		return hash;
	}
	
	@SuppressWarnings("unchecked")
	public void rehash() {
		System.out.println("ReHashing...");
		Elemento<T> [] TabelaTemporaria = TabelaElementos;
		TabelaElementos = (Elemento<T>[]) new Elemento[NextPrime(TabelaTemporaria.length * 2)];

		for (int i = 0; i < TabelaTemporaria.length; i++) {
			if (TabelaTemporaria[i] != null) {
				insere(TabelaTemporaria[i].elemento, TabelaTemporaria[i].prefixo);
			}
		}
	}
	//faz “rehashing”
	public void print() {
		for (int i = 0; i<TabelaElementos.length ; i++) {
			System.out.println(i +": " + TabelaElementos[i]);
		}
	}
}	