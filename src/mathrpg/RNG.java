/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathrpg;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;
/**
 *
 * @author Chong Bin Yong
 */
public class RNG {
    
    GamePanel gp;
    
    public float answerDec;
    public int answerFracNume;
    public int answerFracDeno;
    
    DecimalFormat f = new DecimalFormat("##.00");
    Random rand = new Random();
//    Graphics2D g2;
//    Font maruMonica;
//    public int counter = 0;
    
    // m is coprime to seed r_seed
    static final long m = 2147483647;
  
    // constants
    static final long a = 48271;
    static final long q = 44488;
    static final long r = 3399;
    
    // take a r_seed that is coprime to m
//    long r_seed = System.currentTimeMillis() / 1000000;
    long r_seed = rand.nextInt(9000000) + 1000000;
    
    public RNG(GamePanel gp)
    {
        this.gp = gp;
        
//        try {
//            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
//            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
//        } catch (FontFormatException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    
//    public void draw(Graphics2D g2)
//    {
//        this.g2 = g2;
//        
//        g2.setFont(maruMonica);
//        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//        g2.setColor(Color.white);
        
//        counter++;
//        if(counter==120)
//        {
//            generateDecMul();
//            counter=0;
//        } 
//    }
    
//    public void update()
//    {
//  
//    }
    
    public float uniform()
    {
//        System.out.println(r_seed);
                
        // highest and lowest for a random number generation range
        long hi = r_seed / q;
        long lo = r_seed - q * hi;
//        long lo = r_seed % q;
  
        // calculate random number
        long t = (a * lo) - (r * hi);
  
        // if positive
        if (t > 0)
        {
            r_seed = t;
        }
        else
        {
            r_seed = t + m;
        }
        
//        System.out.println(r_seed);
        return r_seed;
    }
    
    public String generateDecMul()
    {
//        counter++;
//        if(counter==120)
//        {
//        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        gp.player.totalMulDec++;
        String question = null;
        float number[] = generateDecimal();
        if(gp.ui.difficultyLvl == 0)
        {
            question = (number[0]+1.0f) + " × " + (number[1]+1.0f);
            float answer = (float) ((number[0]+1.0f) * (number[1]+1.0f));
            answerDec = (float) (Math.round(answer * 100.0) / 100.0);
        }
        else if(gp.ui.difficultyLvl == 1)
        {
            question = (number[0]+1.0f) + " × " + (number[1]+1.0f) + " × " + (number[2]+1.0f);
            float answer = (float) ((number[0]+1.0f) * (number[1]+1.0f) * (number[2]+1.0f));
            answerDec = (float) (Math.round(answer * 100.0) / 100.0);
        }
        else if(gp.ui.difficultyLvl == 2)
        {
            question = (number[0]+1.0f) + " × " + (number[1]+1.0f) + " × " + (number[2]+1.0f) + " × " + (number[3]+1.0f);
            float answer = (float) ((number[0]+1.0f) * (number[1]+1.0f) * (number[2]+1.0f) * (number[3]+1.0f));
            answerDec = (float) (Math.round(answer * 100.0) / 100.0);
        }
//        System.out.println(question);
//        g2.drawString(question, gp.tileSize*3, gp.tileSize*2);
        return question;
//        }
//        counter=0;
    }
    
    public String generateDecDiv()
    {
        gp.player.totalDivDec++;
        String question = null;
        float number[] = generateDecimal();
        if(gp.ui.difficultyLvl == 0)
        {
            question = (number[0]+1.0f) + " ÷ " + (number[1]+1.0f);
            float answer = (number[0]+1.0f) / (number[1]+1.0f);
            answerDec = (float) (Math.round(answer * 100.0) / 100.0);
        }
        else if(gp.ui.difficultyLvl == 1)
        {
            question = (number[0]+10.0f) + " ÷ " + (number[1]+1.0f) + " ÷ " + (number[2]+1.0f);
            float answer = (number[0]+10.0f) / (number[1]+1.0f) / (number[2]+1.0f);
            answerDec = (float) (Math.round(answer * 100.0) / 100.0);
        }
        else if(gp.ui.difficultyLvl == 2)
        {
            question = (number[0]+100.0f) + " ÷ " + (number[1]+1.0f) + " ÷ " + (number[2]+1.0f) + " ÷ " + (number[3]+1.0f);
            float answer = (number[0]+100.0f) / (number[1]+1.0f) / (number[2]+1.0f) / (number[3]+1.0f);
            answerDec = (float) (Math.round(answer * 100.0) / 100.0);
        }
        return question;
    }
    
    public String generateFracMul()
    {
        gp.player.totalMulFrac++;
        String question = null;
        int number[] = generateNumber();
        if(gp.ui.difficultyLvl == 0)
        {        
            question = (number[0]+1) + "      " + (number[1]+1)
                 + "\n— × —"
                 + "\n" + (number[2]+1) + "      " + (number[3]+1);
            int answerNume = (number[0]+1) * (number[1]+1);
            int answerDeno = (number[2]+1) * (number[3]+1);
            int gcd = calculateGCD(answerNume, answerDeno);
            answerNume /= gcd;
            answerDeno /= gcd;
            answerFracNume = answerNume;
            answerFracDeno = answerDeno;
        }
        else if(gp.ui.difficultyLvl == 1)
        {
            question = (number[0]+1) + "      " + (number[1]+1) + "      " + (number[2]+1)
                 + "\n— × — × —"
                 + "\n" + (number[3]+1) + "      " + (number[4]+1) + "      " + (number[5]+1);
            int answerNume = (number[0]+1) * (number[1]+1) * (number[2]+1);
            int answerDeno = (number[3]+1) * (number[4]+1) * (number[5]+1);
            int gcd = calculateGCD(answerNume, answerDeno);
            answerNume /= gcd;
            answerDeno /= gcd;
            answerFracNume = answerNume;
            answerFracDeno = answerDeno;
        }
        else if(gp.ui.difficultyLvl == 2)
        {
            question = (number[0]+1) + "      " + (number[1]+1) + "      " + (number[2]+1) + "      " + (number[3]+1)
                 + "\n— × — × — × —"
                 + "\n" + (number[4]+1) + "      " + (number[5]+1) + "      " + (number[6]+1) + "      " + (number[7]+1);
            int answerNume = (number[0]+1) * (number[1]+1) * (number[2]+1) * (number[3]+1);
            int answerDeno = (number[4]+1) * (number[5]+1) * (number[6]+1) * (number[7]+1);
            int gcd = calculateGCD(answerNume, answerDeno);
            answerNume /= gcd;
            answerDeno /= gcd;
            answerFracNume = answerNume;
            answerFracDeno = answerDeno;
        }
//        System.out.println(question);
        return question;
    }
    
    public String generateFracDiv()
    {
        gp.player.totalDivFrac++;
        String question = null;
        int number[] = generateNumber();
        if(gp.ui.difficultyLvl == 0)
        {
            question = (number[0]+1) + "      " + (number[1]+1)
                     + "\n— ÷ —"
                     + "\n" + (number[2]+1) + "      " + (number[3]+1);
            int answerNume = (number[0]+1) * (number[3]+1);
            int answerDeno = (number[2]+1) * (number[1]+1);
            int gcd = calculateGCD(answerNume, answerDeno);
            answerNume /= gcd;
            answerDeno /= gcd;
            answerFracNume = answerNume;
            answerFracDeno = answerDeno;
        }
        else if(gp.ui.difficultyLvl == 1)
        {
            question = (number[0]+1) + "      " + (number[1]+1) + "      " + (number[2]+1)
                 + "\n— ÷ — ÷ —"
                 + "\n" + (number[3]+1) + "      " + (number[4]+1) + "      " + (number[5]+1);
            int answerNume = (number[0]+1) * (number[4]+1) * (number[5]+1);
            int answerDeno = (number[3]+1) * (number[1]+1) * (number[2]+1);
            int gcd = calculateGCD(answerNume, answerDeno);
            answerNume /= gcd;
            answerDeno /= gcd;
            answerFracNume = answerNume;
            answerFracDeno = answerDeno;
        }
        else if(gp.ui.difficultyLvl == 2)
        {
            question = (number[0]+1) + "      " + (number[1]+1) + "      " + (number[2]+1) + "      " + (number[3]+1)
                 + "\n— ÷ — ÷ — ÷ —"
                 + "\n" + (number[4]+1) + "      " + (number[5]+1) + "      " + (number[6]+1) + "      " + (number[7]+1);
            int answerNume = (number[0]+1) * (number[5]+1) * (number[6]+1) * (number[7]+1);
            int answerDeno = (number[4]+1) * (number[1]+1) * (number[2]+1) * (number[3]+1);
            int gcd = calculateGCD(answerNume, answerDeno);
            answerNume /= gcd;
            answerDeno /= gcd;
            answerFracNume = answerNume;
            answerFracDeno = answerDeno;
        }
//        System.out.println(question);
        return question;
        
    }
    
    public int calculateGCD(int numerator, int denominator)
    {
        try
        {
            if(numerator % denominator == 0)
            {
                return denominator;
            }       
        }
        catch(ArithmeticException e)
        {
            e.printStackTrace();
        }    
        return calculateGCD(denominator, numerator % denominator);
    }
    
    
    
    public float[] generateDecimal()
    {
        String[] number = new String[10];
        String number1 = "";
        float[] number2 = new float[10];
        int x = 1;
        
        for (int i = 0; i < 10; i++)
        {
            number[i] = String.valueOf(uniform());
//            System.out.println(number[i]);
            number1 = number[i].substring(2, 4);
            BigDecimal unscaled = new BigDecimal(number1);
            BigDecimal scaled = unscaled.scaleByPowerOfTen(-x);
//            System.out.println(scaled);
//            number2[i] = Float.parseFloat(number1);
            number2[i] = scaled.floatValue();
        }
            
//        for (int i = 0; i < 10; i++)
//        {
//            System.out.print("  " + number[i]);
//        }
        
        return number2;
            
//        Random rand = new Random();
//        int m, b, i; // xn = (b*xn-1)mod m
//        m = 100;
//        b = rand.nextInt(100) + 1;
//        float x[] = new float[m];
//        float []u = new float[m];
//        x[0] = 79f;
//        for(i = 0; i < 8; i++)
//        {
//            x[i + 1] = ((b * x[i]) + 71) % m;
//            u[i] = x[i + 1] / 10;
////            System.out.println(u[i]);
//        }
//        return u;
    }
    
    public int[] generateNumber()
    {
        String[] number = new String[10];
        String number1 = "";
        int[] number2 = new int[10];
        
        for (int i = 0; i < 10; i++)
        {
            number[i] = String.valueOf(uniform());
            number1 = number[i].substring(2, 3);
            number2[i] = Integer.parseInt(number1);
        }
            
//        for (int i = 0; i < 10; i++)
//        {
//            System.out.print("  " + number2[i]);
//        }
        
        return number2;

//        Random rand = new Random();
//        int m, b, i; // xn = (b*xn-1)mod m
//        m = 10;
//        b = rand.nextInt(10) + 1;
//        int[] number = new int[10];
//        float x[] = new float[m];
//        float []u = new float[m];
//        x[0] = 3f;
//        for(i = 0; i < 8; i++)
//        {
//            x[i + 1] = (float) (((b * x[i]) + 5) % m);
//            u[i] = x[i + 1];
//            int a = (int) u[i];
//            number[i] = a;
////            System.out.println(number[i]);
//        }
//        return number;
    }
}
