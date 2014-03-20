package rt.featureExtraction.dev;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class POStagging {
	private String sentence;
	/**
	 * Constructor
	 * @param stringToTag
	 */
	public POStagging(String stringToTag)
	{
		setSentence(stringToTag);
	}
	
	public String tag()
	{
		
		 MaxentTagger tagger = new MaxentTagger(
	                "./taggers/english-left3words-distsim.tagger");
		 return tagger.tagString(sentence);
	}
	
	/**
	 * @return the sentence
	 */
	public String getSentence() {
		return sentence;
	}
	/**
	 * @param set the sentence 
	 */
	private void setSentence(String sentence) {
		this.sentence = sentence;
	}
	

}
