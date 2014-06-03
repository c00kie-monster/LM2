package com.kikolski;

import java.util.HashSet;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

public class Automaton {
	public static final int NUMBER_OF_ALL_STATES = 12; 
	private Set<State> currentStates = new HashSet<>();;
	
	private State qStart = new State("START", false);
	private State q0 = new State("0", false);
	private State q1 = new State("1", false);
	private State q2 = new State("2", false);
	private State q3 = new State("3", false);
	private State q4 = new State("4", false);
	private State q5 = new State("5", false);
	private State q6 = new State("6", false);
	private State q7 = new State("7", false);
	private State q8 = new State("8", false);
	private State q9 = new State("9", false);
	private State qFinal = new State("FINAL", true);
	
	public Automaton() {
		initStartState();
		initIntermediaryStates();
		initFinalState();
		currentStates.add(qStart);
	}

	private void initStartState() {
		qStart.addFollowStates("0", qStart, q0);
		qStart.addFollowStates("1", qStart, q1);
		qStart.addFollowStates("2", qStart, q2);
		qStart.addFollowStates("3", qStart, q3);
		qStart.addFollowStates("4", qStart, q4);
		qStart.addFollowStates("5", qStart, q5);
		qStart.addFollowStates("6", qStart, q6);
		qStart.addFollowStates("7", qStart, q7);
		qStart.addFollowStates("8", qStart, q8);
		qStart.addFollowStates("9", qStart, q9);
	}
	
	private void initFinalState() {
		qFinal.addFollowState(qFinal, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
	}
	
	private void initIntermediaryStates() {
		q0.addFollowStates("0", qFinal);
		q1.addFollowStates("1", qFinal);
		q2.addFollowStates("2", qFinal);
		q3.addFollowStates("3", qFinal);
		q4.addFollowStates("4", qFinal);
		q5.addFollowStates("5", qFinal);
		q6.addFollowStates("6", qFinal);
		q7.addFollowStates("7", qFinal);
		q8.addFollowStates("8", qFinal);
		q9.addFollowStates("9", qFinal);
	}
	
	public void move(String value) {
		Set<State> moveResult = new HashSet<>();
		for (State s : currentStates) {
			Set<State> movedStates = s.moveTo(value);
				if (movedStates != null) {
					moveResult.addAll(movedStates);
				}
		}
	
		currentStates.clear();
		
		if (moveResult.contains(qFinal))
			currentStates.add(qFinal);
		else
			currentStates.addAll(moveResult);
		
		System.out.println(getCurrentStates());
	}
	
	public String getCurrentStates() {
		String result = "";
		for (State s : currentStates)
			result += s.getId() + " | ";
		return result;
	}
	
	public boolean isInFinalState() {
		return currentStates.contains(qFinal);
	}
}