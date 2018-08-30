package myTest.Guava;

import com.google.common.base.Preconditions;
import org.assertj.core.util.Strings;

/**
 * Created by jsflz on 2018/8/23.
 */
public class TestPreconditions {

    public static void testCondition() {
        int count = 0;

        //Use java
        if (count <= 0) {
            // throw new IllegalArgumentException("@@@must be positive: " + count);
        }

        //Use Guava  ( "IF NULL()" thought )
        //If the value of count IS NOT >0, then throw the exeception with the specific msg.
        //***Which means: If param IS FALSE, then  throw the exeception with the specific msg.
        Preconditions.checkArgument(count>0,"must be positive: %s",count);
    }

    /**
     * Use this way to check the params in the future!!
     */
    public static void Joiner_checkParams_String() {
        String param1 = "test";
        String param2 = null;

        Preconditions.checkArgument(!Strings.isNullOrEmpty(param1),"@@@param1 can not be empty!");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(param2),"@@@param2 can not be empty!"); //Executed!!!
    }

    public static void Joiner_exception_handled() {
        try {
            Joiner_checkParams_String();
        } catch (IllegalArgumentException e) {
            System.out.println("@@@Catched the null params!!!");
        } catch (Exception e) {
            System.out.println("@@@Catched the exception!!!");
        }
        System.out.println("@@@finished!!!");

        /**
         Result:
         @@@Catched the null params!!!
         @@@finished!!!
         */
    }

    public static void main(String[] args) {
        Joiner_exception_handled();
    }

}
