public class Posicao {
	private char letra;
	private int coluna;
	private int linha;
	private boolean repetido;
	
	public Posicao(char letra, int coluna, int linha, boolean repetido) {
		this.letra = letra;
		this.coluna = coluna;
		this.linha = linha;
		this.repetido = repetido;
	}
	
	public String toString() {
		return "" + letra;
	}

	public char getLetra() {
		return letra;
	}

	public void setLetra(char letra) {
		this.letra = letra;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha; 
	}

	public boolean isRepetido() {
		return repetido;
	}

	public void setRepetido(boolean repetido) {
		this.repetido = repetido;
	}
	

}
