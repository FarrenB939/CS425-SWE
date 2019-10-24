package edu.mum.cs.cs425.pcrepairtrackingsystem.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;


@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="Users")
public class User {

	public User() {
		super();
	}

	public User(
			@NotNull(message = "*User Name is required") @NotBlank(message = "* User Name is required") String username,
			@NotNull(message = "*Password is required") @NotBlank(message = "*Password is required") String password,
			@NotNull(message = "*Email Address is required") @NotBlank(message = "*Email Address is required") String email,
			@NotNull(message = "*First Name is required") @NotBlank(message = "*First Name is required") String firstName,
			String middleName,
			@NotNull(message = "*Last Name is required") @NotBlank(message = "*Last Name is required") String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long userId;
	
	@NotNull(message = "*User Name is required")
    @NotBlank(message = "* User Name is required")
	@Column(unique=true, nullable=false)
	private String username;
	
	@NotNull(message = "*Password is required")
    @NotBlank(message = "*Password is required")
	private String password;
	
	@NotNull(message = "*Email Address is required")
    @NotBlank(message = "*Email Address is required")
	@Column(unique=true, nullable=false)
	private String email;
	
	@NotNull(message = "*First Name is required")
    @NotBlank(message = "*First Name is required")
	private String firstName;
	
	@Nullable
	private String middleName;
	
	@NotNull(message = "*Last Name is required")
    @NotBlank(message = "*Last Name is required")
	private String lastName;
	
	@Nullable
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate creationDate = LocalDate.now();
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "users_roles",
        joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "userId")},
        inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "roleId")}
    )
    private List<Role> roles;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	
	
	
}
