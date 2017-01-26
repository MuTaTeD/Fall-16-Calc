package pk.edu.ucp.fall16_mad_c.MapAPI;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MapAddressComponents
{
	@SerializedName("long_name")
	public String longName;

	@SerializedName("short_name")
	public String shortName;

	@SerializedName("types")
	public ArrayList<String> types;

	public MapAddressComponents ()
	{
		types = new ArrayList<String>();
	}
}
