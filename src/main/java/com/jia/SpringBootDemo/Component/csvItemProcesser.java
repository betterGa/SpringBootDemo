package com.jia.SpringBootDemo.Component;

import com.jia.SpringBootDemo.entry.personEntry;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

// 处理和校验数据
public class csvItemProcesser extends ValidatingItemProcessor<personEntry> {
    Logger logger= LoggerFactory.getLogger(csvItemProcesser.class);
    @Override
    public personEntry process(personEntry item) throws ValidationException {
        // 将 男/女 换成 M/F
        logger.info("process is validating......");

        // 超类里面进行校验
        super.process(item);

        // 进行处理
        if(item.getSex().equals("男")) {
            item.setSex("M");
        } else {
            item.setSex("F");
        }

        logger.info("process end validating......");
        return item;
    }
}
