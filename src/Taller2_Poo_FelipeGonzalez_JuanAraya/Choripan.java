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

}
