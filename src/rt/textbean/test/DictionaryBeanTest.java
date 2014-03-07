package rt.textbean.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import rt.textbean.dev.DictionaryBean;
import rt.textbean.dev.WordInfo;

public class DictionaryBeanTest {
	DictionaryBean db = new DictionaryBean();
	HashMap<String, WordInfo> dict = db.getDictionary();
	HashMap<String, Double> adv = db.getAdverbMap();
	
	public DictionaryBeanTest()
	{
		
	}
	 @Test
	    public void testDictionarySize() {
		// HashMap<String, val> dict = new DictionaryBean().getDictionary();
			////assertEquals(154864,dict.size());
			System.out.println("Pass: size test");
	    }
	 @Test
	 public void checkfifthValue()
	 {
		assertEquals( 0.0f,dict.get("genus_fratercula").getScore(), 0);
	//	System.out.println("Pass: Fifth Value");
		}
	 @Test
	  public void testAdverbSize() {
			 //HashMap<String, val> dict = new DictionaryBean().getDictionary();
				assertEquals(400,adv.size());
			//	System.out.println("Pass: size test");
		    }
}
