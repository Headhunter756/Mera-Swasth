package in.aap.main.user.dto.fetch;

import in.aap.main.user.entity.Users;

public record DoctorDTO(
		long id,
		Users user,
		String name,
		String email,
		String contact,	
		String council,	
		String licence,
		String specialization,
		String address
) {}