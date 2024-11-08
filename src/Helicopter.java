package src;

import java.util.Map;
import java.util.HashMap;

public class Helicopter extends Aircraft implements Flyable{
	
	private WeatherTower tower;
	
	public Helicopter(long p_id, String p_name, Coordinates p_coordinates){
		super(p_id, p_name, p_coordinates);
		System.out.println("Helicopter " + name + "  has been created with ID:" + id);
	}

	private Map<String, String> msg = new HashMap<>();
	{
		msg.put("SUN", "Good visibility Tower, moving up!");
		msg.put("RAIN", "Acid raid is melting the rotor, yikes.");
		msg.put("FOG", "Can't see shit in this fog.");
		msg.put("SNOW", "It's freezing my rotor blades, we're losing altitude!");
		msg.put("GROUNDED", "FUCK WE'RE GOING DOWN *big explosion*");
	}

	@Override
	public void updateConditions(){

		String update = this.updateCoordinates(this.tower.getWeather(this.coordinates), "Helicopter");
		System.out.println("Helicopter#" + this.name + "(" + this.id + "): " + msg.get(update));
		
		if (update.equals("GROUNDED")){
			System.out.println("Helicopter#" + this.name + "(" + this.id + "): landing.");
			System.out.println("Current coordinates: Longtitude: [" + this.coordinates.getLongitude() 
				+ "] Latitude: [" + this.coordinates.getLatitude()
				+ "] Height: [" + this.coordinates.getHeight() + "]");
			this.tower.unregister(this);
			System.out.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
		}	
	}

	@Override
	public void registerTower(WeatherTower weatherTower){
		this.tower = weatherTower;
		this.tower.register(this);
		System.out.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
	}
}