package rt.featureExtraction.dev;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import rt.textbean.dev.propertyBean;

/**
 * Class for loading the training set in memory
 * @author rahultejwani
 *
 */

public class SlurpTrainingSetPolarity {
	static final String sourcePath = (new propertyBean().getPolarityTraining());
	private BufferedReader br;
	private ArrayList<String> lines = new ArrayList<>();
	private ArrayList<Integer> polarity = new ArrayList<>();
	private HashMap<String, Boolean> featureSpace;
	private StopWords stopWords=new StopWords();
	private static HashMap<String, Integer> unigramFaetureSet;
	/*
	 * Loads the entire file to memory when the constructor is called
	 */
	public SlurpTrainingSetPolarity() throws IOException
	{
		featureSpace = new HashMap<>();
		br = new BufferedReader(new FileReader(sourcePath));
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] row = line.split("~~#~");
			row[0] = row[0].replaceAll("[^a-zA-Z ]", " ").toLowerCase();
			//System.out.println(row[0]);
			//System.out.println(row[0]);
			//row[0]= row[0].replaceAll("(..)+", "");
			String[] words = row[0].split("\\s+");
			for (String word : words) {
				if (!stopWords.is(word)){
					featureSpace.put(word, true);
				}
			}
			if(row.length==2)
			{
				lines.add(row[0]);

				polarity.add(Integer.parseInt(row[1]));
			}
		}
		br.close();
		BufferedWriter bufferedWriter = new BufferedWriter(
				new FileWriter("/home/rahul/Development/SentimentAnalysis/features"
						+ "/featureSet.txt"));
		int count = 1;
		for (Entry<String, Boolean> e : featureSpace.entrySet()) {
			bufferedWriter.write(String.valueOf(count));
			bufferedWriter.write(",");
			bufferedWriter.write(e.getKey());
			bufferedWriter.newLine();
			count++;
		}
		bufferedWriter.close();
		unigramFaetureSet = new HashMap<>();
		load();
	}

	public void load(){
		try {
			br = new BufferedReader(new FileReader("/home/rahul/Development/SentimentAnalysis/features"
					+ "/featureSet.txt"));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] row = line.split(",");
				unigramFaetureSet.put(row[1], Integer.parseInt(row[0]));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

public ArrayList<String> getLines()
{
	return this.lines;
}
public ArrayList<Integer> getPolarityList()
{
	return this.polarity;
}

public HashMap<String, Integer> getUnigramFeatureSet()
{
	return unigramFaetureSet;
}
}
