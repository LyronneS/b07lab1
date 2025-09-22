public class Polynomial {
      double[] coefficients;
      
      public Polynomial(){
             coefficients = new double[] {0};
      }
      
      public Polynomial (double[] coeffs){
             int array_length = coeffs.length;
             coefficients =  new double[array_length];
             for(int i =0; i<array_length; i++){
                 coefficients[i] = coeffs[i];
             }
      }

      public Polynomial add(Polynomial P){
             int array_length = Math.max((P.coefficients).length, coefficients.length);
             if (array_length == P.coefficients.length){
                 for(int i =0; i<array_length; i++){
                     if(i<coefficients.length)
                        P.coefficients[i] += coefficients[i];
                     else
                         P.coefficients[i]+=0.0;
                  }
             return P;
             }
             else{
                  for(int i =0; i<array_length; i++){
                      if(i<P.coefficients.length)
                         coefficients[i] += P.coefficients[i];
                      else
                         coefficients[i] += 0.0;
             return this;
             } 
       }
       return P;    
     }

      public double evaluate(double x){
             double value = 0;
             for(int i =1; i<coefficients.length; i++){
                 value = value + (coefficients[i]*(Math.pow(x,i)));
             }
             value = value + coefficients[0];
             return value;
      }

      public boolean hasRoot(double y){
             return (evaluate(y)==0);
      } 

}

                 
             
             
             
                 
