package in.aap.main.user.dto.fetch;

import in.aap.main.user.entity.Users;

public record HospitalDTO(
		long id,
		Users users,
	    String name,
	    String email,
	    String contact,
	    String registration,
	    String accredation,
	    String address
		) {}