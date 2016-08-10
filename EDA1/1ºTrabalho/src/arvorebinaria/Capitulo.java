package arvorebinaria;

public class Capitulo implements Comparable<Capitulo>{
	private String nome;
	private int inicio;
	private int fim;
	private ArvBin<Integer> ocorrencias = new ArvBin<Integer>();
	private ArvBin<Capitulo> subcapitulos;
	
	public Capitulo(String nome) {
		this.nome = nome;
	}

	public int compareTo(Capitulo c) {
		return nome.toLowerCase().compareTo(c.nome.toLowerCase());
	}
	
	public String toString() {
		String a = "";
		
        a += nome + ": "; // "Serie: "
    	
        if (inicio == fim) {
    		if (inicio != 0) {
    			a += inicio ; //"Serie: 2"
    		}
    	}

    	else {
    		a += inicio + "-" + fim + " "; //"Serie: 2-4"
    	}

    	if (ocorrencias != null) { // Se tiver ocorrencias
            a += printOcorrencias();//"Serie: 4,5,6,7"
    	}

		if (subcapitulos != null) {
			a += printSubcapitulos(subcapitulos.raiz);
		}
		
		return a;
	}
	
	private String printSubcapitulos(NoABP<Capitulo> no) {
		if (no.getElemento() == null) { 
    		return "";
    	}
    	
        String a = "";
    	
        a += printSubcapitulos(no.getEsq());
    	a += "\n";
    	a +=  "         " + no.getElemento().nome + ": ";
    	
        if (no.getElemento().inicio == no.getElemento().fim) {
    		if (no.getElemento().inicio != 0) {
    			a += no.getElemento().inicio ;	
    		}
    	}
    	else {
    		a += no.getElemento().inicio + "-" + fim + " ";
    	}
    	
        if (no.getElemento().ocorrencias != null) {
	    	a += no.getElemento().printOcorrencias();
    	}
    	
        a += printSubcapitulos(no.getDir());
    	
        return a;
	}
	
	public String printOcorrencias() {
    	return printOcorrencias(ocorrencias.raiz);
    }
     
    private String printOcorrencias(NoABP<Integer> no) {
     	String a = "";

     	if (no.getElemento() == null) { 
     		return "";
     	}
   	    
        if (no.getEsq().getElemento() != null) {
     		a += printOcorrencias(no.getEsq());
     	}
        
        if (!ocorrencias.raiz.getElemento().equals(no.getElemento())) {
     		a += ", ";
     	}
     	
        a += no.getElemento();
     	
     	if (no.getDir().getElemento() != null) {
     		a += printOcorrencias(no.getDir());
     	}
     	
     	return a;
    }

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public void setFim(int fim) {
		this.fim = fim;
	}

	public ArvBin<Integer> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(ArvBin<Integer> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

	public ArvBin<Capitulo> getSubcapitulos() {
		return subcapitulos;
	}

	public void setSubcapitulos(ArvBin<Capitulo> subcapitulos) {
		this.subcapitulos = subcapitulos;
	}
}
