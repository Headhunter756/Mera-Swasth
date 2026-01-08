package in.aap.main.users.dto.create;

public record CreateDoctorDTO(
		String name,
		String email,
		String password,
		String contact,
		String council,	
		String licence,
		String specialization,
		String address
) {}