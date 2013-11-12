/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Test;

import java.sql.SQLException;

public class NewClass {
    public static void main (String[] args) throws SQLException
    {
        DButitilies.connect conn;
        conn =new DButitilies.connect();
        conn.ketnoi();
        conn.ThucThiSql("SELECT \"UserName\", \"Password\" FROM \"Account\"");
    }
}
