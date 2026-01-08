package in.aap.main.users.dto.create;

public record CreateHospital(
		String name,
	    String email,
	    String password,
	    String contact,
	    String registration,
	    String accredation,
	    String address
		) {}
