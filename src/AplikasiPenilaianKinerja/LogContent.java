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

/*
 *  This class is used to represent each change log content
 *  as objects. These objects are then used to display in a
 *  JavaFX Table
 */
public class LogContent {
    String edit, user, date;

    public LogContent(String edit, String user, String date) {
        this.edit = edit;
        this.user = user;
        this.date = date;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "LogContent{" + "edit=" + edit + ", user=" + user + ", date=" + date + '}';
    }
}
