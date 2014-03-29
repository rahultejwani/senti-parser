package rt.fileManupulations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * temp class to select training set
 * @author rahultejwani
 *
 */
public class CreateTrainingSet {
	BufferedReader br;
	BufferedWriter bwPolarity;
	BufferedWriter bwIntensity;
	public void writeFiles()
	{
		try {
			br = new BufferedReader(new FileReader("/home/rahul/Development/SentimentAnalysis"
					+ "/parsedReviewsWithLabels"
					+ "/parsed_reviews_after_correction.csv"));
			bwPolarity = new BufferedWriter(new FileWriter("/home/rahul/Development/SentimentAnalysis"
					+ "/parsedReviewsWithLabels"
					+ "/polarityTraining.csv"));
			bwIntensity = new BufferedWriter(new FileWriter("/home/rahul/Development/SentimentAnalysis"
					+ "/parsedReviewsWithLabels"
					+ "/intensityTraining.csv"));
			System.out.println("*************************************************************************");
			System.out.println("Writing Polarity and Intensity training file");
			String line;
			int intensityPositive = 0, intensityNegative = 0;
			int polarityPositive = 0, polarityNegative = 0;
			while ((line = br.readLine()) != null) {
				String[] row = line.split("~~#~");
				if(row.length == 3){
					if(intensityNegative < 2500 && Integer.parseInt(row[2]) == 0)
					{
						intensityNegative++;
						bwIntensity.write(row[0]);
						bwIntensity.write("~~#~");
						bwIntensity.write(row[2]);
						bwIntensity.newLine();

					}
					else if(intensityPositive < 2500 && Integer.parseInt(row[2]) == 1)
					{
						intensityPositive++;
						bwIntensity.write(row[0]);
						bwIntensity.write("~~#~");
						bwIntensity.write(row[2]);
						bwIntensity.newLine();

					}
					else if(polarityNegative < 2500 && Integer.parseInt(row[1]) == 0)
					{
						polarityNegative++;
						bwPolarity.write(row[0]);
						bwPolarity.write("~~#~");
						bwPolarity.write(row[1]);
						bwPolarity.newLine();
					}
					else if(polarityPositive < 2500 && Integer.parseInt(row[1]) == 1)
					{
						polarityPositive++;
						bwPolarity.write(row[0]);
						bwPolarity.write("~~#~");
						bwPolarity.write(row[1]);
						bwPolarity.newLine();
					}
				}
			}
			br.close();
			bwIntensity.close();
			bwPolarity.close();
			System.out.println("Writing Complete :)");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
