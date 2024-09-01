package com.luofan.usercenter.once;

import com.alibaba.excel.EasyExcel;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ImportXingQiuUser {
    public static void main(String[] args) {
        String fileName = "D:\\luofan\\代码\\java\\星球项目\\user-center-backend-master\\src\\main\\resources\\testExcel.xlsx";

        List<XingQiuTableUserInfo> userInfoList = EasyExcel.read(fileName).head(XingQiuTableUserInfo.class).sheet().doReadSync();
        System.out.println("总数：" + userInfoList.size());
        Map<String, List<XingQiuTableUserInfo>> listMap = userInfoList.stream().collect(Collectors.groupingBy(XingQiuTableUserInfo::getUsername));
        System.out.println("不重复的昵称数：" + listMap.size());

    }
}
