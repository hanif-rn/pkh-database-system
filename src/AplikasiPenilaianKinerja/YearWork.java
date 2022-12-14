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
public class YearWork{
    WorkerWeek[][] wwArr = new WorkerWeek[12][1000];
    PKHDatabaseRef pkh = new PKHDatabaseRef();
    static int count=0;
    
    public YearWork(int Year) {
        
        wwArr[0]=pkh.getMnthData("January/Januari", Year);
        wwArr[1]=pkh.getMnthData("February/Februari", Year);
        wwArr[2]=pkh.getMnthData("March/Maret", Year);
        wwArr[3]=pkh.getMnthData("April", Year);
        wwArr[4]=pkh.getMnthData("May/Mei", Year);
        wwArr[5]=pkh.getMnthData("June/Juni", Year);
        wwArr[6]=pkh.getMnthData("July/Juli", Year);
        wwArr[7]=pkh.getMnthData("August/Agustus", Year);
        wwArr[8]=pkh.getMnthData("September", Year);
        wwArr[9]=pkh.getMnthData("October/Oktober", Year);
        wwArr[10]=pkh.getMnthData("November/November", Year);
        wwArr[11]=pkh.getMnthData("December/Desember", Year);
    }
    
}

class test{
    public static void main(String[] args) {
        YearWork yw = new YearWork(2020);
        System.out.println(yw.wwArr[0][0]);
        System.out.println(yw.wwArr[0][1]);
        System.out.println(yw.wwArr[0][2]);
        System.out.println(yw.wwArr[1][0]);
        System.out.println(yw.wwArr[11][20]);
        //System.out.println(yw.wwArr[12][2]);
        System.out.println(yw);
    }
}
/*public int getI1(){
        return wwArr[count][0].getI();
    }
    public int getI2(){
        return wwArr[count][1].getI();
    }
    public int getI3(){
        return wwArr[count][2].getI();
    }
    public int getI4(){
        return wwArr[count][3].getI();
    }
    public int getI5(){
        return wwArr[count][4].getI();
    }
    public int getI6(){
        return wwArr[count][5].getI();
    }
    public int getI7(){
        return wwArr[count][6].getI();
    }
    public int getI8(){
        return wwArr[count][7].getI();
    }
    public int getI9(){
        return wwArr[count][8].getI();
    }
    public int getI10(){
        return wwArr[count][9].getI();
    }
    public int getI11(){
        return wwArr[count][11].getI();
    }
    public int getI12(){
        return wwArr[count][12].getI();
    }
    public int getII1(){
        return wwArr[count][0].getII();
    }
    public int getII2(){
        return wwArr[count][1].getII();
    }
    public int getII3(){
        return wwArr[count][2].getII();
    }
    public int getII4(){
        return wwArr[count][3].getII();
    }
    public int getII5(){
        return wwArr[count][4].getII();
    }
    public int getII6(){
        return wwArr[count][5].getII();
    }
    public int getII7(){
        return wwArr[count][6].getII();
    }
    public int getII8(){
        return wwArr[count][7].getII();
    }
    public int getII9(){
        return wwArr[count][8].getII();
    }
    public int getII10(){
        return wwArr[count][9].getII();
    }
    public int getII11(){
        return wwArr[count][10].getIII();
    }
    public int getII12(){
        return wwArr[count][11].getIII();
    }
    public int getIII1(){
        return wwArr[count][0].getIII();
    }
    public int getIII2(){
        return wwArr[count][1].getIII();
    }
    public int getIII3(){
        return wwArr[count][2].getIII();
    }
    public int getIII4(){
        return wwArr[count][3].getIII();
    }
    public int getIII5(){
        return wwArr[count][4].getIII();
    }
    public int getIII6(){
        return wwArr[count][5].getIII();
    }
    public int getIII7(){
        return wwArr[count][6].getIII();
    }
    public int getIII8(){
        return wwArr[count][7].getIII();
    }
    public int getIII9(){
        return wwArr[count][8].getIII();
    }
    public int getIII10(){
        return wwArr[count][9].getIII();
    }
    public int getIII11(){
        return wwArr[count][10].getIII();
    }
    public int getIII12(){
        return wwArr[count][11].getIII();
    }
    public int getIV1(){
        return wwArr[count][0].getIV();
    }
    public int getIV2(){
        return wwArr[count][1].getIV();
    }
    public int getIV3(){
        return wwArr[count][2].getIV();
    }
    public int getIV4(){
        return wwArr[count][3].getIV();
    }
    public int getIV5(){
        return wwArr[count][4].getIV();
    }
    public int getIV6(){
        return wwArr[count][5].getIV();
    }
    public int getIV7(){
        return wwArr[count][6].getIV();
    }
    public int getIV8(){
        return wwArr[count][7].getIV();
    }
    public int getIV9(){
        return wwArr[count][8].getIV();
    }
    public int getIV10(){
        return wwArr[count][9].getIV();
    }
    public int getIV11(){
        return wwArr[count][10].getIV();
    }
    public int getIV12(){
        return wwArr[count][11].getIV();
    }
    public int getV1(){
        return wwArr[count][0].getV();
    }
    public int getV2(){
        return wwArr[count][1].getV();
    }
    public int getV3(){
        return wwArr[count][2].getV();
    }
    public int getV4(){
        return wwArr[count][3].getV();
    }
    public int getV5(){
        return wwArr[count][4].getV();
    }
    public int getV6(){
        return wwArr[count][5].getV();
    }
    public int getV7(){
        return wwArr[count][6].getV();
    }
    public int getV8(){
        return wwArr[count][7].getV();
    }
    public int getV9(){
        return wwArr[count][8].getV();
    }
    public int getV10(){
        return wwArr[count][9].getV();
    }
    public int getV11(){
        return wwArr[count][10].getV();
    }
    public int getV12(){
        count++;
        return wwArr[count][11].getV();
    }*/