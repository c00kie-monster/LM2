package com.kikolski;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class State {
	private static final String[] edges = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "/", "*", "(", ")"};
	private static final int SAME_STATE_NUMBER = 9; //Dla pierwszych znaków 0..9 przejscie jest zawsze takie samo
	private String id;
	private Map<String, State> follows;
	
	private boolean isFinal;
		
	public State(String state) {
		this(state, false);	
	}
	
	public State (String state, boolean isFinal) {
		this.id = state;
		this.isFinal = isFinal;
		this.follows = new HashMap<>();
	}
	
	public String getId() {
		return id;
	}
	/**
	 * Kolejnosc stanow ma znaczenie:
	 * <ul> 
	 * <li>1 parametr - stan dla 0..9
	 * <li>2 parametr - stan dla "+"
	 * <li>3 parametr - stan dla "-"
	 * <li>4 parametr - stan dla "*"
	 * <li>5 parametr - stan dla "/"
	 * <li>6 parametr - stan dla "("
	 * <li>7 parametr - stan dla ")"
	 * </ul>
	 */
	public void addFollowStates(State...states) {
		for (int i = 0 ; i < edges.length; i++) {
			if (i <= SAME_STATE_NUMBER)
				follows.put(edges[i], states[0]);
			else
				follows.put(edges[i], states[i - SAME_STATE_NUMBER]);
		}
	}
	
	public boolean isFinal() {
		return isFinal;
	}
		
	public State moveTo(String edgeValue) {
		return follows.get(edgeValue);
	}
	
	@Override
	public String toString() {
		return String.valueOf(id);
	}

	public void addFollowState(State state) {
		for (int i = 0; i < edges.length; i++)
			follows.put(edges[i], state);
	}
}