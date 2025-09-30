import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

 public class Driver {
            public static void main(String [] args) throws IOException {
                    Polynomial p = new Polynomial();
                    System.out.println(p.evaluate(3));
                    double [] c1 = {6,5};
                    int [] e1 = {0,3};
                    Polynomial p1 = new Polynomial(c1,e1);
                    System.out.println("p(1)="+p1.evaluate(1));
                    double [] c2 = {-2,-9};
                    int[] e2 = {1,4};
                    Polynomial p2 = new Polynomial(c2,e2);
                    Polynomial s = p1.add(p2);
                    System.out.println("s(1) = " + s.evaluate(1));
                    if(s.hasRoot(1))
                       System.out.println("1 is a root of s");
                    else
                        System.out.println("1 is not a root of s");
                    Polynomial q = p1.multiply(p2);
                    System.out.println("q(1) = " + q.evaluate(1));
                    
                    File file_name = new File("Polynomial.txt");
                    Polynomial p3 = new Polynomial(file_name);
                    System.out.println("p3(1) = " + p3.evaluate(1));
                    q.saveToFile("Polynomial.txt");
                    
            }
        }

