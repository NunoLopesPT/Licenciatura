public class Boggle {
	Posicao[][] boggle = new Posicao[4][4];
	LinHashTable<String> hashtable;
	
	public Boggle(char[] b , LinHashTable<String> hashtable){
		int count = 0;
		//char[] b = {'s','e','l','d','o','u','m','o','o','m','e','t','i','n','k','y'};
		//Receber um array de 16 letras e converte-las para uma matriz
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				boggle[i][j] = new Posicao(b[count], i, j, false);
				count++;
			}
		}
	}
	
	ArrayList<Posicao> palavras = new ArrayList<Posicao>(16);
	int index = 0;

	
	public void solve(){
		for(int i = 0; i < 4 ; i++) {
			for(int j = 0; j < 4; j++) {
				
				Posicao x = boggle[i][j];
				x.setRepetido(true);
				
				//ArrayList
				palavras.set(x,	index);
				
				index++;
				
				String palavra = "" + x.getLetra();
				solve(palavra, i, j); //Tentar encontrar uma palavra para cada letra 
				
				x.setRepetido(false);
				index--;
				
			}
		}
	}

	public void solve(String stringpalavra, int coluna, int linha){
			Elemento<String> palavra = hashtable.procurar(stringpalavra); //Procura a palavra na hashtable

			if(palavra == null) {
				return;
			}

			if(!palavra.prefixo && palavra.elemento.length() >= 3) { // Se houver faz o print da mesma

				String solucao = palavra + " ";

				for(int i = 0; i < index ; i++) {
					if(i != 0) {
						solucao += "{>";
					}
					solucao += ("("+palavras.get(i).getLetra()+":("+palavras.get(i).getColuna()+","+palavras.get(i).getLinha()+"))").toUpperCase();
				}

				System.out.println(solucao);
			}
		
		for(int i = -1 ; i <= 1 ; i++) {
			for(int j = -1 ; j <= 1 ; j++) {
				
				int proximacoluna = coluna + i;
				int proximalinha = linha + j;
				
				if(proximacoluna >= 0 && proximacoluna < 4 &&
						proximalinha >= 0 && proximalinha < 4) {
					
					Posicao x = boggle[proximacoluna][proximalinha];
					
					if(!x.isRepetido()) {
						
						x.setRepetido(true);
						palavras.set(x, index);
						index++;	
						
						solve(stringpalavra + x.getLetra(), proximacoluna, proximalinha);
						
						x.setRepetido(false);
						index--;
					}
				}
			}
		}
	}
}
