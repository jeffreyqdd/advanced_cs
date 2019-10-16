package playground;
import java.util.*;
import java.awt.*;
public class MathandScanner {
public static void main (String[] args) {
   Scanner console = new Scanner(System.in);
   DrawingPanel panel = new DrawingPanel (300, 300);
   Graphics g = panel.getGraphics();
   placeCircles(console, g);
   }
      public static void placeCircles(Scanner console, Graphics g) {
      System.out.println ("How many circles do you want?");
      int num = console.nextInt();
      for (int i = 1; i <= num; i++) {
      System.out.println ("What radius circle do you want?");
      int radius = console.nextInt();
      System.out.println ("Circle's x coordinate:");
      int x = console.nextInt();
      System.out.println ("Circle's y coordinate:");
      int y = console.nextInt();
      g.drawOval(x, y, radius * 2, radius * 2);
      
      }
} 
   public void
   printPowersOfN(Scanner console) {
   System.out.println ("Enter the base:");
   int base = console.nextInt();
   System.out.println ("Enter the max exponent:");
   int exponent = console.nextInt();
   for (int j = 1; j <= exponent; j++); {
   Math.pow (base, exponent);
   }
   System.out.println ("First 4 powers of 5:");
   int powers = console.nextInt();
} 
}

   

