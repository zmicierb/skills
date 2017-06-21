package com.barysevich.project.service;

import com.barysevich.project.repository.PositionRepository;
import com.barysevich.project.service.impl.PositionServiceImpl;
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
public class PositionServiceTest {

    @Mock
    private PositionRepository positionRepository;

    @InjectMocks
    private PositionServiceImpl positionService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByNameContainingIgnoreCase() throws Exception {
        positionService.findByNameContainingIgnoreCase("test", new PageRequest(0, 10));

        verify(positionRepository, times(1)).findByNameContainingIgnoreCase("test", new PageRequest(0, 10));
    }

}