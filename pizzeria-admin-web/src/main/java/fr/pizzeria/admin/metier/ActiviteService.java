package fr.pizzeria.admin.metier;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class ActiviteService {

	private List<Evenement> events = new ArrayList<>();

	public List<Evenement> getEvents() {
		return this.events;
	}

	public void ecouteEvent(@Observes Evenement event) {
		this.events.add(event);
	}

}
