package searchouse.bean;

import java.util.ArrayList;

public class AccountBean implements Bean, java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8542124113058593054L;

	private String name;
	private String surname;
	private String username;
	private String password;
	private String phone;
	private String email;
	private ArrayList<AdsBean> ads;
	private boolean logged;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<AdsBean> getAds() {
		return ads;
	}

	public void setAds(ArrayList<AdsBean> ads) {
		this.ads = ads;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isNull() {
		if (name.equals("Name (*)") || name == "" || surname.equals("Surname (*)") || surname == ""
				|| username.equals("Username (*)") || username == "" || password.equals("Password (*)")
				|| password == "" || email.equals("Email (*)") || email == "" )
			return true;
		else
			return false;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

}
