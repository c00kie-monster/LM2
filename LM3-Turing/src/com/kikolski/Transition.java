package com.kikolski;

public class Transition {
	private String write = " ";
	private int move;
	private State nextState;
	
	public Transition(String write, State next, int move) {
		this.write = write;
		this.nextState = next;
		this.move = move;
	}
		
	public String getWrite() {
		return write;
	}

	public int getMove() {
		return move;
	}
	
	public State getNextState() {
		return nextState;
	}
}