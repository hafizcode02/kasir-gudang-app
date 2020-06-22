/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

/**
 *
 * @author Hafiz Caniago
 */
public class userlogin {
private static String username;
public static void setUserLogin(String username){
    userlogin.username = username;
}
public static String getUserLogin(){
    return username;
}
}
