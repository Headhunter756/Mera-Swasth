package in.aap.main.user.dto.fetch;

import in.aap.main.user.entity.Users;

public record PatientDTO(
		long id,
		Users user,
		String name,
		String email,
		String contact,
		int aadhaar,
		String address,
		int abha
		) {}
