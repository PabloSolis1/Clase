package mx.edu.utez.model.game;

import mx.edu.utez.model.category.BeanCategory;
import mx.edu.utez.service.ConnectionMySQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class DaoGame {
    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;
    final private Logger CONSOLE = LoggerFactory.getLogger(DaoGame.class);

    public List<BeanGame> findAll(){
        List<BeanGame> listGames = new ArrayList<>();
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("select nameGame, img_game, date_premiere, status from game;");
            rs = cstm.executeQuery();

            while(rs.next()){
                BeanCategory beanCategory = new BeanCategory();
                BeanGame beanGame = new BeanGame();

                beanGame.setNameGame(rs.getString("nameGame"));
                beanGame.setImgGame(Base64.getEncoder().encodeToString(rs.getBytes("imgGame")));
                beanGame.setDatePremiere(rs.getString("datePremiere"));
                beanGame.setStatus(rs.getInt("status"));


                listGames.add(beanGame);
            }
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido algún error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return listGames;
    }

    public BeanGame findById(int id){
        BeanGame beanGame = new BeanGame();
        return beanGame;
    }

    public boolean create(BeanGame beanGame, InputStream image){
        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_createGame(?,?,?,?)}");

            cstm.setString(1, beanGame.getNameGame());
            cstm.setString(2, beanGame.getDatePremiere());
            cstm.setBlob(3, image);
            cstm.setInt(4,beanGame.getStatus());

        }catch(SQLException e){

        }
        return true;
    }

    public boolean update(BeanGame beanGame){
        boolean flag = false;
        try{
            BeanCategory beanCategory = new BeanCategory();
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_update(?,?,?,?,?)}");
            cstm.setInt(1, beanGame.getIdGame());
            cstm.setString(2, beanGame.getNameGame());
            //pendiente
            cstm.setString(3, beanGame.getImgGame());
            cstm.setInt(4,beanCategory.getIdCategory());
            cstm.setString(5, beanGame.getDatePremiere());
            cstm.setInt(6, beanGame.getStatus());

            flag = cstm.execute();
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

    public boolean delete(int id){
        return true;
    }
}
