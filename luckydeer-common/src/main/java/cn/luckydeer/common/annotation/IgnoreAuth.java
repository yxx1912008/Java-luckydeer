package cn.luckydeer.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解：是否忽略Token验证
 * 
 * @author yuanxx
 * @version $Id: IgnoreAuth.java, v 0.1 2018年6月14日 下午1:42:51 yuanxx Exp $
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreAuth {

}
