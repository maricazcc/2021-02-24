package it.polito.tdp.PremierLeague.model;

public class Evento implements Comparable <Evento> {
	
	enum EventType {
		GOAL,
		ESPULSIONE,
		INFORTUNIO
	}
	
	private EventType tipo;
	private int T;
	
	public Evento(EventType tipo, int t) {
		super();
		this.tipo = tipo;
		T = t;
	}

	public EventType getTipo() {
		return tipo;
	}

	public void setTipo(EventType tipo) {
		this.tipo = tipo;
	}

	public int getT() {
		return T;
	}

	public void setT(int t) {
		T = t;
	}

	@Override
	public int compareTo(Evento o) {
		return this.T - o.getT();
	}
	
	
	

}
