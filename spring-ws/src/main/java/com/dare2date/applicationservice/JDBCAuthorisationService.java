/**
 * Copyright (c) 2013 HAN University of Applied Sciences
 * Arjan Oortgiese
 * Joëll Portier
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package com.dare2date.applicationservice;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCAuthorisationService implements IAuthorisationService {

    private String url;
    private String dbusername;
    private String dbpassword;

    public String getDbusername() {
        return dbusername;
    }

    public void setDbusername(String dbusername) {
        this.dbusername = dbusername;
    }

    public String getDbpassword() {
        return dbpassword;
    }

    public void setDbpassword(String dbpassword) {
        this.dbpassword = dbpassword;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getUsername(String dare2dateUserName, String platformname) {
        return getFromDatebase(dare2dateUserName,platformname,"username");
    }

    public String getAccessToken(String dare2dateUserName, String platformname) {
        return getFromDatebase(dare2dateUserName,platformname,"accesstoken");
    }

    private String getFromDatebase(String dare2dateUserName, String platformname, String field) {
        try {
            // Load the MySQL driver.
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            return "";
        }

        Connection con;
        try{
            con =  DriverManager.getConnection(getUrl(),getDbusername(),getDbpassword());
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select "+field+"\n" +
                    "from userservicesauth\n" +
                    "where dare2dateUsername = '"+dare2dateUserName+"' AND platformname = '"+platformname+"'");
            if (rs.next() ) {
                String result = rs.getString(1);
                con.close();
                return result;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
