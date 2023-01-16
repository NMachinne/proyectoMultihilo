package com.Aulas.proyectoAulasPSP;

import java.io.IOException;
import java.util.concurrent.Semaphore;

import com.Aulas.proyectoAulasPSP.model.Aula;
import com.Aulas.proyectoAulasPSP.model.semaforo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class PrimaryController {

	@FXML
	private Button start;
	@FXML
	private Button inicio;
	@FXML
	private Button stop;
	
	@FXML
	private ListView<String> Alumnos;
	@FXML
	private ListView<String> Aulas;
	
	
	private Aula ControlaAulas;
	private static final int AULAS = 5;
	
	//hilos creados
	Thread alum;
	Thread alumvar;
	Thread aulas;

	@FXML
	public void Inicio() throws InterruptedException {
		Alumnos.getItems().clear();
		start.setVisible(false);
		inicio.setVisible(false);

		alum = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ControlaAulas.entraAlumno("Hilo 1");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		alumvar = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ControlaAulas.entraAlumno("Hilo 2 ");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		aulas = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ControlaAulas.MuestraAlumnos();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		stop.setVisible(true);
		alum.start();
		alumvar.start();
		aulas.start();

	}

	private void AnadeAlumnos() {
		Semaphore misemafoto = new Semaphore(2);
		try {
			misemafoto.acquire();
			Aula au = new Aula();
			Thread[] hilos = new Thread[AULAS];

			for (int i = 0; i < AULAS; i++) {
				Thread th = new Thread(new semaforo(i, 5 , au));
				th.setName("pepito ");
				th.start();
				hilos[i] = th;
				System.out.println(th);

				if (i == AULAS - 1) {
					try {
						th.join();
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			Alumnos.getItems().add("Alumnos Totales:" + au.getAlumno());
			misemafoto.release();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Funcion dentro de primary.fxml que inicia el programa
	 */
	@FXML
	private void start() {
		try {
			ControlaAulas.setAulaVacia(true);
			start.setVisible(false);
			stop.setVisible(true);
			notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Funcion dentro de primary.fxml que para el programa
	 */
	@FXML
	private void stop() {
		// Cliente.interrupt();
		ControlaAulas.setAulaVacia(false);
		start.setVisible(true);
		stop.setVisible(false);
		ControlaAulas.muestraTexto(Alumnos, "Se ha parado el servicio");
	}

	/**
	 * Funcion dentro de primary.fxml que oculta/muestra los campos
	 */
	@FXML
	private void ocultabtn() throws IOException {
		start.setVisible(false);
		inicio.setVisible(true);
		stop.setVisible(false);
	}
	
	public void initialize() throws InterruptedException {
		ControlaAulas = new Aula(Alumnos, Aulas);

		Thread.currentThread().sleep(1000);
		AnadeAlumnos();

	}
}
