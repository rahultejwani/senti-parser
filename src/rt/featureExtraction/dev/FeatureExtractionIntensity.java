package rt.featureExtraction.dev;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeatureExtractionIntensity {
	
	private String review;
	private String[] BagOfWords;
	private static HashMap<String, Integer> unigramFeatureSet;
	private BufferedReader br;
	
	
	public void staticObjects(){
		if(unigramFeatureSet.size() == 0)
			load();
	}
	public void load(){
		try {
			br = new BufferedReader(new FileReader("/home/rahul/Development/"
					+ "SentimentAnalysis/IntensityUnigramSet.csv"));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] row = line.split("#");
				unigramFeatureSet.put(row[0], 0);
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public int getCapitalScore(String str)
	{
		int count =0;
		Pattern pattern = Pattern.compile("\\s[A-Z]+\\s");
		Matcher m = pattern.matcher(str);
		while (m.find())
		{
			if((m.end()-m.start()) > 2)
				count++;

		}
		return count;
	}
	
	
	public double getPunctuationScore()
	{
		int exclamationCount = 0;
		int questionCount = 0;
		int dotCount = 0;
		int prevStart = -3;
		double punctuationScore = 0.0f;
		/*
		 * Thing to consider.......
		 * if semicolon is found then split the sentence and each split act
		 * as a individual Sentence
		 */
		Pattern pattern = Pattern.compile("[.!?]");
		Matcher m = pattern.matcher(review);
		while (m.find()) {

			if(review.charAt(m.start()) == '!')
			{
				if((m.start() - prevStart) > 2)
				{
					exclamationCount++;
				}
			}
			else if(review.charAt(m.start()) == '?')
			{
				if((m.start() - prevStart) > 2)
				{
					questionCount++;
				}
			}
			else
			{
				if((m.start() - prevStart) > 2)
				{
					dotCount++;
				}
			}
			prevStart = m.start();  
		}
		punctuationScore =  ((0.8 * exclamationCount + 0.1 * questionCount + 0.1 * dotCount));

		return punctuationScore;
	}
}
