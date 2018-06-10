package com.barysevich.project.service;

import com.barysevich.project.repository.DepartmentRepository;
import com.barysevich.project.service.impl.DepartmentServiceImpl;
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
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByNameContainingIgnoreCase() throws Exception {
        departmentService.findByNameContainingIgnoreCase("test", new PageRequest(0, 10));

        verify(departmentRepository, times(1)).findByNameContainingIgnoreCase("test", new PageRequest(0, 10));
    }

}