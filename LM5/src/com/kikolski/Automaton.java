package com.kikolski;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Automaton {
	private static final Logger logger = Logger.getLogger(Automaton.class.getName());
	private State start = new State("START");
	private State number = new State("NUMBER", true);
	private State operand = new State("OPERAND");
	private State leftBracket = new State("LEFT_BRACKET");
	private State leftBracketNumber = new State("LEFT_BRACKET_NUMBER");
	private State leftBracketMinus = new State("LEFT_BRACKET_MINUS");
	private State leftBracketOperand = new State("LEFT_BRACKET_OPERAND");
	private State rightBracket = new State("RIGHT_BRACKET", true);
	private State error = new State("ERROR");
	
	private State currentState = start;
	
	public Automaton() {
		start.addFollowStates(number, error, error, error, error, leftBracket, error);
		number.addFollowStates(number, operand, operand, operand, operand, leftBracket, error);
		operand.addFollowStates(number, error, error, error, error, leftBracket, error);
		leftBracket.addFollowStates(leftBracketNumber, error, leftBracketMinus, error, error, error, error);
		leftBracketNumber.addFollowStates(leftBracketNumber, leftBracketOperand, leftBracketOperand, leftBracketOperand, leftBracketOperand, error, rightBracket);
		leftBracketOperand.addFollowStates(leftBracketNumber, error, error, error, error, error, error);
		rightBracket.addFollowStates(error, operand, operand, operand, operand, leftBracket, error);
		leftBracketMinus.addFollowStates(leftBracketNumber, error, error, error, error, error, error);
		error.addFollowState(error);
	}
	
	private void move(String value) {
		currentState = currentState.moveTo(value);
		if (currentState == null)	
			currentState = error;
		logger.log(Level.INFO, value + ": " + currentState.toString() + "| Akceptowalny: " + currentState.isFinal());
	}
	
	public State getCurrentState(){
		return currentState;
	}
	
	public boolean operate(String expression) {
		for (String s : expression.split(""))
			this.move(s);
		return currentState.isFinal();
	}
}


/**
 * 				0...4		+			-			/			*			(			)	
 * START		NUM			ERR			ERR			ERR			ERR			LBRACKET	ERR
 * NUM			NUM			OPER		OPER		OPER		OPER		LBRACKET	ERR			<-- FINAL STATE
 * OPER			NUM			ERR			ERR			ERR			ERR			LBRACKET	ERR
 * LBRACKET		LBRACKNUM	ERR			LBRACKMIN	ERR			ERR			ERR			ERR
 * LBRACKNUM	LBRACKNUM	LBRACKOPER  LBRACKOPER	LBRACKOPER	LBRACKOPER	ERR			RBRACKET
 * LBRACKOPER	LBRACKNUM	ERR			ERR			ERR			ERR			ERR			ERR
 * RBRACKET		ERR			NUM			NUM			NUM			NUM			LBRACKET	ERR
 * LBRACKMIN	LBRACKNUM	ERR			ERR			ERR			ERR			ERR			ERR					
 */
