package com.revagenda.test.service;

import org.junit.Before;
import org.junit.Test;
import revagenda.persistence.ReimbursementDAO;
import revagenda.services.ReimbursementService;


import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReimbursementServiceTest {

private ReimbursementDAO reimbursementDAOMock;
private ReimbursementService reimbursementService;

@Before
public void setup() throws SQLException {

    reimbursementDAOMock = mock(ReimbursementDAO.class);
    reimbursementService = new ReimbursementService(reimbursementDAOMock);
    String description = any();

    List<String> list = Arrays.asList("A");
    when(reimbursementDAOMock.readAllByDescription(description)).thenReturn(list);

}

@Test
public void ifReimburementexist() throws SQLException{
    assertTrue(reimbursementService.checkAllTheReimburement("A"));

}

@Test
    public void ifReimbursementNotExist()  throws SQLException{
    assertFalse(reimbursementService.checkAllTheReimburement("B"));
}



}
