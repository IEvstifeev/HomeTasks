import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import org.apache.logging.log4j.Logger;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * @Author Igor Evstifeev
 * Database - класс для работы с базой данных
 */

public class Main {
    final static char dm = (char) 34;
    private final static Logger logger = getLogger("JDBCAppender");
    /**
     * getConnection - метод подключения к базе данных
     * @return - возвращает connection
     * @throws SQLException - исключение возникающее при подключении к базе
     */
    public static Connection getConnection() throws SQLException{
        final String PATH_TO_PROPERTIES = "src/resources/connection.properties";
        Properties prop = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(PATH_TO_PROPERTIES)) {
            prop.load(fileInputStream);
            String url = prop.getProperty("url");
            String name = prop.getProperty("username");
            String password = prop.getProperty("password");
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DriverManager.getConnection(url, name, password);
            return connection;
        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл " + PATH_TO_PROPERTIES + " не обнаружено");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * queryFromUser - метод осуществляет выборку данных из таблицы User
     * @param conn - соединение с базой
     * @throws SQLException - исключение возникающее при выборке данных
     */
    public static void queryFromUser(Connection conn) throws SQLException{
        try (Statement statement = conn.createStatement()) {
            ResultSet result1 = statement.executeQuery(
                    "SELECT * FROM lesson15." + dm + "User" + dm);
            System.out.println("Выводим все записи из таблицы User");
            while (result1.next()) {
                System.out.println("Номер в выборке #" + result1.getRow()
                        + "\t Номер в базе #" + result1.getInt("id")
                        + "\t" + result1.getString("name")
                        + "\t" + result1.getDate("birthday")
                        + "\t" + result1.getInt("login_id")
                        + "\t" + result1.getString("city")
                        + "\t" + result1.getString("email")
                        + "\t" + result1.getString("description")
                );
            }
            logger.info("Выборка всех юзеров отработала корректно");
        } catch (SQLException e) {
            e.getStackTrace();
            logger.error("Выборка всех юзеров отработала некорректно", e);
        }
    }

    /**
     * insertIntoUser - метод осуществляет вставку в таблицу User
     * @param conn - соединение с базой
     * @param name - имя
     * @param birthday - день рожденья
     * @param login_id - логин id пользователя
     * @param city - город
     * @param email - email пользователя
     * @param description - описание
     * @throws SQLException - исключение возникающее при вставке
     */
    public static void insertIntoUser(Connection conn, String name, Date birthday, Integer login_id, String city, String email, String description) throws SQLException
    {
        String insparamsql = "INSERT INTO lesson15." + dm +"User" + dm + "(name, birthday, login_id, city, email, description) values(?,?,?,?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insparamsql)) {
            pstmt.setString(1, name);
            pstmt.setDate(2, birthday);
            pstmt.setInt(3, login_id);
            pstmt.setString(4, city);
            pstmt.setString(5, email);
            pstmt.setString(6, description);
            pstmt.executeUpdate();
            logger.info("Вставка юзеров отработала корректно");
        } catch (SQLException e) {
            e.getStackTrace();
            logger.error("Вставка юзеров отработала некорректно", e);
        }
    }

    /**
     * batchInsertIntoUser - метод осуществляет вставку данных batch процессом
     * @param conn - соединение с базой
     * @param countrec - количество вставляемых данных
     * @throws SQLException - исключение возникающее при вставке
     */
    public static void batchInsertIntoUser(Connection conn, Integer countrec) throws SQLException
    {
        String insbatchsql = "INSERT INTO lesson15." + dm + "User" + dm + "(name, birthday, login_id, city, email, description) values(?,?,?,?,?,?)";
        try (PreparedStatement pstmtbatch = conn.prepareStatement(insbatchsql)) {
            for (int i = 0; i < countrec; i++) {
                pstmtbatch.setString(1, "name_" + i);
                pstmtbatch.setDate(2, Date.valueOf("1970-05-25"));
                pstmtbatch.setInt(3, 25 + i);
                pstmtbatch.setString(4, "city_" + i);
                pstmtbatch.setString(5, "email_" + i);
                pstmtbatch.setString(6, "description_" + i);
                pstmtbatch.addBatch();
            }
            pstmtbatch.executeBatch();
            logger.info("Вставка batch юзеров отработала корректно");
        }catch(SQLException e){
            e.getStackTrace();
            logger.error("Вставка batch юзеров отработала некорректно", e);
        }
    }

    /**
     * queryParam - метод параметризованной выборки по login_id и name
     * @param conn - соединение с базой
     * @param login_id - логин id пользователя
     * @param name - имя пользователя
     * @throws SQLException - исключение возникающее в процессе выборки
     */
    public static void queryParam(Connection conn, Integer login_id, String name) throws SQLException{
        String selsql = "Select * From lesson15." + dm + "User" + dm + " where login_id = ? and name = ?";
        try (PreparedStatement pst = conn.prepareStatement(selsql)){
            pst.setInt(1, login_id);
            pst.setString(2, name);
            ResultSet ressql = pst.executeQuery();
            System.out.println("Выводим данные параметризованного запроса: ");
            while (ressql.next()) {
                System.out.println(" Id в базе " + ressql.getInt("id")
                        + "\t" + ressql.getString("name")
                        + "\t" + ressql.getDate("birthday")
                        + "\t" + ressql.getInt("login_id")
                        + "\t" + ressql.getString("city")
                        + "\t" + ressql.getString("email")
                        + "\t" + ressql.getString("description")
                );
            }
            logger.info("Параметризованный запрос юзеров отработал корректно");
        }catch (SQLException e){
            e.getStackTrace();
            logger.error("Параметризованный запрос юзеров отработал некорректно", e);
        }
    }

    /**
     * setSavePoints - метод установки логических точек сохранения
     * @param conn - соединение с базой
     * @throws SQLException - исключение
     */
    public static void setSavePoints(Connection conn) throws SQLException {
        try (Statement statement = conn.createStatement()){
            conn.setAutoCommit(false);
            statement.executeUpdate("UPDATE lesson15." + dm +"User" + dm + "Set name = 'Анатолий' where name = 'Антон'");
            conn.commit();
            Savepoint savePointUpd = conn.setSavepoint();
            statement.executeUpdate("Insert INTO lesson15." + dm +"User" + dm + "(name, birthday, login_id, city, email, description) " +
                    "values ('Алексей', '12-01-1970', 1478, 'Казань', 'alex@kazan.ru', 'Привед медвед!')");
            conn.commit();
            Savepoint savePointIns = conn.setSavepoint();
            logger.info("Установка точек сохранения отработала корректно");
        }catch (SQLException e){
            e.getStackTrace();
            logger.error("Установка точек сохранения отработала некорректно", e);
        }
    }

    /**
     * rollbackSavePoints - метод демонстирует откал транзации при добавлении записи с ошибкой
     * @param conn - соединение с базой
     */
    public static void rollbackSavePoints(Connection conn){
        try (Statement statement = conn.createStatement()){
            String correctsql = "INSERT INTO lesson15." + dm + "ROLE" + dm + "(name, description) values ('Administration','Тестируем rollback')";
            statement.executeUpdate(correctsql);
            conn.commit();
            Savepoint savePointCorrect = conn.setSavepoint("InsertCorrect");
            try {
                String incorrectsql = "INSERT INTO lesson15." + dm + "ROLE" + dm + "(name, description) values ('Manager','Тестируем rollback')";
                statement.executeUpdate(incorrectsql);
                Savepoint savePointUnCorrect = conn.setSavepoint("InsertUncorrect");
            }catch (SQLException e){
                conn.rollback(savePointCorrect);
                e.getStackTrace();
                logger.error("Тест rollback должен в error упасть",e);
            }
            logger.info("Откат транзакции отработал корректно");
        }catch(SQLException e){
            e.getStackTrace();
            logger.error("Откат транзакции отработал некорректно", e);
        }
    }

    public static void main(String[] args) throws SQLException {
        try (Connection connection = getConnection()) {
            insertIntoUser(connection, "Андрей", Date.valueOf("1970-04-20"), 2351, "Новосибирск", "test13@mail.ru", "Тестирование вставки");
            batchInsertIntoUser(connection, 10);
            queryFromUser(connection);
            queryParam(connection, 2351, "Андрей");
            setSavePoints(connection);
            rollbackSavePoints(connection);
            logger.info("Приложение отработало корректно");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Приложение отработало некорректно", ex);
        }
    }
}
