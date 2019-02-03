package Core;


/**
 * This class encapsulates a timestamp for a row of data (one entry per channel)
 *
 * @author javiergs
 * @version 20190130
 */
public class Data {

    private double time;
    private int x;
    private int y;
    private int scroll;
    private double value;

    public Data(double time,double value) {
        this.time=time;
        this.value=value;
    }
    public Data(double time, int x,int y, int scroll){
        this.x=x;
        this.y=y;
        this.scroll=scroll;
    }
    public void setData(double data) {
      time=0;
      value=data;
    }

    public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
    
  @Override
  public String toString() {
    return "Data{" + "time=" + time + ", value=" + value + '}';
  }
     
}
