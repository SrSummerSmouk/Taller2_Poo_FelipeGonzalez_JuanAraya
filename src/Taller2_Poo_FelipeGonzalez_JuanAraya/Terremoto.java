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
	
	public void setParticipanteString(String participanteString) {
		this.participanteString = participanteString;
	}

	public void setLitros(double litros) {
		this.litros = litros;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String toLineaArchivo() {
		return "Terremoto;" + participanteString + ";" + litrosCorto() + ";" + fecha;
	}
	
	private String litrosCorto() {
		if (litros == Math.floor(litros)) return "" + (long) litros;
		return "" + litros;
	}
	
	@Override
	public String toString() {
		return "Terremoto | " + participanteString
				+ " | " + litrosCorto() + " L"
				+ " | " + fecha
				+ " | " + redondear(calcularKcalTerremoto()) + " kcal";
	}
	S
	public String redondear(double v) {
		long r = Math.round(v * 100);
		long ent = r / 100;
		long dec = Math.abs(r % 100);
		if (dec < 10) return ent + ".0" + dec;
		return ent + "." + dec;
	}


	public double calcularKcalTerremoto() {
		return (litros*10)/3;
	}
}
