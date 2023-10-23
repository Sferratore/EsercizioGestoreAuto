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

	private void aggiungiAuto() {
		System.out.println("Menu aggiunta");
		System.out.println("Inserisci dati nuova auto");
		Auto newA = new Auto();
		newA.setId(0);
		System.out.print("Marca: ");
		newA.setMarca(scanUser.next());
		System.out.print("Modello: ");
		newA.setModello(scanUser.next());
		System.out.print("Anno: ");
		newA.setAnno(scanUser.nextInt());
		System.out.print("Targa: ");
		newA.setTarga(scanUser.next());
		System.out.print("Prezzo: ");
		newA.setPrezzo(scanUser.nextFloat());
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
		
		System.out.println("AUTO TROVATA: " + a);
		System.out.println("1)Modifica Marca\n2)Modifica Modello\n3)Modifica Targa\n4)Modifica Prezzo\n5)Modifica Anno\n6)Modifica TipoCarburante\n0)Torna al menù precedente");
		
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
			System.out.println("Nuovo prezzo: ");
			a.setPrezzo(scanUser.nextFloat());
			break;
		case 5:
			System.out.println("Nuovo Anno: ");
			a.setAnno(scanUser.nextInt());
			break;
		case 6:
			System.out.println("Nuovo TipoCarburante: ");
			a.setTipoCarburante(scanUser.next());
		case 0:
			this.MenuPrincipale();
		}
		
		g.modificaAuto(a);
	}

	private void eliminaAuto() {
		System.out.println("Menu elimina");
		System.out.print("Inserisci targa: ");
		g.eliminaAuto(scanUser.next());

	}

	private void visualizzaAuto() {
		

	}

	private void cercaAutoTarga() {
		System.out.println("Menu ricerca");
		System.out.print("Inserisci targa: ");
		a = g.cercaAutoPerTarga(scanUser.next());
		
	}

}

/*
 * Aggiungi auto Modifica auto Elimina auto Visualizza tutte le auto Cerca auto
 * per targa Esci
 */
