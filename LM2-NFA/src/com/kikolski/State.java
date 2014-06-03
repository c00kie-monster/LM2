package com.kikolski;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class State {
	private static final String[] edges = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	private String id;
	private Map<String, Set<State>> follows;
	
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
//
//	public void addFollowStates(State... states) {
//		for (int i = 0; i < states.length; i++)
//			follows.put(edges[i], states[i]);
//	}
	
	public void addFollowStates(String edge, State... states) {
		Set<State> statesSet = new HashSet<>();
		for (State s : states)
			statesSet.add(s);
		
		follows.put(edge, statesSet);
	}
	
	public void addFollowState(State state, String... edges) {
		Set<State> states = new HashSet<>();
		states.add(state);
		for (String e : edges)
			follows.put(e, states);
	}
	
	public boolean isFinal() {
		return isFinal;
	}
		
	public Set<State> moveTo(String edgeValue) {
		return follows.get(edgeValue);
	}
	
	@Override
	public String toString() {
		return String.valueOf(id);
	}
}