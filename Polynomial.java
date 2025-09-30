import java.io.File;                  
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;


public class Polynomial {
      double[] coefficients;
      int [] exponents;
      
      public Polynomial(){
             coefficients = new double[] {0};
             exponents = new int[] {0};
      }
      
      public Polynomial (double[] coeffs, int [] exps){
             int coeff_length = coeffs.length;
             int exp_length = exps.length;
             coefficients =  new double[coeff_length];
             exponents = new int[exp_length];
             for(int i =0; i<coeff_length; i++){
                 coefficients[i] = coeffs[i];
                 exponents[i] = exps[i];
             }
      }
    
      public Polynomial(File p_file){
              
              String line="";
              try {
                 Scanner scanner = new Scanner(p_file);
                 while (scanner.hasNextLine()) 
                        line = scanner.nextLine();
                 scanner.close();
              } 
              catch (FileNotFoundException e) {
              System.out.println("Error: File not found.");
              e.printStackTrace();
              }
              String[] components = line.split("[-,+]");
              coefficients = new double[components.length];
              exponents = new int[components.length];
              for(int i=0; i<components.length;i++){
                  coefficients[i] = Double.parseDouble((components[i].split("x"))[0]);
                  if(((components[i].split("x")).length)==2)
                       exponents[i] = Integer.parseInt((components[i].split("x"))[1]);
                  else
                        exponents[i]=0;
              }
              
    }
             
             

      public Polynomial add(Polynomial P){  //1
             
             int exp_count = 0;
             int P_length = P.exponents.length;
             int length = exponents.length;
             int max_P = P.exponents[P_length-1];
             int max = exponents[length-1];
             int max_exp = Math.max(max_P, max);

             for(int x=0; x<=max_exp;x++){ // 2
                 if(in_array(x, P.exponents)||in_array(x, exponents))
                    exp_count++;
             }//2

             int[] new_exponents = new int[exp_count];
             double[] new_coefficients = new double[exp_count];
             int max_array_length = Math.max(P_length,length);
             int min_array_length = Math.min(P_length,length);

             for(int i=0; i<max_array_length; i++){//3
                    if(i<min_array_length){//5

                       if(P.exponents[i] == exponents[i] && P.coefficients[i] + coefficients[i]!=0){//6
                          new_coefficients[i] = P.coefficients[i] + coefficients[i];
                          new_exponents[i] = exponents[i];
                       }//6

                       else if(P.exponents[i] < exponents[i]){//7
                            new_coefficients[i] = P.exponents[i];
                            new_exponents[i] = P.exponents[i];
                            int next_exp = Math.min(exponents[i], P.exponents[i+1]);

                            if(next_exp == exponents[i]){//8
                               new_exponents[i+1] = exponents[i];
                               new_coefficients[i+1] = coefficients[i];
                            }//8

                            else{//9
                                 new_exponents[i+1] = P.exponents[i+1];
                                 new_coefficients[i+1] = P.coefficients[i+1];
                            }//9
                       }//7

                       else if(P.exponents[i] > exponents[i]){//10
                            new_coefficients[i] = exponents[i];
                            new_exponents[i] = exponents[i];
                            int next_exp = Math.min(exponents[i+1], P.exponents[i]);

                            if(next_exp == exponents[i+1]){//11
                               new_exponents[i+1] = exponents[i+1];
                               new_coefficients[i+1] = coefficients[i+1];
                            }//11

                            else{//12
                                 new_exponents[i+1] = P.exponents[i];
                                 new_coefficients[i+1] = P.coefficients[i];
                            }//12

                            i++;
                       }//10

                    }//5

                    else if(P_length==max_array_length){//13
                            new_coefficients[i] = P.coefficients[i];
                            new_exponents[i] = P.exponents[i];
                    }//13

                    else if (length == max_array_length){//14
                             new_coefficients[i] = coefficients[i];
                             new_exponents[i] = exponents[i];
                    }//14

                  }//3
                    
                  Polynomial new_P = new Polynomial(new_coefficients, new_exponents);
                  return new_P;
 
      }//1

      public boolean in_array(int num, int[]ex){   //helper function
             for(int x=0; x< ex.length; x++){
                 if (ex[x] == num)
                     return true;
             }
             return false;
      }
                     

      public double evaluate(double x){
             double value = 0;
             for(int i =0; i<coefficients.length; i++){
                 if(exponents[i]==0)
                    value+= coefficients[i];
                 else
                    value += (coefficients[i]*(Math.pow(x,exponents[i])));
             }
             return value;
      }

      public boolean hasRoot(double y){
             return (evaluate(y)==0);
      } 


      public int find_in_array(int num, int[]ex){   //helper function
             for(int x=0; x< ex.length; x++){
                 if (ex[x] == num)
                     return x;
             }
             return -1;
      }


      public Polynomial multiply(Polynomial P){
             int degree = P.exponents[P.exponents.length-1] + exponents[exponents.length-1];
             int[] new_exps = new int[degree];
             double[] new_coeffs = new double[degree];
             int k=0;
             int new_exp;
             double new_coeff;
             for(int i=0; i<P.coefficients.length;i++){
                 for(int j=0; j<coefficients.length; j++){
                     new_coeff = P.coefficients[i]*coefficients[j];
                     new_exp = P.exponents[i] + exponents[j];
                     int idx = find_in_array(new_exp, new_exps);
                     if (idx!=-1)
                         new_coeffs[idx]+=new_coeff;
                     else{
                         new_exps[k] = new_exp;
                         new_coeffs[k] = new_coeff;
                         k++;
                     }
                  }
              }
        Polynomial new_P = new Polynomial(new_coeffs, new_exps);
        return new_P;
        }

        
              
        public void saveToFile(String file_name) throws IOException{
               StringBuilder sb = new StringBuilder();
               for (int i = 0; i < coefficients.length; i++) {
                     double coeff = coefficients[i];
                     int exp = exponents[i];
                     if (coeff > 0 && sb.length() > 0)
                         sb.append("+");
                     else if (coeff < 0 && sb.length() > 0)
                          sb.append("-");
                     sb.append(coeff);
                     if (exp != 0) 
                         sb.append("x").append(exp);
                }
                
                try (FileWriter writer = new FileWriter(file_name)) {
                       writer.write(sb.toString());
                }
         }
                    
}

                 
             
             
             
                 
