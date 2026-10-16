package Taller2_Poo_FelipeGonzalez_JuanAraya;

public class Choripan {
	private String participante;
	private int largo;
	private int ancho;
	private String fecha; // formato YYYY-MM-DD, tenlo en cuenta juan

	public String getParticipante() {
		return participante;
	}

	public int getLargo() {
		return largo;
	}

	public int getAncho() {
		return ancho;
	}

	public String getFecha() {
		return fecha;
	}

	public Choripan(String participante, int largo, int ancho, String fecha) {
		this.participante = participante;
		this.largo = largo;
		this.ancho = ancho;
		this.fecha = fecha;
	}

	public void setParticipante(String participante) {
		this.participante = participante;
	}

	public void setLargo(int largo) {
		this.largo = largo;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	//es para el formato del archivo
	public String toLineaArchivo() {
		return "Choripan;" + participante + ";" + largo + ";" + ancho + ";" + fecha;
	}

	//este es el metodo para calcular kcal
	public double cancularKcalChoripan() {
		return (largo*ancho)/3;
	}
	//toString que se vea bonito
	@Override
	public String toString() {
		return "Choripan | " + participante
				+ " | " + largo + " cm x " + ancho + " cm"
				+ " | " + fecha
				+ " | " + redondear(cancularKcalChoripan()) + " kcal";
	}
	
	//redondeado 
	public String redondear(double v) {
		long r = Math.round(v * 100);
		long ent = r / 100;
		long dec = Math.abs(r % 100);
		if (dec < 10) return ent + ".0" + dec;
		return ent + "." + dec;
	}
}
