package com.zzh.demo;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-1-8 0:18
 **/
@Component
public class DownloadRoute extends RouteBuilder {

    private static Logger logger = LoggerFactory.getLogger(DownloadRoute.class);
    @Value("${ftp.server.info}")
    private String ftpServer;
    @Value("${ftp.local.dir}")
    private String downloadLocation;

    @Override
    public void configure() throws Exception {
        Date start = new Date();
        logger.error("the ftp download started" + start);
        from(ftpServer).log(LoggingLevel.ERROR, logger, "the ftp download started:"
                + new Date()).to(downloadLocation)
                .log(LoggingLevel.ERROR, logger, "Downloaded file ${file:name} complete." +
                        " end:" + new Date());
        logger.error("the ftp download ended" + new Date());
    }
}
