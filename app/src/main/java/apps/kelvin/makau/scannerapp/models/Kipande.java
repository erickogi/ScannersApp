package apps.kelvin.makau.scannerapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by KaKaNg on 12/29/2017.
 */

public class Kipande implements Parcelable {
    String Serial_no;
    String MRZ_lines;
    String Country_code;
    String Dob;
    String Sex;
    String Full_names;
    String Doi;
    String Id_no;
    String Country;
    String Img_path;

    public Kipande() {
    }

    public String getSerial_no() {

        return Serial_no != null ? Serial_no : "Not Found";
    }

    public void setSerial_no(String serial_no) {
        Serial_no = serial_no;
    }

    public String getMRZ_lines() {
        return MRZ_lines;
    }

    public void setMRZ_lines(String MRZ_lines) {
        this.MRZ_lines = MRZ_lines;
    }

    public String getCountry_code() {
        return Country_code;
    }

    public void setCountry_code(String country_code) {
        Country_code = country_code;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getFull_names() {
        return Full_names;
    }

    public void setFull_names(String full_names) {
        Full_names = full_names;
    }

    public String getDoi() {
        return Doi;
    }

    public void setDoi(String doi) {
        Doi = doi;
    }

    public String getId_no() {
        return Id_no;
    }

    public void setId_no(String id_no) {
        Id_no = id_no;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getImg_path() {
        return Img_path;
    }

    public void setImg_path(String img_path) {
        Img_path = img_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Serial_no);
        dest.writeString(this.MRZ_lines);
        dest.writeString(this.Country_code);
        dest.writeString(this.Dob);
        dest.writeString(this.Sex);
        dest.writeString(this.Full_names);
        dest.writeString(this.Doi);
        dest.writeString(this.Id_no);
        dest.writeString(this.Country);
        dest.writeString(this.Img_path);
    }

    protected Kipande(Parcel in) {
        this.Serial_no = in.readString();
        this.MRZ_lines = in.readString();
        this.Country_code = in.readString();
        this.Dob = in.readString();
        this.Sex = in.readString();
        this.Full_names = in.readString();
        this.Doi = in.readString();
        this.Id_no = in.readString();
        this.Country = in.readString();
        this.Img_path = in.readString();
    }

    public static final Creator<Kipande> CREATOR = new Creator<Kipande>() {
        @Override
        public Kipande createFromParcel(Parcel source) {
            return new Kipande(source);
        }

        @Override
        public Kipande[] newArray(int size) {
            return new Kipande[size];
        }
    };
}
