package pivotalsoft.com.JobSuchi.Response;

import java.util.List;

/**
 * Created by USER on 2/26/2018.
 */

public class StudentDetailsItem {


    /**
     * onedata : [{"testdetailsid":"1","studenttestid":"1","section":"Introduction","totalscore":"5.00","obtainedscore":"1.00","percentage":"20.0000","remarks":"Bad Performace"},{"testdetailsid":"2","studenttestid":"1","section":"UI Design","totalscore":"5.00","obtainedscore":"3.00","percentage":"60.0000","remarks":"Good"}]
     * message : success
     */

    private String message;
    private List<OnedataBean> onedata;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<OnedataBean> getOnedata() {
        return onedata;
    }

    public void setOnedata(List<OnedataBean> onedata) {
        this.onedata = onedata;
    }

    public static class OnedataBean {
        /**
         * testdetailsid : 1
         * studenttestid : 1
         * section : Introduction
         * totalscore : 5.00
         * obtainedscore : 1.00
         * percentage : 20.0000
         * remarks : Bad Performace
         */

        private String testdetailsid;
        private String studenttestid;
        private String section;
        private String totalscore;
        private String obtainedscore;
        private String percentage;
        private String remarks;

        public String getTestdetailsid() {
            return testdetailsid;
        }

        public void setTestdetailsid(String testdetailsid) {
            this.testdetailsid = testdetailsid;
        }

        public String getStudenttestid() {
            return studenttestid;
        }

        public void setStudenttestid(String studenttestid) {
            this.studenttestid = studenttestid;
        }

        public String getSection() {
            return section;
        }

        public void setSection(String section) {
            this.section = section;
        }

        public String getTotalscore() {
            return totalscore;
        }

        public void setTotalscore(String totalscore) {
            this.totalscore = totalscore;
        }

        public String getObtainedscore() {
            return obtainedscore;
        }

        public void setObtainedscore(String obtainedscore) {
            this.obtainedscore = obtainedscore;
        }

        public String getPercentage() {
            return percentage;
        }

        public void setPercentage(String percentage) {
            this.percentage = percentage;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }
}
