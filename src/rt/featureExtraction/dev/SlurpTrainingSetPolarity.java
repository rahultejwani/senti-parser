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
	private static HashMap<String, Integer> unigramFaetureSet;
	/*
	 * Loads the entire file to memory when the constructor is called
	 */
	public SlurpTrainingSetPolarity() throws IOException
	{
		br = new BufferedReader(new FileReader(sourcePath));
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] row = line.split("~~#~");
		//	row[0] = row[0].replaceAll("[^a-zA-Z ]", " ").toLowerCase();
			if(row.length==2)
			{
				lines.add(row[0]);

				polarity.add(Integer.parseInt(row[1]));
			}
		}
		br.close();
		
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
