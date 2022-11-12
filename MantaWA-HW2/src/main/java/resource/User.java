package resource;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static resource.Resource.JSON_FACTORY;

public class User extends Resource{

	private String email;
	private String password;
	private String first_name;
	private String last_name;
	private String role;

	public User(String email){
		this.email = email;
	}

	public User(String email, String password){
		this.email = email;
		this.password = password;
	}

	public User(String email, String first_name, String last_name, String role) {
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.role = role;
	}

	public User(String email, String password, String first_name, String last_name, String role) {
		this.email = email;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public String getRole() {
		return role;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public final void toJSON(final OutputStream out) throws IOException {

		final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

		jg.writeStartObject();

		jg.writeFieldName("user");

		jg.writeStartObject();

		jg.writeStringField("email", email);

		jg.writeStringField("password", password);

		jg.writeStringField("firstname", first_name);

		jg.writeStringField("lastname", last_name);

		jg.writeStringField("role", role);

		jg.writeEndObject();

		jg.writeEndObject();

		jg.flush();
	}

	public static User fromJSON(final InputStream in) throws IOException {
		String email = null;
		String fname = null;
		String lname = null;
		String role = null;
		String password = null;

		final JsonParser jp = JSON_FACTORY.createParser(in);

		while (jp.getCurrentToken() != JsonToken.FIELD_NAME || "user".equals(jp.getCurrentName()) == false) {
			// there are no more events
			if (jp.nextToken() == null) {
				throw new IOException("Unable to parse JSON: no user object found.");
			}
		}

		while (jp.nextToken() != JsonToken.END_OBJECT) {

			if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {

				switch (jp.getCurrentName()) {
					case "email":
						jp.nextToken();
						email = jp.getText();
						break;
					case "firstname":
						jp.nextToken();
						fname = jp.getText();
						break;
					case "lastname":
						jp.nextToken();
						lname = jp.getText();
						break;
					case "password":
						jp.nextToken();
						password = jp.getText();
						break;
					case "role":
						jp.nextToken();
						role = jp.getText();
						break;
				}
			}
		}

		return new User(email, password, fname, lname, role);
	}

}
