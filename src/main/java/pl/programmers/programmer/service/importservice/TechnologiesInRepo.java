package pl.programmers.programmer.service.importservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class TechnologiesInRepo {

    String repoPath = "https://github.com/MaciejBabicki/Programmers";
    Set<String> technologiesUsed;
    public Set<String> showTechnologies() {

            try {
                URL url = new URL(repoPath);
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                String line;
                StringBuilder content = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                reader.close();
                String code = content.toString();
                technologiesUsed = identifyTechnologies(code);
                System.out.println("Technologies used in the repository:");
                for (String technology : technologiesUsed) {
                    System.out.println(technology);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return technologiesUsed;
    }

    public static Set<String> identifyTechnologies(String code) {
        Set<String> technologiesUsed = new HashSet<>();
        if(code.contains(".java")){
            technologiesUsed.add("Java");
        } else if (code.contains(".pyc")) {
            technologiesUsed.add("Python");
        } else if (code.contains(".js")) {
            technologiesUsed.add("JavaScrips");
        }
        return technologiesUsed;
    }
}
