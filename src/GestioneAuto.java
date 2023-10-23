import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GestioneAuto {

	public GestioneAuto() {
		this.listaAuto = new ArrayList<Auto>();
		this.syncFromFile();
	}

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
		
		this.listaAuto.add(a);
		this.syncToFile();
	}

	public void modificaAuto(String targaAuto) {

		Auto autoInModifica = cercaAutoPerTarga(targaAuto);
		// Espandi menù
		// Continua operazioni
	}

	public void eliminaAuto(String targaAuto) {

		for (int i = 0; i < this.listaAuto.size(); i++) {
			if (this.listaAuto.get(i).getTarga().equals(targaAuto)) {
				this.listaAuto.remove(i);
			}
		}

		this.syncToFile();

	}

	public Auto cercaAutoPerTarga(String targaAuto) {

		for (int i = 0; i < listaAuto.size(); i++) {
			if (listaAuto.get(i).getTarga().equals(targaAuto)) {
				return listaAuto.get(i);
			}
		}

		return null;
	}

	private void syncFromFile() {

		List<String> lines;

		try {
			this.listaAuto = new ArrayList<Auto>();
			lines = Files.readAllLines(Paths.get("auto.txt"));
			Auto autoDaSincronizzare = null;

			for (String line : lines) {
				if (line.startsWith("Id: ")) {
					autoDaSincronizzare = new Auto();
					autoDaSincronizzare.setId(Integer.parseInt(line.split(": ")[1]));
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
				} else if (line.startsWith("---")) {
					this.listaAuto.add(autoDaSincronizzare);
				}
			}

			// Aggiungi l'ultima auto
			if (autoDaSincronizzare != null && !this.listaAuto.contains(autoDaSincronizzare)) {
				this.listaAuto.add(autoDaSincronizzare);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void syncToFile() {

		try (FileWriter file = new FileWriter("auto.txt")) {
			
			file.flush(); //Pulisci il file
			
			for (int i = 0; i < this.listaAuto.size(); i++) { // Memorizza tutte le auto
				System.out.println("Registrando: " + this.listaAuto.get(i));
				this.registerAuto(this.listaAuto.get(i));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private boolean registerAuto(Auto a) {

		try (FileWriter file = new FileWriter("auto.txt", true)) {

			// Aggiungi l'auto
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
