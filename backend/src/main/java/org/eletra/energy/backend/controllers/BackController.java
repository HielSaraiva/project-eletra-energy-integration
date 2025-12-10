package org.eletra.energy.backend.controllers;

import java.util.ArrayList;

import org.eletra.energy.backend.models.LineMeter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BackController {
    // Atributos auxiliares
    private ArrayList<LineMeter> meterLines;

    // Métodos auxiliares
    public void loadMeterLines() {
        SessionFactory factory = null;
        Session session = null;

        try {
            factory = new Configuration().configure().buildSessionFactory();
            session = factory.openSession();

            meterLines = new ArrayList<>(
                    session.createQuery("FROM LineMeter", LineMeter.class).list()
            );
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados iniciais: " + e.getMessage());
        } finally {
            if (session != null) session.close();
            if (factory != null) factory.close();
        }
    }

    public ArrayList<LineMeter> getMeterLines() {
        if (meterLines == null) {
            loadMeterLines();
        }
        return meterLines;
    }
}
