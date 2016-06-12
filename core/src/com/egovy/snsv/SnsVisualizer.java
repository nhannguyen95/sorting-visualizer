package com.egovy.snsv;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import utils.Assets;
import utils.Constants;
import vpcalculator.MultipleVirtualViewportBuilder;
import vpcalculator.OrthographicCameraWithVirtualViewport;
import vpcalculator.VirtualViewport;

public class SnsVisualizer implements ApplicationListener {

	private SpriteBatch batch;
//	private Stage stage;
	private MultipleVirtualViewportBuilder multipleVirtualViewportBuilder;
	private VirtualViewport virtualViewport;
	private OrthographicCameraWithVirtualViewport camera;

//	private TextureManager textureManager;
	private Scene2DManager scene2dManager;

	@Override
	public void create() {

		Assets.instance.init(new AssetManager());

		this.batch = new SpriteBatch();

		this.multipleVirtualViewportBuilder = new MultipleVirtualViewportBuilder();
		this.multipleVirtualViewportBuilder.init(Constants.MIN_WIDTH, Constants.MIN_HEIGHT,
				Constants.MAX_WIDTH, Constants.MAX_HEIGHT);
		this.virtualViewport = this.multipleVirtualViewportBuilder
				.getVirtualViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		this.camera = new OrthographicCameraWithVirtualViewport();
		this.camera.init(this.virtualViewport);
		this.camera.updateViewport();
		this.camera.position.set(0,0,0);

		scene2dManager = new Scene2DManager(this.camera);

//		this.stage = new Stage();
//		this.stage.getViewport().setCamera(this.camera);
//		rebuildStage();

//		textureManager = new TextureManager(this.virtualViewport.getWidth(), this.virtualViewport.getHeight());

//		Gdx.input.setInputProcessor(this.stage);
	}

//	private void rebuildStage() {
//		Table localTable1 = buildFrontBar();
//
//		this.stage.clear();
//		Stack localStack =  new Stack();
//		this.stage.addActor(localStack);
//		localStack.setSize(Constants.MAX_WIDTH, Constants.MAX_HEIGHT);
//
//		localStack.add(localTable1);
//	}

//	private Button btnTest;
	private Table buildFrontBar() {
		Table localTable = new Table();
//		this.btnTest = Assets.instance.scene2D.btnTest;
//		localTable.addActor(this.btnTest);
//
//		this.btnTest.setPosition(0,Gdx.graphics.getHeight()/2-this.btnTest.getHeight()/2,0);
		return localTable;
	}

	@Override
	public void resize(int width, int height) {
		VirtualViewport localVirtualViewport = this.multipleVirtualViewportBuilder
				.getVirtualViewport(width, height);
		this.camera.setVirtualViewport(localVirtualViewport);
		this.camera.updateViewport();
		this.camera.position.set(0,0,0);

//		textureManager.resize(localVirtualViewport.getWidth(), localVirtualViewport.getHeight());
		scene2dManager.resize(localVirtualViewport.getWidth(), localVirtualViewport.getHeight());
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		float deltaTime = Gdx.graphics.getDeltaTime();

		// update
		this.camera.update();
		scene2dManager.update(deltaTime);

		// render
		scene2dManager.render();

		this.batch.begin();

		// value of sorting array
		for(ElementManager.Element elem : scene2dManager.getElementManager().getElems()) {
			int k = 10;
			if (scene2dManager.isUsingCount()) k = 30;
			Image img = elem.img;
			if (img.getColor().a != 0) {
				if (img.getY() == 38) {
					Assets.instance.fonts.numFont.draw(this.batch, "" + (int) img.getHeight() / k,
							img.getX() + this.camera.getVirtualViewport().getWidth() / 2.0f + 5,
							img.getY() + this.camera.getVirtualViewport().getHeight() / 2.0f + img.getHeight() + 25);
				}
			}
		}

		// amt of count slot
		for(ElementManager.Element elem : scene2dManager.getCountSlotManager().getElems()) {
			Image img = elem.img;
			if (scene2dManager.isUsingCount()) {
				if (img.getColor().a != 0) {
					Assets.instance.fonts.numFont.draw(this.batch, "" + elem.amt,
							img.getX() + this.camera.getVirtualViewport().getWidth() / 2.0f + 10,
							img.getY() + this.camera.getVirtualViewport().getHeight() / 2.0f - 8);
					Assets.instance.fonts.numFont.draw(this.batch, "" + (int)img.getHeight() / 30,
							img.getX() + this.camera.getVirtualViewport().getWidth() / 2.0f + 5,
							img.getY() + this.camera.getVirtualViewport().getHeight() / 2.0f + img.getHeight() + 25);
				}
			}
		}

		this.batch.end();

//		textureManager.render(batch);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
//		this.stage.dispose();
		scene2dManager.dispose();
		this.batch.dispose();
	}
}
