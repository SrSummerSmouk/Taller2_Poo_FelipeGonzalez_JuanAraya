package Taller2_Poo_FelipeGonzalez_JuanAraya;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		lectorParticipantes();
		lectorConsumo();
		menu();
	}

	private static void lectorConsumo() {
		try {

			Scanner scanner = new Scanner(new File("Consumido.txt"));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] partes = line.split(";");
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
	
	private static int leerEntero(String prompt, int min, int max) {
		while (true) {
			System.out.print(prompt);
			String linea = sc.nextLine().trim();
			if (linea.isEmpty()) {
				System.out.println(">> Error: no ingreso ninguna opcion. Intente nuevamente.");
				continue;
			}
			try {
				int v = Integer.parseInt(linea);
				if (v < min || v > max) {
					System.out.println("Error: opcion fuera de rango. Intente nuevamente.");
					continue;
				}
				return v;
			} catch (NumberFormatException e) {
				System.out.println("Error: la opcion debe ser un numero entre " + min + " y " + max + ".");
			}
		}
	}

	
	
	//AQUI MENU, JUAN
	private static void menu() {
		
		int opcion;
		do {
			System.out.println();
			System.out.println("===========================================");
			System.out.println("LA FONDA PAPU");
			System.out.println("Registros cargados: " + (cantChori + cantTerre));
			System.out.println("1) Ver registros");
			System.out.println("2) Actualizar registros");
			System.out.println("3) Calcular metricas");
			System.out.println("4) Salir");
			opcion = leerEntero("Seleccione una opcion: ", 1, 4);

			switch (opcion) {
				case 1: menuVerRegistros(); break;
				case 2: menuActualizar(); break;
				case 3: menuMetricas(); break;
				case 4: System.out.println("Hasta luego..."); break;
			}
		} while (opcion != 4);
		
	}

	// juan igual echale un vistazo, esto es mas o menos un copia y pega de mi
	// taller 1 asique capaz se me olvido cambiar algo
	private static void sobreEscribirConsumo() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("Consumido.txt"))) {

			// este de aqui es para escribir chiropanes btw
			for (int i = 0; i < cantChori; i++) {
				if (choripanesHistorial[i] != null) {
					String line = "Choripan," + choripanesHistorial[i].getParticipante() + ","
							+ choripanesHistorial[i].getLargo() + "," + choripanesHistorial[i].getAncho() + ","
							+ choripanesHistorial[i].getFecha();
					bw.write(line);
					bw.newLine();
				}
			}
			// y este para terremotos
			for (int i = 0; i < cantTerre; i++) {
				String line = "Terremoto," + terremotosHistorial[i].getParticipanteString() + ","
						+ terremotosHistorial[i].getLitros() + "," + terremotosHistorial[i].getFecha();
				bw.write(line);
				bw.newLine();
			}
			System.out.println("Archivo Consumido.txt actualizado (no se rompio)\n");
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("[ERROR] NO SE PUEDE SOBREESCRIBIR CONSUMIDO.TXT\n");
		}
	}
}
