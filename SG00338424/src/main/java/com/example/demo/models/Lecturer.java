package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Validation.LecturerPOSTValidation;
import com.example.demo.Validation.LecturerPUTValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

@Entity
@Table(name = "lecturer")
public class Lecturer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Null(message = "ID not allowed!", groups = LecturerPUTValidation.class)
	@Null(message = "ID not allowed!", groups = LecturerPOSTValidation.class)
	private Integer id;
	@Column(unique = true)
	@Null(message = "LID not allowed!", groups = LecturerPUTValidation.class)
	@NotNull(message = "LID is required", groups = LecturerPOSTValidation.class)
	private String lid;
	@NotNull(message = "Name is required", groups = LecturerPUTValidation.class)
	@NotNull(message = "Name is required", groups = LecturerPOSTValidation.class)
	private String name;
	@OneToMany(mappedBy = "lecturer")
	private List<Module> modules = new ArrayList<Module>();
	private String taxBand;
	private Double salaryScale;

	public String getTaxBand() {
		return taxBand;
	}

	public void setTaxBand(String taxBand) {
		this.taxBand = taxBand;
	}

	public Double getSalaryScale() {
		return salaryScale;
	}

	public void setSalaryScale(Double double1) {
		this.salaryScale = double1;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLid() {
		return lid;
	}

	public void setLid(String lid) {
		this.lid = lid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

}
