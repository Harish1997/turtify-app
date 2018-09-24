package rexzen.turtify;

/**
 * Created by harishananth on 28/02/17.
 */

public class datanest {
    public String Title;
    public String day;
    public String hour;
    public String minute;
    public String second;
    public String date;
    public String anestid;

    datanest(String anestid,String Title,String day,String hour,String minute,String second,String date)
    {
        this.anestid=anestid;
        this.Title=Title;
        this.day=day;
        this.hour=hour;
        this.minute=minute;
        this.second=second;
        this.date=date;
    }
}
