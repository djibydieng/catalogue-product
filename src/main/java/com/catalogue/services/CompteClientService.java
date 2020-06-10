package com.catalogue.services;

import java.security.NoSuchAlgorithmException;

import com.catalogue.entities.CompteClient;
import com.catalogue.exceptions.AuthenticationFailedException;

public interface CompteClientService {

	public CompteClient connecter(String username, String password) throws NoSuchAlgorithmException, AuthenticationFailedException;
	public CompteClient ajouterUtilisateur(CompteClient compte) throws NoSuchAlgorithmException;
	public CompteClient listerClientParId(Long id);
}
