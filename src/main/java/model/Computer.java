package model;

import java.time.LocalDate;

public class Computer {

	
	private Company company;
	private int id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	
	public Computer(ComputerBuilder computerBuilder) {
		this.id = computerBuilder.id;
		this.name = computerBuilder.name;
		this.introduced = computerBuilder.introduced;
		this.discontinued = computerBuilder.discontinued;
		this.company = computerBuilder.company;
	}
	
	public Computer() {
		
	}

	public Company getCompany() {
		return company;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}


	public LocalDate getIntroduced() {
		return introduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}
	
	@Override
	public String toString() {
		return ", id=" + id + ", name=" + name + ", introduced=" + introduced
				+ ", discontinued=" + discontinued + ", company_id=" + this.company + "]";
	}
	
	public static class ComputerBuilder {
		
		private Company company;
		private int id;
		private String name;
		private LocalDate introduced;
		private LocalDate discontinued;
		
		
		public ComputerBuilder setCompany(Company company) {
			this.company = company;
			return this;
		}
		
		public ComputerBuilder setId(int id) {
			this.id = id;
			return this;
		}
		
		public ComputerBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public ComputerBuilder setIntroduced(LocalDate introduced) {
			this.introduced = introduced;
			return this;
		}

		public ComputerBuilder setDiscontinued(LocalDate discontinued) {
			this.discontinued = discontinued;
			return this;
		}
		
		public Computer build() {
			return new Computer(this);
		}

	}
	
	
}
