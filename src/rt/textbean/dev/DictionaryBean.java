package rt.textbean.dev;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import rt.textbean.dev.propertyBean;

/**
 * @Author rahultejwani
 */
public class DictionaryBean{
	private HashMap<String, WordInfo> Dictionary = new HashMap<>();
	private HashMap<String, Double> Adverb = new HashMap<>();
	public DictionaryBean()
	{
		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(new propertyBean().getLexiconPath()));
			String line;
			while((line = br.readLine()) != null)
			{
				String [] row = line.split(",");
				WordInfo v = new WordInfo(row[1].charAt(0),Double.parseDouble(row[2]));
				Dictionary.put(row[0], v);
			}
			System.out.println("Filliing Adverb map");
			br = new BufferedReader(new FileReader(new propertyBean().getAdverbPath()));
			while((line = br.readLine()) != null)
			{
				String [] row = line.split("\t");
				Adverb.put(row[0], Double.parseDouble(row[1]));
			}
			
		} catch (FileNotFoundException e) {
		
			System.out.println("file not found");
			e.printStackTrace();
		} catch (IOException e) {
			
			System.out.println("IO exception");
			e.printStackTrace();
		}
		
		
	}
	
	
	public HashMap<String, WordInfo> getDictionary() {
		return this.Dictionary;
	}
	public HashMap<String, Double> getAdverbMap() {
		return this.Adverb;
	}
	
	public static void main(String[] args) {
	

	}
	
}
