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
public class iduser {

    private static String id_user;

    public static void setiduser(String id_user) {

        iduser.id_user = id_user;
    }

    public static String getIdUser() {
        return id_user;
    }
}
