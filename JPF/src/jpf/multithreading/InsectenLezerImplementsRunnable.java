/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpf.multithreading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;

public class InsectenLezerImplementsRunnable implements Runnable {

    private String bestandsNaam;
    // zal insecten1.csv of insecten2.csv zijn 
    private BigDecimal maximum = new BigDecimal(3);
    private PrintStream stream;
    private int aantalRegels;

    // staat voor System.out of System.err 
    public InsectenLezerImplementsRunnable(String bestand, PrintStream stream) {
        this.bestandsNaam = bestand;
        this.stream = stream;
    }
    
    
    @Override
    public void run() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File(bestandsNaam)));
            String regel = reader.readLine();
            while (regel != null) {
                String[] regelOnderdelen = regel.split(";");
                BigDecimal prijs = new BigDecimal(regelOnderdelen[1]);
                if (prijs.compareTo(maximum) <= 0) {
                    //stream.println(bestandsNaam + ':' + regel);
                    ++aantalRegels;
                }
                regel = reader.readLine();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    stream.println(ex);
                }
            }
        }
    }

    public int getAantalRegels() {
        return aantalRegels;
    }
}
