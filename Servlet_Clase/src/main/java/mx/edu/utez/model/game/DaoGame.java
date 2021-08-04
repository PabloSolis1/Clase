package mx.edu.utez.model.game;

import mx.edu.utez.model.category.BeanCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import mx.edu.utez.service.ConnectionMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class DaoGame {
    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;
    final private Logger CONSOLE = LoggerFactory.getLogger(DaoGame.class);
    //Visualizar todos los registros
    public List<BeanGame> findAll(){
        List<BeanGame> listGame = new ArrayList<>();
        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_findGames}");
            rs = cstm.executeQuery();

            while(rs.next()){
                BeanCategory category = new BeanCategory();
                BeanGame game = new BeanGame();

                category.setIdCategory(rs.getInt("idCategory"));
                category.setNameCategory(rs.getString("nameCategory"));
                category.setStatus(rs.getInt("status"));

                game.setIdGame(rs.getInt("idGame"));
                game.setNameGame(rs.getString("nameGame"));
                //byte [] bytesImg = rs.getBytes("imgGames");
                game.setImg_game(Base64.getEncoder().encodeToString(rs.getBytes("imgGame")));
                game.setCategory_idCategory(category);
                game.setDate_premiere(rs.getString("date_premiere"));
                game.getStatus(rs.getInt("status"));

                listGame.add(game);
            }
        }catch (SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return listGame;
    }
    //Consultar por ID
    public BeanGame findById(long id){
        BeanGame game = null;
        try {
            con = ConnectionMySQL.getConnection();
            //Procedimiento almacenado (SELECT pendiente)
            cstm = con.prepareCall("SELECT * FROM game AS G INNER JOIN category AS C where G.Category_idCategory = ?;");
            cstm.setLong(1, id);
            rs = cstm.executeQuery();

            if(rs.next()){
                BeanCategory category = new BeanCategory();
                game = new BeanGame();

                category.setIdCategory(rs.getInt("idCategory"));
                category.setNameCategory(rs.getString("nameCategory"));
                category.setStatus(rs.getInt("status"));


                game.setIdGame(rs.getInt("idGame"));
                game.setNameGame(rs.getString("nameGame"));
                //byte [] bytesImg = rs.getBytes("imgGames");
                game.setImg_game(Base64.getEncoder().encodeToString(rs.getBytes("imgGame")));
                game.setCategory_idCategory(category);
                game.setDate_premiere(rs.getString("date_premiere"));
                game.getStatus(rs.getInt("status"));
            }
        }catch (SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return game;
    }
    //Crear nuevo juego
    public boolean create(BeanGame game){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_create(?,?,?,?,?)}");
            cstm.setString(1, game.getNameGame());
            cstm.setString(2, game.getImg_game());
            cstm.setInt(3, game.getCategory_idCategory());
            cstm.setString(4, game.getDate_premiere());
            cstm.setInt(5, game.getStatus());
            cstm.execute();
            flag = true;
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }
    //Actualizar juego
    public boolean update(BeanGame game){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_update(?,?,?,?,?)}");
            cstm.setString(1, game.getNameGame());
            cstm.setString(2, game.getImg_game());
            cstm.setInt(3, game.getCategory_idCategory());
            cstm.setString(4, game.getDate_premiere());
            cstm.setInt(5, game.getStatus());

            flag = cstm.execute();
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

    public boolean delete(long idGame){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_delete(?)}");
            cstm.setLong(1, idGame);

            flag = cstm.execute();
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }
}
