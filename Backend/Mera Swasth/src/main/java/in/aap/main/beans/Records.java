package in.aap.main.beans;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Records {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long serialno;
	@ManyToOne
	@JoinColumn(name = "patient_id",referencedColumnName = "id")
	private Patient patient;
	@ManyToOne
	@JoinColumn(name = "doctor_id",referencedColumnName = "id")
	private Doctor doctor;
	@Column
	private String diagnose;
	@Column
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime date;
	
	public Records() {}
	
	public long getSerialno() {
		return serialno;
	}
	public void setSerialno(long serialno) {
		this.serialno = serialno;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public String getDiagnose() {
		return diagnose;
	}
	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
}
