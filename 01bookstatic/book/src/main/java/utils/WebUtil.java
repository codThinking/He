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

    /**
     * 将字符串转换成为Int类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue){
        if(strInt != null){
            try{
                return Integer.parseInt(strInt);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return defaultValue;
    }
}
