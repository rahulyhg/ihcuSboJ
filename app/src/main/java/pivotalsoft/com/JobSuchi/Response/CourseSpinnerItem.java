package pivotalsoft.com.JobSuchi.Response;

import java.util.List;

/**
 * Created by USER on 2/20/2018.
 */

public class CourseSpinnerItem {


    /**
     * alldata : [{"coursename":"B.Tech"},{"coursename":"B.E"}]
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
         * coursename : B.Tech
         */

        private String coursename;

        public String getCoursename() {
            return coursename;
        }

        public void setCoursename(String coursename) {
            this.coursename = coursename;
        }
    }
}
