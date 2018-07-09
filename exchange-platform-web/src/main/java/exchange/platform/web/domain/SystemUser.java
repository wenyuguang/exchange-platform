package exchange.platform.web.domain;

public class SystemUser {
	private int id;
	private String username;
	private String password;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password == null ? null : this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
