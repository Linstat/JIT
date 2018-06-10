package ru.sivak.bugify.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import ru.sivak.bugify.model.Issue;

import java.io.IOException;
import java.util.Set;

/**
 * @author p.sivak.
 * @since 30.04.2018.
 */
public class IssueHelper extends HelperBase{
    @BeforeClass
    public void init(){
        RestAssured.authentication = RestAssured.basic("28accbe43ea112d9feb328d2c00b3eed", "");
    }

    public IssueHelper(ApplicationManager app) {
        super(app);
    }

    public Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json")).returnContent().asString();
       // String json = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    public int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                        new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
      /*  String json = RestAssured.given()
                .parameter("subject", newIssue.getSubject())
                .parameter("description", newIssue.getDescription())
                .post("http://demo.bugify.com/api/issues.json").asString();*/
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    public String checkIssueState(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues/"+issueId+".json")).returnContent().asString();
     //   String json = RestAssured.get("http://demo.bugify.com/api/issues/"+issueId+".json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<Issue> array = new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
        return array.iterator().next().getState_name();
    }
}
