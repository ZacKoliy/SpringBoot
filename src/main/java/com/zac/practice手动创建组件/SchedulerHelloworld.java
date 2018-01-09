package com.zac.practice手动创建组件;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by Zac Koliy on 2018/1/9.
 */
public class SchedulerHelloworld {
    public static void main(String[] args) {
        JobDetail jobDetail = JobBuilder.newJob(JobHelloWorld.class)
                .withIdentity("myjob", "job_group_1")
                .usingJobData("data1", "Quartz测试")
                .build();

//        Trigger cronTrigger = TriggerBuilder.newTrigger()
//                .withIdentity("mytrigger", "trigger_group_1").startNow()
//                .withSchedule(
//                        SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInSeconds(5)).build();
        CronTrigger cronTrigger = (CronTrigger)TriggerBuilder.newTrigger()
                .withIdentity("mytrigger", "trigger_group_1").startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ? *"))
                .build();
        // scheduler 实例
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.scheduleJob(jobDetail, cronTrigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
