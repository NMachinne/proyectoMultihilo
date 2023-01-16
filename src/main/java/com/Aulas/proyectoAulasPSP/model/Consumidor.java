package com.Aulas.proyectoAulasPSP.model;


public class Consumidor extends Thread{
	private Aula au;

	public Consumidor(Aula au) {
		this.au=au;
	}
	
	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				au.MuestraAlumnos();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(i + " => se ha leido: " + i);
		}
	}
}