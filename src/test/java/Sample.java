import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class Sample {
    @Test(dataProvider ="data" )
    public void test1(Map.Entry<String,Integer> entry){

        System.out.println(entry.getKey()+" "+entry.getValue());
        //Random random=new Random();
       // System.out.println(random.nextInt(1000));


    }

    @DataProvider(name = "data")
    public Iterator<Map.Entry<String,Integer>> dataGive(){
        Set<Map.Entry<String, Integer>> set = new LinkedHashSet<>(Map.of("one", 1, "two", 2).entrySet());
       // set.forEach(System.out::println);
        return set.iterator();
    }
}
