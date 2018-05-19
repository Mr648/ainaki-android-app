package apps.sffa.com.ainaki.model;

/**
 * Created by Diako on 19/05/2018.
 */

public class FAQ {
    private int id;
    private String title;
    private String answer;
    private String question;
    public FAQ(String title,String answer, String question){
       this.setTitle(title);
       this.setAnswer(answer);
       this.setQuestion(question);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
