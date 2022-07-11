/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

/**
 *
 * @author mosta
 */
public class saveData {
    private static int id_user;
    private  static String userName;
    public static void SetSaveData(int id_user , String userName){
        saveData.id_user = id_user;
        saveData.userName = userName;
    }
    public static int getIdUser(){
        return id_user;
    }
    public static String getUserName(){
        return userName;
    }
}
