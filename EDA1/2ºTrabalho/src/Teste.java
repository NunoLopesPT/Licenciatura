import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Teste {
	public static void main(String[] args) {
		String file = "dicionario.txt";
		String line;
		LinHashTable<String> a = new LinHashTable<String>(1000000);
		char[] b1 = {'s','e','l','d','o','u','m','o','o','m','e','t','i','n','k','y'};
		Boggle b = new Boggle(b1,a);

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			while ((line = br.readLine()) != null) {
				for (int i = 1; i <= line.length() ; i++) {
					
					boolean c = (i != line.length());
					a.insere(line.substring(0, i), c);
				}
			}

			br.close();
		}
		catch (IOException e) { 
			System.out.println("Ups");
		}

		b.hashtable = a;
		b.solve();
	}

}
