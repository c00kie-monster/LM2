package com.kikolski;

public class Tape {
	private static final int TAPE_SIZE = 30;
	private String[] tape = new String[TAPE_SIZE];
	private int pointer = TAPE_SIZE / 2;
	
	public Tape() {
		for (int i = 0; i < TAPE_SIZE; i++) {
			tape[i] = "_";
		}
	}
	
	public void move(int side) {
		pointer += side;
	}
	
	public void write(String value) {
		tape[pointer] = value;
	}
	
	public String read() {
		return tape[pointer];
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < tape.length; i++) {
			if (i == pointer)
				result.append("->");
			result.append(tape[i]);
		}
		return result.toString();
	}
}