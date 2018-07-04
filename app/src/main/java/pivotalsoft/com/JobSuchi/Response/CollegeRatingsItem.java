package pivotalsoft.com.JobSuchi.Response;

import java.util.List;

/**
 * Created by USER on 2/22/2018.
 */

public class CollegeRatingsItem {


    /**
     * alldata : [{"ratingid":"1","ratingname":"NBA","description":"A+ Rating","ratedon":"2012-02-22","collegeid":"1"}]
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
         * ratingid : 1
         * ratingname : NBA
         * description : A+ Rating
         * ratedon : 2012-02-22
         * collegeid : 1
         */

        private String ratingid;
        private String ratingname;
        private String description;
        private String ratedon;
        private String collegeid;

        public String getRatingid() {
            return ratingid;
        }

        public void setRatingid(String ratingid) {
            this.ratingid = ratingid;
        }

        public String getRatingname() {
            return ratingname;
        }

        public void setRatingname(String ratingname) {
            this.ratingname = ratingname;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getRatedon() {
            return ratedon;
        }

        public void setRatedon(String ratedon) {
            this.ratedon = ratedon;
        }

        public String getCollegeid() {
            return collegeid;
        }

        public void setCollegeid(String collegeid) {
            this.collegeid = collegeid;
        }
    }
}
