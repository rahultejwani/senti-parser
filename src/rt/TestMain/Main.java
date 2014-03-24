package rt.TestMain;

import java.util.ArrayList;

import rt.featureExtraction.dev.Ngrams;
import rt.featureExtraction.dev.POStagging;
import rt.textbean.dev.DictionaryBean;
import rt.textbean.dev.ExtractJsonReviews;

public class Main {
	public static void main(String[] args) {
	
	 //check Ngrams class
		Ngrams ng = new Ngrams("I am just checking the output");
		ArrayList<String> ngrams = ng.getNgrams(4);
		System.out.println("{");
		for (String string : ngrams) {
			System.out.println(string + ",");
		}
		System.out.println("}");
		
		//checking the tagger
		/*
		System.out.println("Taggers Output:-");
		POStagging pos = new POStagging("I am just checking the output");
		System.out.println(pos.tag());
		*/
		
		//JSON object test
	//	System.out.println("JSON object Test");
	//	ExtractJsonReviews ejr = new ExtractJsonReviews();
		
		//
		DictionaryBean db = new DictionaryBean();
		
	}
	

}
