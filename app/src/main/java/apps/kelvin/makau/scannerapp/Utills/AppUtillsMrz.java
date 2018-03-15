package apps.kelvin.makau.scannerapp.Utills;

import apps.kelvin.makau.scannerapp.models.Kipande;

/**
 * Created by Eric on 3/15/2018.
 */

public class AppUtillsMrz {
    public static Kipande perseData(String data) {

        Kipande mKipande = new Kipande();
        try {
            mKipande.setSerial_no(data.subSequence(5, 14).toString().replace("<", ""));
        }catch (Exception nm){

        }try {
            mKipande.setDob(data.subSequence(30,37).toString().replace("<",""));
        }catch (Exception nm){

        }try {
            mKipande.setSex(data.subSequence(38,39).toString().replace("<",""));
        }catch (Exception nm){

        }try {
            mKipande.setDoi(data.subSequence(39,45).toString().replace("<",""));
        }catch (Exception nm){

        }try {
            mKipande.setId_no(data.subSequence(49,57).toString().replace("<",""));
        }catch (Exception nm){

        }try {
            mKipande.setFull_names(data.subSequence(62,data.length()).toString().replace("<"," ").trim());
        }catch (Exception nm){

        }try {
            mKipande.setMRZ_lines(data.toString().trim());
        }catch (Exception nm){

        }


        if(mKipande!=null) {
            return mKipande;
        }else return new Kipande();



    }
}
