import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	
	public Menu() {
		scanUser = new Scanner(System.in);
		g = new GestioneAuto();
	}

	public void MenuPrincipale() {
		boolean m = true;
		do {
			System.out.println("Menu Principale");
			System.out.println("1) Aggiungi Auto");
			System.out.println("2) Modifica Auto");
			System.out.println("3) Elimina Auto");
			System.out.println("4) Visualizza Auto");
			System.out.println("5) Cerca Auto per targa");	
			System.out.println("6) Cerca Auto per anno");		
			System.out.println("7) Cerca Auto per prezzo");
			System.out.println("0) Esci");
			System.out.print("Scegli opzione: ");
			switch (scanUser.nextInt()) {
			case 1:
				aggiungiAuto();
				break;
			case 2:
				modificaAuto();
				break;
			case 3:
				eliminaAuto();
				break;
			case 4:
				visualizzaAuto();
			break;
			case 5:
				cercaAutoTarga();
				break;		
			case 6:
				cercaAutoAnno();
				break;
			case 7:
				cercaAutoPrezzo();
				break;
			case 0:
				m = false;
				break;
			}
		} while (m);

	}

	// funziona aggiunta. 

	private void aggiungiAuto() {

		System.out.println("\nMenu aggiunta\n");
		System.out.println("Inserisci dati nuova auto");
		Auto newA = new Auto();
		System.out.print("Marca: ");
		newA.setMarca(scanUser.next());
		System.out.print("Modello: ");
		newA.setModello(scanUser.next());
		boolean annoC = false;
		while (annoC == false) {
			try {
				System.out.print("Anno: ");
				newA.setAnno(scanUser.nextInt());
				annoC = true;
			} catch (InputMismatchException e) {
				System.out.println("input sbagliato");
				scanUser.next();
			}
		}
		System.out.print("Targa: ");
		newA.setTarga(scanUser.next().toUpperCase());
		boolean prezzoC = false;
		while (prezzoC == false) {
			try {
				System.out.print("Prezzo: ");
				newA.setPrezzo(scanUser.nextFloat());
			} catch (InputMismatchException e) {
				System.out.println("input sbagliato");
				scanUser.next();
			}

		}

		System.out.print("Tipo Carburante: ");
		newA.setTipoCarburante(scanUser.next());
		g.aggiungiAuto(newA);

	}

	private void modificaAuto() {
		System.out.println("Menu modifica");
		System.out.print("Inserisci targa: ");
		this.modificaAutoExpanded(g.cercaAutoPerTarga(scanUser.next()));
	}

	private void modificaAutoExpanded(Auto a) {
		boolean m = true;
		do {
			System.out.println("AUTO TROVATA: " + a);
			System.out.println(
					"1)Modifica Marca\n2)Modifica Modello\n3)Modifica Targa\n4)Modifica Prezzo\n5)Modifica Anno\n6)Modifica TipoCarburante\n0)Torna al menù precedente");

			switch (scanUser.nextInt()) {
			case 1:
				System.out.println("Nuova marca: ");
				a.setMarca(scanUser.next());
				break;
			case 2:
				System.out.println("Nuova modello: ");
				a.setModello(scanUser.next());
				break;
			case 3:
				System.out.println("Nuova targa: ");
				a.setTarga(scanUser.next());
				break;
			case 4:
				boolean prezzoC = false;
				while (prezzoC == false) {
					try {
						System.out.print("Prezzo: ");
						a.setPrezzo(scanUser.nextFloat());
					} catch (InputMismatchException e) {
						System.out.println("input sbagliato");
						scanUser.next();
					}

				}
				break;
			case 5:
				System.out.println("Nuovo Anno: ");
				boolean annoC = false;
				while (annoC == false) {
					try {
						System.out.print("Anno: ");
						a.setAnno(scanUser.nextInt());
						annoC = true;
					} catch (InputMismatchException e) {
						System.out.println("input sbagliato");
						scanUser.next();
					}
				}
				break;
			case 6:
				System.out.println("Nuovo TipoCarburante: ");
				a.setTipoCarburante(scanUser.next());
			case 0:
				m = false;
			default:
				System.out.println("Scelta non corretta");
			}

			g.modificaAuto(a);
		} while (m);
	}

	// funziona. da gestire mancanza di corrispondenza

	private void eliminaAuto() {
		System.out.println("\nMenu elimina\n");
		System.out.print("Inserisci targa: ");
		a = g.cercaAutoPerTarga(scanUser.next().toUpperCase());
		if (a != null) {
			g.eliminaAuto(a.getTarga());
			a = null;
		} else {
			System.out.println("Targa non trovata");
		}

	}

	// funziona. magari migliorare formattazione

	private void visualizzaAuto() {
		System.out.println("\nLista Auto \n");
		System.out.println(g.toString() + "\n");

	}

	// funziona. anche qui gestire mancanza corrispondenza

	private void cercaAutoTarga() {
		System.out.println("\nMenu ricerca\n");
		System.out.print("Inserisci targa: ");
		a = g.cercaAutoPerTarga(scanUser.next().toUpperCase());
		if (a != null) {
			System.out.println("\n" + a + "\n");
			System.out.println("1) Modifica auto");
			System.out.println("2) Elimina auto");
			System.out.print("Scelgi opzione: ");
			switch (scanUser.nextInt()) {
			case 1:
				modificaAutoExpanded(a);
				break;
			case 2:
				g.eliminaAuto(a.getTarga());
				break;
			default:
				System.out.println("Scelta non corretta");
			}
		} else {
			System.out.println("Targa non trovata");
		}
		a = null;
	}
	
	
	private void cercaAutoAnno() {
		System.out.println("\nMenu ricerca\n");
		System.out.print("Inserisci anno: ");
		System.out.println(g.cercaAutoPerAnno(scanUser.nextInt()));

	}
	
	private void cercaAutoPrezzo() {
		float eSinistro, eDestro;
		System.out.println("\nMenu ricerca\n");
		System.out.print("Inserisci il prezzo di partenza: ");
		eSinistro = scanUser.nextFloat();		
		System.out.print("Inserisci il prezzo massimo: ");
		eDestro = scanUser.nextFloat();
		System.out.println(g.filtraAutoPerPrezzo(eSinistro, eDestro));

	}
	
	private Scanner scanUser;
	private GestioneAuto g;
	private Auto a;
}


