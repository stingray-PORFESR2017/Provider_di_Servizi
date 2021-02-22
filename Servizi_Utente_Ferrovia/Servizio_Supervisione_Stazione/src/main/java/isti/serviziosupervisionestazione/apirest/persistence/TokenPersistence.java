package isti.serviziosupervisionestazione.apirest.persistence;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import isti.message.config.ConfigCommand;
import isti.message.config.StationConfig;
import isti.message.impl.cmad.JCMAD;
import isti.message.impl.cmad.JCMADID;



public interface TokenPersistence {




Query createNativeQuery(String string);

EntityTransaction getTransaction();

void persist(JCMAD ar);

boolean update(JCMAD ar);

boolean update(ConfigCommand class1);

void persistConfigCommand(ConfigCommand ar);

void persistStationConfing(StationConfig ar);

JCMAD find(
		Class<JCMAD> class1, String value);

JCMAD findid(
		Class<JCMAD> class1, JCMADID value);



ConfigCommand findimei(
		Class<ConfigCommand> class1, String value);

TypedQuery<JCMAD> createNamedQuery(String string, Class<JCMAD> class1);

TypedQuery<String> createNamedQueryS(String string, Class<String> class1);

TypedQuery<ConfigCommand> createNamedQuery2(String string, Class<ConfigCommand> class1);

TypedQuery<StationConfig> createNamedQueryStationConfig(String string, Class<StationConfig> class1);


}