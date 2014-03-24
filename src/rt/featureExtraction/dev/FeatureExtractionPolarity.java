
package rt.featureExtraction.dev;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import rt.textbean.dev.propertyBean;


public class FeatureExtractionPolarity {
	private HashMap<String, Integer> emotScoreMap = new HashMap<String,Integer>();
	private HashMap<String, Integer> negationList = new HashMap<>();

	public FeatureExtractionPolarity()
	{
		
		negationListMap();
		fillEmoticonMap();
		

	}

	public void fillEmoticonMap()
	{
		//happy emoticons
		this.emotScoreMap.put(":)", 1);
		this.emotScoreMap.put(":-)", 1);
		this.emotScoreMap.put(":D", 1);
		this.emotScoreMap.put(":-D", 1);
		this.emotScoreMap.put(";)", 1);
		this.emotScoreMap.put(";-)", 1);

		//Sad emoticons
		this.emotScoreMap.put(":(", -1);
		this.emotScoreMap.put(":-(", -1);
		this.emotScoreMap.put(":'(", -1);
		this.emotScoreMap.put(":-'(", -1);
		this.emotScoreMap.put(":/", -1);
		this.emotScoreMap.put("</3", -1);
		//this.emotScoreMap.put("", 0);




	}
	public void negationListMap()
	{
		this.negationList.put("no", -1);
		this.negationList.put("not", -1);
		this.negationList.put("none", -1);
		this.negationList.put("nobody", -1);
		this.negationList.put("nothing", -1);
		this.negationList.put("neither", -1);
		this.negationList.put("nowhere", -1);
		this.negationList.put("never", -1);
		this.negationList.put("hardly", -1);
		this.negationList.put("scarcely", -1);
		this.negationList.put("barely", -1);
		this.negationList.put("doesn’t", -1);
		this.negationList.put("isn’t", -1);
		this.negationList.put("wasn’t", -1);
		this.negationList.put("shouldn’t", -1);
		this.negationList.put("wouldn’t", -1);
		this.negationList.put("couldn’t", -1);
		this.negationList.put("won’t", -1);
		this.negationList.put("can’t", -1);
		this.negationList.put("don’t", -1);
		this.negationList.put("ain't", -1);
		this.negationList.put("wont", -1);
		this.negationList.put("cant", -1);
		this.negationList.put("dont", -1);
		this.negationList.put("aint", -1);
		this.negationList.put("doesnt", -1);
		this.negationList.put("isnt", -1);
		this.negationList.put("wasnt", -1);
		this.negationList.put("shouldnt", -1);
		this.negationList.put("wouldnt", -1);
		this.negationList.put("couldnt", -1);

	}


	
	public int getEmotcionScore(String str)
	{
		
		int netScore = 0;
		
		return netScore;
	}
	public double getPunctuationScore(String str)
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
		Matcher m = pattern.matcher(str);
		while (m.find()) {

			if(str.charAt(m.start()) == '!')
			{
				if((m.start() - prevStart) > 2)
				{
					exclamationCount++;
				}
			}
			else if(str.charAt(m.start()) == '?')
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

	public double getUnigramScore(String str)
	{
		double score =0;
		
		return score;
	}

	public double getBigramFirstScore(String str)
	{
		double score =0;
		
		return score;	
	}

	public void writeToFile()
	{
		
	}



}
