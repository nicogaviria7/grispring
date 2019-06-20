package co.edu.uniquindio.gri.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class User.
 */
@Entity(name = "USERS")
@Table(name = "USERS", schema = "gri")
public class User  implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name = "ID", length = 300)
	private long id;

	/** The username. */
	@Column(name = "USERNAME", length = 300)	
	private String username;

	/** The password. */
	@Column(name = "PASSWORD", length = 300)
	private String password;

	/**
	 * Instantiates a new user.
	 */
	public User() {
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param id the id
	 * @param username the username
	 * @param password the password
	 */
	public User(long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param user the user
	 */
	public User(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	
}
