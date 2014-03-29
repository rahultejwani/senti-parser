package rt.featureExtraction.dev;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeatureExtractionIntensity {
	
	private String review;
	private String[] BagOfWords;
	
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
