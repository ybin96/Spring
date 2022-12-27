package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member {
	
	@Id
	private String id;
	private String pwd;
	private String name;
	private String role;
	
	@OneToMany(mappedBy = "member",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
//	@OneToMany(mappedBy = "member",fetch = FetchType.EAGER)
	private List<Board> boards;
}














