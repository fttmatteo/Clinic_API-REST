package app.adapter.in.validators;

import java.sql.Date;
import java.util.regex.Pattern;
import app.application.exceptions.InputsException;

public abstract class SimpleValidator {

    public String stringValidator(String element, String value) throws Exception {
        if (value == null || value.isBlank()) {
            throw new InputsException(element + " no puede estar vacío");
        }
        return value.trim();
    }

    public int intValidator(String element, String value) throws Exception {
        stringValidator(element, value);
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new InputsException(element + " debe ser un número entero");
        }
    }

    public long longValidator(String element, String value) throws Exception {
        stringValidator(element, value);
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            throw new InputsException(element + " debe ser un número entero");
        }
    }

    public float floatValidator(String element, String value) throws Exception {
  		stringValidator(element, value);
  		try { return Float.parseFloat(value); }
  		catch (Exception e) { throw new InputsException(element + " debe ser un número (float)"); }
	}

    public boolean booleanValidator(String element, String value) throws Exception {
        stringValidator(element, value);
        if (!"true".equalsIgnoreCase(value) && !"false".equalsIgnoreCase(value)) {
            throw new InputsException(element + " debe ser true o false");
        }
        return Boolean.parseBoolean(value);
    }

    public Date dateValidator(String element, String value) throws Exception {
        stringValidator(element, value);
        try {
            return Date.valueOf(value);
        } catch (Exception e) {
            throw new InputsException(element + " debe tener formato yyyy-MM-dd");
        }
    }

    public String maxLength(String element, String value, int max) throws Exception {
        String v = stringValidator(element, value);
        if (v.length() > max) {
            throw new InputsException(element + " supera el máximo de " + max + " caracteres");
        }
        return v;
    }

    public String digitsExact(String element, String value, int digits) throws Exception {
        String v = stringValidator(element, value);
        if (!v.matches("\\d{" + digits + "}")) {
            throw new InputsException(element + " debe tener exactamente " + digits + " dígitos numéricos");
        }
        return v;
    }

    public String digitsMax(String element, String value, int maxDigits) throws Exception {
        String v = stringValidator(element, value);
        if (!v.matches("\\d{1," + maxDigits + "}")) {
            throw new InputsException(element + " debe tener como máximo " + maxDigits + " dígitos numéricos");
        }
        return v;
    }

    public String emailValidator(String value) throws Exception {
        String v = stringValidator("email", value);
        String regex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        if (!Pattern.compile(regex).matcher(v).matches()) {
            throw new InputsException("email no tiene un formato válido");
        }
        return v;
    }

    public String bloodPressureValidator(String value) throws Exception {
        String v = stringValidator("presión arterial", value);
        if (!v.matches("\\d{2,3}/\\d{2,3}")) {
            throw new InputsException("presión arterial debe tener el formato NN/NN (ej: 120/80)");
        }
        return v;
    }

    public int rangeInt(String element, String value, int min, int max) throws Exception {
        int n = intValidator(element, value);
        if (n < min || n > max) {
            throw new InputsException(element + " debe estar entre " + min + " y " + max);
        }
        return n;
    }

    public float rangeFloat(String element, String value, float min, float max) throws Exception {
  		float n = floatValidator(element, value);
  		if (n < min || n > max) throw new InputsException(element + " debe estar entre " + min + " y " + max);
  		return n;
	}
}
