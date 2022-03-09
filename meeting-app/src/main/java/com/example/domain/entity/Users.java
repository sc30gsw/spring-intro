package com.example.domain.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * ユーザーエンティティクラス
 *
 */
@Data
public class Users implements Serializable {

	private static final long serialVersionUID = 2L;
	
	/**連番*/
	private long seqNo;
	
	/**ユーザーID*/
	private String userId;
	
	/**名前*/
	private String firstName;
	
	/**苗字*/
	private String lastName;
	
	/**パスワード*/
	private String password;
	
	/**ロール名*/
	private String roleName;
}
