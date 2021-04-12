package it.dinfo.stlab.model.user;

import it.dinfo.stlab.model.AdminAuthorization;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "UserAccount")
public class UserAccount {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Enumerated(EnumType.STRING)
	private UserRole userRole;

	//utilizzo la bidirezionalità esclusimante per l'utilizzo del cascade, ie, per fare in modo che quando elimino un
	// UserAccount, si eliminino anche le righe corrispondenti della tabella AdminAuthorization
	@OneToMany(mappedBy = "userAccount", cascade = {CascadeType.REMOVE})
	private List<AdminAuthorization> adminAuthorizations;

    /*  //municipality è inteso come luogo di nascita
    @ManyToOne(fetch = FetchType.EAGER) //la relazione tra UserAccount e Municipality è Nto1 monodirezionale
    @JoinColumn(name = "municipality_id")
    private Municipality municipality;*/

	// Utils

	public void assignPassword(String plainPassword) {
		this.password = PasswordEncoder.encodePassword(plainPassword);
	}

	public boolean hasRole(UserRole role) {
		if (role == this.getUserRole())
			return true;
		else
			return false;
	}

	// Getters & Setters

	public String getId() {
		return id;
	}

	public void setId(String uuid) {
		this.id = uuid;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public List<AdminAuthorization> getAdminAuthorizations() {
		return adminAuthorizations;
	}

	public void setAdminAuthorizations(List<AdminAuthorization> adminAuthorizations) {
		this.adminAuthorizations = adminAuthorizations;
	}
}
