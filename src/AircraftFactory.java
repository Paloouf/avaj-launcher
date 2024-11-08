package src;

public class AircraftFactory{

	private static long p_id = 0;

	private static long nextId() {
		return (p_id++);
	}

	public static Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates){
		if (p_type.toLowerCase().equals("helicopter")) {
			//System.out.println(p_type + " " + p_name + " has been created.");
			return new Helicopter(nextId(), p_name, p_coordinates);
		}
		else if (p_type.toLowerCase().equals("jetplane")) {
			//System.out.println(p_type + " " + p_name + "  has been created.");
			return new JetPlane(nextId(), p_name, p_coordinates);
		}
		else if (p_type.toLowerCase().equals("balloon") || p_type.toLowerCase().equals("baloon") ) {
			//System.out.println(p_type + " " + p_name + "  has been created.");
			return new Balloon(nextId(), p_name, p_coordinates);
		}
		return null;
	}
}