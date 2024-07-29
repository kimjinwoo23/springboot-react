package com.kh.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor //기본생성자
@AllArgsConstructor//필수생성자
public class User {
	private int id;
	private String name;
	private String email;

}