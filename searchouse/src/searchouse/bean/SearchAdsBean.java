package searchouse.bean;

import java.util.UUID;

public class SearchAdsBean implements Bean,java.io.Serializable{

	private static final long serialVersionUID = 9184735950003283551L;
	private UUID id;
	private String provincia = "";
	private String district = "";
	private String distance = "1000";
	private int minSuperficie = 10;
	private int maxSuperficie = 1500;
	private int minPrezzo = 1;
	private int maxPrezzo = 20000000;
	private String type;
	private float x = 0f;
	private float y;
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		if(distance != "")
		this.distance = distance;
	}

	public int getMinSuperficie() {
		return minSuperficie;
	}
	public void setMinSuperficie(int minSuperficie) {
		this.minSuperficie = minSuperficie;
	}
	public int getMaxSuperficie() {
		return maxSuperficie;
	}
	public void setMaxSuperficie(int maxSuperficie) {
		this.maxSuperficie = maxSuperficie;
	}
	public int getMinPrezzo() {
		return minPrezzo;
	}
	public void setMinPrezzo(int minPrezzo) {
		this.minPrezzo = minPrezzo;
	}
	public int getMaxPrezzo() {
		return maxPrezzo;
	}
	public void setMaxPrezzo(int maxPrezzo) {
		this.maxPrezzo = maxPrezzo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "SearchAdsBean [id=" + id + ", provincia=" + provincia + ", district=" + district + ", distance=" + distance + ", minSuperficie="
				+ minSuperficie + ", maxSuperficie=" + maxSuperficie + ", minPrezzo=" + minPrezzo + ", maxPrezzo="
				+ maxPrezzo + ", type=" + type + "]";
	}
	
	
	
}
