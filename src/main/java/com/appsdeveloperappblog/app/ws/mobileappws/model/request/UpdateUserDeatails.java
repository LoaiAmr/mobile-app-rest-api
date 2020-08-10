package com.appsdeveloperappblog.app.ws.mobileappws.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateUserDeatails {

	@NotNull(message = "The First Name must be not null")
	@Size(min = 2, max = 18, message = "The size of First name must be min=8 and max=18")
	private String firstName;

	@NotNull(message = "The Last Name must be not null")
	@Size(min = 2, max = 18, message = "The size of The Last name must be min=8 and max=18")
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
