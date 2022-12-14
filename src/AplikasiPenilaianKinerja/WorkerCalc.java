/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AplikasiPenilaianKinerja;

/**
 *
 * @author 53792
 */
public class WorkerCalc {
    String name;
    int totscor;

    public void addToScore(int x){
        totscor += x;
    }

    public String getName() {
        return name;
    }

    public int getTotscor() {
        return totscor;
    }
    
    public WorkerCalc(String name, int totscor) {
        this.name = name;
        this.totscor = totscor;
    }
    
}
