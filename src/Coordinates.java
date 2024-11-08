package src;


public class Coordinates{
	private int longitude;
	private int latitude;
	private int height;

	Coordinates(int p_longitude, int p_latitude, int p_height){
		this.longitude = p_longitude;
		this.latitude = p_latitude;
		this.height = p_height;
		if (this.height > 100){
			this.height = 100;
		}
	}

	public int getLongitude(){
		return this.longitude;
	}

	public int getLatitude(){
		return this.latitude;
	}

	public int getHeight(){
		return this.height;
	}
}
