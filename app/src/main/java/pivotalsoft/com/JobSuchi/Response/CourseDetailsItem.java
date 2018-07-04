package pivotalsoft.com.JobSuchi.Response;

import java.util.List;

/**
 * Created by USER on 2/19/2018.
 */

public class CourseDetailsItem {


    /**
     * alldata : [{"detailsid":"1","course":"B.Tech","specialization":"CSE","intake":"60","remarks":"","collegeid":"1"},{"detailsid":"2","course":"B.Tech","specialization":"ECE","intake":"60","remarks":"","collegeid":"1"}]
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
         * detailsid : 1
         * course : B.Tech
         * specialization : CSE
         * intake : 60
         * remarks :
         * collegeid : 1
         */

        private String detailsid;
        private String course;
        private String specialization;
        private String intake;
        private String remarks;
        private String collegeid;

        public String getDetailsid() {
            return detailsid;
        }

        public void setDetailsid(String detailsid) {
            this.detailsid = detailsid;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getSpecialization() {
            return specialization;
        }

        public void setSpecialization(String specialization) {
            this.specialization = specialization;
        }

        public String getIntake() {
            return intake;
        }

        public void setIntake(String intake) {
            this.intake = intake;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getCollegeid() {
            return collegeid;
        }

        public void setCollegeid(String collegeid) {
            this.collegeid = collegeid;
        }
    }
}
