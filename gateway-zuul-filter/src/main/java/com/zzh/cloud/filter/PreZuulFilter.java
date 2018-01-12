package com.zzh.cloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-1-12 16:32
 **/
public class PreZuulFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(PreZuulFilter.class);

    /**
     * 前置过滤器。
     * <p>
     * 但是在 zuul 中定义了四种不同生命周期的过滤器类型：
     * <p>
     * 1、pre：可以在请求被路由之前调用；
     * <p>
     * 2、route：在路由请求时候被调用；
     * <p>
     * 3、post：在route和error过滤器之后被调用；
     * <p>
     * 4、error：处理请求时发生错误时被调用；
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤的优先级，数字越大，优先级越低。
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 是否执行该过滤器。
     * <p>
     * true：说明需要过滤；
     * <p>
     * false：说明不要过滤；
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String host = request.getRemoteHost();
        logger.info("<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        logger.info("                    请求的host:{}                          ", host);
        logger.info("<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return null;
    }
}
