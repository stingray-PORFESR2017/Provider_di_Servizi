package isti.message.impl.cmad;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "jCMADs")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListJCMAD {
	@XmlElement(name = "DatiCMAD")
    List<JCMAD> jCMADs = new ArrayList<JCMAD>();

    public ListJCMAD() {}

    public void setList(List<JCMAD> amenities) {
        this.jCMADs = amenities;
    }

}
