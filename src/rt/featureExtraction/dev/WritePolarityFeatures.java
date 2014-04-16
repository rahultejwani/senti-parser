package rt.featureExtraction.dev;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class WritePolarityFeatures {
	private ArrayList<String> lines;
	private ArrayList<Integer> polarity;
	
//	private ArrayList<Integer> intensity;
	SlurpTrainingSetPolarity sts ;
	
	public WritePolarityFeatures() {
		
		try {
			
			sts = new SlurpTrainingSetPolarity();
			this.lines = sts.getLines();
		//	this.intensity = sts.getIntensityList();
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
					new FileWriter("/home/rahul/Development/SentimentAnalysis/features"
							+ "/f_tagged_180.csv"));
			
//			bufferedWriter.write("SentiUnigram");
//			bufferedWriter.write(",");
//			bufferedWriter.write("SentiBigram");
//			bufferedWriter.write(",");
//			bufferedWriter.write("SentiTrigram");
//			bufferedWriter.write(",");
//			for (int i = 0; i < 2505; i++) {
//				bufferedWriter.write("Faeture" + String.valueOf(i+1));
//				bufferedWriter.write(",");
//			}
//			bufferedWriter.write("EmoticonScore");
//			bufferedWriter.write(",");
//			bufferedWriter.write("Label");
//			bufferedWriter.newLine();
			for (String string : lines) {
				FeatureExtractionPolarity fep =  new FeatureExtractionPolarity(string);
		//		bufferedWriter.write(String.valueOf(fep.getWordCount()));
		//		bufferedWriter.write(",");
				
				
				bufferedWriter.write(String.valueOf(fep.getUnigramScore()));
				bufferedWriter.write(",");
				bufferedWriter.write(String.valueOf(fep.getBigramFirstScore()));
				bufferedWriter.write(",");
				bufferedWriter.write(String.valueOf(fep.getTrigramScore()));
				bufferedWriter.write(",");
				HashMap<Integer, Boolean> unigramFeatures = fep.getReviewUnigrams();
				HashMap<Integer, Integer> taggedFeatures = fep.getTaggedFeatures();
				
/*				
 * for (int i = 0; i < 1380; i++) {
					if(unigramFeatures.containsKey(i)){
						bufferedWriter.write("1");
						bufferedWriter.write(",");
					}
					else{
						bufferedWriter.write("0");
						bufferedWriter.write(",");
					}
				}
*/
				
				for (int i = 2; i < 180; i++) {
					if(taggedFeatures.containsKey(i))
					{
						bufferedWriter.write(String.valueOf(taggedFeatures.get(i)));
					}
					else{
						bufferedWriter.write("0");
					}
					bufferedWriter.write(",");
				}
				bufferedWriter.write(String.valueOf(fep.getEmotcionScore()));
				bufferedWriter.write(",");
				bufferedWriter.write(String.valueOf(polarity.get(count)));
				
				
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
