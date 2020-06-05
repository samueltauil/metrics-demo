package com.redhat.metricsdemo;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MetricsDemoRestController {
    
    public boolean healthy=true;
    public String hostname="";
    
    public  MetricsDemoRestController(){
		try {
			hostname= "Hello from " + InetAddress.getLocalHost().getHostName().toString();
		}
		catch (UnknownHostException ex){
			hostname= "error";
		}
    }
    
    @RequestMapping("/")
	public String home(){
         return "<h1>"+hostname+"</h1>";
    }
    
    @RequestMapping("/healthz")
	public ResponseEntity healthz(){
		if (healthy)
			return new ResponseEntity(HttpStatus.ACCEPTED);
		else
			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }
    
    @RequestMapping("/kill")
	public String kill(){
		healthy=false;
		return "Killed "+hostname;
    }
    
}