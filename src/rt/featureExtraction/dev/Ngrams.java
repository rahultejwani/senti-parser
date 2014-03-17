package rt.featureExtraction.dev;

import java.util.ArrayList;

public class Ngrams {
	
	private String sentence;
	private String[] WordSplit;
	public Ngrams(String string)
	{
		this.setSentence(string);
		this.setWordSplit(string.split("\\s"));
	}
	public ArrayList<String>  getBigrams()
	{
		ArrayList<String> bigrams = new ArrayList<>();
		for (int i = 0; i < WordSplit.length-1; i++) {
			String bigram = WordSplit[i] + "_" + WordSplit[i+1];
			bigrams.add(bigram);
		}
		return bigrams;
		
		
		
	}
	public ArrayList<String>  getTrigrams()
	{
		ArrayList<String> trigrams = new ArrayList<>();
		for (int i = 0; i < WordSplit.length-2; i++) {
			String trigram = WordSplit[i] + "_" + WordSplit[i+1] + "_" + WordSplit[i+2];
			trigrams.add(trigram);
		}
		return trigrams;
		
		
		
	}
	public String[] getWordSplit() {
		return WordSplit;
	}
	public void setWordSplit(String[] wordSplit) {
		WordSplit = wordSplit;
	}
	public String getSentence() {
		return sentence;
	}
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

}
