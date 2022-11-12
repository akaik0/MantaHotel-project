package resource;

import com.fasterxml.jackson.core.JsonGenerator;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;

public class Message extends Resource{
    final boolean error;
    final String message;
    private String errorCode;
    private String errorDetails;


    public Message(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public Message(final String message, final String errorCode, final String errorDetails) {
        this.message = message;
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
        this.error = true;
    }

    public String getMessage() {
        return message;
    }
    public boolean isError() {
        return error;
    }
    public final String getErrorCode() { return errorCode; }
    public final String getErrorDetails() { return errorDetails; }


    public JSONObject toJSON(){
        JSONObject result = new JSONObject();
        result.put("error", this.error);
        result.put("message", this.message);
        return result;
    }

    @Override
    public final void toJSON(final OutputStream out) throws IOException {

        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

        jg.writeStartObject();

        jg.writeFieldName("message");

        jg.writeStartObject();

        jg.writeStringField("message", message);

        if(errorCode != null) {
            jg.writeStringField("error-code", errorCode);
        }

        if(errorDetails != null) {
            jg.writeStringField("error-details", errorDetails);
        }

        jg.writeEndObject();

        jg.writeEndObject();

        jg.flush();
    }

}