package com.jrock.forum.groovy.util

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.cfg.AnnotationConfiguration
import com.jrock.forum.util.PropertyLoader

public class HibernateUtil {
    static SessionFactory sessionFactory

    static {
        try {
            def config = new AnnotationConfiguration().configure()
            def props = PropertyLoader.loadProperties("dbconfig.properties")

            config.setProperties(props) //load properties file

            sessionFactory = config.buildSessionFactory()
        } catch (ex) {
            println ex.printStackTrace()
        }
    }

    static ThreadLocal<Session> session = new ThreadLocal<Session>()

    public static SessionFactory getSessionFactory(){
        return sessionFactory
    }

    public static Session getSession(){
        if(session?.get() == null){
            session.set(sessionFactory.getCurrentSession())
        }
        return session.get()
    }


}
