package com.melo.focus.util;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * 
 * @author Melo
 *
 */
public class Asserts {

    private static final Validator VALIDATOR;

    static {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        VALIDATOR = factory.getValidator();
    }
    
    /**
     * 校验bean对象
     * @param target 目标对象
     * @param beanName 对象名
     */
    public static<T> void validate(T target,String beanName){
    	validate(target,beanName,Default.class);
    }
    
    /**
     * 校验对象
     * @param target 目标对象
     * @param beanName 对象名
     * @param group 分组校验
     */
    public static<T> void validate(T target,String beanName,Class<?>...group){
    	
    	notEmpty(target,"对象"+beanName+"不能为空");
    	
    	Set<ConstraintViolation<T>> errors = VALIDATOR.validate(target,group);
    	Set<FiledError> filedErrors=new HashSet <FiledError>();
    	if(!CollectionUtils.isEmpty(errors)){
    		error(errors,beanName,filedErrors);
    		throw new DataValidateFiledException(filedErrors);
    	}
    }
    
    /**
     * 校验对象
     * @param target 目标对象
     * @param properties 需要校验的字段名
     * @param beanName 对象名
     * @param group 分组校验
     */
    public static<T> void validate(T target,String []properties,String beanName,Class<?>...group){
    	notEmpty(target,"对象"+beanName+"不能为空");
    	
    	if(properties!=null&&properties.length>0){
    		Set<FiledError> filedErrors=new HashSet <FiledError>();
    		for(String property:properties){
        		Set<ConstraintViolation<T>> validateProperty = VALIDATOR.validateProperty(target, property, group);
        		error(validateProperty,beanName,filedErrors);
    		}
    		if(!CollectionUtils.isEmpty(filedErrors)){
        		throw new DataValidateFiledException(filedErrors);
        	}
    	}
    }
    
    /**
     * 对象是否非空
     * @param target 目标对象
     * @param message 提示信息
     * @param beanName 对象名
     * @param filedName 对象内字段名
     */
    public static<T> void notEmpty(T target,String message,String beanName,String filedName){
    	if(!ObjectUtils.isEmpty(target)){return;}
    	throw new DataValidateFiledException(new FiledError(StringUtils.isEmpty(message)?"对象不能为空":message, 
    			StringUtils.isEmpty(beanName)?"":beanName, StringUtils.isEmpty(filedName)?"":filedName));
    }
    
    /**
     * 对象是否非空
     * @param target
     * @param message
     */
    public static<T> void notEmpty(T target,String message){
    	notEmpty(target,message,"","");
    }
    
    /**
     * 对象是否非空
     * @param target
     */
    
    public static<T> void notEmpty(T target){
    	notEmpty(target,"");
    }
    
    
    /**
     * 把Set<ConstraintViolation<T>> 中的校验信息，收集，最后放在异常中的message即可
     * @param errors 
     * @param beanName
     * @param filedErrors
     */
    private static<T> void error(Set<ConstraintViolation<T>> errors,String beanName,Set<FiledError> filedErrors){
    	for(ConstraintViolation<T> error:errors){
    		
    		Path path = error.getPropertyPath();
    		filedErrors.add(new FiledError(
                    error.getMessage(),
    				StringUtils.isEmpty(beanName) ? "" : beanName,
                    ObjectUtils.isEmpty(path) ? "" : path.toString()
    				)
    		);
    	/*	FiledError f=new FiledError();
    		f.setMessage(error.getMessage());
    		filedErrors.add(f);*/
    	}
    }
}
