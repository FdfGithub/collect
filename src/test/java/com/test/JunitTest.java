package com.test;

import com.qianjing.collect.CollectApplication;
import com.qianjing.collect.dao.CollectMapper;
import com.qianjing.collect.properties.FilePathProperties;
import com.qianjing.collect.util.EmailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.*;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CollectApplication.class)
public class JunitTest {

    @Test
    public void test(){

    }

}
