package pivotalsoft.com.JobSuchi.Response;

import java.util.List;

/**
 * Created by USER on 2/20/2018.
 */

public class SpeclizationSpinnerItem {


    /**
     * alldata : [{"specialization":"CIVIL"},{"specialization":"Architecture"}]
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
         * specialization : CIVIL
         */

        private String specialization;

        public String getSpecialization() {
            return specialization;
        }

        public void setSpecialization(String specialization) {
            this.specialization = specialization;
        }
    }
}
