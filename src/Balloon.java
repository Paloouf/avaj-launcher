package src;

import java.util.Map;
import java.util.HashMap;

public class Balloon extends Aircraft implements Flyable{
	
	private WeatherTower tower;

	public Balloon(long p_id, String p_name, Coordinates p_coordinates){
		super(p_id, p_name, p_coordinates);
		System.out.println("Balloon " + name + "  has been created with ID:" + id);
	}

	private Map<String, String> msg = new HashMap<>();
	{
		msg.put("SUN", "Sunny day, nice time for a balloon ride.");
		msg.put("RAIN", "Why did we choose this shit balloon man...");
		msg.put("FOG", "Very nice views bro... it's foggy as hell.");
		msg.put("SNOW", "AND NOW IT SNOWS, REALLY DUDE?");
		msg.put("GROUNDED", "Well, thanks for the trip but we're in the middle of nowhere.");
	}

	@Override
	public void updateConditions(){

		String update = this.updateCoordinates(this.tower.getWeather(this.coordinates), "Balloon");
		System.out.println("Balloon#" + this.name + "(" + this.id + "): " + msg.get(update));
		
		if (update.equals("GROUNDED")){
			System.out.println("Balloon#" + this.name + "(" + this.id + "): landing.");
			System.out.println("Current coordinates: Longtitude: [" + this.coordinates.getLongitude() 
				+ "] Latitude: [" + this.coordinates.getLatitude()
				+ "] Height: [" + this.coordinates.getHeight() + "]");
			this.tower.unregister(this);
			System.out.println("Tower says: Balloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
		}
	}

	@Override
	public void registerTower(WeatherTower weatherTower){
		this.tower = weatherTower;
		this.tower.register(this);
		System.out.println("Tower says: Balloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
	}
}