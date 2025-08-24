package app.domain.services.validation;

import app.domain.common.exceptions.ValidationException;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public final class Validators {

	private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
	private static final Pattern USERNAME = Pattern.compile("^[A-Za-z0-9]{1,15}$");
	private static final Pattern USER_PHONE = Pattern.compile("^[0-9]{1,10}$");
	private static final Pattern PATIENT_PHONE = Pattern.compile("^[0-9]{10}$");
	private static final Pattern HAS_UPPER = Pattern.compile(".*[A-Z].*");
	private static final Pattern HAS_NUMBER = Pattern.compile(".*[0-9].*");
	private static final Pattern HAS_SPECIAL = Pattern.compile(".*[^A-Za-z0-9].*");

	private Validators() {}

	public static void validateEmail(String email) {
		if (isBlank(email)) return;
		if (!EMAIL.matcher(email).matches()) throw new ValidationException("Invalid email");
	}

	public static void validateUsername(String username) {
		if (!USERNAME.matcher(username).matches())
			throw new ValidationException("Username must be alphanumeric and up to 15 characters");
	}

	public static void validateStrongPassword(String password) {
		if (password == null || password.length() < 8)
			throw new ValidationException("Password must be at least 8 characters");
		if (!HAS_UPPER.matcher(password).find())
			throw new ValidationException("Password must contain at least one uppercase letter");
		if (!HAS_NUMBER.matcher(password).find())
			throw new ValidationException("Password must contain at least one number");
		if (!HAS_SPECIAL.matcher(password).find())
			throw new ValidationException("Password must contain at least one special character");
	}

	public static void validateAddress(String address) {
		if (address == null) return;
		if (address.length() > 30) throw new ValidationException("Address must be at most 30 characters");
	}

	public static void validateAge150(LocalDate birthDate) {
		if (birthDate == null) throw new ValidationException("Birth date is required");
		int age = Period.between(birthDate, LocalDate.now()).getYears();
		if (age < 0 || age > 150) throw new ValidationException("Age out of range (0-150)");
	}

	public static void validateUserPhone(String phone) {
		if (phone == null) return;
		if (!USER_PHONE.matcher(phone).matches())
			throw new ValidationException("User phone must have 1 to 10 digits");
	}

	public static void validatePatientPhone(String phone) {
		if (!PATIENT_PHONE.matcher(phone).matches())
			throw new ValidationException("Patient phone must have 10 digits");
	}

	public static boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }
}


