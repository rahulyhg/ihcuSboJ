package pivotalsoft.com.JobSuchi.Response;

/**
 * Created by USER on 1/31/2018.
 */

public class AllQuestionsItem {


    private String id;
    private String section;
    private String question;
    private String questionurl;
    private String answer1;
    private String answer1url;
    private String answer2;
    private String answer2url;
    private String answer3;
    private String answer3url;
    private String answer4;
    private String answer4url;
    private String answer5;
    private String answer5url;
    private String testid;
    private String rightanswer;
    private int score;

    public AllQuestionsItem(String id, String section, String question, String questionurl, String answer1, String answer1url, String answer2, String answer2url, String answer3, String answer3url, String answer4, String answer4url, String answer5, String answer5url, String testid, String rightanswer, int score) {
        this.id = id;
        this.section = section;
        this.question = question;
        this.questionurl = questionurl;
        this.answer1 = answer1;
        this.answer1url = answer1url;
        this.answer2 = answer2;
        this.answer2url = answer2url;
        this.answer3 = answer3;
        this.answer3url = answer3url;
        this.answer4 = answer4;
        this.answer4url = answer4url;
        this.answer5 = answer5;
        this.answer5url = answer5url;
        this.testid = testid;
        this.rightanswer = rightanswer;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionurl() {
        return questionurl;
    }

    public void setQuestionurl(String questionurl) {
        this.questionurl = questionurl;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer1url() {
        return answer1url;
    }

    public void setAnswer1url(String answer1url) {
        this.answer1url = answer1url;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer2url() {
        return answer2url;
    }

    public void setAnswer2url(String answer2url) {
        this.answer2url = answer2url;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer3url() {
        return answer3url;
    }

    public void setAnswer3url(String answer3url) {
        this.answer3url = answer3url;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getAnswer4url() {
        return answer4url;
    }

    public void setAnswer4url(String answer4url) {
        this.answer4url = answer4url;
    }

    public String getAnswer5() {
        return answer5;
    }

    public void setAnswer5(String answer5) {
        this.answer5 = answer5;
    }

    public String getAnswer5url() {
        return answer5url;
    }

    public void setAnswer5url(String answer5url) {
        this.answer5url = answer5url;
    }

    public String getTestid() {
        return testid;
    }

    public void setTestid(String testid) {
        this.testid = testid;
    }

    public String getRightanswer() {
        return rightanswer;
    }

    public void setRightanswer(String rightanswer) {
        this.rightanswer = rightanswer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
