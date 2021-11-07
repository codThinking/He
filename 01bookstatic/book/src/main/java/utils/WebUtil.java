package utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtil {

    /**
     * 把Map中属性注入到bean对象中
     * @param value
     * @param bean
     */
    public static <T>T copyParamToBean(Map value, T bean){
        try {
//            System.out.println(bean);

            BeanUtils.populate(bean,value);
//            System.out.println(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
