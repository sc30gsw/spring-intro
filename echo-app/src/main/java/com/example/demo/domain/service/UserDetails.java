package com.example.demo.domain.service;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public interface UserDetails extends Serializable{
	
	//ユーザー名を返却するメソッド
	String getUsername();
	
	//登録されているパスワードを返却するメソッド
	String getPassword();
	
	//有効なユーザーかを判定するメソッド(有効な場合true)
	boolean isEnabled();
	
	//アカウントのロック状態を判定するメソッド(ロックされていない場合true)
	boolean isAccountNonLocked();
	
	//アカウントの有効期限の状態を判定するメソッド(有効期限内の場合true)
	boolean isAccountNonExpired();
	
	//資格情報の有効期限の状態を判定するメソッド(有効期限内の場合true)
	boolean isCredentialsNonExpired();
	
	//ユーザーに与えられている権限リストを返却するメソッド
	Collection<? extends GrantedAuthority> getAuthorities();

}
