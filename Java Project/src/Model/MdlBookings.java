/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

public class MdlBookings {
    private int ID;
    private String Username;
    private String Email;
    private String Movie;
    private String Date;
    private String Time;

    public MdlBookings(int ID, String Name, String Email, String Movie, String Date, String Time) {
        this.ID = ID;
        this.Username = Name;
        this.Email = Email;
        this.Movie = Movie;
        this.Date = Date;
        this.Time = Time;
    }
    public MdlBookings(){
        
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Name) {
        this.Username = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getMovie() {
        return Movie;
    }

    public void setMovie(String Movie) {
        this.Movie = Movie;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }
    
}
