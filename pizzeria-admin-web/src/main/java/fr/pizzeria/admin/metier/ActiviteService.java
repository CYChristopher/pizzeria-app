package fr.pizzeria.admin.metier;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class ActiviteService {

	private List<Evenement> events = new ArrayList<>();

	public List<Evenement> getEvents() {
		return events;
	}

	public void ecouteEvent(@Observes Evenement event) {
		events.add(event);
		events.stream().forEach(ev -> System.out.println(ev.getType()));
	}

}
