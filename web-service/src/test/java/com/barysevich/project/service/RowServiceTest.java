package com.barysevich.project.service;

import com.barysevich.project.repository.RowRepository;
import com.barysevich.project.service.impl.RowServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by BarysevichD on 2017-06-21.
 */
@RunWith(SpringRunner.class)
public class RowServiceTest {

    @Mock
    private RowRepository rowRepository;

    @InjectMocks
    private RowServiceImpl rowService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByNameContainingIgnoreCase() throws Exception {
        rowService.findByNameContainingIgnoreCase("test", new PageRequest(0, 10));

        verify(rowRepository, times(1)).findByNameContainingIgnoreCase("test", new PageRequest(0, 10));
    }

}