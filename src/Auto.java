
public class Auto {

	public Auto() {
	}

	public Auto(int id, String marca, String modello, int anno, String targa, float prezzo, String tipoCarburante) {
		this.id = id;
		this.marca = marca;
		this.modello = modello;
		this.anno = anno;
		this.targa = targa;
		this.prezzo = prezzo;
		this.tipoCarburante = tipoCarburante;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public String getTipoCarburante() {
		return tipoCarburante;
	}

	public void setTipoCarburante(String tipoCarburante) {
		this.tipoCarburante = tipoCarburante;
	}

	@Override
	public String toString() {
		return "Auto [id=" + id + ", marca=" + marca + ", modello=" + modello + ", anno=" + anno + ", targa=" + targa
				+ ", prezzo=" + prezzo + ", tipoCarburante=" + tipoCarburante + "]";
	}

	private int id;
	private String marca;
	private String modello;
	private int anno;
	private String targa;
	private float prezzo;
	private String tipoCarburante;

}

//Attributi: id, marca, modello, anno, targa, prezzo, tipoCarburante
//Metodi: getter, setter, toString(), costruttori