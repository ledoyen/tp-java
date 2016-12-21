package samples.web.spring;

import java.util.Objects;

public class Customer {

	private Integer id;

	private final String firstName;
	private final String lastName;

	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Customer && Integer.valueOf(obj.hashCode())
				.equals(hashCode());
	}

	@Override
	public String toString() {
		return String.format("Customer[firstName='%s', lastName='%s']", firstName, lastName);
	}
}
