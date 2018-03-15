package apps.kelvin.makau.scannerapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by KaKaNg on 12/29/2017.
 */

public class Kipande implements Parcelable {

    private int KEY_ID;
    private String Serial_no;
    private String MRZ_lines;
    private String Country_code;
    private String Dob;
    private String Sex;
    private String Full_names;
    private String Date_of_issue;
    private String Id_no;
    private String Country;
    private String Img_path;

    private String TimeStamp;



    private String Vehicle_plate;
    private String Office;
    private String Visit_type;
    private String check_out_time;
    private String comment;


    public int getKEY_ID() {
        return KEY_ID;
    }

    public void setKEY_ID(int KEY_ID) {
        this.KEY_ID = KEY_ID;
    }

    public String getVehicle_plate() {
        return Vehicle_plate != null ? Vehicle_plate : "Nill";
    }

    public void setVehicle_plate(String vehicle_plate) {
        Vehicle_plate = vehicle_plate;
    }

    public String getOffice() {
        return Office != null ? Office : "Nill";
    }

    public void setOffice(String office) {
        Office = office;
    }

    public String getVisit_type() {
        return Visit_type != null ? Visit_type : "Nill";
    }

    public void setVisit_type(String visit_type) {
        Visit_type = visit_type;
    }

    public String getCheck_out_time() {
        return check_out_time != null ? check_out_time : "Nill";
    }

    public void setCheck_out_time(String check_out_time) {
        this.check_out_time = check_out_time;
    }

    public String getComment() {
        return comment != null ? comment : "Nill";
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate_of_issue() {
        return Date_of_issue != null ? Date_of_issue : "Nill";
    }

    public void setDate_of_issue(String date_of_issue) {
        Date_of_issue = date_of_issue;
    }

    public String getTimeStamp() {
        return TimeStamp != null ? TimeStamp : "Nill";
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }

    public Kipande() {
    }

    public String getSerial_no() {

        return Serial_no != null ? Serial_no : "Nill";
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
        return Country_code != null ? Country_code : "KYA";
    }

    public void setCountry_code(String country_code) {
        Country_code = country_code;
    }

    public String getDob() {
        return Dob != null ? Dob : "000000";
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getSex() {
        return Sex != null ? Sex : "N";
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getFull_names() {
        return Full_names != null ? Full_names : "Nill";
    }

    public void setFull_names(String full_names) {
        Full_names = full_names;
    }

    public String getDoi() {
        return Date_of_issue != null ? Date_of_issue : "Nill";
    }

    public void setDoi(String doi) {
        Date_of_issue = doi;
    }

    public String getId_no() {
        return Id_no != null ? Id_no : "Not Found";
    }

    public void setId_no(String id_no) {
        Id_no = id_no;
    }

    public String getCountry() {
        return Country != null ? Country : "Kenya";
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getImg_path() {
        return Img_path != null ? Img_path : "";
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
        dest.writeString(this.Date_of_issue);
        dest.writeString(this.Id_no);
        dest.writeString(this.Country);
        dest.writeString(this.Img_path);
        dest.writeInt(this.KEY_ID);






        dest.writeString(this.TimeStamp);
        dest.writeString(this.Vehicle_plate);
        dest.writeString(this.Office);
        dest.writeString(this.Visit_type);
        dest.writeString(this.check_out_time);
        dest.writeString(this.comment);

    }

    protected Kipande(Parcel in) {
        this.Serial_no = in.readString();
        this.MRZ_lines = in.readString();
        this.Country_code = in.readString();
        this.Dob = in.readString();
        this.Sex = in.readString();
        this.Full_names = in.readString();
        this.Date_of_issue = in.readString();
        this.Id_no = in.readString();
        this.Country = in.readString();
        this.Img_path = in.readString();
        this.KEY_ID = in.readInt();


        this.TimeStamp = in.readString();
        this.Vehicle_plate = in.readString();
        this.Office = in.readString();
        this.Visit_type = in.readString();
        this.check_out_time = in.readString();
        this.comment = in.readString();



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
