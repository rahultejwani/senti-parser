package rt.featureExtraction.dev;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.tartarus.snowball.SnowballProgram;

import rt.textbean.dev.DictionaryBean;
/**
 * Class to extract intensity features from a given text
 * @author rahultejwani
 *
 */
public class FeatureExtractionIntensity {

	private String review;
	private String[] BagOfWords;
	private static HashMap<String, Integer> unigramFeatureSet = new HashMap<>();
	private BufferedReader br;
	private Ngrams ngrams;
	private static HashMap<String, HashMap<String, Integer>> taggedDictionary = new HashMap<>();
	private HashMap<Integer, Integer> taggedFeatures = new HashMap<>();
	static StopWords stopwords ;
	static DictionaryBean db;
	private HashMap<Integer, Boolean> reviewUnigrams = new HashMap<>();
	/**
	 * Constructor that takes the entire review as a String input and extracts features from it
	 * @param review
	 */
	public FeatureExtractionIntensity(String review){

		this.review = review;
		ngrams =  new Ngrams(review);
		staticObjects();
	}


	public void staticObjects(){
		if(unigramFeatureSet.size() == 0)
			load();
		if(stopwords == null)
			stopwords = new StopWords();
		if(db == null)
			db = new DictionaryBean();
		if(taggedDictionary.size()== 0)
			taggedDictionary = db.getTaggedDictionary();
	}
	/**
	 * This function loads the Unigram feature set to memory
	 */
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

	/**
	 * get the count of all caps words like "GOOD"
	 * @return
	 */
	public int getCapitalScore()
	{
		int count =0;
		Pattern pattern = Pattern.compile("\\s[A-Z]+\\s");
		Matcher m = pattern.matcher(review);
		while (m.find())
		{
			if((m.end()-m.start()) > 2)
				count++;

		}
		return count;
	}

	/**
	 * returns the count of question marks
	 * @return
	 */
	public double getQuesionMarkScore()
	{
		int questionCount = 0;
		int prevStart = -3;
		Pattern pattern = Pattern.compile("[.!?]");
		Matcher m = pattern.matcher(review);
		while (m.find()) {
			if(review.charAt(m.start()) == '?')
			{
				if((m.start() - prevStart) > 2)
				{
					questionCount++;
				}
			}

			prevStart = m.start();  
		}
		return questionCount;
	}
	/**
	 * returns the count of exclamation marks
	 * @return
	 */
	public double getExclamationMarkScore()
	{
		int exclamationCount = 0;
		int prevStart = -3;
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
			prevStart = m.start();  
		}
		return exclamationCount;
	}


	/**
	 * get count of words like "gooood"
	 * @return
	 */
	public int getElongatedScore(){
		int result =0;
		Pattern pattern = Pattern.compile("([a-zA-Z])\\1{2,}");
		Matcher m = pattern.matcher(review);
		while (m.find()) {
			result++;
		}
		return result;
	}
	/**
	 * This functions writes the features to a map in the format
	 * <Feature_number, Category_count> 
	 * @return
	 */
	public HashMap<Integer, Integer> getTaggedFeatures(){

		String FilterReview = review.replaceAll("[^a-zA-Z ]", " ").toLowerCase();
		String [] words = FilterReview.split("\\s+");
		for (String string : words) {
			if(!stopwords.is(string)){
				if(taggedDictionary.containsKey(string)){
					//System.out.println("contains key");
					HashMap<String, Integer> value = taggedDictionary.get(string);
					for(Map.Entry<String, Integer> e: value.entrySet()){
						if(!taggedFeatures.containsKey(e.getValue()))
							taggedFeatures.put(e.getValue(), 1);
						else{
							int val = taggedFeatures.get(e.getValue()) + 1;
							taggedFeatures.put(e.getValue(), val);
						}
					}
				}
			}
		} 
		return this.taggedFeatures;
	}
	public HashMap<Integer, Boolean> getReviewUnigrams(){

		//			stemClass = Class.forName("org.tartarus.snowball.ext.englishStemmer");
		//			stemmer = (SnowballProgram) stemClass.newInstance();
		String FilterReview = review.replaceAll("[^a-zA-Z ]", " ").toLowerCase();
		String [] words = FilterReview.split("\\s+");
		for (String string : words) {
			if(!stopwords.is(string)){
				//					stemmer.setCurrent(string);
				//					string = stemmer.getCurrent();
				if(unigramFeatureSet.containsKey(string)){
					this.reviewUnigrams.put(unigramFeatureSet.get(string), true);
				}
			}



		}
		return this.reviewUnigrams;
	}
}



