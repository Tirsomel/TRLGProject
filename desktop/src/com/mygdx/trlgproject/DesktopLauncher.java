package com.mygdx.trlgproject;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Screen;


public class DesktopLauncher extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    public Music gameMusic;



    public static void main (String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Roguelike Game");
        config.setWindowedMode(800, 480);
        new Lwjgl3Application(new DesktopLauncher(), config);
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/534347__orginaljun__funky-pixel-melody-loop.wav"));
        gameMusic.setVolume(0.5f);
        gameMusic.setLooping(true);
        gameMusic.play();
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        gameMusic.dispose();
    }
}
class MainMenuScreen implements Screen {
    final DesktopLauncher game;
    private Texture playButtonTexture;
    private Texture settingsButtonTexture;
    private Texture exitButtonTexture;
    private Texture arrowUpTexture;
    private Texture arrowDownTexture;
    private boolean settingsOpen = false;
    private int volume = 50; // Начальная громкость в процентах
    private int playButtonX = 350, playButtonY = 200, buttonWidth = 100, buttonHeight = 50;
    private int settingsButtonX = 350, settingsButtonY = 150;
    private int exitButtonX = 350, exitButtonY = 100;
    private int arrowUpX = 700, arrowUpY = 250, arrowSize = 20;
    private int arrowDownX = 700, arrowDownY = 200;



    public MainMenuScreen(DesktopLauncher game) {
        this.game = game;
        playButtonTexture = new Texture("assets/Play_Button.png");
        settingsButtonTexture = new Texture("assets/Settings_Button.png");
        exitButtonTexture = new Texture("assets/Exit_Button.png");
        arrowUpTexture = new Texture("assets/Up_Square_Button.png"); // Предполагается, что у вас есть эта текстура
        arrowDownTexture = new Texture("assets/Down_Square_Button.png"); // Предполагается, что у вас есть эта текстура
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(playButtonTexture, playButtonX, playButtonY, buttonWidth, buttonHeight);
        game.batch.draw(settingsButtonTexture, settingsButtonX, settingsButtonY, buttonWidth, buttonHeight);
        game.batch.draw(exitButtonTexture, exitButtonX, exitButtonY, buttonWidth, buttonHeight);

        if (settingsOpen) {
            game.batch.draw(arrowUpTexture, arrowUpX, arrowUpY, arrowSize, arrowSize);
            game.batch.draw(arrowDownTexture, arrowDownX, arrowDownY, arrowSize, arrowSize);
            game.font.draw(game.batch, "Volume: " + volume + "%", 700, 240); // Отрисовка процентной громкости
        }

        game.batch.end();

        // Обработка нажатий кнопок
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Координата Y инвертирована

            // Проверка нажатия кнопки Play
            if (mouseX >= playButtonX && mouseX <= playButtonX + buttonWidth &&
                    mouseY >= playButtonY && mouseY <= playButtonY + buttonHeight) {
                game.setScreen(new MainClass(game)); // Переключение на игровой экран
            }

            // Проверка нажатия кнопки Settings
            if (mouseX >= settingsButtonX && mouseX <= settingsButtonX + buttonWidth &&
                    mouseY >= settingsButtonY && mouseY <= settingsButtonY + buttonHeight) {
                settingsOpen = !settingsOpen; // Открытие или закрытие настроек
            }

            // Проверка нажатия кнопки Exit
            if (mouseX >= exitButtonX && mouseX <= exitButtonX + buttonWidth &&
                    mouseY >= exitButtonY && mouseY <= exitButtonY + buttonHeight) {
                Gdx.app.exit();
            }
            if (settingsOpen && mouseX >= arrowUpX && mouseX <= arrowUpX + arrowSize &&
                    mouseY >= arrowUpY && mouseY <= arrowUpY + arrowSize) {
                volume = Math.min(volume + 10, 100); // Увеличение громкости не более 100%
                game.gameMusic.setVolume(volume / 100f); // Обращение к gameMusic через game
            }

            // Проверка нажатия стрелочки вниз
            if (settingsOpen && mouseX >= arrowDownX && mouseX <= arrowDownX + arrowSize &&
                    mouseY >= arrowDownY && mouseY <= arrowDownY + arrowSize) {
                volume = Math.max(volume - 10, 0); // Уменьшение громкости не менее 0%
                game.gameMusic.setVolume(volume / 100f); // Обращение к gameMusic через game
            }
        }
    }


    @Override
    public void resize(int width, int height) {
        // Метод для обработки изменения размера экрана
    }

    @Override
    public void pause() {
        // Метод вызывается, когда игра сворачивается или теряет фокус
    }

    @Override
    public void resume() {
        // Метод вызывается, когда игра возвращается из свернутого состояния или получает фокус
    }

    @Override
    public void hide() {
        // Метод вызывается, когда текущий экран перестает быть активным
    }

    @Override
    public void dispose() {
        playButtonTexture.dispose();
        settingsButtonTexture.dispose();
        exitButtonTexture.dispose();
        arrowUpTexture.dispose();
        arrowDownTexture.dispose();
    }
}
