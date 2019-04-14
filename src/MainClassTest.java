import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {



    @Test
    public void testGetLocalNumber(){
        Assert.assertTrue(this.getLocalNumber() == 14);

    }

    public  int getLocalNumber(){
        return 14;
    }

}
