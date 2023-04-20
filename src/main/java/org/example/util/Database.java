package org.example.util;

import jakarta.persistence.EntityManager;
import java.util.function.Consumer;
import java.util.function.Function;

public class Database {
    public static <R> R doInTransaction(
            Function<EntityManager, R> returningTransactionFunction) {
        var entityManager = JPAUtil.getEntityManager();
        var transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            R result = returningTransactionFunction.apply(entityManager);
            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            //TODO -> create custom JPA exception mapper
            e.printStackTrace();
            throw e;
            //return null;
        } finally {
            entityManager.close();
        }
    }
    public static void doInTransactionWithoutResult(
            Consumer<EntityManager> voidTransactionFunction) {
        var entityManager = JPAUtil.getEntityManager();
        var transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            voidTransactionFunction.accept(entityManager);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            entityManager.close();
        }
    }
}