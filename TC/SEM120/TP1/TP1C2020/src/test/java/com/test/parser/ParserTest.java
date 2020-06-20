package com.test.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.helper.ObjectConverter;
import com.app.punto2.parser.Parser;

class ParserTest {

	static String _filePath = "src/test/resources/parserFiles/testParser.txt";
	static File _file;
	static ObjectConverter _oc;
	static Parser _parser;
	
	/* No accept. */
	static String _str01 = "@";
	static String _str02 = "+";
	static String _str03 = "(";
	static String _str04 = ")";
	static String _str05 = "()";
	static String _str06 = ")(";
	static String _str07 = "(a)+";
	static String _str08 = "+(a)";
	static String _str09 = "a+";
	static String _str10 = "+a";
	static String _str11 = "a)";
	static String _str12 = "(a";
	static String _str13 = "(+a";
	static String _str14 = "a+)";
	/* Accept */
	static String _str15 = "a";
	static String _str16 = "(a)";
	static String _str17 = "a+a";
	static String _str18 = "(a)+a";
	static String _str19 = "((a))";
	static String _str20 = "((a))+a";
	static String _str21 = "((a))+(a)+(a+a)";
	
	@BeforeAll
	static void setUp() {
		_file = new File( _filePath );
		_oc = new ObjectConverter();
		_parser = _oc.parserTopDownNoRecursiveFromFile( _file, "@" );
	}
	
	@Test
	void fileExistsTest() {
		boolean condition = _file.exists();
		
		assertTrue( condition );
	}
	
	@Test
	void accept_string01_Test() {
		boolean condition = _parser.AcceptString( _str01 );
		
		assertFalse( condition );
	}
	
	@Test
	void accept_string02_Test() {
		boolean condition = _parser.AcceptString( _str02 );
		
		assertFalse( condition );
	}
	
	@Test
	void accept_string03_Test() {
		boolean condition = _parser.AcceptString( _str03 );
		
		assertFalse( condition );
	}
	
	@Test
	void accept_string04_Test() {
		boolean condition = _parser.AcceptString( _str04 );
		
		assertFalse( condition );
	}
	
	@Test
	void accept_string05_Test() {
		boolean condition = _parser.AcceptString( _str05 );
		
		assertFalse( condition );
	}
	
	@Test
	void accept_string06_Test() {
		boolean condition = _parser.AcceptString( _str06 );
		
		assertFalse( condition );
	}
	
	@Test
	void accept_string07_Test() {
		boolean condition = _parser.AcceptString( _str07 );
		
		assertFalse( condition );
	}
	
	@Test
	void accept_string08_Test() {
		boolean condition = _parser.AcceptString( _str08 );
		
		assertFalse( condition );
	}
	
	@Test
	void accept_string09_Test() {
		boolean condition = _parser.AcceptString( _str09 );
		
		assertFalse( condition );
	}
	
	@Test
	void accept_string10_Test() {
		boolean condition = _parser.AcceptString( _str10 );
		
		assertFalse( condition );
	}
	
	@Test
	void accept_string11_Test() {
		boolean condition = _parser.AcceptString( _str11 );
		
		assertFalse( condition );
	}
	
	@Test
	void accept_string12_Test() {
		boolean condition = _parser.AcceptString( _str12 );
		
		assertFalse( condition );
	}
	
	@Test
	void accept_string13_Test() {
		boolean condition = _parser.AcceptString( _str13 );
		
		assertFalse( condition );
	}
	
	@Test
	void accept_string14_Test() {
		boolean condition = _parser.AcceptString( _str14 );
		
		assertFalse( condition );
	}
	
	@Test
	void accept_string15_Test() {
		boolean condition = _parser.AcceptString( _str15 );
		
		assertTrue( condition );
	}
	
	@Test
	void accept_string16_Test() {
		boolean condition = _parser.AcceptString( _str16 );
		
		assertTrue( condition );
	}
	
	@Test
	void accept_string17_Test() {
		boolean condition = _parser.AcceptString( _str17 );
		
		assertTrue( condition );
	}
	
	@Test
	void accept_string18_Test() {
		boolean condition = _parser.AcceptString( _str18 );
		
		assertTrue( condition );
	}
	
	@Test
	void accept_string19_Test() {
		boolean condition = _parser.AcceptString( _str19 );
		
		assertTrue( condition );
	}
	
	@Test
	void accept_string20_Test() {
		boolean condition = _parser.AcceptString( _str20 );
		
		assertTrue( condition );
	}
	
	@Test
	void accept_string21_Test() {
		boolean condition = _parser.AcceptString( _str21 );
		
		assertTrue( condition );
	}
}
