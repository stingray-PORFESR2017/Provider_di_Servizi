package isti.serviziosupervisionestazione.apirest.persistence;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import isti.JCMAD;
import isti.JCMADID;

@Singleton
public class RelationDBPersistence implements TokenPersistence {

	 @Inject
	 private  EntityManagerFactory emFactory;
	 
	 private  EntityManager em;// = emFactory.createEntityManager();

	 @PostConstruct
	 public void init(){
		 if(emFactory!=null){
			 em = emFactory.createEntityManager();
		 }
		 
	 }

	 //ONLY FOR TEST PURPOSE
	 public void setEntitymanagerFactory(EntityManagerFactory emf){
		 this.emFactory = emf;
		 em = emFactory.createEntityManager();
	 }
	
	@Override
	public Query createNativeQuery(String string) {
		//em = emFactory.createEntityManager();
		return em.createNamedQuery(string);
	}

	@Override
	public EntityTransaction getTransaction() {
		return em.getTransaction();
	}

	@Override
	public void persist(JCMAD ar) {
		em.persist(ar);
	}

	@Override
	public JCMAD find(
			Class<JCMAD> class1, String value) {
	//	em = emFactory.createEntityManager();
		return em.find(class1, value);
	}

	@Override
	public TypedQuery<JCMAD> createNamedQuery(String string,
			Class<JCMAD> class1) {
		return em.createNamedQuery(string,class1);
	}

	@Override
	public JCMAD findid(Class<JCMAD> class1, JCMADID value) {
		// TODO Auto-generated method stub
		return em.find(class1, value);
	}

}