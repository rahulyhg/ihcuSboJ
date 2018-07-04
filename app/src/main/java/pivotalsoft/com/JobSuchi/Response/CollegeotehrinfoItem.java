package pivotalsoft.com.JobSuchi.Response;

import java.util.List;

/**
 * Created by USER on 2/19/2018.
 */

public class CollegeotehrinfoItem
{


    /**
     * alldata : [{"amenityid":"1","amenityname":"Hostel","description":"Only For Girls","collegeid":"1"},{"amenityid":"2","amenityname":"Library","description":"2 Floors, 2000 Books with 200 Seating Capacity","collegeid":"1"},{"amenityid":"3","amenityname":"Head of the Institution","description":"Mrs. Kumari CH","collegeid":"1"},{"amenityid":"4","amenityname":"Contact Number","description":"9848012345","collegeid":"1"}]
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
         * amenityid : 1
         * amenityname : Hostel
         * description : Only For Girls
         * collegeid : 1
         */

        private String amenityid;
        private String amenityname;
        private String description;
        private String collegeid;

        public String getAmenityid() {
            return amenityid;
        }

        public void setAmenityid(String amenityid) {
            this.amenityid = amenityid;
        }

        public String getAmenityname() {
            return amenityname;
        }

        public void setAmenityname(String amenityname) {
            this.amenityname = amenityname;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCollegeid() {
            return collegeid;
        }

        public void setCollegeid(String collegeid) {
            this.collegeid = collegeid;
        }
    }
}
