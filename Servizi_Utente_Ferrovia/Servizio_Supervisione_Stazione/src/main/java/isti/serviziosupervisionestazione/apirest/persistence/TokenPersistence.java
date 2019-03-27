package isti.serviziosupervisionestazione.apirest.persistence;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import isti.JCMAD;
import isti.JCMADID;



public interface TokenPersistence {




Query createNativeQuery(String string);

EntityTransaction getTransaction();

void persist(JCMAD ar);

JCMAD find(
		Class<JCMAD> class1, String value);

JCMAD findid(
		Class<JCMAD> class1, JCMADID value);

TypedQuery<JCMAD> createNamedQuery(String string, Class<JCMAD> class1);


}