package rt.textbean.dev;
/**
 * Structure class used for storing POS of the word and its polarity score.
 * @author rahul
 *
 */
public class WordInfo
{
	private double score = 0;
	private char pos;
	public WordInfo(char c, double d)
	{
		this.setPos(c);
		this.setScore(d);
	}
	public char getPos() {
		return pos;
	}
	public void setPos(char pos) {
		this.pos = pos;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
	
}
