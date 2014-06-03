package com.kikolski;

import java.util.LinkedList;

public class StatesList extends LinkedList<State>{
	private static final long serialVersionUID = 3604895147225848994L;

	public StatesList() {
		super();
	}
	
	public void add(State... states) {
		for (State s : states)
			add(s);
	}
}
