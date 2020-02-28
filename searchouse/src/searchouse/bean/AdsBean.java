package searchouse.bean;

import java.util.*;


public class AdsBean implements Bean, java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9184735950003283551L;
	private UUID id;
	private String id_account;
	private String title;
	private String address;
	private String provincia;
	private String description;
	private int dimension;
	private String type;
	private int price;
	private ArrayList<String> imagesName;
	private float x;
	private float y;
	private String contact;
	
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

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getId_account() {
		return id_account;
	}

	public void setId_account(String id_account) {
		this.id_account = id_account;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public ArrayList<String> getImagesName() {
		return imagesName;
	}

	public void setImagesName(ArrayList<String> images) {
		this.imagesName = images;
	}

	public boolean isNull() {
		if (title.equals("") || title.equals("Title") || address.equals("") || address.equals("Address")
				|| description.equals("") || description.equals("Description") || price < 0 || dimension <0) {
			return true;
		}
		return false;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
}
