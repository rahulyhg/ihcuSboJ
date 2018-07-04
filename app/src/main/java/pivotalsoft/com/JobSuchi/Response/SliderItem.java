package pivotalsoft.com.JobSuchi.Response;

/**
 * Created by Gangadhar on 10/30/2017.
 */

public class SliderItem {
    String questionNo ;
    String question ;

    public SliderItem(String questionNo, String question) {

        this.questionNo = questionNo;
        this.question = question;
    }

    public String getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(String questionNo) {
        this.questionNo = questionNo;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
