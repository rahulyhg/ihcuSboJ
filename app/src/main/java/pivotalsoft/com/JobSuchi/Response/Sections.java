package pivotalsoft.com.JobSuchi.Response;

/**
 * Created by USER on 2/24/2018.
 */

public class Sections {

    private String sectionName;
    private int totalquestions;
    private int totalsocre;

    public Sections(String sectionName, int totalquestions, int totalsocre) {
        this.sectionName = sectionName;
        this.totalquestions = totalquestions;
        this.totalsocre = totalsocre;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public int getTotalquestions() {
        return totalquestions;
    }

    public void setTotalquestions(int totalquestions) {
        this.totalquestions = totalquestions;
    }

    public int getTotalsocre() {
        return totalsocre;
    }

    public void setTotalsocre(int totalsocre) {
        this.totalsocre = totalsocre;
    }
}
