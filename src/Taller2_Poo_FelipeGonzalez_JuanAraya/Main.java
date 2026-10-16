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
				System.out.println("[Error]: no ingreso ninguna opcion. Intente nuevamente.");
				continue;
			}
			try {
				int v = Integer.parseInt(linea);
				if (v < min || v > max) {
					System.out.println("[Error]: opcion fuera de rango. Intente nuevamente.");
					continue;
				}
				return v;
			} catch (NumberFormatException e) {
				System.out.println("[Error]: la opcion debe ser un numero entre " + min + " y " + max + ".");
			}
		}
	}

	// AQUI MENU, JUAN
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
			case 1:
				menuVerRegistros();
				break;
			case 2:
				menuActualizar();
				break;
			case 3:
				menuMetricas();
				break;
			case 4:
				System.out.println("Hasta luego...");
				break;
			}
		} while (opcion != 4);

	}

	private static void pausar() {
		System.out.print("Presione ENTER para continuar...");
		sc.nextLine();
	}

	private static void menuVerRegistros() {
		int op;
		do {
			System.out.println();
			System.out.println("VER REGISTROS");
			System.out.println("1) Choripanes");
			System.out.println("2) Terremotos");
			System.out.println("3) Volver al menu principal");
			op = leerEntero("Seleccione una opcion: ", 1, 3);
			if (op == 1)
				mostrarChoripanes();
			else if (op == 2)
				mostrarTerremotos();
		} while (op != 3);
	}

	private static String padR(String s, int ancho) {
		String r = s;
		while (r.length() < ancho)
			r = r + " ";
		return r;
	}

	private static void mostrarChoripanes() {
		System.out.println();
		System.out.println("=============== CHORIPANES ===============");
		System.out.println("#   Consumidor     Largo   Ancho   Fecha         KCal");
		int total = 0;
		for (int i = 0; i < cantChori; i++) {
			Choripan c = choripanesHistorial[i];
			total++;
			System.out.println(padR("" + total, 4) + padR(c.getParticipante(), 15) + padR(c.getLargo() + " cm", 8)
					+ padR(c.getAncho() + " cm", 8) + padR(c.getFecha(), 14) + redondear(c.cancularKcalChoripan()));
		}
		System.out.println("=========================================");
		System.out.println("Total: " + total + " choripanes registrados");
		System.out.println("=========================================");
		pausar();
	}

	private static void mostrarTerremotos() {
		System.out.println();
		System.out.println("=============== TERREMOTOS ===============");
		System.out.println("#   Consumidor     Litros   Fecha         KCal");
		int total = 0;
		double litros = 0;
		for (int i = 0; i < cantTerre; i++) {
			Terremoto t = terremotosHistorial[i];
			total++;
			litros += t.getLitros();
			System.out.println(
					padR("" + total, 4) + padR(t.getParticipanteString(), 15) + padR(redondear(t.getLitros()) + " L", 9)
							+ padR(t.getFecha(), 14) + redondear(t.calcularKcalTerremoto()));
		}
		System.out.println("=========================================");
		System.out.println("Total: " + total + " terremotos registrados (" + redondear(litros) + " L)");
		System.out.println("=========================================");
		pausar();
	}

	private static void menuActualizar() {
		int op;
		do {
			System.out.println();
			System.out.println("========== ACTUALIZAR REGISTROS ==========");
			System.out.println("[1] Agregar");
			System.out.println("[2] Editar");
			System.out.println("[3] Eliminar");
			System.out.println("[4] Volver al menu principal");
			op = leerEntero("Seleccione una opcion: ", 1, 4);
			switch (op) {
			case 1:
				agregar();
				break;
			case 2:
				editar();
				break;
			case 3:
				eliminar();
				break;
			}
		} while (op != 4);
	}

	private static void agregar() {
		System.out.println();
		System.out.println("Tipo de registro:");
		System.out.println("[1] Choripan");
		System.out.println("[2] Terremoto");
		System.out.println("[3] Cancelar");
		int tipo = leerEntero("Seleccione una opcion: ", 1, 3);
		if (tipo == 3)
			return;

		if (participantesVacio()) {
			System.out.println("[Error]: no hay participantes cargados.");
			return;
		}
		if (tipo == 1 && cantChori >= 200) {
			System.out.println("[Error]: capacidad maxima de choripanes alcanzada (200).");
			return;
		}
		if (tipo == 2 && cantTerre >= 200) {
			System.out.println("[Error]: capacidad maxima de terremotos alcanzada (200).");
			return;
		}

		System.out.println();
		System.out.println("Consumidores disponibles:");
		for (int i = 0; i < cantPersonas; i++) {
			System.out.println("[" + (i + 1) + "] " + personas[i].getNombre());
		}
		int idx = leerEntero("Seleccione el consumidor: ", 1, cantPersonas);
		String consumidor = personas[idx - 1].getNombre();

		if (tipo == 1) {
			int largo = (int) leerDouble("Largo del choripan (cm): ");
			int ancho = (int) leerDouble("Ancho del choripan (cm): ");
			String fecha = leerFecha("Fecha (YYYY-MM-DD): ");
			choripanesHistorial[cantChori] = new Choripan(consumidor, largo, ancho, fecha);
			cantChori++;
			sobreEscribirConsumo();
			System.out.println(">> Registro agregado correctamente y guardado en Consumido.txt");
			System.out.println(choripanesHistorial[cantChori - 1].toString());
		} else {
			double litros = leerDouble("Litros del terremoto: ");
			String fecha = leerFecha("Fecha (YYYY-MM-DD): ");
			terremotosHistorial[cantTerre] = new Terremoto(consumidor, litros, fecha);
			cantTerre++;
			sobreEscribirConsumo();
			System.out.println(">> Registro agregado correctamente y guardado en Consumido.txt");
			System.out.println(terremotosHistorial[cantTerre - 1].toString());
		}
	}

	private static void editar() {
		System.out.println();
		System.out.println("Tipo de registro a editar:");
		System.out.println("[1] Choripan");
		System.out.println("[2] Terremoto");
		System.out.println("[3] Cancelar");
		int tipo = leerEntero("Seleccione una opcion: ", 1, 3);
		if (tipo == 3)
			return;

		if (tipo == 1)
			editarChoripan();
		else
			editarTerremoto();
	}

	private static void editarChoripan() {
		if (cantChori == 0) {
			System.out.println("No hay choripanes registrados.");
			return;
		}
		System.out.println();
		System.out.println("#   Consumidor     Largo   Ancho   Fecha");
		for (int i = 0; i < cantChori; i++) {
			Choripan c = choripanesHistorial[i];
			System.out.println(padR("" + (i + 1), 4) + padR(c.getParticipante(), 15) + padR(c.getLargo() + " cm", 8)
					+ padR(c.getAncho() + " cm", 8) + c.getFecha());
		}
		int sel = leerEntero("Seleccione el registro a editar (0 para cancelar): ", 0, cantChori);
		if (sel == 0)
			return;

		Choripan c = choripanesHistorial[sel - 1];
		System.out.println();
		System.out.println("Registro actual -> " + c.toString());
		System.out.println("Deje el campo vacio para mantener el valor actual.");

		System.out.print("Consumidor [" + c.getParticipante() + "]: ");
		String nc = sc.nextLine().trim();
		if (!nc.isEmpty())
			c.setParticipante(nc);

		System.out.print("Largo [" + c.getLargo() + "]: ");
		String s = sc.nextLine().trim();
		if (!s.isEmpty())
			try {
				int v = Integer.parseInt(s);
				if (v > 0)
					c.setLargo(v);
				else
					System.out.println("Valor invalido, se mantiene el anterior.");
			} catch (NumberFormatException e) {
				System.out.println(">> Valor invalido, se mantiene el anterior.");
			}

		System.out.print("Ancho [" + c.getAncho() + "]: ");
		s = sc.nextLine().trim();
		if (!s.isEmpty())
			try {
				int v = Integer.parseInt(s);
				if (v > 0)
					c.setAncho(v);
				else
					System.out.println("Valor invalido, se mantiene el anterior.");
			} catch (NumberFormatException e) {
				System.out.println("Valor invalido, se mantiene el anterior.");
			}

		System.out.print("Fecha [" + c.getFecha() + "]: ");
		String nf = sc.nextLine().trim();
		if (!nf.isEmpty() && nf.matches("\\d{4}-\\d{2}-\\d{2}"))
			c.setFecha(nf);

		sobreEscribirConsumo();
		System.out.println("Registro actualizado correctamente y guardado en Consumido.txt");
		System.out.println(c.toString());
	}

	private static void editarTerremoto() {
		if (cantTerre == 0) {
			System.out.println("No hay terremotos registrados.");
			return;
		}
		System.out.println();
		System.out.println("#   Consumidor     Litros   Fecha");
		for (int i = 0; i < cantTerre; i++) {
			Terremoto t = terremotosHistorial[i];
			System.out.println(padR("" + (i + 1), 4) + padR(t.getParticipanteString(), 15)
					+ padR(redondear(t.getLitros()) + " L", 9) + t.getFecha());
		}
		int sel = leerEntero("Seleccione el registro a editar (0 para cancelar): ", 0, cantTerre);
		if (sel == 0)
			return;

		Terremoto t = terremotosHistorial[sel - 1];
		System.out.println();
		System.out.println("Registro actual -> " + t.toString());
		System.out.println("Deje el campo vacio para mantener el valor actual.");

		System.out.print("Consumidor [" + t.getParticipanteString() + "]: ");
		String nc = sc.nextLine().trim();
		if (!nc.isEmpty())
			t.setParticipanteString(nc);

		System.out.print("Litros [" + t.getLitros() + "]: ");
		String s = sc.nextLine().trim();
		if (!s.isEmpty())
			try {
				double v = Double.parseDouble(s);
				if (v > 0)
					t.setLitros(v);
				else
					System.out.println("Valor invalido, se mantiene el anterior.");
			} catch (NumberFormatException e) {
				System.out.println("Valor invalido, se mantiene el anterior.");
			}

		System.out.print("Fecha [" + t.getFecha() + "]: ");
		String nf = sc.nextLine().trim();
		if (!nf.isEmpty() && nf.matches("\\d{4}-\\d{2}-\\d{2}"))
			t.setFecha(nf);

		sobreEscribirConsumo();
		System.out.println("Registro actualizado correctamente y guardado en Consumido.txt");
		System.out.println(t.toString());
	}

	private static void eliminar() {
		System.out.println();
		System.out.println("Tipo de registro a eliminar:");
		System.out.println("[1] Choripan");
		System.out.println("[2] Terremoto");
		System.out.println("[3] Cancelar");
		int tipo = leerEntero("Seleccione una opcion: ", 1, 3);
		if (tipo == 3)
			return;

		if (tipo == 1)
			eliminarChoripan();
		else
			eliminarTerremoto();
	}

	private static void eliminarChoripan() {
		if (cantChori == 0) {
			System.out.println("No hay choripanes registrados.");
			return;
		}
		System.out.println();
		System.out.println("#   Consumidor     Largo   Ancho   Fecha");
		for (int i = 0; i < cantChori; i++) {
			Choripan c = choripanesHistorial[i];
			System.out.println(padR("" + (i + 1), 4) + padR(c.getParticipante(), 15) + padR(c.getLargo() + " cm", 8)
					+ padR(c.getAncho() + " cm", 8) + c.getFecha());
		}
		int sel = leerEntero("Seleccione el registro a eliminar (0 para cancelar): ", 0, cantChori);
		if (sel == 0)
			return;

		int idx = sel - 1;
		System.out.print("Confirma la eliminacion de \"" + choripanesHistorial[idx].toString() + "\"? (s/n): ");
		String conf = sc.nextLine().trim();
		if (!conf.equalsIgnoreCase("s")) {
			System.out.println("Eliminacion cancelada.");
			return;
		}

		for (int i = idx; i < cantChori - 1; i++) {
			choripanesHistorial[i] = choripanesHistorial[i + 1];
		}
		choripanesHistorial[cantChori - 1] = null;
		cantChori--;

		sobreEscribirConsumo();
		System.out.println("Registro eliminado correctamente. Cambios guardados en Consumido.txt");
	}

	private static void eliminarTerremoto() {
		if (cantTerre == 0) {
			System.out.println("No hay terremotos registrados.");
			return;
		}
		System.out.println();
		System.out.println("#   Consumidor     Litros   Fecha");
		for (int i = 0; i < cantTerre; i++) {
			Terremoto t = terremotosHistorial[i];
			System.out.println(padR("" + (i + 1), 4) + padR(t.getParticipanteString(), 15)
					+ padR(redondear(t.getLitros()) + " L", 9) + t.getFecha());
		}
		int sel = leerEntero("Seleccione el registro a eliminar (0 para cancelar): ", 0, cantTerre);
		if (sel == 0)
			return;

		int idx = sel - 1;
		System.out.print("Confirma la eliminacion de \"" + terremotosHistorial[idx].toString() + "\"? (s/n): ");
		String conf = sc.nextLine().trim();
		if (!conf.equalsIgnoreCase("s")) {
			System.out.println("Eliminacion cancelada.");
			return;
		}

		for (int i = idx; i < cantTerre - 1; i++) {
			terremotosHistorial[i] = terremotosHistorial[i + 1];
		}
		terremotosHistorial[cantTerre - 1] = null;
		cantTerre--;

		sobreEscribirConsumo();
		System.out.println("Registro eliminado correctamente. Cambios guardados en Consumido.txt");
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
				if (terremotosHistorial[i] != null) {

					String line = "Terremoto," + terremotosHistorial[i].getParticipanteString() + ","
							+ terremotosHistorial[i].getLitros() + "," + terremotosHistorial[i].getFecha();
					bw.write(line);
					bw.newLine();
				}
			}
			System.out.println("Archivo Consumido.txt actualizado (no se rompio)\n");
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("[ERROR] NO SE PUEDE SOBREESCRIBIR CONSUMIDO.TXT\n");
		}
	}

}
