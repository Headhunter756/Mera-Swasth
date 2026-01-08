package in.aap.main.users.entity;

import java.util.Collection;
import java.util.Collections;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Patient implements UserDetails {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	@Column
	private String name;
	@Column
	private String password;
	@Column
	private String email;
	@Column(length = 15)
	private String contact;
	@Column
	private int aadhaar;
	@Column
	private String address;
	@Column
	private int abha;

	public Patient() {
	}

	public Patient(Patient patient) {
		this.id = patient.id;
		this.name = patient.name;
		this.password = patient.password;
		this.email = patient.email;
		this.contact = patient.contact;
		this.aadhaar = patient.aadhaar;
		this.address = patient.address;
		this.abha = patient.abha;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getAadhaar() {
		return aadhaar;
	}

	public void setAadhaar(int aadhaar) {
		this.aadhaar = aadhaar;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAbha() {
		return abha;
	}

	public void setAbha(int abha) {
		this.abha = abha;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority("PATIENT"));
	}

	@Override
	public @Nullable String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		return email;
	}

}
