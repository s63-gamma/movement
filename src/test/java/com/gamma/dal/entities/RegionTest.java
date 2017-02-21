package com.gamma.dal.entities;

import com.gamma.dal.util.DatabaseUtil;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by requinard on 2/21/17.
 */
public class RegionTest {
    @BeforeClass
    public static void setUp(){
    }


    @Test
    public void setLogin() throws Exception {
        Session session = new DatabaseUtil().getSession();
        session.beginTransaction();

        Region region = new Region("MANC UNITED", 1, 1, 1, 1);
        session.save(region);

        session.getTransaction().commit();
        session.close();

        assertNotNull(region.getId());
    }

}
