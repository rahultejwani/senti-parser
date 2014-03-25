package rt.TestMain;

import java.util.ArrayList;

import rt.featureExtraction.dev.FeatureExtractionPolarity;
import rt.featureExtraction.dev.Ngrams;
import rt.featureExtraction.dev.POStagging;
import rt.featureExtraction.dev.WritePolarityFeatures;
import rt.textbean.dev.DictionaryBean;
import rt.textbean.dev.ExtractJsonReviews;

public class Main {
	public static void main(String[] args) {
	
	 //check Ngrams class
	/*
		Ngrams ng = new Ngrams("I am just checking the output");
		ArrayList<String> ngrams = ng.getNgrams(4);
		System.out.println("{");
		for (String string : ngrams) {
			System.out.println(string + ",");
		}
		System.out.println("}");
		
		*/
		
		
		//checking the tagger
		/*
		System.out.println("Taggers Output:-");
		POStagging pos = new POStagging("I am just checking the output");
		System.out.println(pos.tag());
		*/
		
		//JSON object test
	//	System.out.println("JSON object Test");
	ExtractJsonReviews ejr = new ExtractJsonReviews();
		
		// testing polarity extracted features
		
		/*
		FeatureExtractionPolarity fep = new FeatureExtractionPolarity("i am very good today. Feeling a"
				+ " lot better. :) hope it continues the same. Excited!!! :D ");
		double unigram = fep.getUnigramScore();
		double bigram = fep.getBigramFirstScore();
		System.out.println("Unigram Score:"+ unigram);
		System.out.println("Bigram Score:"+ bigram);
		System.out.println("emoticon score:" + fep.getEmotcionScore());
		System.out.println("Size:"+ fep.getWordCount());
		//System.out.println("punctuation score:" + fep.getPunctuationScore());
		 
		 */
		
		//writing polarity features file
		WritePolarityFeatures wpf = new WritePolarityFeatures();
		wpf.write();
		
		
	}
	

}
