package in.aap.main.users.dto;

public record PatientDTO(
		long id,
		String name,
		String email,
		String contact,
		int aadhaar,
		String address,
		int abhaF
		) {}
