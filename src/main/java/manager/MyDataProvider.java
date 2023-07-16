package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyDataProvider {
    @DataProvider
    public Iterator<Object[]>userModelListDTO_CSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/regData.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] splitLine = line.split(",");
            list.add(new Object[]{new User()
                    .withEmail(splitLine[0])
                    .withPassword(splitLine[1])
            });
            line = reader.readLine();
        }
        return list.iterator();
    }
}
