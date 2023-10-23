
public class Main {

	public static void main(String[] args) {
		
		GestioneAuto a = new GestioneAuto();
		Auto ab = new Auto();
		
		System.out.println(a);
		
		ab.setId(1);
		ab.setMarca("Marca");
		ab.setModello("Modello");
		ab.setTarga("YYY");
		ab.setPrezzo((float) 33000.99);
		ab.setTipoCarburante("Benzina");
		ab.setAnno(2000);
		a.aggiungiAuto(ab);
		
		System.out.println(a);

	}

}
