package rt.featureExtraction.dev;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

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
	private static HashMap<String, Double> dictionary = new DictionaryBean().getDictionary();
//	private static HashMap<String, Integer> unigramFeatureSet;
	public CleanFeatureSet(){
		try {
			br = new BufferedReader(new FileReader("/home/rahul/Development/SentimentAnalysis/"
					+ "features/featureSetPolarity.txt"));
			bw = new BufferedWriter(new FileWriter("/home/rahul/Development/SentimentAnalysis/"
					+ "features/featureSetPolarity_1_02.txt"));
			String line = "";
			int count =0;
			while ((line = br.readLine()) != null) {
				String[] row = line.split(",");
				if(dictionary.containsKey(row[1] + "#a") || dictionary.containsKey(row[1] + "#r")){
					bw.write(String.valueOf(count));
					bw.write(",");
					bw.write(row[1]);
					bw.newLine();
					count++;
				}
			}
			br.close();
			bw.close();
			System.out.println("File Write Complete :)");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
