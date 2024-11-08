package src;

import java.util.Map;
import java.util.HashMap;

public class JetPlane extends Aircraft implements Flyable{

	private WeatherTower tower;

	public JetPlane(long p_id, String p_name, Coordinates p_coordinates){
		super(p_id, p_name, p_coordinates);
		System.out.println("JetPlane " + name + "  has been created with ID:" + id);
	}

	private Map<String, String> msg = new HashMap<>();
	{
		msg.put("SUN", "Beautiful weather Goose.");
		msg.put("RAIN", "It's raining but I'm a jet, don't care.");
		msg.put("FOG", "Closing my eyes and praying I don't hit a bird");
		msg.put("SNOW", "Losing altitude, snow in the jet intake!");
		msg.put("GROUNDED", "KABOOM *jet dies*");
	}

	@Override
	public void updateConditions(){
		String update = this.updateCoordinates(this.tower.getWeather(this.coordinates), "JetPlane");
		System.out.println("JetPlane#" + this.name + "(" + this.id + "): " + msg.get(update));
		
		if (update.equals("GROUNDED")){
			System.out.println("JetPlane#" + this.name + "(" + this.id + "): landing.");
			System.out.println("Current coordinates: Longtitude: [" + this.coordinates.getLongitude() 
				+ "] Latitude: [" + this.coordinates.getLatitude()
				+ "] Height: [" + this.coordinates.getHeight() + "]");
			this.tower.unregister(this);
			System.out.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
		}		
	}

	@Override
	public void registerTower(WeatherTower weatherTower){
		this.tower = weatherTower;
		this.tower.register(this);
		System.out.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
	}
}