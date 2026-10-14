package Taller2_Poo_FelipeGonzalez_JuanAraya;

public class Terremoto {
	private String participanteString;
	private double litros;
	private String fecha; // formato YYYY-MM-DD, tenlo en cuenta juan

	public String getParticipanteString() {
		return participanteString;
	}

	public double getLitros() {
		return litros;
	}

	public String getFecha() {
		return fecha;
	}

	public Terremoto(String participanteString, double litros, String fecha) {
		this.participanteString = participanteString;
		this.litros = litros;
		this.fecha = fecha;
	}
}
