package com.sunknowledge.dme.rcm.application.properties;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfigurationSetup implements AsyncConfigurer {
    private static final String TASK_EXECUTOR_DEFAULT = "taskExecutor";
    private static final String TASK_EXECUTOR_NAME_PREFIX_DEFAULT = "taskExecutor-";
    private static final String TASK_EXECUTOR_NAME_PREFIX_CONTROLLER = "controllerTaskExecutor-";
    private static final String TASK_EXECUTOR_NAME_PREFIX_SERVICE = "serviceTaskExecutor-";
    private static final String TASK_EXECUTOR_NAME_PREFIX_REPOSITORY = "repositoryTaskExecutor-";

    public static final String TASK_EXECUTOR_CONTROLLER = "controllerTaskExecutor";
    public static final String TASK_EXECUTOR_SERVICE = "serviceTaskExecutor";
    public static final String TASK_EXECUTOR_REPOSITORY = "repositoryTaskExecutor";

    private final ApplicationPropertiesSetup applicationProperties;
    public AsyncConfigurationSetup(ApplicationPropertiesSetup applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Override
    @Bean(name = TASK_EXECUTOR_DEFAULT)
    public Executor getAsyncExecutor(){
        return newTaskExecutor(TASK_EXECUTOR_NAME_PREFIX_DEFAULT);
    }

    @Bean(name = TASK_EXECUTOR_CONTROLLER)
    public Executor getControllerAsyncExecutor(){
        return newTaskExecutor(TASK_EXECUTOR_NAME_PREFIX_CONTROLLER);
    }

    @Bean(name = TASK_EXECUTOR_SERVICE)
    public Executor getServiceAsyncExecutor(){
        return newTaskExecutor(TASK_EXECUTOR_NAME_PREFIX_SERVICE);
    }

    @Bean(name = TASK_EXECUTOR_REPOSITORY)
    public Executor getRepositoryAsyncExecutor(){
        return newTaskExecutor(TASK_EXECUTOR_NAME_PREFIX_REPOSITORY);
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler(){
        return new SimpleAsyncUncaughtExceptionHandler();
    }

    private Executor newTaskExecutor(final String taskExecutorNamePrefix){
        final ApplicationPropertiesSetup.Async asyncProperties = applicationProperties.getAsync();
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(asyncProperties.getCorePoolSize());
        executor.setQueueCapacity(asyncProperties.getQueueCapacity());
        executor.setMaxPoolSize(asyncProperties.getMaxPoolSize());
        executor.setThreadNamePrefix(taskExecutorNamePrefix);
        return executor;
    }
}
