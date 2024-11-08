package src;

import java.util.Map;
import java.util.HashMap;

public class Aircraft{
	protected long id;
	protected String name;
	protected Coordinates coordinates;

	protected Aircraft(long p_id, String p_name, Coordinates p_coordinates){
		this.id = p_id;
		this.name = p_name;
		this.coordinates = p_coordinates;
	}


	private Map<String, int[]> sun = new HashMap<>();
	{
		sun.put("JetPlane", new int[] {0, 10, 2});
		sun.put("Helicopter", new int[] {10, 0, 2});
		sun.put("Balloon", new int[] {2, 0, 4});
	}

	private Map<String, int[]> rain = new HashMap<>();
	{
		rain.put("JetPlane", new int[] {0, 5, 0});
		rain.put("Helicopter", new int[] {5, 0, 0});
		rain.put("Balloon", new int[] {0, 0, -5});
	}

	private Map<String, int[]> fog = new HashMap<>();
	{
		fog.put("JetPlane", new int[] {0, 1, 0});
		fog.put("Helicopter", new int[] {1, 0, 0});
		fog.put("Balloon", new int[] {0, 0, -3});
	}

	private Map<String, int[]> snow = new HashMap<>();
	{
		snow.put("JetPlane", new int[] {0, 0, -7});
		snow.put("Helicopter", new int[] {0, 0, -12});
		snow.put("Balloon", new int[] {0, 0, -15});
	}

	protected String updateCoordinates(String weather, String type){
		int changes[] = null;

		if (weather.equals("SUN")){
			changes = sun.get(type);
			this.coordinates = new Coordinates(
                this.coordinates.getLongitude() + changes[0],  // Longitude at index 0
                this.coordinates.getLatitude() + changes[1],   // Latitude at index 1
                this.coordinates.getHeight() + changes[2] // Height at index 2
            );
		}
		if (weather.equals("RAIN")){
			changes = rain.get(type);
			this.coordinates = new Coordinates(
                this.coordinates.getLongitude() + changes[0],
                this.coordinates.getLatitude() + changes[1],
                this.coordinates.getHeight() + changes[2]
            );
		}
		if (weather.equals("FOG")){
			changes = fog.get(type);
			this.coordinates = new Coordinates(
                this.coordinates.getLongitude() + changes[0],
                this.coordinates.getLatitude() + changes[1],
                this.coordinates.getHeight() + changes[2]
            );
		}
		if (weather.equals("SNOW")){
			changes = snow.get(type);
			this.coordinates = new Coordinates(
                this.coordinates.getLongitude() + changes[0],
                this.coordinates.getLatitude() + changes[1],
                this.coordinates.getHeight() + changes[2]
            );
		}
		
		if (this.coordinates.getHeight() <= 0) {
        	return "GROUNDED";
    	}

		return weather;
	}
}