package task01;

/***
 * Main class
 * @author Igor Evstifeev
 * @version 2.0
 */

public class Main {
    /**
     * Generate exception «NullPointerException»
     * Generate exception  «ArrayIndexOutOfBoundsException»
     * Generate Exception with throw
     */
    public static void main(String[] args){

        try{
            String str = null;
            if (str.equals("Message")){
                System.out.println(str);
            }
        }
        catch (NullPointerException npe){
            System.out.println("Генерируется NullPointerException: " + npe);
        }

        try {
            int[] mArr = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
            for (int i= mArr.length; i>0; i++ ){
                System.out.println(mArr[i]);
            }
        }
        catch(ArrayIndexOutOfBoundsException aio){
            System.out.println("Генерируется ArrayIndexOutOfBoundsException: " + aio);
        }

        try {
            int a = 0;
            if (a == 0) {
                throw new RuntimeException("Генерируется свое исключение через throw");
            }
        }
        catch (RuntimeException e) {
            throw e;
        }
    }
}
