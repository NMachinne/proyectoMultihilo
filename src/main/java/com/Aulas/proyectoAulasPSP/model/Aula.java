package com.Aulas.proyectoAulasPSP.model;

import java.util.LinkedList;

import javafx.application.Platform;
import javafx.scene.control.ListView;

public class Aula extends Thread {

	private Boolean AulaVacia;
	private ListView<String> nAlumnos;
	private ListView<String> nAulas;
	private int Alumno = 0;
	int vari = 0;
	private final int capAula = 2;
	LinkedList<Integer> list = new LinkedList<>();

	public Aula(ListView<String> nalum, ListView<String> nalu) {
		this.nAlumnos = nalum;
		this.nAulas = nalu;
		this.AulaVacia = true;
	}

	public Aula(int Alumno) {
		this.Alumno = Alumno;
	}

	public Aula() {
		super();
	}

	public Boolean getAulaVacia() {
		return AulaVacia;
	}

	public void setAulaVacia(Boolean aulavacia) {
		this.AulaVacia = aulavacia;
	}

	public int getAlumno() {
		return Alumno;
	}

	public int incrementa() {
		this.Alumno++;
		return Alumno;

	}

	/**
	 * funcion que se usa para mostrar contendo dentro de la consola y la aplicacion en el apartado "ALUMNOS"
	 * 
	 * @param alumno
	 * @throws InterruptedException
	 */
	public synchronized void entraAlumno(String alumno) throws InterruptedException {
		while (true) {
			while (list.size() == capAula || AulaVacia == false) {
				wait();
			}
			System.out.println("Productor - " + vari);
			muestraTexto(nAlumnos, alumno + " Alumno " + vari + " va a entrar en el Aula \n ");
			list.add(vari++);
			notifyAll();
			Thread.sleep(1500);
		}
	}

	/**
	 *funcion que se usa para mostrar contendo dentro de la consola y la aplicacion en el apartado "AULAS"
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void MuestraAlumnos() throws InterruptedException {
		while (true) {
			while (list.isEmpty()) {
				wait();
			}
			int elimprimero = list.removeFirst();
			int random = (int) (Math.random() * 6) + 1;
			System.out.println("Consumidor - " + elimprimero);
			muestraTexto(nAulas, "El Alumno:  " + elimprimero + " ha entrado en el Aula durante: " + random + " segundos");
			notifyAll();
			Thread.sleep(random * 1500);
		}
	}

	/**
	 * funcion que muestra texto a traves de la interfaz
	 * 
	 * @param nalum
	 * @param nombre
	 */
	public static synchronized void muestraTexto(ListView<String> nalum, String nombre) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				nalum.getItems().add(nombre);
			}

		});
	}

}
