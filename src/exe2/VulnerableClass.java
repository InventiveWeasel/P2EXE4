package exe2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VulnerableClass {
	//O metodo sera declarado boolean a fim de facilitar a implementacao dos testes
	public boolean vulnerableMethod(String FILENAME){
		
		//verficacao string com o nome do arquivo
		if(!isValidFile(FILENAME)){
			return false;
		}
		
		//While retirado, uma vez que pode causar DOS
	    Scanner console = new Scanner(System.in);
	    System.out.print("Digite a operacao desejada para realizar no arquivo <R para ler um arquivo, "
	    		+ "W para escrever em um arquivo>? ");
		
	    String opr= console.nextLine();
		
	    if (opr.equals("R")){
			BufferedReader br = null;
			FileReader fr = null;
			
			try {

				fr = new FileReader(FILENAME);
				br = new BufferedReader(fr);

				String sCurrentLine;

				br = new BufferedReader(new FileReader(FILENAME));

				while ((sCurrentLine = br.readLine()) != null) {
					System.out.println(sCurrentLine);
				}

			} catch (IOException e) {

				e.printStackTrace();

			} 
		}
		
	    if (opr.equals("W")){
			  BufferedWriter buffWrite;
			  
			  try {
				buffWrite = new BufferedWriter(new FileWriter(FILENAME));
				String linha = "";
				System.out.println("Escreva algo: ");
			    linha = console.nextLine();
			    buffWrite.append(linha + "\n");
			    
			    //Buffer precisa ser fechado para efetuar as alterações
			    buffWrite.close();
			     
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	private boolean isValidFile(String file){
		String[] folders = file.split("\\\\");
		for(int i = 0; i < folders.length; i++){
			if(folders[i].equals(".."))
				return false;
			if(folders[i].equals("."))
				return false;
		}
		
		//Verificando nome de arquivo
		String filename = folders[folders.length-1];
		String[] aux = filename.split("\\.");
		String name = aux[0];
		String extension = aux[aux.length-1];
		
		Pattern pattern = Pattern.compile("[^A-Za-z0-9._]");
		Matcher matcher = pattern.matcher(name);
		if(matcher.find()){
			return false;
		}
		
		if(!extension.equals("txt"))
			return false;
		
		return true;
	}
}
