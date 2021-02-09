package com.jia.SpringBootDemo.Component;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class LogUtil extends ClassicConverter {
    Logger logger= LoggerFactory.getLogger(LogUtil.class);

    @Override
    public String convert(ILoggingEvent event) {
        String ip=null;
        try {
            ip=InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }
}
