package by.epam.fitness.pool.manager;

public class TransactionManager {
//    private ProxyConnection connection;
//
//    /**
//     * Begin transaction
//     *
//     * @param dao  dao
//     * @param daos daos
//     * @throws DaoException when sql error
//     */
//    public void beginTransaction(BaseDao dao, BaseDao... daos) throws DaoException {
//
//        try {
//            connection = ConnectionPool.getInstance().retrieveConnection();
//        } catch (ConnectionPoolException e) {
//            throw new DaoException(e);
//        }
//
//        try {
//            connection.setAutoCommit(false);
//        } catch (SQLException e) {
//            throw new DaoException("Set auto commit 'false' error", e);
//        }
//        dao.setConnection(connection);
//        for (BaseDao d : daos) {
//            d.setConnection(connection);
//        }
//    }
//
//    /**
//     * End transaction
//     *
//     * @throws DaoException when sql error
//     */
//    public void endTransaction() throws DaoException {
//        try {
//            connection.setAutoCommit(true);
//
//        } catch (SQLException e) {
//            throw new DaoException("Set auto commit 'true' error", e);
//        }
//        ConnectionPool.getInstance().putbackConnection(connection);
//    }
//
//    /**
//     * Commit transaction
//     *
//     * @throws DaoException when sql error
//     */
//    public void commit() throws DaoException {
//        try {
//            connection.commit();
//        } catch (SQLException e) {
//            throw new DaoException("Commit error", e);
//        }
//    }
//
//    /**
//     * Rollback transaction
//     *
//     * @throws DaoException when sql error
//     */
//    public void rollback() throws DaoException {
//        try {
//            connection.rollback();
//        } catch (SQLException e) {
//            throw new DaoException("Rollback error", e);
//        }
//    }

}
