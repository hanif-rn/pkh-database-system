/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AplikasiPenilaianKinerja;

import java.util.Arrays;

/**
 *
 * @author 53792
 */
public class WorkerWeek {
    static int num=1;
    String district;
    String name;
    int[] weekScor;
    int total=0;
    int I;
    int II;
    int III;
    int IV;
    int V;
    public static int getNum() {
        return num;
    }

    public String getDist() {
        return district;
    }

    public String getName() {
        return name;
    }

    public int getI() {
        return weekScor[0];
    }
    public int getII() {
        return weekScor[1];
    }
    public int getIII() {
        return weekScor[2];
    }
    public int getIV() {
        return weekScor[3];
    }
    public int getV() {
        return weekScor[4];
    }
    
    public int getTot() {
        return total;
    }

    @Override
    public String toString() {
        return "WorkerWeek{" + "district=" + district + ", name=" + name + ", weekScor=" + Arrays.toString(weekScor) + ", total=" + total + '}';
    }
    
    public WorkerWeek(String district, String name, int[] weekScor) {
        this.district = district;
        this.name = name;
        this.weekScor = weekScor;
        for (int i = 0; i < weekScor.length; i++) {
            total+=weekScor[i];
        }
    }
    
}
