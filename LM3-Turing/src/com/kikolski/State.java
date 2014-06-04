package com.kikolski;

import java.util.HashMap;
import java.util.Map;

public class State {
	private static final String[] edges = {"1", "2", "5", "_"};
	private String id;
	private Map<String, Transition> transitions;
	
	private boolean isFinal;
	private boolean isUnacceptable;
	
	public State(String state) {
		this(state, false, true);	
	}
	
	public State (String state, boolean isFinal, boolean isUnacceptable) {
		this.id = state;
		this.isFinal = isFinal;
		this.isUnacceptable = isUnacceptable;
		this.transitions = new HashMap<>();
	}
	
	public String getId() {
		return id;
	}
	
	public void addTransitions(Transition... transitions) {
		for (int i = 0; i < transitions.length; i++)
			this.transitions.put(edges[i], transitions[i]);
	}
	
	public boolean isFinal() {
		return isFinal;
	}
		
	public boolean isUnacceptable() {
		return isUnacceptable;
	}

	public Transition move(String edgeValue) {
		return transitions.get(edgeValue);
	}
	
	@Override
	public String toString() {
		return String.valueOf(id);
	}
}