package in.aap.main.user.dto.create;

public record CreatePatient(
		 String name,
		 String password,
		 String email,
		 String contact,
		 int aadhaar,
		 String address,
		 int abha
) {}
