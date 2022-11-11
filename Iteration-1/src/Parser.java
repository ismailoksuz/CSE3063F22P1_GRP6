import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Parser {
    static Parser parser = new Parser();

    public List<Advisor> parseAdvisors() throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        File file = new File("Iteration-1/src/test_advisor.json");
        FileReader reader = new FileReader(file);
        JSONObject advisorsList = (JSONObject) parser.parse(reader);

        JSONArray advisors = (JSONArray) advisorsList.get("advisors");
        List<Advisor> advisorList = new ArrayList<Advisor>();

        for (Object anAdvisor : advisors) {
            JSONObject advisor = (JSONObject) anAdvisor;
            String firstName = (String) advisor.get("firstName");
            String lastName = (String) advisor.get("lastName");
            Advisor newAdvisor = new Advisor(firstName, lastName);
            advisorList.add(newAdvisor);
        }
        return advisorList;
    }
}
