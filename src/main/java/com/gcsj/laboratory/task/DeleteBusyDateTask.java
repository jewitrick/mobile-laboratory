package com.gcsj.laboratory.task;

import com.alibaba.fastjson.JSONObject;
import com.gcsj.laboratory.mapper.CarMapper;
import com.gcsj.laboratory.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Configuration      // 1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
@SuppressWarnings("all")
public class DeleteBusyDateTask {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CarMapper carMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteBusyDateTask.class);

    @Scheduled(cron = "0 0 0 */1 * ?")  // 每天零点执行一次
//    @Scheduled(cron = "*/5 * * * * ?")  // 每5s执行一次
    public void executeInternal() {
        LOGGER.info("--------DeleteBusyDateTask--------");
        delUserBusyDate();
        delCarBusyDate();
    }

    private void delCarBusyDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        carMapper.selectAll()
                .stream()
                .filter(car -> !ObjectUtils.isEmpty(car.getBusy_date()))
                .collect(Collectors.toList())
                .forEach(car -> {
                    List<String> busyDates = JSONObject.parseArray(car.getBusy_date()).toJavaList(String.class);
                    busyDates = filterBeforeCurrentDate(busyDates, currentDate);
                    if (busyDates.size() == 0) {
                        car.setBusy_date(null);
                    } else {
                        car.setBusy_date(JSONObject.toJSONString(busyDates));
                    }
                    carMapper.updateByPrimaryKey(car);
                });
    }

    private List<String> filterBeforeCurrentDate(List<String> busyDates, String currentDate) {
        return busyDates.stream().filter(busyDate -> busyDate.compareTo(currentDate) > 0).collect(Collectors.toList());
    }

    private void delUserBusyDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        userMapper.selectAll()
                .stream()
                .filter(user -> !ObjectUtils.isEmpty(user.getBusy_date()))
                .collect(Collectors.toList())
                .forEach(user -> {
                    List<String> busyDates = JSONObject.parseArray(user.getBusy_date()).toJavaList(String.class);
                    busyDates = filterBeforeCurrentDate(busyDates, currentDate);
                    if (busyDates.size() == 0) {
                        user.setBusy_date(null);
                    } else {
                        user.setBusy_date(JSONObject.toJSONString(busyDates));
                    }
                    userMapper.updateByPrimaryKey(user);
                });
    }
}
