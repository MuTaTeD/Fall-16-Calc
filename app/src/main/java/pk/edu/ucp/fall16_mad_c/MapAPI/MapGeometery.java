package pk.edu.ucp.fall16_mad_c.MapAPI;

import com.google.gson.annotations.SerializedName;

public class MapGeometery
{
	@SerializedName("location")
	public  MapLocation location;

	@SerializedName("location_type")
	public String locationType;

	@SerializedName("viewport")
	public MapViewPort viewPort;

}
