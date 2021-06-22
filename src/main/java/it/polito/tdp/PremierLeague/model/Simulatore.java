package it.polito.tdp.PremierLeague.model;

import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.PremierLeague.model.Evento.EventType;


public class Simulatore {
	
	//Coda degli eventi
	private PriorityQueue <Evento> queue ;
	
	//Modello del mondo
	private int T;
	private Model model;
	
	//Parametri di input	
	private Match partita;
	private int NumAzioni;
	private String TeamHome;
	private String TeamAway;
	private int HomePlayers;
	private int AwayPlayers;
	private int BestPlayer;
	private String TeamBestPlayer;
	
	//Valori di output
	private int goalHome;
	private int goalAway;
	private int EspulsioniHome;
	private int EsplulsioniAway;
	
	
	public Simulatore(Model model) {
		this.model = model;
	}

	public void init(Match m, int N) {
		this.partita = m;
		this.NumAzioni = N;
		this.TeamHome = m.getTeamHomeNAME();
		this.TeamAway = m.getTeamAwayNAME();
		
		this.HomePlayers = 11;
		this.AwayPlayers = 11;		
		this.goalHome = 0;
		this.goalAway = 0;
		this.EspulsioniHome = 0;
		this.EsplulsioniAway = 0;
		this.T = 0;
		
		this.queue = new PriorityQueue <Evento>();
		
		
		this.TeamBestPlayer = this.model.getTeamFromPlayer(this.model.getMigliore().getP(), m);
		
		if(this.TeamBestPlayer.equals(this.TeamHome))
			this.BestPlayer = 1;
		else if(this.TeamBestPlayer.equals(this.TeamAway))
			this.BestPlayer = -1;
		else this.BestPlayer = 0;			
	}
	
	public void run() {
		while (this.NumAzioni>0 && this.HomePlayers>0 && this.AwayPlayers>0) {
			double prob = Math.random();
												
			if (prob < 0.5) {
				this.queue.add(new Evento(EventType.GOAL, this.T++));
				
				if(this.HomePlayers < this.AwayPlayers)
					this.goalAway++;
				else if (this.AwayPlayers < this.HomePlayers)
					this.goalHome++;
				else {
					if(this.BestPlayer == 1)
						this.goalHome++;
					else if(this.BestPlayer == -1)
						this.goalAway++;
					else
						System.out.println("Errore giocatore migliore!");
				}
			}
			else if(prob < 0.8) {
				this.queue.add(new Evento(EventType.ESPULSIONE, this.T++));
				double probE = Math.random();
				
				if(probE < 0.6) {
					if(this.BestPlayer == 1) {
						this.EspulsioniHome++;
						this.HomePlayers--;
					}
				
					else if(this.BestPlayer == -1) {
						 this.EsplulsioniAway++;
						 this.AwayPlayers--;
					}
					
					else
						System.out.println("Errore giocatore migliore!");
				}
				
				else {
					if(this.BestPlayer == 1) {
						this.EspulsioniHome++;
						this.HomePlayers--;
					}
				
					else if(this.BestPlayer == -1) {
						 this.EsplulsioniAway++;
						 this.AwayPlayers--;
					}
					
					else
						System.out.println("Errore giocatore migliore!");
				}
				
			}
			else {
				this.queue.add(new Evento(EventType.INFORTUNIO, this.T++));
				double probR = Math.random();
				
				if(probR < 0.5)
					this.NumAzioni+=2;
				else 
					this.NumAzioni+=3;
			}
			
			this.NumAzioni--;			
		}
		
	}

	public int getGoalHome() {
		return goalHome;
	}

	public int getGoalAway() {
		return goalAway;
	}

	public int getEspulsioniHome() {
		return EspulsioniHome;
	}

	public int getEsplulsioniAway() {
		return EsplulsioniAway;
	}
	
	
	
	

}
