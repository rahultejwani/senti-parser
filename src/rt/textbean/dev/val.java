package rt.textbean.dev;

public class val
{
	private double score = 0;
	private char pos;
	public val(char c, double d)
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
