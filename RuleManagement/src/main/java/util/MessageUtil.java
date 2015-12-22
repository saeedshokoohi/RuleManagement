package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by saeed on 01/01/2016.
 */
@Named
public class MessageUtil {

    ResourcesUtil resourcesUtil;

    public ResourcesUtil getResourcesUtil() {
        return resourcesUtil;
    }

    public void setResourcesUtil(ResourcesUtil resourcesUtil) {
        this.resourcesUtil = resourcesUtil;
    }

    public  void addInfoMessage(String clientId, String messageKey)
    {
        String summery = resourcesUtil.getProperty(messageKey);
        String detail = "";
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summery, detail);
        FacesContext.getCurrentInstance().addMessage(clientId,message);
    }
    public  void addErrorMessage(String clientId,String messageKey)
    {
      addErrorMessage(clientId,messageKey,"");
    }
    public  void addErrorMessages(String clientId,List<String> messageKeys)
    {
        for(String m:messageKeys)
        addErrorMessage(clientId,m);
    }

    public void addErrorMessage(String clientId, String messageKey, String detail) {
        String summery = resourcesUtil.getProperty(messageKey);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summery, detail);
        FacesContext.getCurrentInstance().addMessage(clientId,message);
    }
}
