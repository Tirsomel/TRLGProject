package com.mygdx.trlgproject;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GameMenuScreen implements Screen {
    private Stage stage;

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        // Создание кнопок без скина
        TextButton playButton = new TextButton("Play", new TextButton.TextButtonStyle());
        TextButton settingsButton = new TextButton("Settings", new TextButton.TextButtonStyle());
        TextButton exitButton = new TextButton("Exit", new TextButton.TextButtonStyle());

        // Установка функций для кнопок
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Переход к игровому экрану
            }
        });

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Переход к экрану настроек
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        // Расположение кнопок на экране
        Table table = new Table();
        table.setFillParent(true);
        table.add(playButton).padBottom(10);
        table.row();
        table.add(settingsButton).padBottom(10);
        table.row();
        table.add(exitButton);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1); // Установка белого фона
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

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

    }

    // Реализация остальных методов интерфейса Screen...
}

