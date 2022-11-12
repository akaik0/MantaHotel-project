package utils;

import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public enum ErrorCode {
    OK(0, HttpServletResponse.SC_OK, "OK."),
    WRONG_FORMAT(-100, HttpServletResponse.SC_BAD_REQUEST, "Wrong Person id format."),
    ROOM_NOT_FOUND(-101, HttpServletResponse.SC_NOT_FOUND, "Room not found."),
    EMPTY_INPUT_FIELDS(-102, HttpServletResponse.SC_BAD_REQUEST, "One or more input fields are empty."),
    EMAIL_MISSING(-103, HttpServletResponse.SC_BAD_REQUEST, "Email missing"),
    PASSWORD_MISSING(-104, HttpServletResponse.SC_BAD_REQUEST, "Password missing"),
    WRONG_CREDENTIALS(-105, HttpServletResponse.SC_BAD_REQUEST, "Submitted credentials are wrong"),
    DELETED_USER_NOT_PRESENT(-106, HttpServletResponse.SC_NOT_FOUND, "User to be deleted not found."),
    DIFFERENT_PASSWORDS(-107, HttpServletResponse.SC_CONFLICT, "Different passwords"),
    MAIL_ALREADY_USED(-108, HttpServletResponse.SC_CONFLICT, "Mail already used"),
    UNRECOGNIZED_ROLE(-109, HttpServletResponse.SC_BAD_REQUEST, "Unrecognized role"),
    USER_ALREADY_PRESENT(-110, HttpServletResponse.SC_CONFLICT, "User already exists."),
    INVALID_DOCUMENT_NUMBER(-111, HttpServletResponse.SC_BAD_REQUEST, "Invalid document number"),
    WRONG_REST_FORMAT(-112, HttpServletResponse.SC_BAD_REQUEST, "Wrong rest request format."),
    COSTUMER_NOT_FOUND(-113, HttpServletResponse.SC_NOT_FOUND, "Customer not registered"),
    PERSONID_MISMATCH(-114, HttpServletResponse.SC_CONFLICT, "Person ID requested and Person ID provided do not match"),
    USER_NOT_FOUND(-115, HttpServletResponse.SC_NOT_FOUND, "User not found."),
    PERSONID_NOT_PROVIDED(-116, HttpServletResponse.SC_BAD_REQUEST, "Person ID is missing."),
    BOOKING_NOT_FOUND(-117, HttpServletResponse.SC_NOT_FOUND, "Booking not found"),
    DATE_NOT_PROVIDED(-118, HttpServletResponse.SC_BAD_REQUEST, "Date is missing."),
    BADLY_FORMATTED_JSON(-119, HttpServletResponse.SC_BAD_REQUEST, "The input json is in the wrong format."),
    LOGS_NOT_FOUND(-120, HttpServletResponse.SC_NOT_FOUND, "No logs found."),
    PAYMENT_NOT_FOUND(-121, HttpServletResponse.SC_NOT_FOUND, "Payment not found."),
    OPERATION_UNKNOWN(-200, HttpServletResponse.SC_BAD_REQUEST, "Operation unknown."),
    METHOD_NOT_ALLOWED(-500, HttpServletResponse.SC_METHOD_NOT_ALLOWED, "The method is not allowed"),
    TOKEN_TAMPERED(-520, HttpServletResponse.SC_UNAUTHORIZED, "The token has been tampered!!!!"),
    TOKEN_EXPIRED(-521, HttpServletResponse.SC_UNAUTHORIZED, "The token has expired."),
    INTERNAL_ERROR(-997, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Error");


    private final int errorCode;
    private final int httpCode;
    private final String errorMessage;

    ErrorCode(int errorCode, int httpCode, String errorMessage) {
        this.errorCode = errorCode;
        this.httpCode = httpCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getHTTPCode() {
        return httpCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public JSONObject toJSON() {
        JSONObject data = new JSONObject();
        data.put("code", errorCode);
        data.put("message", errorMessage);
        JSONObject info = new JSONObject();
        info.put("error", data);
        return info;
    }
}
