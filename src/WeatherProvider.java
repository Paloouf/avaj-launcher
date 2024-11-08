package src;

import java.util.Random;

public class WeatherProvider{
	private String[] weather = {"RAIN","FOG","SUN","SNOW"};
	private static WeatherProvider weatherProvider = new WeatherProvider();

	private WeatherProvider(){}

	public static WeatherProvider getProvider(){
		return WeatherProvider.weatherProvider;
	}

	public String getCurrentWeather(Coordinates p_coordinates){
		int value = p_coordinates.getLongitude() + p_coordinates.getLatitude() + p_coordinates.getHeight();
		int randomOffset = new Random().nextInt(4);
		return weather[(value + randomOffset) % 4];
	}
}