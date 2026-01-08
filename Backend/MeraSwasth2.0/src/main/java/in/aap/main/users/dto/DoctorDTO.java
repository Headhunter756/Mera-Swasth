package in.aap.main.users.dto;

public record DoctorDTO(
		long id,
		String name,
		String email,
		String contact,	
		String council,	
		String licence,
		String specialization,
		String address
) {}