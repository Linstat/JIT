package ru.sivak.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.sivak.mantis.model.Issue;
import ru.sivak.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author p.sivak.
 * @since 30.04.2018.
 */
public class SoapHelper {

    private final ApplicationManager app;

    public SoapHelper (ApplicationManager app){
        this.app = app;
    }

    public Set<Project> getProgects() throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible(app.getProperty("web.adminLogin"), app.getProperty("web.adminPass"));
        return Arrays.asList(projects).stream().map((p)->new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator().getMantisConnectPort(new URL(app.getProperty("soap.wsdlURL")));
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String[] categoryes = mc.mc_project_get_categories(app.getProperty("web.adminLogin"), app.getProperty("web.adminPass"), BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        issueData.setCategory(categoryes[0]);
        BigInteger issueId = mc.mc_issue_add(app.getProperty("web.adminLogin"), app.getProperty("web.adminPass"), issueData);
        IssueData createdIssueData =  mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPass"), issueId);
        return new Issue().withId(createdIssueData.getId().intValue())
                .withSummary(createdIssueData.getSummary()).withDescription(createdIssueData.getDescription())
                .withProject(new Project().withId(createdIssueData.getPriority().getId().intValue()).withName(createdIssueData.getProject().getName()));
    }

    public String checkIssueState(int id) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData issue = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPass"), BigInteger.valueOf(id));
        ObjectRef status = issue.getStatus();
        return status.getName();
    }
}