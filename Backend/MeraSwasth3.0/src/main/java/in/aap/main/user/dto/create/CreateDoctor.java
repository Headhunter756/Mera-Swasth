package in.aap.main.user.dto.create;

public record CreateDoctor(
		String name,
		String email,
		String password,
		String contact,
		String council,	
		String licence,
		String specialization,
		String address
) {}