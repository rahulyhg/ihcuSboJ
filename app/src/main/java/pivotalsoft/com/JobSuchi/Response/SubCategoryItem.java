package pivotalsoft.com.JobSuchi.Response;

import java.util.List;

/**
 * Created by USER on 1/31/2018.
 */

public class SubCategoryItem {


    /**
     * alldata : [{"subcategoryid":"2","subcategoryname":"EAMCET Engineering","description":"Entrance test for admission into B.Tech course across the states of A.P and Telangana","categoryid":"1"}]
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
         * subcategoryid : 2
         * subcategoryname : EAMCET Engineering
         * description : Entrance test for admission into B.Tech course across the states of A.P and Telangana
         * categoryid : 1
         */

        private String subcategoryid;
        private String subcategoryname;
        private String description;
        private String categoryid;

        public String getSubcategoryid() {
            return subcategoryid;
        }

        public void setSubcategoryid(String subcategoryid) {
            this.subcategoryid = subcategoryid;
        }

        public String getSubcategoryname() {
            return subcategoryname;
        }

        public void setSubcategoryname(String subcategoryname) {
            this.subcategoryname = subcategoryname;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCategoryid() {
            return categoryid;
        }

        public void setCategoryid(String categoryid) {
            this.categoryid = categoryid;
        }
    }
}
