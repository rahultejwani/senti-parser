package rt.featureExtraction.dev;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class WriteIntensityFeatures {
	private ArrayList<String> lines;
	private ArrayList<Integer> Intensity;
	SlurpTrainingSetIntensity sts ;
	public WriteIntensityFeatures(){
		try {
			sts = new SlurpTrainingSetIntensity();
			this.lines = sts.getLines();
			//	this.intensity = sts.getIntensityList();
			this.Intensity = sts.getIntensityList();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void  write()
	{
		System.out.println("Writing features file to location:"
				+ "/home/rahul/Development/SentimentAnalysis/features/");
		int count = 0;
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(
					new FileWriter("/home/rahul/Development/SentimentAnalysis/features"
							+ "/f_i_2086.csv"));

			for (String string : lines) {
				FeatureExtractionIntensity fep =  new FeatureExtractionIntensity(string);
				bufferedWriter.write(String.valueOf(fep.getCapitalScore()));
				bufferedWriter.write(",");
				bufferedWriter.write(String.valueOf(fep.getElongatedScore()));
				bufferedWriter.write(",");
				bufferedWriter.write(String.valueOf(fep.getExclamationMarkScore()));
				bufferedWriter.write(",");
				bufferedWriter.write(String.valueOf(fep.getQuesionMarkScore()));
				bufferedWriter.write(",");
				HashMap<Integer, Integer> taggedFeatures = fep.getTaggedFeatures();
				HashMap<Integer, Boolean> unigramFeatures = fep.getReviewUnigrams();
				//to write unigram featues set depending on the size.		
				for (int i = 0; i < 2086; i++) {
					if(unigramFeatures.containsKey(i)){
						bufferedWriter.write("1");
						bufferedWriter.write(",");
					}
					else{
						bufferedWriter.write("0");
						bufferedWriter.write(",");
					}
				}



				//to write 180 features
/*							
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
				
*/
				bufferedWriter.write(String.valueOf(Intensity.get(count)));


				bufferedWriter.newLine();
				count++;
			}
			bufferedWriter.close();
			System.out.println("Feature file write complete :)");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
