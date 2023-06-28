/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathrpg;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 *
 * @author Chong Bin Yong
 */
public class InputHandler {
    
    GamePanel gp;
    
    float inputDec;
    int inputFrac[] = new int[2];
    
    public int hitEnemy = 2;
    
    int questionCounter = 0; // time display for input window
    
    public InputHandler(GamePanel gp)
    {
        this.gp = gp;
    }
    
    public void update()
    {
        closeWindow();
    }
    
    public void closeWindow()
    {
        questionCounter++;
        if(questionCounter > gp.ui.givenTime)
        {
            questionCounter = 0;
            JOptionPane.getRootFrame().dispose();
        }
        else if(gp.ui.combatScreenState == 0)
        {
            questionCounter = 0;
        }
    }
    
    public float getDecimal()
    {
        // create dialog box to accept input
        // accept float and int only
        // close after 20 seconds
//        int i = 1;
        String[] options = {"OK"};
        JPanel panel = new JPanel();
        JLabel lbl = new JLabel("Answer: ");
        JTextField txt = new JTextField(10);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(lbl);
        panel.add(txt);
//        do{
            int selectedOption = JOptionPane.showOptionDialog(null, panel, "Enter your answer:", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
            if(selectedOption == 0)
            {
                try{
                    inputDec = Float.parseFloat(txt.getText());
//                    i = 0;
                }catch(NumberFormatException e){
                    inputDec = 0;
//                    i = 1;
                }
    //            System.out.println(inputDec);
                // ...
            }
//        }while(i == 0);
        
        return inputDec; 
    }
    
    public int[] getFraction()
    {
//        int i = 1;
        String[] options = {"OK"};
        JPanel panel = new JPanel();
        JLabel lbl = new JLabel("Numerator: ");
        JLabel lbl1 = new JLabel("Denominator: ");
        JTextField txt = new JTextField(10);
        JTextField txt1 = new JTextField(10);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(lbl);
        panel.add(txt);
        panel.add(lbl1);
        panel.add(txt1);
//        do{
            int selectedOption = JOptionPane.showOptionDialog(null, panel, "Enter your answer:", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if(selectedOption == 0)
            {
                try{
                    inputFrac[0] = Integer.parseInt(txt.getText());
                    inputFrac[1] = Integer.parseInt(txt1.getText());
//                    i = 0;
                }catch(NumberFormatException e){
                    inputFrac[0] = 0;
                    inputFrac[1] = 0;
//                    i = 1;
                }
        //            System.out.println(inputFrac[0]);
        //            System.out.println(inputFrac[1]);
                    // ...
            }
//        }while(i == 1);
        
        return inputFrac;
    }
    
    public void compareAnswer()
    {
        if(gp.ui.inputCounter == 0) // Decimal
        {
            // show input
            // compare input and answer
            // display result 
            // 1: Answer Correct - Damage Enemy & Damage By Enemy
            // 2: Answer Wrong - Damage By Enemy
            // 3: Timeout - Damage By Enemy (Input dialog box disappear)
            gp.inputH.getDecimal();
            float a = gp.rng.answerDec;
            double b = gp.inputH.inputDec;
            if(a == b)
            {
                gp.player.correctMulDec++;
                hitEnemy = 1;
//                System.out.println("enemy hp decrease");
//                System.out.println("player hp decrease");
//                gp.ui.combatScreenState = 0;
//                gp.ui.questionCounter=0;
            }
            else
            {
                hitEnemy = 0;
//                System.out.println("player hp decrease");
//                gp.ui.combatScreenState = 0;
//                gp.ui.questionCounter=0;
            }
        }
        else if(gp.ui.inputCounter == 1) // Decimal
        {
            // show input
            // compare input and answer
            // display result 
            // 1: Answer Correct - Damage Enemy & Damage By Enemy
            // 2: Answer Wrong - Damage By Enemy
            // 3: Timeout - Damage By Enemy (Input dialog box disappear)
            gp.inputH.getDecimal();
            float a = gp.rng.answerDec;
            double b = gp.inputH.inputDec;
            if(a == b)
            {
                gp.player.correctDivDec++;
                hitEnemy = 1;
//                System.out.println("enemy hp decrease");
//                System.out.println("player hp decrease");
//                gp.ui.combatScreenState = 0;
//                gp.ui.questionCounter=0;
            }
            else
            {
                hitEnemy = 0;
//                System.out.println("player hp decrease");
//                gp.ui.combatScreenState = 0;
//                gp.ui.questionCounter=0;
            }
        }
        else if(gp.ui.inputCounter == 2) // Fraction
        {
            gp.inputH.getFraction();
            int a = gp.rng.answerFracNume;
            int b = gp.rng.answerFracDeno;
            int c[] = new int[2];
            c[0] = gp.inputH.inputFrac[0];
            c[1] = gp.inputH.inputFrac[1];
            if(a == c[0] && b == c[1])
            {
                gp.player.correctMulFrac++;
                hitEnemy = 1;
//                System.out.println("enemy hp decrease");
//                System.out.println("player hp decrease");
//                gp.ui.combatScreenState = 0;
            }
            else
            {
                hitEnemy = 0;
//                System.out.println("player hp decrease");
//                gp.ui.combatScreenState = 0;
            }
        }
        else if(gp.ui.inputCounter == 3) // Fraction
        {
            gp.inputH.getFraction();
            int a = gp.rng.answerFracNume;
            int b = gp.rng.answerFracDeno;
            int c[] = new int[2];
            c[0] = gp.inputH.inputFrac[0];
            c[1] = gp.inputH.inputFrac[1];
            if(a == c[0] && b == c[1])
            {
                gp.player.correctDivFrac++;
                hitEnemy = 1;
//                System.out.println("enemy hp decrease");
//                System.out.println("player hp decrease");
//                gp.ui.combatScreenState = 0;
            }
            else
            {
                hitEnemy = 0;
//                System.out.println("player hp decrease");
//                gp.ui.combatScreenState = 0;
            }
        }
    }
}
