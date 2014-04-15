package rt.featureExtraction.dev;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.tartarus.snowball.SnowballProgram;

import rt.textbean.dev.DictionaryBean;
/**
 * This class compares each word extracted from training set, with a lexicon 
 * and keeps only the adjectives and adverbs as the final features
 * @author rahultejwani
 *
 */
public class CleanFeatureSet {
	BufferedReader br;
	BufferedWriter bw;
	SnowballProgram stemmer;
	Class stemClass;
	private static HashMap<String, Double> dictionary = new DictionaryBean().getDictionary();
//	private static HashMap<String, Integer> unigramFeatureSet;
	
	public CleanFeatureSet(){
		try {
			 stemClass = Class.forName("org.tartarus.snowball.ext.englishStemmer");
			 stemmer = (SnowballProgram) stemClass.newInstance();
			 String line = "";
				int count =0;
				try {
					br = new BufferedReader(new FileReader("/home/rahul/Development/SentimentAnalysis/"
							+ "features/featureSetPolarity.txt"));
					bw = new BufferedWriter(new FileWriter("/home/rahul/Development/SentimentAnalysis/"
							+ "features/featureSetPolarity_onlyAdjectives_stemmed.txt"));
					while ((line = br.readLine()) != null) {
						String[] row = line.split(",");
						if(dictionary.containsKey(row[1] + "#a")){
							bw.write(String.valueOf(count));
							bw.write(",");
							stemmer.setCurrent(row[1]);
							row[1] =  stemmer.getCurrent();
							bw.write(row[1]);
							bw.newLine();
							count++;
						}
					//	br.close();
					//	bw.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("File Write Complete :)");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
