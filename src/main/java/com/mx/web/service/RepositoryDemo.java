package com.mx.web.service;

import org.springframework.session.Session;
import org.springframework.session.SessionRepository;

import com.mx.web.bean.User;

public class RepositoryDemo<S extends Session> {
    private SessionRepository<S> repository; 

    public void demo() {
        S toSave = repository.createSession(); 

        
        User rwinch = new User("hello", "hello");
        toSave.setAttribute("USER", rwinch);

        repository.save(toSave); 

        S session = repository.getSession(toSave.getId()); 

        
        User user = session.getAttribute("USER");
//        assertThat(user).isEqualTo(rwinch);
    }

    // ... setter methods ...
}