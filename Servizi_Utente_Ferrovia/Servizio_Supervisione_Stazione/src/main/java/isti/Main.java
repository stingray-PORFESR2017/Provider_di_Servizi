package isti;

import org.glassfish.grizzly.http.server.HttpServer;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import isti.mqtt.subscriber.Application;


/**
 * 
 *@startuml
class Main [[java:isti.Main]] {
	+{static}void main(String[] args)
}

class SecureSUF [[java:isti.secure.SecureSUF]] {
	-{static}org.apache.log4j.Logger log
 }

class Subscriber [[java:isti.mqtt.subscriber.Subscriber]] {
	-{static}org.apache.log4j.Logger log
	-int qos
	-String topic
	-IMqttClient publisher
	-TokenPersistence em
	+void run()
	+void connectionLost(Throwable cause)
	+void deliveryComplete(IMqttDeliveryToken cause)
	-boolean check(String s)
	+void messageArrived(String topic, MqttMessage message)
}
interface MqttCallback [[java:org.eclipse.paho.client.mqttv3.MqttCallback]] {
}

class StartGrizzly [[java:isti.rest.StartGrizzly]] {
	+{static}String BASE_URI
	+{static}String BASEH_URI
	+{static}String protocol
	+{static}Optional<String> host
	+{static}String path
	+{static}Optional<String> port
	-{static}String KEYSTORE_SERVER_FILE
	-{static}String KEYSTORE_SERVER_PWD
	+{static}HttpServer startServer()
	+{static}void starthttps()
	+{static}void main(String[] args)
}

class ApiServizioCMAD [[java:isti.serviziosupervisionestazione.apirest.impl.ApiServizioCMAD]] {
	~TokenPersistence em
	-{static}org.apache.log4j.Logger log
	+JCMAD daticmad(String key, String datei, String datef, HttpServletRequest request, HttpServletResponse response)
	+JCMAD daticmadmac(String key, HttpServletRequest request, HttpServletResponse response)
	+List<JCMAD> daticmadmacall(String key, HttpServletRequest request, HttpServletResponse response)
	+List<JCMAD> alldaticmad(String key, HttpServletRequest request, HttpServletResponse response)
	+String receiveCommand(JCMADCommand message, String key, HttpServletRequest request, HttpServletResponse response)
	+String AuthInfo(AuthInfo message, String key, HttpServletRequest request, HttpServletResponse response)
}

class MessageCMAD [[java:isti.message.MessageCMAD]] {
	-{static}org.apache.log4j.Logger log
	~String CMAD_HEADER
	~String MAC_ADR
	~int CMAD_TYPE
	~int CMAD_REVISION
	~short CMAD_POSITION
	~String CMAD_DESCRIPTION
	~long CMAD_LONGITUDE
	~long CMAD_LATITUDE
	~short CMAD_DIGITAL_INFO
	~byte[] CMAD_ANALOG_INFO
	~byte[] CMAD_Dummy
	~int CMAD_CRC
	~long Timestamp
	~int armamento
	~CMADAnalogInfo cCMAD_ANALOG_INFO
	~byte[] mess
	~List<JMadRed> listred
	~List<JMADILL> listill
	+MessageCMAD(byte[] message)
	+JCMAD getJCMAD()
	+{static}byte[] intToBytes(int x)
	+String toString()
	+byte[] toByte()
}

class JCMAD [[java:isti.message.impl.cmad.JCMAD]] {
	~JCMADID Id
	~String CMAD_HEADER
	~int CMAD_TYPE
	~int CMAD_REVISION
	~String CMAD_POSITION
	~String CMAD_DESCRIPTION
	~String CMAD_LONGITUDE
	~String CMAD_LATITUDE
	~String CMAD_DIGITAL_INFO
	~CMADAnalogInfo CMAD_ANALOG_INFO
	~String CMAD_RAW
	~String CMAD_CRC
	~List<JMadRed> listred
	~List<JMADILL> listill
	+JCMAD()
	+JCMAD(String cMAD_HEADER)
	+void Init(String cMAD_HEADER)
	+String getCMAD_HEADER()
	+void setCMAD_HEADER(String cMAD_HEADER)
	+int getCMAD_TYPE()
	+void setCMAD_TYPE(int cMAD_TYPE)
	+int getCMAD_REVISION()
	+void setCMAD_REVISION(int cMAD_REVISION)
	+String getCMAD_POSITION()
	+void setCMAD_POSITION(String cMAD_POSITION)
	+String getCMAD_DESCRIPTION()
	+void setCMAD_DESCRIPTION(String cMAD_DESCRIPTION)
	+String getCMAD_LONGITUDE()
	+void setCMAD_LONGITUDE(String cMAD_LONGITUDE)
	+String getCMAD_LATITUDE()
	+void setCMAD_LATITUDE(String cMAD_LATITUDE)
	+String getCMAD_DIGITAL_INFO()
	+void setCMAD_DIGITAL_INFO(String cMAD_DIGITAL_INFO)
	+CMADAnalogInfo getCMAD_ANALOG_INFO()
	+void setCMAD_ANALOG_INFO(CMADAnalogInfo cMAD_ANALOG_INFO)
	+String getCMAD_RAW()
	+void setCMAD_RAW(String cMAD_Dummy)
	+String getCMAD_CRC()
	+void setCMAD_CRC(String cMAD_CRC)
	+JCMADID getId()
	+void setId(JCMADID id)
	+int hashCode()
	+boolean equals(Object obj)
	+List<JMadRed> getListred()
	+void setListred(List<JMadRed> listred)
	+List<JMADILL> getListill()
	+void setListill(List<JMADILL> listill)
	+byte[] toByte()
}

java_io_Serializable <|.. JCMAD

class JMADILL [[java:isti.message.impl.ill.JMADILL]] {
	~JCMADID Id
	~String HEADER
	~int TYPE
	~int REVISION
	~String POSITION
	~String DESCRIPTION
	~String LONGITUDE
	~String LATITUDE
	~String DIGITAL_INFO
	~AnalogInfo ANALOG_INFO
	~String RAW
	~String CRC
	+JMADILL(String cmAC)
	+JMADILL()
	+int hashCode()
	+boolean equals(Object obj)
	+JCMADID getId()
	+void setId(JCMADID id)
	+String getHEADER()
	+void setHEADER(String hEADER)
	+int getTYPE()
	+void setTYPE(int tYPE)
	+int getREVISION()
	+void setREVISION(int rEVISION)
	+String getPOSITION()
	+void setPOSITION(String pOSITION)
	+String getDESCRIPTION()
	+void setDESCRIPTION(String dESCRIPTION)
	+String getLONGITUDE()
	+void setLONGITUDE(String lONGITUDE)
	+String getLATITUDE()
	+void setLATITUDE(String lATITUDE)
	+String getDIGITAL_INFO()
	+void setDIGITAL_INFO(String dIGITAL_INFO)
	+AnalogInfo getANALOG_INFO()
	+void setANALOG_INFO(AnalogInfo aNALOG_INFO)
	+String getRAW()
	+void setRAW(String rAW)
	+String getCRC()
	+void setCRC(String cRC)
}


java_io_Serializable <|.. JMADILL

class JMadRed [[java:isti.message.impl.red.JMadRed]] {
	~JCMADID Id
	~String HEADER
	~int TYPE
	~int REVISION
	~String POSITION
	~String DESCRIPTION
	~String LONGITUDE
	~String LATITUDE
	~String DIGITAL_INFO
	~String WIRE_DIGITAL_INFO
	~WireAnalogInfo WIRE_ANALOG_INFO
	~String RAW
	~String CRC
	+JMadRed()
	+JMadRed(String cmAC)
	+JCMADID getId()
	+void setId(JCMADID id)
	+String getHEADER()
	+void setHEADER(String hEADER)
	+int getTYPE()
	+void setTYPE(int tYPE)
	+int getREVISION()
	+void setREVISION(int rEVISION)
	+String getPOSITION()
	+void setPOSITION(String pOSITION)
	+String getDESCRIPTION()
	+void setDESCRIPTION(String dESCRIPTION)
	+String getLONGITUDE()
	+void setLONGITUDE(String lONGITUDE)
	+String getLATITUDE()
	+void setLATITUDE(String lATITUDE)
	+String getDIGITAL_INFO()
	+void setDIGITAL_INFO(String dIGITAL_INFO)
	+String getWIRE_DIGITAL_INFO()
	+void setWIRE_DIGITAL_INFO(String wIRE_DIGITAL_INFO)
	+WireAnalogInfo getWIRE_ANALOG_INFO()
	+void setWIRE_ANALOG_INFO(WireAnalogInfo wIRE_ANALOG_INFO)
	+String getRAW()
	+void setRAW(String rAW)
	+String getCRC()
	+void setCRC(String cRC)
	+int hashCode()
	+boolean equals(Object obj)
}

java_io_Serializable <|.. JMadRed

JCMAD..|>JMadRed

JCMAD..|>JMADILL

Subscriber..|>MessageCMAD

Subscriber..|>JCMAD

ApiServizioCMAD..|>JCMAD

StartGrizzly..|>ApiServizioCMAD

Main..|>Subscriber

Main..|>StartGrizzly

MqttCallback <|.. Subscriber

StartGrizzly..|>SecureSUF
Subscriber..|>SecureSUF
JCMAD..|>SecureSUF
ApiServizioCMAD..|>SecureSUF
@enduml
 */




public class Main {

	
	public static void main(String[] args) {
		Weld weld = new Weld();
		   WeldContainer container = weld.initialize();
		   Application application = container.select(Application.class).get();
		   application.setWeld(container);
		   application.run();
		   
		 isti.rest.StartGrizzly mai = container.select(isti.rest.StartGrizzly.class).get();
		 mai.starthttps();
		   
		   
		 //  weld.shutdown();
		 
		
	}
}
