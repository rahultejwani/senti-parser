package rt.featureExtraction.dev;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WritePolarityFeatures {
	private ArrayList<String> lines;
	private ArrayList<Integer> polarity;
	private ArrayList<Integer> intensity;
	SlurpTrainingSet sts ;
	public WritePolarityFeatures() {
		
		try {
			
			sts = new SlurpTrainingSet();
			this.lines = sts.getLines();
			this.intensity = sts.getIntensityList();
			this.polarity = sts.getPolarityList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Write features in the format: (length,Unigram_Score,Bigram_Score,Emoticon_Score)
	 */
	public void  write()
	{
		System.out.println("Writing features file to location:"
				+ "/home/rahul/Development/SentimentAnalysis/features/");
		int count = 0;
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(
					new FileWriter("/home/rahul/Development/SentimentAnalysis/features/featurePolarityTest_1_01.csv"));
			for (String string : lines) {
				FeatureExtractionPolarity fep =  new FeatureExtractionPolarity(string);
		//		bufferedWriter.write(String.valueOf(fep.getWordCount()));
		//		bufferedWriter.write(",");
				
				bufferedWriter.write(String.valueOf(polarity.get(count)));
				bufferedWriter.write(",");
				bufferedWriter.write(String.valueOf(fep.getUnigramScore()));
				bufferedWriter.write(",");
				bufferedWriter.write(String.valueOf(fep.getBigramFirstScore()));
				bufferedWriter.write(",");
				bufferedWriter.write(String.valueOf(fep.getEmotcionScore()));
				
				bufferedWriter.newLine();
				count++;
			}
			bufferedWriter.close();
			System.out.println("Feature file write complete :)");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
