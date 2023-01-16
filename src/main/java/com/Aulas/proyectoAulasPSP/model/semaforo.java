package com.Aulas.proyectoAulasPSP.model;

public class semaforo extends Thread {
	private final Aula cont;
	int nHilo;
	int cantAula;
	int divi;

	public int getnHilo() {
		return nHilo;
	}

	public int getcantAula() {
		return cantAula;
	}

	public void setcantAula(int cantAula) {
		this.cantAula = cantAula;
	}

	public semaforo(int nHilo, int divi, Aula cont) {
		this.nHilo = nHilo;
		this.divi = divi;
		this.cont = cont;
	}

	public void run() {
		for (int i = 0; i < divi; i++) {
			this.cont.incrementa();
			cantAula++;
		}
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
