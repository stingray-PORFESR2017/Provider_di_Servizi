package it.dinfo.stlab.rest;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/") //oppure si puo mettere "/rest"
public class MyRestApplication extends Application {
    //no methods required

    //per istanziare gli oggetti a mano senza utilizzare CDI
    /* @Override
    public Set<Object> getSingletons() {
        Set<Object> set = new HashSet<>();

        set.add(new UserAccountResource()); //aggiungo il primo endpoint
        set.add(new SmartStationResource()); //aggiungo il secondo endpoint
        set.add(new InfomobilityServiceResource()); //aggiungo il terzo endpoint
        return set;
    }*/
}
