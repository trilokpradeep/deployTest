import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JNDI {

    public static void main(String args[]) {
        Connection conn = null;

        try {
            //JNDI
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/siperian-localhost-orcl-bbh_ors-ds");

            conn = ds.getConnection();
            //JNDI end
            String date = "";
            String sql = "select * from C_ADDRESS";
            Statement stm = conn.createStatement();
            ResultSet rst = stm.executeQuery(sql);
            while (rst.next()) {
                date = rst.getString("creator");
                System.out.println("Date " + date + ",");
            }
            if (conn != null) {
                conn.close();
                System.out.println("Successfully closed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}