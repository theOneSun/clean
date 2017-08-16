package com.sun.clean.serviceTest;

import com.sun.clean.FrameApplication;
import com.sun.clean.facade.ProcessorFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * @authur sunjian.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FrameApplication.class)
@TestPropertySource("classpath:application.properties")
public class RunTest
{
    @Resource
    private ProcessorFacade processorFacade;

    @Test
    public void testRun() throws Exception
    {
        processorFacade.checkDataAndImport();
    }
}
