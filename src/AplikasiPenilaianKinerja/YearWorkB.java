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
public class YearWorkB {
    WorkerWeek[][] wwArr = new WorkerWeek[12][1000];
    PKHDatabaseRef pkh = new PKHDatabaseRef();
    int count=0;
    String Dist;
    String Name;
    int I1;
    int I2;
    int I3;
    int I4;
    int I5;
    int I6;
    int I7;
    int I8;
    int I9;
    int I10;
    int I11;
    int I12;
    int II1;
    int II2;
    int II3;
    int II4;
    int II5;
    int II6;
    int II7;
    int II8;
    int II9;
    int II10;
    int II11;
    int II12;
    int III1;
int III2;
int III3;
int III4;
int III5;
int III6;
int III7;
int III8;
int III9;
int III10;
int III11;
int III12;
int IV1;
int IV2;
int IV3;
int IV4;
int IV5;
int IV6;
int IV7;
int IV8;
int IV9;
int IV10;
int IV11;
int IV12;
int V1;
int V2;
int V3;
int V4;
int V5;
int V6;
int V7;
int V8;
int V9;
int V10;
int V11;
int V12;
int Tot1;
int Tot2;
int Tot3;
int Tot4;
int Tot5;
int Tot6;
int Tot7;
int Tot8;
int Tot9;
int Tot10;
int Tot11;
int Tot12;
int Tot;

    public String getDist() {
        return wwArr[0][count].getDist();
    }

    public String getName() {
        return wwArr[0][count].getName();
    }
    
    public YearWorkB(WorkerWeek[][] wwArr) {
        this.wwArr=wwArr;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public int getI1() {
        return wwArr[0][count].getI();
    }
public int getI2() {
        return wwArr[1][count].getI();
    }
public int getI3() {
        return wwArr[2][count].getI();
    }
public int getI4() {
        return wwArr[3][count].getI();
    }
public int getI5() {
        return wwArr[4][count].getI();
    }
public int getI6() {
        return wwArr[5][count].getI();
    }
public int getI7() {
        return wwArr[6][count].getI();
    }
public int getI8() {
        return wwArr[7][count].getI();
    }
public int getI9() {
        return wwArr[8][count].getI();
    }
public int getI10() {
        return wwArr[9][count].getI();
    }
public int getI11() {
        return wwArr[10][count].getI();
    }
public int getI12() {
        return wwArr[11][count].getI();
    }
public int getII1() {
        return wwArr[0][count].getII();
    }
public int getII2() {
        return wwArr[1][count].getII();
    }
public int getII3() {
        return wwArr[2][count].getII();
    }
public int getII4() {
        return wwArr[3][count].getII();
    }
public int getII5() {
        return wwArr[4][count].getII();
    }
public int getII6() {
        return wwArr[5][count].getII();
    }
public int getII7() {
        return wwArr[6][count].getII();
    }
public int getII8() {
        return wwArr[7][count].getII();
    }
public int getII9() {
        return wwArr[8][count].getII();
    }
public int getII10() {
        return wwArr[9][count].getII();
    }
public int getII11() {
        return wwArr[10][count].getII();
    }
public int getII12() {
        return wwArr[11][count].getII();
    }
public int getIII1() {
        return wwArr[0][count].getIII();
    }
public int getIII2() {
        return wwArr[1][count].getIII();
    }
public int getIII3() {
        return wwArr[2][count].getIII();
    }
public int getIII4() {
        return wwArr[3][count].getIII();
    }
public int getIII5() {
        return wwArr[4][count].getIII();
    }
public int getIII6() {
        return wwArr[5][count].getIII();
    }
public int getIII7() {
        return wwArr[6][count].getIII();
    }
public int getIII8() {
        return wwArr[7][count].getIII();
    }
public int getIII9() {
        return wwArr[8][count].getIII();
    }
public int getIII10() {
        return wwArr[9][count].getIII();
    }
public int getIII11() {
        return wwArr[10][count].getIII();
    }
public int getIII12() {
        return wwArr[11][count].getIII();
    }
public int getIV1() {
        return wwArr[0][count].getIV();
    }
public int getIV2() {
        return wwArr[1][count].getIV();
    }
public int getIV3() {
        return wwArr[2][count].getIV();
    }
public int getIV4() {
        return wwArr[3][count].getIV();
    }
public int getIV5() {
        return wwArr[4][count].getIV();
    }
public int getIV6() {
        return wwArr[5][count].getIV();
    }
public int getIV7() {
        return wwArr[6][count].getIV();
    }
public int getIV8() {
        return wwArr[7][count].getIV();
    }
public int getIV9() {
        return wwArr[8][count].getIV();
    }
public int getIV10() {
        return wwArr[9][count].getIV();
    }
public int getIV11() {
        return wwArr[10][count].getIV();
    }
public int getIV12() {
        return wwArr[11][count].getIV();
    }
public int getV1() {
        return wwArr[0][count].getV();
    }
public int getV2() {
        return wwArr[1][count].getV();
    }
public int getV3() {
        return wwArr[2][count].getV();
    }
public int getV4() {
        return wwArr[3][count].getV();
    }
public int getV5() {
        return wwArr[4][count].getV();
    }
public int getV6() {
        return wwArr[5][count].getV();
    }
public int getV7() {
        return wwArr[6][count].getV();
    }
public int getV8() {
        return wwArr[7][count].getV();
    }
public int getV9() {
        return wwArr[8][count].getV();
    }
public int getV10() {
        return wwArr[9][count].getV();
    }
public int getV11() {
        return wwArr[10][count].getV();
    }
public int getV12() {
        return wwArr[11][count].getV();
    }
    
    public int getTot1() {
        return wwArr[0][count].getTot();
    }
public int getTot2() {
        return wwArr[1][count].getTot();
    }
public int getTot3() {
        return wwArr[2][count].getTot();
    }
public int getTot4() {
        return wwArr[3][count].getTot();
    }
public int getTot5() {
        return wwArr[4][count].getTot();
    }
public int getTot6() {
        return wwArr[5][count].getTot();
    }
public int getTot7() {
        return wwArr[6][count].getTot();
    }
public int getTot8() {
        return wwArr[7][count].getTot();
    }
public int getTot9() {
        return wwArr[8][count].getTot();
    }

    public int getTot10() {
        return wwArr[9][count].getTot();
    }

    public int getTot11() {
        return wwArr[10][count].getTot();
    }

    public int getTot12() {
        return wwArr[11][count].getTot();
    }
    
    public int getTot() {
        Tot = (getTot1()+getTot2()+getTot3()+getTot4()+getTot5()+getTot6()+getTot7()+
                getTot8()+getTot9()+getTot10()+getTot11()+getTot12());
        return Tot;
    }

    @Override
    public String toString() {
        return "YearWorkB{" + "count=" + count + ", Dist=" + getDist() + ", Name=" + getName() + ", Tot=" + getTot() + '}';
    }
    
    
}
