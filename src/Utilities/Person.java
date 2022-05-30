/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import javax.swing.JTable;

/**
 *
 * @author mosta
 */
public abstract class Person{
    String id_person;
    String name;
    String phone;
    String address;
    String idInternational;
    double firstBalance;
    public String tableName;

    public Person(String tableName) {
        this.tableName = tableName;
    }
    
    
    public void setData(String id_person , String name , String phone , String address , String idInternational , double firstBalance){
        this.address = address ;
        this.idInternational = idInternational;
        this.id_person = id_person;
        this.name = name;
        this.phone = phone;
        this.firstBalance = firstBalance;
    }
    
    public boolean Save(){
        String sql = "INSERT INTO "+tableName+" VALUES ("+id_person+",'"+name+"','"+phone+"','"+address+"','"+idInternational+"',"+firstBalance+")";
        return ConnectDB.ExucuteAnyQuery(sql);
    }
    public boolean Update(String id){
      String sql = "update client set name_"+tableName+"='"+name+"' , phone='"+phone+"' , address='"+address+"' , idNational = '"+idInternational+"' , firstBalance='"+firstBalance+"' where id_"+tableName+" ="+id;
      return ConnectDB.ExucuteAnyQuery(sql);
    }
    public boolean Delete(String id){
        String sql = "DELETE FROM "+tableName+" WHERE id_"+tableName+"="+id;
        return ConnectDB.ExucuteAnyQuery(sql);
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setId_person(String id_person) {
        this.id_person = id_person;
    }
    
    
    public void filTable(JTable table) {
        String sql = "select firstBalance , idNational , address , phone , name_"+tableName+" , id_"+tableName+" from "+tableName ;
        String[] coulmnName = {"رصيد سابق", "رقم قومي", "عنوان", "هاتف", "الاسم", "مسلسل"};
        ConnectDB.fillAndCenterTable(sql, table, coulmnName);
    }

    
    public String lastid() {
        return ConnectDB.LastId(tableName, "id_"+tableName);
    }
    
    public String getIdByName(String name){
        String id = ConnectDB.getIdFrmName(tableName, name);
        return id;
    }
}
