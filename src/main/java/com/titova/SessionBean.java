package com.titova;

import com.titova.insurance.model.Link;
import com.titova.insurance.model.User;

public class SessionBean {
    
    private User currentUser;
    private Link link;
    
    public SessionBean(){
        
    }
    

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
    
    
    
    
}
