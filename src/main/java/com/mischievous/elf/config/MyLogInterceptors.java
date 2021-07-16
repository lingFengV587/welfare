package com.mischievous.elf.config;

import com.mischievous.elf.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author lifang
 */
public class MyLogInterceptors implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(MyLogInterceptors.class);

    /**
     * 预处理
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.warn("==预处理开始==时间：{}", DateUtils.format(new Date()));
        logger.warn("==预处理开始==ServletPath：{}", request.getServletPath());
        logger.warn("==预处理开始==Method：{}", request.getMethod());
        return true;
    }

    /**
     * 返回处理
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        logger.warn("==返回处理==response:{}", response.getWriter());
    }

    /**
     * 后处理
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        logger.warn("==后处理==时间：{}", DateUtils.format(new Date()));
    }
}
