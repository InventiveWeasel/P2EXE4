package test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import exe2.VulnerableClass;

public class VulnerableTest {
	VulnerableClass vul;

	@Before
	public void setup(){
		vul = new VulnerableClass();
	}
	
	@Test
	public void testForMultipleDoubleDots() {
		String file = "teste\\..\\..\\..\\..\\..\\..\\..\\..\\teste.txt";
		ByteArrayInputStream in = new ByteArrayInputStream("W\nhacked".getBytes());
		System.setIn(in);
		System.setIn(System.in);
		assertFalse(vul.vulnerableMethod(file));
		
	}
	
	@Test
	public void testForDoubleDots(){
		String file = "teste\\..\\teste.txt";
		ByteArrayInputStream in = new ByteArrayInputStream("W\nhacked".getBytes());
		System.setIn(in);
		System.setIn(System.in);
		assertFalse(vul.vulnerableMethod(file));
	}
	
	@Test
	public void testForFileExtension(){
		String file = "teste\\teste.c";
		ByteArrayInputStream in = new ByteArrayInputStream("W\nhacked".getBytes());
		System.setIn(in);
		System.setIn(System.in);
		assertFalse(vul.vulnerableMethod(file));
	}
	
	@Test
	public void testForSingleDot(){
		String file = "teste\\.\\ext\\teste2.txt";
		ByteArrayInputStream in = new ByteArrayInputStream("W\nhacked".getBytes());
		System.setIn(in);
		System.setIn(System.in);
		assertFalse(vul.vulnerableMethod(file));
	}
	
	@Test
	public void testForStrangeFilenames(){
		String file = "teste\\ext\\teste2%@.txt";
		ByteArrayInputStream in = new ByteArrayInputStream("W\nhacked".getBytes());
		System.setIn(in);
		System.setIn(System.in);
		assertFalse(vul.vulnerableMethod(file));
	}
	
	@Test
	public void testForOKWriting(){
		String file = "teste.txt";
		ByteArrayInputStream in = new ByteArrayInputStream("W\nhacked".getBytes());
		System.setIn(in);
		System.setIn(System.in);
		assertTrue(vul.vulnerableMethod(file));
	}
	
	@Test
	public void testForOKReading(){
		String file = "teste.txt";
		ByteArrayInputStream in = new ByteArrayInputStream("R\n".getBytes());
		System.setIn(in);
		System.setIn(System.in);
		assertTrue(vul.vulnerableMethod(file));
	}
	

}
