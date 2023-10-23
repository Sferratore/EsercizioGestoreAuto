import java.util.Scanner;

public class Menu {

	Scanner scanUser;

	GestioneAuto g;

	Auto a;

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

			System.out.println("6) Esci");

			System.out.print("Scelgi opzione: ");

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

		System.out.print("Anno: ");

		newA.setAnno(scanUser.nextInt());

		System.out.print("Targa: ");

		newA.setTarga(scanUser.next().toUpperCase());

		System.out.print("Prezzo: ");

		newA.setPrezzo(scanUser.nextFloat());

		System.out.print("Tipo Carburante: ");

		newA.setTipoCarburante(scanUser.next());

		g.aggiungiAuto(newA);

	}

	private void modificaAuto() {

		System.out.println("\nMenu modifica\n");

		System.out.print("Inserisci targa: ");

		g.modificaAuto(g.cercaAutoPerTarga(scanUser.next().toUpperCase()));

	}

	// funziona. da gestire mancanza di corrispondenza

	private void eliminaAuto() {

		System.out.println("\nMenu elimina\n");

		System.out.print("Inserisci targa: ");

		g.eliminaAuto(scanUser.next().toUpperCase());

		// gestire mancanza di corrispondenza

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

				g.modificaAuto(g.cercaAutoPerTarga(scanUser.next().toUpperCase()));

				break;

			case 2:

				g.eliminaAuto(a.getTarga());

				break;

			}

		} else {

			System.out.println("Auto non trovata");

		}

		a = null;

	}

}
