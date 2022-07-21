package tn.esprit.softib.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@RestController
public class SmsController {

        @GetMapping(value = "/sendSMS")
        public ResponseEntity<String> sendSMS() {
        	
                //Twilio.init("AC5000345f881795fa87747639935a069c", "346cf975c1404fc505178f98518e4e64");
           

               // Message.creator(new PhoneNumber("+21629407444"),
               //                 new PhoneNumber("+19282725058"), "Hello from Twilio 📞").create();
        	
        	 Twilio.init("AC42557a52bc3464b537d56d6fb195f109", "a4ca8266fc5d1506af874c3c46e9c3de");
            

             Message.creator(new PhoneNumber("+21629407444"),
                            new PhoneNumber("+12693714672"), "Hello, We welcome you and thank you for choosing us as your banking servicer 📞").create();
              log.debug("message sent");
                return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
        }
        
    
}