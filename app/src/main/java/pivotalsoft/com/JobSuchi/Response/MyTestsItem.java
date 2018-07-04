package pivotalsoft.com.JobSuchi.Response;

import java.util.List;

/**
 * Created by USER on 2/23/2018.
 */

public class MyTestsItem {


    /**
     * onedata : [{"studenttestid":"1","testid":"1","userid":"7","totalscore":"10.00","obtainedscore":"4.00","percentage":"40.0000","result":"Pass","remarks":"0","testtime":"2018-02-14 10:00:00","testname":"Android Sample Test","rank":"5"},{"studenttestid":"2","testid":"1","userid":"7","totalscore":"10.00","obtainedscore":"5.00","percentage":"50.0000","result":"Pass","remarks":"0","testtime":"2018-02-18 10:22:33","testname":"Android Sample Test","rank":"2"}]
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
         * studenttestid : 1
         * testid : 1
         * userid : 7
         * totalscore : 10.00
         * obtainedscore : 4.00
         * percentage : 40.0000
         * result : Pass
         * remarks : 0
         * testtime : 2018-02-14 10:00:00
         * testname : Android Sample Test
         * rank : 5
         */

        private String studenttestid;
        private String testid;
        private String userid;
        private String totalscore;
        private String obtainedscore;
        private String percentage;
        private String result;
        private String remarks;
        private String testtime;
        private String testname;
        private String rank;

        public String getStudenttestid() {
            return studenttestid;
        }

        public void setStudenttestid(String studenttestid) {
            this.studenttestid = studenttestid;
        }

        public String getTestid() {
            return testid;
        }

        public void setTestid(String testid) {
            this.testid = testid;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
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

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getTesttime() {
            return testtime;
        }

        public void setTesttime(String testtime) {
            this.testtime = testtime;
        }

        public String getTestname() {
            return testname;
        }

        public void setTestname(String testname) {
            this.testname = testname;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }
    }
}
