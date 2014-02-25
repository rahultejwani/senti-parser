package rt.textbean.dev;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import rt.textbean.dev.propertyBean;

/*
 * @Author rahultejwani
 */
public class DictionaryBean{
	private HashMap<String, val> Dictionary = new HashMap<>();
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
				val v = new val(row[1].charAt(0),Double.parseDouble(row[2]));
				Dictionary.put(row[0], v);
			}
			System.out.println("Filliing Adverb map");
			br = new BufferedReader(new FileReader(new propertyBean().getAdverbPath()));
		//	bw = new BufferedWriter(new FileWriter("/home/rahul/Development/SentimentAnalysis/adverbScore.csv"));
		//	System.out.println("Writing to file");
			while((line = br.readLine()) != null)
			{
				String [] row = line.split("\t");
				Adverb.put(row[0], Double.parseDouble(row[1]));
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("file not found");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IO exception");
			e.printStackTrace();
		}
		
		
	}
	
	
	public HashMap<String, val> getDictionary() {
		return this.Dictionary;
	}
	public HashMap<String, Double> getAdverbMap() {
		return this.Adverb;
	}
	
	public static void main(String[] args) {
		DictionaryBean swb = new DictionaryBean();
	//	HashMap<String, Double> dict =swb.getAdverbMap();
	//	Set<String> set = dict.keySet();
//		for (String string : set) {
//			System.out.println(string+ "\t" + dict.get(string));
//			
//		}
	}
	
}
