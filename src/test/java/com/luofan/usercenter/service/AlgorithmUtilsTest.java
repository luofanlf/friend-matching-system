package com.luofan.usercenter.service;

import com.luofan.usercenter.model.domain.User;
import com.luofan.usercenter.utils.AlgorithmUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: shayu
 * @date: 2022/12/06
 * @ClassName: yupao-backend01
 * @Description:    用户插入单元测试，注意打包时要删掉或忽略，不然打一次包就插入一次
 */
@SpringBootTest
public class AlgorithmUtilsTest {


    @Test
    void testCompareTags(){
        List<String> tagList1 = Arrays.asList("java", "大一", "男");
        List<String> tagList2 = Arrays.asList("java", "大二", "女");
        List<String> tagList3 = Arrays.asList("python", "大二", "女");
        int score1 = AlgorithmUtils.minDistance(tagList1,tagList2);
        int score2 = AlgorithmUtils.minDistance(tagList1,tagList3);

        System.out.println(score1);
        System.out.println(score2);
    }
}
