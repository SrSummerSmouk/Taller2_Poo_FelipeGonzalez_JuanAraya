package Taller2_Poo_FelipeGonzalez_JuanAraya;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Integrante 1: Juan Araya, RUT: 21.566.260-8, Carrera: ITI
//Integrante 2: Felipe Gonzalez, RUT: 21.776.516-1, carrera: ITI
public class Main {
	static Persona[] personas = new Persona[200];
	static int cantPersonas = 0;
	static Choripan[] choripanesHistorial = new Choripan[200];
	static int cantChori = 0;
	static Terremoto[] terremotosHistorial = new Terremoto[200];
	static int cantTerre = 0;

	public static void main(String[] args) {
		
	}

	private static void lectorConsumo() {
		try {

			Scanner scanner = new Scanner(new File("Consumido.txt"));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] partes = line.split(",");
				String consumo = partes[0];
				if (consumo.equalsIgnoreCase("Terremoto")) {
					String participante = partes[1];
					double litros = Double.parseDouble(partes[2]);
					String fecha = partes[3];

					terremotosHistorial[cantTerre] = new Terremoto(participante, litros, fecha);
					cantTerre++;
				}
				if (consumo.equalsIgnoreCase("Choripan")) {
					String participante = partes[1];
					int largo = Integer.parseInt(partes[2]);
					int ancho = Integer.parseInt(partes[3]);
					String fecha = partes[4];

					choripanesHistorial[cantChori] = new Choripan(participante, largo, ancho, fecha);
					cantChori++;

				}
			}
			System.out.println("arch Consumo.txt cargado.");
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("arch Consumido.txt: " + e);
		}

	}

	private static void lectorParticipantes() {
		try {
			Scanner scanner = new Scanner(new File("Participantes.txt"));
			while (scanner.hasNextLine()) {
				String nombre = scanner.nextLine().trim();
				personas[cantPersonas] = new Persona(nombre);
				cantPersonas++;
			}
			scanner.close();
			System.out.println("arch Participantes cargado.");
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("arch Participantes.txt: " + e);

		}
	}
	
	private static void menu() {
		
	}
}
