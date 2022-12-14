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
public class FamSort {
    PKHDatabaseRef pkh = new PKHDatabaseRef();
    FamiliesServed[] sorted = new FamiliesServed[1000];
    int index = 0;
    
    //This method describes the sorting algorithm that is used to sort the data values from highest to lowest
    public FamiliesServed[] sort(FamiliesServed[] fs){
        for (int i = 0; i < fs.length-1; i++) {
            if(fs[i]!=null&& fs[i+1]!=null){
                if(!fs[i].getSubdistrict().equals(fs[i+1].getSubdistrict())||!fs[i].getName().equals(fs[i+1].getName())){
                    sorted[index]=fs[i];
                    index++;
                }
            } else if (fs[i+1]==null && fs[i]!=null){
                sorted[index]=fs[i];
                index++;
            }
        }
        if(fs[fs.length-1]!=null){
            sorted[index]=fs[fs.length-1];
        }
        return sorted;
    }
    
}
