package isti.ai;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.TypedQuery;

import isti.message.impl.cmad.CommandType;
import isti.message.impl.cmad.FormatoType;
import isti.message.impl.cmad.JCMAD;
import isti.message.impl.cmad.JCMADCommand;
import isti.message.impl.red.JMadRed;
import isti.mqtt.publisher.thread.PubThread;
import isti.mqtt.subscriber.AndroidSender;
import isti.serviziosupervisionestazione.apirest.persistence.TokenPersistence;

@Singleton
public class StingrayAI implements Runnable {

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(StingrayAI.class);

	private JCMAD jmad;


	@Inject
	private TokenPersistence em;
	
	public StingrayAI() {
		
	}

	public StingrayAI(JCMAD mad) {
		jmad = mad;
	}
	
	public void setCMAD(JCMAD mad) {
		jmad = mad;
	}

	@Override
	public void run() {
		try {
			Date now = jmad.getId().getCMAD_DATE();
			TimeUnit.HOURS.toMillis(1);
			Instant tenminuteslateri = now.toInstant().minus( 10 , ChronoUnit.MINUTES );
			Date tenminuteslater =  java.util.Date.from(tenminuteslateri);

			Instant eeminuteslateri = now.toInstant().minus( 11 , ChronoUnit.MINUTES );
			Date eeminuteslater =  java.util.Date.from(eeminuteslateri);

			TypedQuery<JCMAD>	r = 	em.createNamedQuery("JCMAD.findMacBetweenTime", JCMAD.class);
			r.setParameter(1, jmad.getId().getMAC_ADR());
			r.setParameter(2, eeminuteslater);
			r.setParameter(3, tenminuteslater);
			List<JCMAD> temp = r.getResultList();
			boolean flag = false;
			for(JCMAD jm : temp) {
				List<JMadRed> madreds = jm.getListred();
				for (JMadRed madred : madreds) {
					String macreds =  madred.getId().getMAC_ADR();
					int temp1 = madred.getWIRE_ANALOG_INFO().getTemperatura1();

					if (temp1 < 10 ||temp1 > 200 ) {
						flag = true;
					}
				}
			}
			if(flag | true) {
				checkAI(jmad);
			}
		}catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}

	}


	private void checkAI(JCMAD elementRead) {


		if (elementRead!=null) {
			String mac = elementRead.getId().getMAC_ADR();
			List<JMadRed> madreds = elementRead.getListred();
			for (JMadRed madred : madreds) {
				String macreds =  madred.getId().getMAC_ADR();
				int temp1 = madred.getWIRE_ANALOG_INFO().getTemperatura1();

				if (temp1 < 10) {
					JCMADCommand messae = new JCMADCommand();
					messae.setMAC_ADR_RED(madred.getId().getMAC_ADR());
					CommandType com = new CommandType();
					com.setCommandred(FormatoType.fromValue("ON"));

					messae.setCommand(com);
					PubThread th = new PubThread(messae);
					Thread thread = new Thread(th);

					thread.start();

					AndroidSender.sendToToken(mac+" "+macreds, "Accensione Automatica MADRED");
				}

				if (temp1 > 200) {
					JCMADCommand messae = new JCMADCommand();
					messae.setMAC_ADR_RED(madred.getId().getMAC_ADR());
					CommandType com = new CommandType();
					com.setCommandred(FormatoType.fromValue("OFF"));

					messae.setCommand(com);
					PubThread th = new PubThread(messae);
					Thread thread = new Thread(th);

					thread.start();

					AndroidSender.sendToToken(mac+" "+macreds, "Speghimento Automatico MADRED");

				}
			}
		}

	}

}
