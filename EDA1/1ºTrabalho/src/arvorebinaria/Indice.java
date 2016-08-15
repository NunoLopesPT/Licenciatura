package arvorebinaria;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Indice{
	public static void main(String[] args) {
		String line;
		String file="file.txt";
		String token;
		ArvBin<Capitulo> ABP = new ArvBin<Capitulo>();
		Capitulo capitulo;
		Capitulo subCapitulo;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				if (line.substring(0,5).equals("IX: {")) {
					//Tira o "IX: {"
					line = line.substring(5);
					
					//Se tiver subcapitulos
					if (line.contains("!")) {
						StringTokenizer b = new StringTokenizer(line, "!", false);
						token = b.nextToken();
						
						//Adiciona capitulo à ABP
						capitulo = new Capitulo(token.substring(0, token.length()-1));
						if (ABP.contains(capitulo)) {
							capitulo = ABP.find(capitulo);
						}
						ABP.insere(capitulo);
						
						//Remove os sinais que nao interessam e em principio
						//Tem que dar 3 ou 4 tokens:
						//Subcapitulo
						//Sinal "(" ou ")"
						//Espaço " "
						//Ocorrencia
						token = b.nextToken();
						b = new StringTokenizer(token, "|{}" , false);
						
						//Adiciona SubCapitulo
						token = b.nextToken();
						subCapitulo = new Capitulo(token.substring(1));
						
						if (capitulo.getSubcapitulos() == null) {
							capitulo.setSubcapitulos(new ArvBin<Capitulo>());// = new ArvBin<Capitulo>(); 
						}
						
						if (capitulo.getSubcapitulos().contains(subCapitulo)) {
							subCapitulo = capitulo.getSubcapitulos().find(subCapitulo);
						}
						capitulo.getSubcapitulos().insere(subCapitulo);
						
						//Adiciona Inicio ou fim saltando o token com o espaço " "
						token = b.nextToken();
						//Inicio
						if (token.equals("(")) {
							b.nextToken();
							capitulo.getSubcapitulos().find(subCapitulo).setInicio(Integer.parseInt(b.nextToken()));
							}
						//Fim
						else if (token.equals(")")) {
							b.nextToken();
							capitulo.getSubcapitulos().find(subCapitulo).setFim(Integer.parseInt(b.nextToken()));
							}
						//Ocorrencia
						else {
							token = b.nextToken();
							if (capitulo.getSubcapitulos().find(subCapitulo).getOcorrencias() == null) {
								capitulo.getSubcapitulos().find(subCapitulo).setOcorrencias( new ArvBin<Integer>());
							}
							capitulo.getSubcapitulos().find(subCapitulo).getOcorrencias().insere(Integer.parseInt(token));
						}
						capitulo = null;
					}
					else {
						//Se nao tiver subcapitulos, passa diretamente 
						//para a parte onde se separam os sinais que nao interessa
						StringTokenizer b = new StringTokenizer(line, "|{}" , false);
						token = b.nextToken();
						
						if (token.charAt(token.length()-1) == ' ') {
							token = token.substring(0, token.length()-1);
						}
						//Adiciona Capitulo
						capitulo = new Capitulo(token.substring(0, token.length()));
						if (ABP.contains(capitulo)) {
							capitulo = ABP.find(capitulo);
						}

						ABP.insere(capitulo);
						
						//Adiciona Inicio ou fim saltando o token com o espaço " "
						token = b.nextToken();
						//Inicio
						if (token.equals("(")) {
							b.nextToken();
							ABP.find(capitulo).setInicio(Integer.parseInt(b.nextToken()));
						}
						//Fim
						else if (token.equals(")")) {
							b.nextToken();
							ABP.find(capitulo).setFim (Integer.parseInt(b.nextToken()));
						}
						//Ocorrencias
						else {
							token = b.nextToken();
							if (ABP.find(capitulo).getOcorrencias() == null) {
								ABP.find(capitulo).setOcorrencias(new ArvBin<Integer>());
							}
							ABP.find(capitulo).getOcorrencias().insere(Integer.parseInt(token));
						}
					}
				}
			}
		br.close();
		}
			
		catch(IOException e) { 
			System.out.println("Ups");
		}

		//Print da Arvore
		System.out.println(ABP.printEmOrdem());
	}
}
