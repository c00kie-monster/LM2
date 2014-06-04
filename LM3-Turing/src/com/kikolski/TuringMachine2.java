package com.kikolski;

public class TuringMachine2 {
	public static final int HEAD_MOVE_R = 1;
	public static final int HEAD_MOVE_L = -1;
	public static final int HEAD_STAY = 0;
	public static final String SYMBOL_1 = "1";
	public static final String SYMBOL_2 = "2";
	public static final String SYMBOL_5 = "5";
	public static final String SYMBOL_EMPTY = "_";
	
	private Tape tape = new Tape();

	private State q0 = new State("q0"); 
	private State q1 = new State("q1");
	private State q2 = new State("q2");
	private State q3 = new State("q3");
	private State q4 = new State("q4");
	private State q5 = new State("q5");
	private State q6 = new State("q6");
	private State q7 = new State("q7");
	private State q8 = new State("q8");
	private State q9 = new State("q9");
	private State q10 = new State("q10");
	private State q11 = new State("q11");
	private State q12 = new State("q12");
	private State qOdd = new State("ODD_MONEY");
	private State qRollback = new State("ROLLBACK");
	private State qPositive = new State("POSITIVE", true, false);
	private State qNegative = new State("NEGATIVE", true, true);
	private State currentState = q0;
		
	public TuringMachine2() {
		q0.addTransitions(
				new Transition(SYMBOL_1, q1, HEAD_MOVE_R),
				new Transition(SYMBOL_2, q2, HEAD_MOVE_R),
				new Transition(SYMBOL_5, q5, HEAD_MOVE_R),
				new Transition(SYMBOL_EMPTY, qRollback, HEAD_MOVE_L));
		q1.addTransitions(
				new Transition(SYMBOL_1, q2, HEAD_MOVE_R),
				new Transition(SYMBOL_2, q3, HEAD_MOVE_R),
				new Transition(SYMBOL_5, q6, HEAD_MOVE_R),
				new Transition(SYMBOL_EMPTY, qRollback, HEAD_MOVE_L));
		q2.addTransitions(
				new Transition(SYMBOL_1, q3, HEAD_MOVE_R),
				new Transition(SYMBOL_2, q4, HEAD_MOVE_R),
				new Transition(SYMBOL_5, q7, HEAD_MOVE_R),
				new Transition(SYMBOL_EMPTY, qRollback, HEAD_MOVE_L));
		q3.addTransitions(
				new Transition(SYMBOL_1, q4, HEAD_MOVE_R),
				new Transition(SYMBOL_2, q5, HEAD_MOVE_R),
				new Transition(SYMBOL_EMPTY, q8, HEAD_MOVE_R),
				new Transition(SYMBOL_EMPTY, qRollback, HEAD_MOVE_L));
		q4.addTransitions(
				new Transition(SYMBOL_1, q5, HEAD_MOVE_R),
				new Transition(SYMBOL_2, q6, HEAD_MOVE_R),
				new Transition(SYMBOL_EMPTY, q9, HEAD_MOVE_R),
				new Transition(SYMBOL_EMPTY, qRollback, HEAD_MOVE_L));
		q5.addTransitions(
				new Transition(SYMBOL_1, q6, HEAD_MOVE_R),
				new Transition(SYMBOL_EMPTY, q7, HEAD_MOVE_R),
				new Transition(SYMBOL_EMPTY, q10, HEAD_MOVE_R),
				new Transition(SYMBOL_EMPTY, qRollback, HEAD_MOVE_L));
		q6.addTransitions(
				new Transition(SYMBOL_EMPTY, q7, HEAD_MOVE_R),
				new Transition(SYMBOL_EMPTY, q8, HEAD_MOVE_R),
				new Transition(SYMBOL_EMPTY, q11, HEAD_MOVE_R),
				new Transition(SYMBOL_EMPTY, qRollback, HEAD_MOVE_L));
		q7.addTransitions(
				new Transition(SYMBOL_EMPTY, q8, HEAD_MOVE_R),
				new Transition(SYMBOL_EMPTY, q9, HEAD_MOVE_R),
				new Transition(SYMBOL_EMPTY, q12, HEAD_MOVE_R),
				new Transition(SYMBOL_EMPTY, qOdd, HEAD_STAY));
		q8.addTransitions(
				new Transition(SYMBOL_1, q8, HEAD_MOVE_R),
				new Transition(SYMBOL_2, q8, HEAD_MOVE_R),
				new Transition(SYMBOL_5, q8, HEAD_MOVE_R),
				new Transition(SYMBOL_1, qOdd, HEAD_STAY));
		q9.addTransitions(
				new Transition(SYMBOL_1, q9, HEAD_MOVE_R),
				new Transition(SYMBOL_2, q9, HEAD_MOVE_R),
				new Transition(SYMBOL_5, q9, HEAD_MOVE_R),
				new Transition(SYMBOL_2, qOdd, HEAD_STAY));
		q10.addTransitions(
				new Transition(SYMBOL_1, q10, HEAD_MOVE_R),
				new Transition(SYMBOL_2, q10, HEAD_MOVE_R),
				new Transition(SYMBOL_5, q10, HEAD_MOVE_R),
				new Transition(SYMBOL_2, q8, HEAD_MOVE_R));
		q11.addTransitions(
				new Transition(SYMBOL_1, q11, HEAD_MOVE_R),
				new Transition(SYMBOL_2, q11, HEAD_MOVE_R),
				new Transition(SYMBOL_5, q11, HEAD_MOVE_R),
				new Transition(SYMBOL_2, q9, HEAD_MOVE_R));
		q12.addTransitions(
				new Transition(SYMBOL_1, q12, HEAD_MOVE_R),
				new Transition(SYMBOL_2, q12, HEAD_MOVE_R),
				new Transition(SYMBOL_5, q12, HEAD_MOVE_R),
				new Transition(SYMBOL_2, q10, HEAD_MOVE_R));
		qOdd.addTransitions(
				new Transition(SYMBOL_EMPTY, qOdd, HEAD_MOVE_L),
				new Transition(SYMBOL_EMPTY, qOdd, HEAD_MOVE_L),
				new Transition(SYMBOL_EMPTY, qOdd, HEAD_MOVE_L),
				new Transition(SYMBOL_EMPTY, qPositive, HEAD_STAY));
		qRollback.addTransitions(
				new Transition(SYMBOL_EMPTY, qRollback, HEAD_MOVE_L),
				new Transition(SYMBOL_EMPTY, qRollback, HEAD_MOVE_L),
				new Transition(SYMBOL_EMPTY, qRollback, HEAD_MOVE_L),
				new Transition(SYMBOL_EMPTY, qNegative, HEAD_STAY));
	}
	
	public void initTape(String coin) {
		Transition t = currentState.move(coin);
		tape.write(t.getWrite());
		currentState = t.getNextState();
		tape.move(t.getMove());
		System.out.println(tape.toString());
	}
	
	public String operate() {
			String value = tape.read();
			Transition t = currentState.move(value);
			tape.write(t.getWrite());
			currentState = t.getNextState();
			tape.move(t.getMove());
			System.out.println(tape.toString());
			return value;
	}
	
	public State getCurrentState() {
		return currentState;
	}
	
	public String getTapeState() {
		return tape.toString();
	}
}

/**
 * 				1					2				5				_
 * ---------------------------------------------------------------------
 * q0			1,q1,R			2,q2,R			5,q5,R			_,ROLL,-
 * q1			1,q2,R			2,q3,R			5,q6,R			_,ROLL,- 
 * q2			1,q3,R			2,q4,R			5,q7,R			_,ROLL,- 
 * q3			1,q4,R			2,q5,R			_,q8,R			_,ROLL,-
 * q4			1,q5,R			2,q6,R			_,q9,R			_,ROLL,-
 * q5			1,q6,R			_,q7,R			_,q10,R			_,ROLL,-	
 * q6			_,q7,R			_,q8,R			_,q11,R			_,ROLL,-
 * q7			_,q8,R			_,q9,R			_,q12,R			-,ODD,-
 * q8			1,q8,R			2,q8,R			5,q8,R			1,ODD,R
 * q9			1,q9,R			2,q9,R			5,q9,R			2,ODD,R
 * q10			1,q10,R			2,q10,R			5,q10,R			2,q8,R
 * q11			1,q11,R			2,q11,R			5,q11,R			2,q9,R
 * q12			1,q12,R			2,q12,R			5,q12,R			2,q10,R
 * ODD			_,ODD,L			_,ODD,L			_,ODD,L			-,POSITIVE,-
 * ROLL			_,ROLL,L		_,ROLL,L		_,ROLL,L		-,NEGATIVE,-
 * POSITIVE		
 * NEGATIVE
 */