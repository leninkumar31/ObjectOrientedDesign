package com.udemy.spring.aop.springaop.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPointConfig {

	@Pointcut("execution(* com.udemy.spring.aop.springaop.dao.*.*(..))")
	public void dataLayerExecution() {

	}

	@Pointcut("execution(* com.udemy.spring.aop.springaop.business.*.*(..))")
	public void businessLayerExecution() {

	}

	@Pointcut("com.udemy.spring.aop.springaop.aspect.CommonJoinPointConfig.dataLayerExecution() && com.udemy.spring.aop.springaop.aspect.CommonJoinPointConfig.businessLayerExecution()")
	public void allLayerExecution() {

	}
	
	@Pointcut("bean(*dao*)")
	public void beanContainingDao() {
		
	}
	@Pointcut("within(com.udemy.spring.aop.springaop.dao..*)")
	public void dataLayerExecutionWithWithIn() {

	}
	
	@Pointcut("@annotation(com.udemy.spring.aop.springaop.aspect.TrackTime)")
	public void trackTimeAnnotation() {

	}
}
