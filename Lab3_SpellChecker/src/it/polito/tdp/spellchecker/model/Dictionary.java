package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {

	private List<String> dizionario = new ArrayList<String>();
	private boolean dicotomica = false;
	
	public void loadDictionary(String language){
		try {
			FileReader fr;
			if(language.equals("English")){
				fr = new FileReader("rsc/English.txt");
			} else {
				fr = new FileReader("rsc/Italian.txt");
			}
			
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
				
				dizionario.add(word);
			}
			br.close();
		} catch (IOException e){
			System. out.println("Errore nella lettura del file");
		}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<String> listaDaCorreggere= new ArrayList<String>(inputTextList);
		
		List<RichWord> words= new ArrayList<RichWord>();
		
		for(String parola: listaDaCorreggere){
			RichWord rwtemp;
			
			if(dicotomica){
				if (binarySearch(parola.toLowerCase())) 
					rwtemp = new RichWord(parola, true);
				else 
					rwtemp = new RichWord(parola, false);
				
				words.add(rwtemp);
			}
			else{
			if(dizionario.contains(parola)){
				rwtemp= new RichWord(parola, true);
						words.add(rwtemp);
			} else {
				rwtemp= new RichWord(parola, false);
						words.add(rwtemp);
					}
			}
		}
		return words;
			
	}

	private boolean binarySearch(String word) {
	int inizio = 0;
	int fine = dizionario.size();
	     
	  while(inizio!=fine){
		  int meta = inizio + (fine - inizio)/2;
	     if(word.compareToIgnoreCase(dizionario.get(meta))==0){
	    	 return true;
	     }
	     if(word.compareToIgnoreCase(dizionario.get(meta))>0){
	    	 inizio = meta+1;
	     }
	     else
	    	 fine= meta;
	}
	  return false;
	}
}
