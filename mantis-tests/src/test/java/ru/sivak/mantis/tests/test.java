package ru.sivak.mantis.tests;

import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

/**
 * @author p.sivak.
 * @since 30.04.2018.
 */
public class test extends TestBase {
    @Test
    public void test() throws RemoteException, ServiceException, MalformedURLException {
        app.soap().checkIssueState(0000001);
    }
}
