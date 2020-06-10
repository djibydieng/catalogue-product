package com.catalogue.services;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogue.dao.CompteClientDao;
import com.catalogue.entities.CompteClient;
import com.catalogue.exceptions.AuthenticationFailedException;
import com.catalogue.utils.Md5Hashing;

@Service
public class CompteClientServiceImpl implements CompteClientService{

	@Autowired
	private CompteClientDao compteClientDao;
	
	@Override
	public CompteClient connecter(String username, String password)
			throws NoSuchAlgorithmException, AuthenticationFailedException {
		CompteClient compte = compteClientDao.findCompteByUsername(username);
		if(compte.getPassword().equals(Md5Hashing.getEncodedPassword(password)))
			return compte;
		else
			throw new AuthenticationFailedException();
	}

	@Override
	public CompteClient ajouterUtilisateur(CompteClient compte) throws NoSuchAlgorithmException {
		compte.setPassword(Md5Hashing.getEncodedPassword(compte.getPassword()));
		return compteClientDao.save(compte);
		
	}

	@Override
	public CompteClient listerClientParId(Long id) {
		CompteClient compte = this.compteClientDao.findById(id).get();
		return compte;
	}

}
