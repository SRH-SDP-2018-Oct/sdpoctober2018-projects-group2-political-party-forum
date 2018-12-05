package com.politicalforum.utils;

public class GenericUser<U> {

	private U genericUser;
	
	public <T> GenericUser(T genericUser) {
		this.genericUser = (U)genericUser;
	}
	
	public U getGenericUser() {
		return genericUser;
	}

	public void setGenericUser(U genericUser) {
		this.genericUser = genericUser;
	}
	
}
