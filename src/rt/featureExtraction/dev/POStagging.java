package rt.featureExtraction.dev;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
/**
 * 
 * @author rahultejwani
 *
 */
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
	
	/**
	 * 
	 * @return the tagged Sentence
	 * example:
	 */
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
