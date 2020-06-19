package com.test.automaton.component;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.punto1.automaton.components.StateA;
import com.app.punto1.automaton.components.StateOfSetStates;

class StateTest {
	
	static int _numberOfState;
	static String _lineOfStates;
	static List< StateA > _lstFromNumber;
	static List< StateA > _lstFromLine;
	
	@BeforeAll
	static void setUp() {
		_numberOfState = 4;
		_lineOfStates = "1    ,  2,3";
		_lstFromNumber = StateA.generateListStatesFromInt( _numberOfState );
		_lstFromLine = StateA.generateListStatesFromLine( _lineOfStates );
	}
	
	@Test
	void sizeNumberTest() {
		int actual = _lstFromNumber.size();
		int expected = 4;
		
		assertEquals( expected, actual );
	}
	
	@Test
	void sizeLineTest() {
		int actual = _lstFromLine.size();
		int expected = 3;
		
		assertEquals( expected, actual );
	}
	
	@Test
	void generateNewStateWithStateRepeatTest() {
		String line = " 1,2,3,3,2,1,3 ";
		int actual = StateA.generateListStatesFromLine( line ).size();
		int expected = 3;
		
		assertEquals( expected, actual );
	}
	
	@Test
	void fromNumberContainNumberStateTest() {
		StateA state = new StateA( 1 );
		
		boolean actual = _lstFromNumber.contains( state );
		boolean expected = true;
		
		assertEquals( expected, actual );
	}
	
	@Test
	void fromNumberContainStringStateTest() {
		StateA state = new StateA( "1" );
		
		boolean actual = _lstFromNumber.contains( state );
		boolean expected = true;
		
		assertEquals( expected, actual );
	}
	
	@Test
	void fromLineContainNumberStateTest() {
		StateA state = new StateA( 1 );
		
		boolean actual = _lstFromLine.contains( state );
		boolean expected = true;
		
		assertEquals( expected, actual );
	}
	
	@Test
	void fromLineContainStringStateTest() {
		StateA state = new StateA( "1" );
		
		boolean actual = _lstFromLine.contains( state );
		boolean expected = true;
		
		assertEquals( expected, actual );
	}
	
	@Test
	void fromLineContainNumberStateDFAFromNFATest() {
		StateOfSetStates state01 = new StateOfSetStates( "10" );
		state01.addState( _lstFromLine.get(1) );
		
		StateOfSetStates state02 = new StateOfSetStates( "20" );
		state02.addState( _lstFromLine.get(1) );
				
		assertEquals( state01, state02 );
	}
	
	@Test
	void fromNumberContainNumberStateDFAFromNFATest() {
		StateOfSetStates state01 = new StateOfSetStates( "10" );
		state01.addState( _lstFromNumber.get(1) );
		
		StateOfSetStates state02 = new StateOfSetStates( "20" );
		state02.addState( _lstFromNumber.get(1) );
				
		assertEquals( state01, state02 );
	}
	
	@Test
	void isStartStateTest() {
		boolean actual = _lstFromNumber.get(0).isStartState();
		boolean expected = true;
		
		assertEquals( expected, actual );
	}
	@Test
	void isFinalStateFalseTest() {
		boolean actual = _lstFromNumber.get(0).isFinalState();
		
		assertFalse( actual );
	}
	
	@Test
	void isFinalStateTrueTest() {
		StateA state = new StateA( "10" );
		state.FinalState( true );
		
		boolean actual = state.isFinalState();
		
		assertTrue( actual );
	}
	
	@Test
	void startStateFromAnotherState() {
		
		StateOfSetStates state = new StateOfSetStates( 1 );
		StateA state02 = new StateA(2);
		state02.startState( true );
		
		state.addState( state02 );
		
		StateA state03 = state.toStateA();
		
		assertTrue( state03.isStartState() );
		
	}
	
	@Test
	void finalStateFromAnotherState() {
		
		StateOfSetStates state = new StateOfSetStates( 1 );
		StateA state02 = new StateA(2);
		state02.FinalState( true );
		
		state.addState( state02 );
		
		StateA state03 = state.toStateA();
		
		assertTrue( state03.isFinalState() );	
	}
	
	@Test
	void addSameStateTest() {
		StateOfSetStates state = new StateOfSetStates( 1 );
		StateA state02 = new StateA(2);
		StateA state03 = new StateA(2);
		
		state.addState( state02 );
		state.addState( state03 );
		
		int actual = state.getStates().size();
		int expected = 1;
		
		assertEquals( expected, actual );
	}
}