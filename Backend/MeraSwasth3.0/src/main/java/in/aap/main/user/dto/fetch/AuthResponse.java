package in.aap.main.user.dto.fetch;

public record AuthResponse(
		String token,
		UsersDTO user
		) {}
