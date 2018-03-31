package shiksha.teambitcoders.com.shiksha;

/**
 * Created by Subham Lenka on 31-03-2018.
 */

public class Noticeboardclass {
    public String noticeUniqueId;
    private String noticeDate;
    private String noticeName;
    private String noticeDetails;

    public Noticeboardclass() {
        // This is default constructor.
    }
    public Noticeboardclass(String noticeUniqueId,String noticeDate,String noticeName) {
        this.noticeUniqueId=noticeUniqueId;
        this.noticeDate=noticeDate;
        this.noticeName=noticeName;

        // This is default constructor.
    }


    public String getNoticeUniqueId() {

        return noticeUniqueId;
    }

    public void setNoticeUniqueId(String noticeUniqueId) {

        this.noticeUniqueId = noticeUniqueId;
    }
    public String getNoticeDate() {

        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {

        this.noticeDate = noticeDate;
    }

    public String getNoticeName() {

        return noticeName;
    }

    public void setNoticeName(String noticeName) {

        this.noticeName = noticeName;
    }
    public String getNoticeDetails() {

        return noticeDetails;
    }

    public void setNoticeDetails(String noticeDate) {

        this.noticeDetails = noticeDetails;
    }
}
