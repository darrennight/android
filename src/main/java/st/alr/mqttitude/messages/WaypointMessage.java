package st.alr.mqttitude.messages;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import st.alr.mqttitude.App;
import st.alr.mqttitude.db.Waypoint;
import st.alr.mqttitude.support.StringifiedJSONObject;

public class WaypointMessage {
	Waypoint waypoint;
    	String trackerId;
	public WaypointMessage(Waypoint w) {
        	this.waypoint = w;
    	}

	@Override
	public String toString() {
        	return toJSONObject().toString();
	}

    public StringifiedJSONObject toJSONObject() {
        StringifiedJSONObject json = new StringifiedJSONObject();
        try {
            json.put("_type", "waypoint")
                    .put("desc", this.waypoint.getDescription())
                    .put("lat", this.waypoint.getLatitude())
                    .put("lon", this.waypoint.getLongitude())
                    .put("tst", (int) (TimeUnit.MILLISECONDS.toSeconds(this.waypoint.getDate().getTime())))
                    .put("rad", this.waypoint.getRadius() != null ? this.waypoint.getRadius() : 0);

            if (this.trackerId != null && !this.trackerId.isEmpty()) 
                json.put("tid", this.trackerId);

        }catch (JSONException e) {
        }

        return json;
    }

    public void setTrackerId(String tid) {
        this.trackerId = tid;
    }
    public String getTrackerId(){ return this.trackerId; }
}
