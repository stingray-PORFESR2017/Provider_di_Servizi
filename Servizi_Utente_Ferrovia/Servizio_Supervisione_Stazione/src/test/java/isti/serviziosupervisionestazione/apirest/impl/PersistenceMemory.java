package isti.serviziosupervisionestazione.apirest.impl;


import javax.inject.Singleton;
import javax.persistence.Persistence;

import isti.serviziosupervisionestazione.apirest.persistence.RelationDBPersistence;
import isti.serviziosupervisionestazione.apirest.persistence.TokenPersistence;

@Singleton
public class PersistenceMemory implements org.glassfish.hk2.api.Factory<TokenPersistence> {
	

	@Override
	 public TokenPersistence provide() {
		  RelationDBPersistence rdbp = new RelationDBPersistence();
		rdbp.setEntitymanagerFactory(Persistence.createEntityManagerFactory("jcmad_pers-test"));
		  return rdbp;

	}

	@Override
	public void dispose(TokenPersistence instance) {
		// TODO Auto-generated method stub
		
	}
	
	



}
