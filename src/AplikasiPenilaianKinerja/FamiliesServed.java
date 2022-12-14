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
public class FamiliesServed {
    String District;
    String Name;
    String Subdistrict;
    int SbDFamilies;
    int TotalFamilies;
    int DistFamilies;
    PKHDatabaseRef pkh = new PKHDatabaseRef();

    
    public FamiliesServed(String District, String Name, String Subdistrict) {
        this.District = District;
        this.Name = Name;
        this.Subdistrict = Subdistrict;
        int[] totals =pkh.getTotalFam(Name,Subdistrict,District);
        this.SbDFamilies = totals[1];
        this.TotalFamilies = totals[0];
        this.DistFamilies=totals[2];
    }
    
    public FamiliesServed(String District, String Name, String Subdistrict, int empty) {
        this.District = District;
        this.Name = Name;
        this.Subdistrict = Subdistrict;
        this.SbDFamilies = empty;
        this.TotalFamilies = empty;
        this.DistFamilies = empty;
    }

    public int getDistFamilies() {
        return DistFamilies;
    }
    
    public String getDistrict() {
        return District;
    }

    public String getName() {
        return Name;
    }

    public String getSubdistrict() {
        return Subdistrict;
    }

    public int getSbDFamilies() {
        return SbDFamilies;
    }

    public int getTotalFamilies() {
        return TotalFamilies;
    }

    public PKHDatabaseRef getPkh() {
        return pkh;
    }

    
    
    @Override
    public String toString() {
        return "FamiliesServed{" + "District=" + District + ", Name=" + Name + ", Subdistrict=" + Subdistrict + ", Families=" + SbDFamilies + ", TotalFamilies=" + TotalFamilies + '}';
    }
    
    
}
