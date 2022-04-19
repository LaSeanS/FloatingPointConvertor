
package project2csc4101;

public class Convertor {
    
    public Convertor(){}
    
    public static String IEEE754(double num){
        StringBuilder rString = new StringBuilder();
        StringBuilder mantissa = new StringBuilder();
        int wholeNum = 0;
        double fractPart = 0.0;
        int numShifts = 0;
        
        //determine signed bit
        rString.append(signedBit(num));
        
        //turning num into a positive double b/c the rest of the binary stuff is in unsigned binary
        num = Math.abs(num);
        
        if(num >= 1) {
            wholeNum = (int)Math.floor(num);
            fractPart = num - wholeNum;
            
            mantissa.append(findWholeNumBinary(wholeNum));
            numShifts = findShifts(mantissa) + 127;
            mantissa.delete(0, mantissa.indexOf("1") + 1);
            mantissa.append(findFractNumBinary(fractPart));
            
            rString.append(format(findWholeNumBinary(numShifts), mantissa));
            
        }
        else {
            fractPart = num; 
        }
        
        return rString.toString();
    }
    
    private static StringBuilder findWholeNumBinary(int num) {
        StringBuilder rWhole = new StringBuilder();
        
        while(num > 1) {
            rWhole.insert(0, String.valueOf(num%2));
            num /= 2;
        }
        
        rWhole.insert(0, String.valueOf(num));
        
        return rWhole;
    }
    
        private static StringBuilder findFractNumBinary(double fract) {
        StringBuilder rFract = new StringBuilder();
        int i;
        
        for(i = 0; i < 23; i++) {
            int nextBit = (int)Math.floor(fract*2);
            rFract.append(String.valueOf(nextBit));
            fract = (fract*2) - nextBit;
        }
        
        return rFract;
    }
        
    private static int findShifts(StringBuilder num) {
        int shifts;  
        shifts = ((num.length() - 1) - num.indexOf("1"));    
        return shifts;
    }
    
    private static StringBuilder format(StringBuilder exp, StringBuilder mant) {
        StringBuilder result = new StringBuilder();
        result.append(" ");
        result.append(exp);
        result.append(" | ");
        if(mant.length() > 23) {
            mant.delete(23, mant.length());
        }
        result.append(mant);
        return result;
    }
    
    private static String signedBit(double num){
        String rString = "";
        //determine signed bit
        if(num < 0){
            rString += "1 |";
        }else{
            rString +="0 |";
        }
        return rString;
    }
    
}
