package rt.textbean.dev;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ExtractJsonReviews {
	
	public ExtractJsonReviews()
	{
		JSONParser parser = new JSONParser();
		BufferedReader br; 
		BufferedWriter bufferedWriter;
		try {
			bufferedWriter = new BufferedWriter(
					new FileWriter("/home/rahul/Development/SentimentAnalysis/parsedReviewsWithLabels"
							+ "/parsed_reviews_after_correction.csv"));
			br = new BufferedReader(new FileReader(new propertyBean().getReviewPath()));
			String line;
			int count = 0;
//			for (int i = 0; i < 7500; i++) {
//				br.readLine();
//			}
			while((line = br.readLine()) != null)
			{
				Object obj = parser.parse(line);
				JSONObject jsonObject = (JSONObject) obj;
				String text = (String) jsonObject.get("text");
				text = text.replaceAll("\\n", "");
				long rating = (long)(jsonObject.get("stars"));
			//	double rating = Double.parseDouble((String)jsonObject.get("stars"));
				//matching 5 stars to (P,I) = (1,1)
				if(rating == 5)
				{
					bufferedWriter.write(text);;
					bufferedWriter.write("~~#~");
					bufferedWriter.write("1");
					bufferedWriter.write("~~#~");
					bufferedWriter.write("1");
					bufferedWriter.newLine();
				}
				//matching 1 stars to (P,I) = (0,0)
				else if(rating == 1)
				{
					bufferedWriter.write(text);
					bufferedWriter.write("~~#~");
					bufferedWriter.write("0");
					bufferedWriter.write("~~#~");
					bufferedWriter.write("1");
					bufferedWriter.newLine();
				}
				//matching 3 stars to (P,I) = (1,0)
				else if(rating == 3)
				{
					bufferedWriter.write(text);
					bufferedWriter.write("~~#~");
					bufferedWriter.write("1");
					bufferedWriter.write("~~#~");
					bufferedWriter.write("0");
					bufferedWriter.newLine();
					
				}
				//matching 2 stars to (P,I) = (0,1)
				
				count++;
				//taking first 1500 for testing, will scale the values in future
				if(count>80000)
					break;
				
				//System.out.println(name);
			}
			
			
	 
//			long age = (Long) jsonObject.get("age");
//			System.out.println(age);
//	 
//			// loop array
//			JSONArray msg = (JSONArray) jsonObject.get("messages");
//			Iterator<String> iterator = msg.iterator();
//			while (iterator.hasNext()) {
//				System.out.println(iterator.next());
//			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	 
	}
	
}
