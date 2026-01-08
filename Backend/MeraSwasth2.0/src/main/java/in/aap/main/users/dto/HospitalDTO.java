package in.aap.main.users.dto;

public record HospitalDTO(
		long id,
	    String name,
	    String email,
	    String contact,
	    String registration,
	    String accredation,
	    String address
		) {}