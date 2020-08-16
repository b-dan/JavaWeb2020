package model;

public class RegistrationUser {
	private String login;
	private String password;
	private String rePassword;
	private String name;
	private String gender;
	private String address;
	private String comment;
	
	public RegistrationUser() {
	}

	public RegistrationUser(String login, String password, String rePassword, String name, String gender,
			String address, String comment) {
		super();
		this.login = login;
		this.password = password;
		this.rePassword = rePassword;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.comment = comment;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "RegistrationUser [login=" + login + ", password=" + password + ", rePassword=" + rePassword + ", name="
				+ name + ", gender=" + gender + ", address=" + address + ", comment=" + comment + "]";
	}

	
	

}
