package pivotalsoft.com.JobSuchi.Response;

import java.util.List;

/**
 * Created by USER on 1/26/2018.
 */

public class TestItem  {


    /**
     * alldata : [{"testid":"1","testname":"Android Sample Test","totalquestions":"10","totalmarks":"10","negativemarks":"0","duration":"10","subcategoryid":"2"},{"testid":"2","testname":"EAMCET MOCK Test 1","totalquestions":"10","totalmarks":"10","negativemarks":"0","duration":"10","subcategoryid":"2"}]
     * message : success
     */

    private String message;
    private List<AlldataBean> alldata;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AlldataBean> getAlldata() {
        return alldata;
    }

    public void setAlldata(List<AlldataBean> alldata) {
        this.alldata = alldata;
    }

    public static class AlldataBean {
        /**
         * testid : 1
         * testname : Android Sample Test
         * totalquestions : 10
         * totalmarks : 10
         * negativemarks : 0
         * duration : 10
         * subcategoryid : 2
         */

        private String testid;
        private String testname;
        private String totalquestions;
        private String totalmarks;
        private String negativemarks;
        private String duration;
        private String subcategoryid;

        public String getTestid() {
            return testid;
        }

        public void setTestid(String testid) {
            this.testid = testid;
        }

        public String getTestname() {
            return testname;
        }

        public void setTestname(String testname) {
            this.testname = testname;
        }

        public String getTotalquestions() {
            return totalquestions;
        }

        public void setTotalquestions(String totalquestions) {
            this.totalquestions = totalquestions;
        }

        public String getTotalmarks() {
            return totalmarks;
        }

        public void setTotalmarks(String totalmarks) {
            this.totalmarks = totalmarks;
        }

        public String getNegativemarks() {
            return negativemarks;
        }

        public void setNegativemarks(String negativemarks) {
            this.negativemarks = negativemarks;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getSubcategoryid() {
            return subcategoryid;
        }

        public void setSubcategoryid(String subcategoryid) {
            this.subcategoryid = subcategoryid;
        }
    }
}
