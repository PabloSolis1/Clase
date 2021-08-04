package mx.edu.utez.model.game;

import mx.edu.utez.model.category.BeanCategory;

public class BeanGame {
    private int idGame;
    private String nameGame;
    private String img_game;
    private BeanCategory Category_idCategory;
    private String date_premiere;
    private int status;

    public BeanGame() {
    }

    public BeanGame(int idGame, String nameGame, String img_game, BeanCategory category_idCategory, String date_premiere, int status) {
        this.idGame = idGame;
        this.nameGame = nameGame;
        this.img_game = img_game;
        Category_idCategory = category_idCategory;
        this.date_premiere = date_premiere;
        this.status = status;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public String getNameGame() {
        return nameGame;
    }

    public void setNameGame(String nameGame) {
        this.nameGame = nameGame;
    }

    public String getImg_game() {
        return img_game;
    }

    public void setImg_game(String img_game) {
        this.img_game = img_game;
    }

    public int getCategory_idCategory() {
        return 0;
    }

    public void setCategory_idCategory(BeanCategory category_idCategory) {
        Category_idCategory = category_idCategory;
    }

    public String getDate_premiere() {
        return date_premiere;
    }

    public void setDate_premiere(String date_premiere) {
        this.date_premiere = date_premiere;
    }

    public int getStatus(int status) {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return 0;
    }
}
