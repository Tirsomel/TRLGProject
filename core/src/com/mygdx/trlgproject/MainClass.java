package com.mygdx.trlgproject;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

class Button {
    private Texture texture;
    private float x, y, width, height;

    public Button(String texturePath, float x, float y, float width, float height) {
        this.texture = new Texture(texturePath);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public boolean isClicked(float screenX, float screenY) {
        return screenX >= x && screenX <= x + width && screenY >= y && screenY <= y + height;
    }

}
class Player {
    private Vector2 position;
    private Texture texture;
    private float playerWidth = 16; // Ширина игрока в пикселях
    private float playerHeight = 16;

    public Player(Texture texture, float x, float y) {
        this.texture = texture;
        this.position = new Vector2(x, y);
    }

    public void move(Vector2 delta) {
        position.add(delta);
    }

    public void render(SpriteBatch batch) {
        // Масштабирование и отрисовка текстуры
        float scaleX = playerWidth / texture.getWidth();
        float scaleY = playerHeight / texture.getHeight();
        batch.draw(texture, position.x, position.y, playerWidth, playerHeight);
    }
}

public class MainClass implements Screen, InputProcessor {

    private Player player;
    private SpriteBatch batch;

    private Texture playerTexture;
    private float playerWidth = 16; // Ширина игрока в пикселях
    private float playerHeight = 16; // Высота игрока в пикселях
    private float playerX = 100; // Начальная X координата игрока
    private float playerY = 100; // Начальная Y координата игрока
    private boolean playerSelected = false;

    private TextureMapObject selectedObject;
    final Game game;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;


    private OrthographicCamera camera;
    private Button leftButton;
    private Button rightButton;
    private Button upButton;
    private Button downButton;

    public MainClass(Game game) {
        this.game = game;
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);
        playerTexture = new Texture("assets/2311965_1.png"); // Загрузка изображения JPEG
        player = new Player(playerTexture, 400, 200);


        // Загрузка карты
        tiledMap = new TmxMapLoader().load("assets/Start.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1 / 16f); // Масштабирование в соответствии с размером тайла

        // Создание камеры
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 16, 16); // Установка размера области просмотра в тайлах
        camera.update();
        leftButton = new Button("assets/Back_Square_Button.png", 50, 50, 64, 64);
        rightButton = new Button("assets/Next_Square_Button.png", 150, 50, 64, 64);
        upButton = new Button("assets/Up_Square_Button.png", 100, 100, 64, 64);
        downButton = new Button("assets/Down_Square_Button.png", 100, 0, 64, 64);
    }



    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Проверяем, была ли нажата кнопка влево
        if (leftButton.isClicked(screenX, screenY)) {
            player.move(new Vector2(-16, 0)); // Перемещаем игрока на 16 пикселей влево
            return true;
        }
        // Проверяем, была ли нажата кнопка вправо
        if (rightButton.isClicked(screenX, screenY)) {
            player.move(new Vector2(16, 0)); // Перемещаем игрока на 16 пикселей вправо
            return true;
        }
        // Проверяем, была ли нажата кнопка вверх
        if (upButton.isClicked(screenX, screenY)) {
            player.move(new Vector2(0, 16)); // Перемещаем игрока на 16 пикселей вверх
            return true;
        }
        // Проверяем, была ли нажата кнопка вниз
        if (downButton.isClicked(screenX, screenY)) {
            player.move(new Vector2(0, -16)); // Перемещаем игрока на 16 пикселей вниз
            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    private void movePlayer(float deltaX, float deltaY) {
        // Здесь ваша логика перемещения игрока
    }





    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
    @Override
    public void render(float delta) {

        // Очистка экрана
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        camera.update();
        tiledMapRenderer.setView(camera);


        tiledMapRenderer.render();


        batch.begin();
        player.render(batch);
        leftButton.render(batch);
        rightButton.render(batch);
        upButton.render(batch);
        downButton.render(batch);
        // Отрисовка объектов
        for (MapLayer layer : tiledMap.getLayers()) {
            if (layer.getName().equals("Objects")) {
                for (MapObject object : layer.getObjects()) {
                    if (object instanceof TextureMapObject) {
                        TextureMapObject textureObj = (TextureMapObject) object;

                        batch.draw(textureObj.getTextureRegion(),
                                textureObj.getX() + 273,
                                textureObj.getY() + 112,
                                textureObj.getTextureRegion().getRegionWidth() * 1,
                                textureObj.getTextureRegion().getRegionHeight() * 1);
                    }
                }
            }
        }
        if (selectedObject != null) {

        }
        leftButton.render(batch);
        rightButton.render(batch);
        upButton.render(batch);
        downButton.render(batch);



        batch.end();
    }


    @Override
    public void show() {

    }



    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width / 16f; //
        camera.viewportHeight = height / 16f;
        camera.update();
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        batch.dispose();
        tiledMap.dispose();
        tiledMapRenderer.dispose();

    }
}







