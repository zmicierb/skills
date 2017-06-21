package com.barysevich.project.service;

import com.barysevich.project.model.CompanyInfo;
import com.barysevich.project.model.Position;
import com.barysevich.project.model.Project;
import com.barysevich.project.repository.CompanyInfoRepository;
import com.barysevich.project.repository.ProjectRepository;
import com.barysevich.project.service.impl.CompanyInfoServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by BarysevichD on 2017-06-21.
 */
@RunWith(SpringRunner.class)
public class CompanyInfoServiceTest {

    @Mock
    private CompanyInfoRepository companyInfoRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private CompanyInfoServiceImpl companyInfoService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void remove() throws Exception {
        Long companyId = 1L;
        Project project1 = new Project(1L, new Position("test"), "test", "test", "test", null, null);
        project1.setId(1L);
        Project project2 = new Project(1L, new Position("test"), "test", "test", "test", null, null);
        project2.setId(2L);
        List<Project> projects = new ArrayList<>(Arrays.asList(project1, project2));

        when(projectRepository.findByCompanyId(companyId)).thenReturn(projects);

        companyInfoService.remove(companyId);

        verify(projectRepository, times(1)).findByCompanyId(companyId);
        verify(projectRepository, times(1)).remove(project1.getId());
        verify(projectRepository, times(1)).remove(project2.getId());
    }

    @Test
    public void findByNameContainingIgnoreCase() throws Exception {
        companyInfoService.findByNameContainingIgnoreCase("test", new PageRequest(0, 10));

        verify(companyInfoRepository, times(1)).findByNameContainingIgnoreCase("test", new PageRequest(0, 10));
    }

    @Test
    public void update() throws Exception {
        Long companyId = 1L;
        CompanyInfo companyInfo = new CompanyInfo("test", LocalDate.now(), LocalDate.now());
        companyInfo.setId(companyId);

        when(companyInfoRepository.findOne(companyId)).thenReturn(companyInfo);

        companyInfoService.update(companyId, companyInfo);

        verify(companyInfoRepository, times(1)).findOne(companyId);
        verify(companyInfoRepository, times(1)).save(companyInfo);

    }

}