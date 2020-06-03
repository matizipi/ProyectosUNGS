package main;

import javax.swing.UIManager;

import com.ui.controller.MainController;

public class Main {
	public static void main(String[] arg01) {
		
		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		MainController mc = new MainController();
		
		mc.start();
		
		//Start read automaton file.
//		File f = new File("src/main/resources/exampleAutomaton2.txt");
//		
//		AutomatonConverter ac = new AutomatonConverter();
//		
//		NFA nfa = ac.newNFAFromFile( f );
//		
//		DFA dfa = ac.DFAFromNFA( nfa );
	}
}