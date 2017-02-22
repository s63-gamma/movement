package com.gamma.dal.util

import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration

/**
 * Created by requinard on 2/21/17.
 */
class DatabaseUtil {
    companion object {
        val dbSession:  SessionFactory by lazy {
            Configuration()
                    .configure()
                    .buildSessionFactory()
        }
    }

    fun getSession() = dbSession.openSession()
}
