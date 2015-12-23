package rule_engine;

import org.drools.compiler.kie.builder.impl.MessageImpl;
import org.kie.api.builder.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saeed on 02/12/2015.
 */
public class MethodResult {
    private boolean done;
    private List<Message> messages=new ArrayList<Message>();
    private List<Message> errorMessages=new ArrayList<Message>();
    private List<Message> warningMessages=new ArrayList<Message>();
    private List<Message> infoMessages=new ArrayList<Message>();

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<String> getDisplayMessages() {
        List<String> retMessages=new ArrayList<String>();
        if(getMessages()!=null)
            for(Message m:getMessages())
            retMessages.add(makeMessageFormat(m));

        return  retMessages;


    }

    public void setErrorMessages(List<Message> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public List<Message> getErrorMessages() {
        return errorMessages;
    }

    public void setWarningMessages(List<Message> warningMessages) {
        this.warningMessages = warningMessages;
    }

    public List<Message> getWarningMessages() {
        return warningMessages;
    }

    public void setInfoMessages(List<Message> infoMessages) {
        this.infoMessages = infoMessages;
    }

    public List<Message> getInfoMessages() {
        return infoMessages;
    }

    private String makeMessageFormat(Message m) {
        return m.getLevel().toString()+" : "+m.getText()+" / line :"+m.getLine() +" / column :"+m.getColumn() ;
    }

    public void addException(Exception ex)
    {
        Message.Level l= Message.Level.ERROR;
        Message err=new MessageImpl(1L, l,"",ex.getMessage());
        getErrorMessages().add(err);
        getMessages().add(err);
    }
}
