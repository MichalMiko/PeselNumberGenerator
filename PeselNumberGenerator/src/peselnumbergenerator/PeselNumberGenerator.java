/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peselnumbergenerator;
import java.util.GregorianCalendar;
import java.util.Calendar;

/**
 * @author Michal.Mikolajczyk
 */
public class PeselNumberGenerator {

        public static int randBetween(int start, int end) {
            return start + (int)Math.round(Math.random() * (end - start)); // losowanie z zakresu
        }
    
        public static void main(String[] args) {
        // TODO code application logic here
                
        GregorianCalendar gc = new GregorianCalendar();
        int actYear = Calendar.getInstance().get(Calendar.YEAR);    // sprawdzenie aktualnego roku
        int year = randBetween(1800, actYear);  // losowy rok
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR)); /*https://www.tutorialspoint.com/java/util/calendar_getactualmaximum.htm*/
        gc.set(gc.DAY_OF_YEAR, dayOfYear);
                
        System.out.println("Data wyjściowa: " + gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH)+1) + "-" + gc.get(gc.DAY_OF_MONTH));

        // Budowanie pesla
        //Dzien
        int peselDay = gc.get(gc.DAY_OF_MONTH);
        // Miesiac
        int peselMonth = (gc.get(gc.MONTH))+1;
        
        if (year >= 1800 && year < 1899) {
            peselMonth = peselMonth + 80;
        }
        else if (year >= 2000 && year < 2099) {
            peselMonth = peselMonth + 20;
        }
        else if (year >= 2100 && year < 2199) {
            peselMonth = peselMonth + 40;
        }
        else if (year >= 2200 && year < 2299) {
            peselMonth = peselMonth + 60;
        }
        
        //System.out.println("Miesiac na 2 znakach: " + String.format("%02d", peselMonth));
        
        //Seria
        int seria1 = randBetween(0, 9);
        int seria2 = randBetween(0, 9);
        int seria3 = randBetween(0, 9);
        
        //Plec
        int gender = randBetween(0, 9);
        
        //Cyfra kontrolna
        String peselNumberStr = (Integer.toString(year).substring(2)) + String.format("%02d", peselMonth) + String.format("%02d", peselDay) + seria1 + seria2 + seria3 + gender;
        String digitWeight = "1379137913";
        
        int controlDigit = 0;
        for (int i=0; i<10; i++){
            controlDigit = controlDigit + (Character.getNumericValue(peselNumberStr.charAt(i)) * Character.getNumericValue(digitWeight.charAt(i)));
            }
        /*int k = 10- (controlDigit%10);
        if (k==10) {
            k=0;
        }*/
        
        if ((10- (controlDigit%10))==10) {
            controlDigit=0;
        } else {controlDigit = (10- (controlDigit%10));}
        System.out.println( "PESEL: " +peselNumberStr + controlDigit);
    }
    
    
}
