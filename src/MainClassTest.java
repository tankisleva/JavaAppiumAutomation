import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    private int class_number = 20;
    private String class_string = "Hello, world";



    @Test
    public void testGetLocalNumber(){
        Assert.assertTrue("метод getLocalNumber не возвращает 14!",this.getLocalNumber() == 14);

    }


    @Test
    public void testGetClassNumber(){
        Assert.assertTrue("метод getClassNumber возвращает больше 20!",this.getClassNumber() <= 20);

    }


    @Test
    public void testGetClassString(){
        Assert.assertTrue("Метод testGetClassString не содержит слова Hello",this.getClassString().contains("Hello"));
    }

    public  int getLocalNumber(){
        return 14;
    }


    public int getClassNumber(){
        return this.class_number;
    }

    public String getClassString(){
        return class_string;
    }
}
