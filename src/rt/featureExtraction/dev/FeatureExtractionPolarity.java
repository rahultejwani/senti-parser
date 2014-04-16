
package rt.featureExtraction.dev;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.tartarus.snowball.SnowballProgram;

import rt.textbean.dev.DictionaryBean;



public class FeatureExtractionPolarity {
	private HashMap<String, Integer> emotScoreMap = new HashMap<String,Integer>();
	private HashMap<String, Integer> negationList = new HashMap<>();
	private Ngrams ngrams;
	private String review;
	private int  NumOfSentences = 0;
	private static HashMap<String, Double> dictionary = new HashMap<>() ;
	private static HashMap<String, HashMap<String, Integer>> taggedDictionary = new HashMap<>();
	private HashMap<Integer, Integer> taggedFeatures = new HashMap<>();
	private String[] BagOfWords;
	private ArrayList<String> Lines;
	private BufferedReader br;
	private static HashMap<String, Integer> unigramFeatureSet;
	private HashMap<Integer, Boolean> reviewUnigrams = new HashMap<>();
	StopWords stopwords;
	static DictionaryBean db;
	SnowballProgram stemmer;
	Class stemClass;
	public FeatureExtractionPolarity(String review)
	{		
		this.review = review;
		ngrams =  new Ngrams(review);
		this.Lines = new ArrayList<>();
		unigramFeatureSet =  new HashMap<>();
		BagOfWords = ngrams.getWordSplit();
		staticObjects();
		BreakInLines();

	}
	public void staticObjects(){
		if(db == null)
			db = new DictionaryBean();
		if(taggedDictionary.size()== 0)
			taggedDictionary = db.getTaggedDictionary();
		if(dictionary.size() == 0)
			dictionary = db.getDictionary();
		if(negationList.size() == 0 )
			negationListMap();
		if(emotScoreMap.size() == 0)
			fillEmoticonMap();
		if(unigramFeatureSet.size() == 0)
			load();
		if(stopwords == null)
			stopwords = new StopWords();
		
	}
	public void  cleanReview(){
		review = review.replaceAll("(..)+", "");
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
	public void load(){
		try {
			//non stemmed emotional tagged words
			br = new BufferedReader(new FileReader("/home/rahul/Development/SentimentAnalysis/PolarityUnigramSet.csv"));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] row = line.split(",");
				unigramFeatureSet.put(row[1].toLowerCase(), Integer.parseInt(row[0]));
				//	System.out.println(row[0]);
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
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



	public int getEmotcionScore()
	{
		int netScore = 0;
		for (Map.Entry<String, Integer> e : emotScoreMap.entrySet()) {
			if(review.contains(e.getKey()))
				netScore+=e.getValue();
		}
		return netScore;
	}


	public double getUnigramScore()
	{
		boolean b = false;
		double score =0;
		double lineScore = 0;
		int wordPerLine = 0;
		for (String line : Lines) {
			String[] sentence = line.split("\\s+");
			b = false;
			for (String string : sentence) {

				if(negationList.containsKey(string) || b){
					b = true;
					if(dictionary.containsKey(string + "#a"))
						lineScore+=(dictionary.get(string + "#a")* -1);
					else if (dictionary.containsKey(string + "#r"))
						lineScore+=(dictionary.get(string + "#r") * -1);
					else if (dictionary.containsKey(string + "#n"))
						lineScore+=(dictionary.get(string + "#n") * -1);
					else if (dictionary.containsKey(string + "#v"))
						lineScore+=(dictionary.get(string + "#v") * -1);
					wordPerLine++;
				}
				else
				{
					if(dictionary.containsKey(string + "#a"))
						lineScore+=dictionary.get(string + "#a");
					else if (dictionary.containsKey(string + "#r"))
						lineScore+=dictionary.get(string + "#r");
					else if (dictionary.containsKey(string + "#n"))
						lineScore+=dictionary.get(string + "#n");
					else if (dictionary.containsKey(string + "#v"))
						lineScore+=dictionary.get(string + "#v");
					wordPerLine++;
				}
			}
			score += lineScore;
			score /= wordPerLine;
			wordPerLine = 0;
		}

		return score/NumOfSentences;
	}

	public double getBigramFirstScore()
	{
		ArrayList<String> bigrams = ngrams.getNgrams(2);
		double score =0;

		for (String string : bigrams) {
			if(dictionary.containsKey(string + "#a"))
				score+=dictionary.get(string + "#a");
			else if (dictionary.containsKey(string + "#r"))
				score+=dictionary.get(string + "#r");
			else if (dictionary.containsKey(string + "#n"))
				score+=dictionary.get(string + "#n");
			else if (dictionary.containsKey(string + "#v"))
				score+=dictionary.get(string + "#v");

		}

		return score;	
	}

	public double getTrigramScore()
	{
		ArrayList<String> trigrams = ngrams.getNgrams(3);
		double score =0;

		for (String string : trigrams) {
			if(dictionary.containsKey(string + "#a"))
				score+=dictionary.get(string + "#a");
			else if (dictionary.containsKey(string + "#r"))
				score+=dictionary.get(string + "#r");
			else if (dictionary.containsKey(string + "#n"))
				score+=dictionary.get(string + "#n");
			else if (dictionary.containsKey(string + "#v"))
				score+=dictionary.get(string + "#v");

		}

		return score;	
	}

	public int getWordCount()
	{
		return this.BagOfWords.length;
	}
	/**
	 * Extract out sentences from the reviews.
	 * to take into account the negative lists
	 * Later Check Stanford Document tokenizer
	 */

	private void BreakInLines()
	{
		//this.Lines = review.split(". ");
		BreakIterator border = BreakIterator.getSentenceInstance(Locale.US);
		border.setText(review);
		//	System.out.println(review);
		int start = border.first();
		//iterate, creating sentences out of all the Strings between the given boundaries
		for (int end = border.next(); end != BreakIterator.DONE; start = end, end = border.next()) {
			//System.out.println(review.substring(start,end));
			Lines.add(review.substring(start, end));
			NumOfSentences++;
		}
		//System.out.println(NumOfSentences);
	}


	public HashMap<Integer, Boolean> getReviewUnigrams(){
		try {
			stemClass = Class.forName("org.tartarus.snowball.ext.englishStemmer");
			stemmer = (SnowballProgram) stemClass.newInstance();
			String FilterReview = review.replaceAll("[^a-zA-Z ]", " ").toLowerCase();
			String [] words = FilterReview.split("\\s+");
			for (String string : words) {
				if(!stopwords.is(string)){
					stemmer.setCurrent(string);
					string = stemmer.getCurrent();
					if(unigramFeatureSet.containsKey(string)){
						this.reviewUnigrams.put(unigramFeatureSet.get(string), true);
					}
				}
			}
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

		return this.reviewUnigrams;
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

}
