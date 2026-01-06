package org.eletra.energy.backend.configurations;

import org.eletra.energy.backend.models.CategoryMeter;
import org.eletra.energy.backend.models.LineMeter;
import org.eletra.energy.backend.models.ModelMeter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class DataPersistConfig {
    public static void main(String[] args) {
        try {
            // Configurar Hibernate
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();

            // Criar Linhas
            List<LineMeter> meterLines = new ArrayList<>();
            meterLines.add(new LineMeter("Cronos"));
            meterLines.add(new LineMeter("Ares"));

            // Criar Categorias
            List<CategoryMeter> cronosCategories = new ArrayList<>();
            cronosCategories.add(new CategoryMeter("Cronos Old", meterLines.get(0)));
            cronosCategories.add(new CategoryMeter("Cronos L", meterLines.get(0)));
            cronosCategories.add(new CategoryMeter("Cronos-NG", meterLines.get(0)));

            List<CategoryMeter> aresCategories = new ArrayList<>();
            aresCategories.add(new CategoryMeter("Ares TB", meterLines.get(1)));
            aresCategories.add(new CategoryMeter("Ares THS", meterLines.get(1)));

            // Criar Modelos
            List<ModelMeter> cronosOldModels = new ArrayList<>();
            cronosOldModels.add(new ModelMeter("Cronos 6001-A", cronosCategories.get(0)));
            cronosOldModels.add(new ModelMeter("Cronos 6003", cronosCategories.get(0)));
            cronosOldModels.add(new ModelMeter("Cronos 7023", cronosCategories.get(0)));

            List<ModelMeter> cronosLModels = new ArrayList<>();
            cronosLModels.add(new ModelMeter("Cronos 6021L", cronosCategories.get(1)));
            cronosLModels.add(new ModelMeter("Cronos 7023L", cronosCategories.get(1)));

            List<ModelMeter> cronosNGModels = new ArrayList<>();
            cronosNGModels.add(new ModelMeter("Cronos 6001-NG", cronosCategories.get(2)));
            cronosNGModels.add(new ModelMeter("Cronos 6003-NG", cronosCategories.get(2)));
            cronosNGModels.add(new ModelMeter("Cronos 6021-NG", cronosCategories.get(2)));
            cronosNGModels.add(new ModelMeter("Cronos 6031-NG", cronosCategories.get(2)));
            cronosNGModels.add(new ModelMeter("Cronos 7021-NG", cronosCategories.get(2)));
            cronosNGModels.add(new ModelMeter("Cronos 7023-NG", cronosCategories.get(2)));

            List<ModelMeter> aresTBModels = new ArrayList<>();
            aresTBModels.add(new ModelMeter("ARES 7021", aresCategories.get(0)));
            aresTBModels.add(new ModelMeter("ARES 7031", aresCategories.get(0)));
            aresTBModels.add(new ModelMeter("ARES 7023", aresCategories.get(0)));

            List<ModelMeter> aresTHSModels = new ArrayList<>();
            aresTHSModels.add(new ModelMeter("ARES 8023 15", aresCategories.get(1)));
            aresTHSModels.add(new ModelMeter("ARES 8023 200", aresCategories.get(1)));
            aresTHSModels.add(new ModelMeter("ARES 8023 2,5", aresCategories.get(1)));

            // Salvar no banco de dados
            session.beginTransaction();

            for (LineMeter meterLine : meterLines) {
                session.persist(meterLine);
            }
            for (CategoryMeter category : cronosCategories) {
                session.persist(category);
            }
            for (CategoryMeter category : aresCategories) {
                session.persist(category);
            }
            for (ModelMeter model : cronosOldModels) {
                session.persist(model);
            }
            for (ModelMeter model : cronosLModels) {
                session.persist(model);
            }
            for (ModelMeter model : cronosNGModels) {
                session.persist(model);
            }
            for (ModelMeter model : aresTBModels) {
                session.persist(model);
            }
            for (ModelMeter model : aresTHSModels) {
                session.persist(model);
            }

            session.getTransaction().commit();

            // Encerrar sessão
            session.close();
            sessionFactory.close();
        } catch (Exception e) {
            System.out.println("Erro ao persistir dados iniciais: " + e.getMessage());
        }
    }
}