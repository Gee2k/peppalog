package de.gx.catfoodist.io.ean.opengtindb;

import de.gx.catfoodist.io.ean.Ean;
import de.gx.catfoodist.io.ean.EanService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OpenGTINService implements EanService {

    String base_uri = "http://opengtindb.org/";
    String ressource ="?ean=";
    String command = "&cmd=query";
    String queryId = "&queryid=";

    @Value("${opengtin-queryId}")
    String querIdToken;

    @Override
    public Ean getInfoForEan(String ean) {
        // get info
        String rawResult = queryEan(ean);

        //validate
        if (!validateResult(rawResult)) {
            return null;    //throw exception or something else dunno...
        }
        //map and return
        return mapResultToEan(rawResult, ean);
    }

    private String queryEan(String ean) {
        String queryUrl = base_uri + ressource + ean + command + queryId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(queryUrl, String.class);

        return response.getBody();
    }

    private boolean validateResult(String rawResult) {
        return rawResult.contains("error=0");
    }

    private Ean mapResultToEan(String result, String eanCode) {
        Ean ean = new Ean();
        ean.setEan(eanCode);

        String name = findSubString(OpenGTINDB.NAME, result);
        String detailName = findSubString(OpenGTINDB.DETAIL_NAME, result);

        if (StringUtils.hasText(name)) {
            ean.setName(name);
        }
        else if(StringUtils.hasText(detailName)) {
            ean.setName(detailName);
        }
        else {
            ean.setName("NO NAME FOUND");
        }

        return ean;
    }

    public String findSubString(OpenGTINDB key, String haystack) {
        String result = "";
        String regex = "(\\n)" + key.getValue() + "=.*\\n";//(\\n|\\R)");

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(haystack);

        if (matcher.find()) {
            String match = matcher.group();
            result = match.substring(2 + key.getValue().length(), match.length() - 1);
        }

        return result;
    }
}
