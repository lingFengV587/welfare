package com.mischievous.elf.controller.base;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author lifang
 */
public class BaseController {
    public static final String CONTENTTYPE_HTML = "text/html";
    public static final String CONTENTTYPE_JSON = "application/json";
    public static final String CHARTSET_UTF8 = "utf-8";
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpSession session;
    @Autowired
    protected ServletContext servletContext;
    protected RequestContext requestContext;
    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 从Request获取参数
     *
     * @param name
     * @return
     */
    final protected String getParam(String name) {
        return request.getParameter(name);
    }

    /**
     * 从Request获取参数，去除头尾空格
     *
     * @param name
     * @return
     */
    final protected String getParamTrim(String name) {
        return StringUtils.trimToEmpty(request.getParameter(name));
    }

    /**
     * 从Request获取参数
     *
     * @param name
     * @return
     */
    final protected String[] getParams(String name) {
        return request.getParameterValues(name);
    }

    /**
     * 获取参数为Map
     *
     * @return
     */
    final protected Map<String, String> getParamMap() {
        Map<String, String> m = new HashMap<String, String>();
        Iterator<String> keys = request.getParameterMap().keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            m.put(key, getParam(key));
        }
        return m;
    }

    final protected String getMessages(String code) {
        if (requestContext == null) {
            requestContext = new RequestContext(request);
        }

        return requestContext.getMessage(code);
    }

//    protected String toJsonString(Object obj){
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//        }
//        return "{}";
//    }

    //获取浏览器端客户IP地址
    protected String getClientAddr() {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-real-ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (ip != null && ip.contains(",")) {
            //CDN返回多组ip
            ip = ip.split(",")[0];
        }
        return ip;
    }

    /**
     * 向HttpServletResponse输出文本
     *
     * @param text        输出的字符串
     * @param contentType 类型
     * @param charset     编码
     */
    final public void outputText(HttpServletResponse response, String text, String contentType, String charset) {
        response.setCharacterEncoding(charset);
        //指定内容类型
        response.setContentType(contentType + ";charset=" + charset);
        //禁止缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        OutputStream o = null;
        try {
            byte[] content = text.getBytes(charset);
            o = response.getOutputStream();
            o.write(content);
        } catch (IOException e) {
            logger.error("outputText-error:{}", e);
        } finally {
            try {
                if (o != null) {
                    o.close();
                }
                o = null;
            } catch (IOException e) {
                logger.error("outputText-error:{}", e);
            }
        }
    }

}
