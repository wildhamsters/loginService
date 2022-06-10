package org.wildhamsters.loginservice;

import java.time.LocalDateTime;

import org.springframework.web.client.RestTemplate;

class Logger {
    static void log(Log.Level level, Class<?> className, String msg) {
        // RestTemplate restTemplate = new RestTemplate();
        // Log log = new Log(level.toString(), LocalDateTime.now().toString(),
        //         "battleShips", className.getName(), msg);
        
        // restTemplate.postForObject("http://elk:8000/log", log, Log.class);
    }
}
