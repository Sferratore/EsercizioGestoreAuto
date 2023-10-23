import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GestioneAuto {

	//Costruttore unico
	public GestioneAuto() {
		this.syncFromFile(); //Sincronizzazione da file con inizializzazione this.listaAuto
	}

	
	//Aggiunge auto sia alla lista attuale che al file
	public void aggiungiAuto(Auto a) {
		
		// Controllando duplicato targa
		for (int i = 0; i < this.listaAuto.size(); i++) {
			if (this.listaAuto.get(i).getTarga().equals(a.getTarga())) {
				System.err.println("Errore di duplicazione targa");
				return;
			}
		}
		
		// Controllando duplicato id
				for (int i = 0; i < this.listaAuto.size(); i++) {
					if (this.listaAuto.get(i).getId() == a.getId()) {
						System.err.println("Errore di duplicazione id");
						return;
					}
				}
		
		this.listaAuto.add(a); //Aggiungo alla lista
		this.syncToFile(); //Scrivo la lista attuale nel file
	}

	
	public void modificaAuto(String targaAuto) {

		Auto autoInModifica = cercaAutoPerTarga(targaAuto);
		// Espandi menù
		// Continua operazioni
	}

	
	//Cancello un'auto
	public void eliminaAuto(String targaAuto) {

		for (int i = 0; i < this.listaAuto.size(); i++) {  //Cerco l'auto per targa
			if (this.listaAuto.get(i).getTarga().equals(targaAuto)) {
				this.listaAuto.remove(i); //Rimuovo l'auto dalla lista se trovata
			}
		}

		this.syncToFile(); //Scrivo la lista attuale nel file

	}

	//Cerco un'auto nella lista e la restituisco
	public Auto cercaAutoPerTarga(String targaAuto) {

		for (int i = 0; i < listaAuto.size(); i++) {
			if (listaAuto.get(i).getTarga().equals(targaAuto)) {
				return listaAuto.get(i);
			}
		}

		return null;
	}

	
	//Tiro su dal file tutti i dati che contiene e li metto in this.listaAuto
	private void syncFromFile() {

		//Contiene le stringhe nel documento. Il documento è formattato in modo che ogni linea contenga un valore e che le auto siano divise da ---
		List<String> lines;

		try {
			this.listaAuto = new ArrayList<Auto>();  //Inizializzo listaAuto
			lines = Files.readAllLines(Paths.get("auto.txt")); //File di input, prendo la lista di stringhe
			Auto autoDaSincronizzare = null; 

			for (String line : lines) {
				if (line.startsWith("Id: ")) { //Se la stringa inizia per Id allora creo una nuova auto
					autoDaSincronizzare = new Auto();
					autoDaSincronizzare.setId(Integer.parseInt(line.split(": ")[1])); //Splitto il tag dal valore e lo setto nell'oggetto
				} else if (line.startsWith("Marca: ")) {
					autoDaSincronizzare.setMarca(line.split(": ")[1]);
				} else if (line.startsWith("Modello: ")) {
					autoDaSincronizzare.setModello(line.split(": ")[1]);
				} else if (line.startsWith("Anno: ")) {
					autoDaSincronizzare.setAnno(Integer.parseInt(line.split(": ")[1]));
				} else if (line.startsWith("Targa: ")) {
					autoDaSincronizzare.setTarga(line.split(": ")[1]);
				} else if (line.startsWith("Prezzo: ")) {
					autoDaSincronizzare.setPrezzo(Float.parseFloat(line.split(": ")[1]));
				} else if (line.startsWith("TipoCarburante: ")) {
					autoDaSincronizzare.setTipoCarburante(line.split(": ")[1]);
				} else if (line.startsWith("---")) { //Aggiungo auto una volta finiti i valori
					this.listaAuto.add(autoDaSincronizzare);
				}
			}

			// Aggiungi l'ultima auto se rimasta appesa perché magari non ha "---" alla fine
			if (autoDaSincronizzare != null && !this.listaAuto.contains(autoDaSincronizzare)) { 
				this.listaAuto.add(autoDaSincronizzare);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	//Butto i dati della listaAuto nel file
	private void syncToFile() {

		try (FileWriter file = new FileWriter("auto.txt")) {
			
			file.flush(); //Pulisce il file
			
			for (int i = 0; i < this.listaAuto.size(); i++) { // Gira tutte le auto
				System.out.println("Registrando: " + this.listaAuto.get(i));
				this.registerAuto(this.listaAuto.get(i)); //Ogni auto viene registrata nel file con questo metodo, separatamente
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	
	//Registro un'auto nel file
	private boolean registerAuto(Auto a) {

		try (FileWriter file = new FileWriter("auto.txt", true)) {  //FileWriter viene creato con "true" per indicare un append ad un file già esistente

			// Aggiungo l'auto
			file.append("Id: " + a.getId() + "\n");
			file.append("Marca: " + a.getMarca() + "\n");
			file.append("Modello: " + a.getModello() + "\n");
			file.append("Anno: " + a.getAnno() + "\n");
			file.append("Targa: " + a.getTarga() + "\n");
			file.append("Prezzo: " + a.getPrezzo() + "\n");
			file.append("TipoCarburante: " + a.getTipoCarburante() + "\n");
			file.append("---\n");

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	

	@Override
	public String toString() {
		return "GestioneAuto [listaAuto=" + listaAuto + "]";
	}



	private ArrayList<Auto> listaAuto;
}
