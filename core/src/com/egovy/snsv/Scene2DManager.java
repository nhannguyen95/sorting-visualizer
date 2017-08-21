package com.egovy.snsv;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import utils.ArrayFactory;
import utils.Assets;
import utils.Constants;
import vpcalculator.OrthographicCameraWithVirtualViewport;

import java.util.ArrayList;

public class Scene2DManager implements Disposable {

    private Stage stage;
    private float vpWidth;
    private float vpHeight;
    public boolean firstAction;
    private ElementManager elemManager;
    private ElementManager countSlotManager;

    // flags
    private boolean selectClicked = false;
    private boolean insertClicked = false;
    private boolean bubbleClicked = false;
    private boolean quickClicked = false;
    private boolean countClicked = false;

    // top bar
    private boolean isEnterSort;
    private boolean isExitSort;
    private boolean isEnterSearch;
    private boolean isExitSearch;
    private Image imgTopBar;
    private Button btnSort;
    private Button btnSearch;
    private Button btnComp;
    private Button btnAbout;
    private Button btnSelect;
    private Button btnInsert;
    private Button btnBubble;
    private Button btnQuick;
    private Button btnMerge;
    private Button btnCount;
    private Button btnSequential;
    private Button btnBinary;

    // bot bar
    private float paddingLeft = -395.0f;
    private float disBwBtns = 28.0f;
    private Image imgBotBar;
    private Button btnHammer;
    private Button btnPseudo;
    private Button btnDescription;
    private Button btnFStep;
    private Button btnPStep;
    private Button btnPlay;
    private Button btnNStep;
    private Button btnLStep;

    // logo
    private float logoOffsetY = -50.0f;
    private float disNameLogo = 50.0f;
    private Image imgLogo;
    private Image imgName;

    public Scene2DManager(OrthographicCameraWithVirtualViewport camera) {
        this.stage = new Stage();
        this.stage.getViewport().setCamera(camera);

        this.vpWidth = camera.getVirtualViewport().getWidth();
        this.vpHeight = camera.getVirtualViewport().getHeight();

        this.isEnterSort = false;
        this.isExitSort = false;
        this.isEnterSearch = false;
        this.isExitSearch = false;

        this.firstAction = true;

        this.countSlotManager = new ElementManager(9, -280, Constants.COUNT_SLOT_COLOR_ARRAY[0], null);
        this.elemManager = new ElementManager(Constants.MAX_ARRAY_ELEMENT, 38,
                Constants.DEFAULT_COLOR, this.countSlotManager);


        Gdx.input.setInputProcessor(this.stage);

        initScene2D();
        buildStage();
    }

    private void initScene2D() {
        this.imgTopBar = Assets.instance.scene2D.imgTopBar;
        this.btnSort = Assets.instance.scene2D.btnSort;
        this.btnSearch = Assets.instance.scene2D.btnSearch;
        this.btnComp = Assets.instance.scene2D.btnComp;
        this.btnAbout = Assets.instance.scene2D.btnAbout;
        this.btnSelect = Assets.instance.scene2D.btnSelect;
        this.btnInsert = Assets.instance.scene2D.btnInsert;
        this.btnBubble = Assets.instance.scene2D.btnBubble;
        this.btnQuick = Assets.instance.scene2D.btnQuick;
        this.btnMerge = Assets.instance.scene2D.btnMerge;
        this.btnCount = Assets.instance.scene2D.btnCount;
        this.btnSequential = Assets.instance.scene2D.btnSequential;
        this.btnBinary = Assets.instance.scene2D.btnBinary;

        this.imgBotBar = Assets.instance.scene2D.imgBotBar;
        this.btnHammer = Assets.instance.scene2D.btnHammer;
        this.btnPseudo = Assets.instance.scene2D.btnPseudo;
        this.btnDescription = Assets.instance.scene2D.btnDescription;
        this.btnFStep = Assets.instance.scene2D.btnFStep;
        this.btnPStep = Assets.instance.scene2D.btnPStep;
        this.btnPlay = Assets.instance.scene2D.btnPlay;
        this.btnNStep = Assets.instance.scene2D.btnNStep;
        this.btnLStep = Assets.instance.scene2D.btnLStep;

        this.imgLogo = Assets.instance.scene2D.imgLogo;
        this.imgName = Assets.instance.scene2D.imgName;
    }

    private void buildStage() {

        this.stage.clear();
        Stack localStack = new Stack();
        this.stage.addActor(localStack);
        localStack.setSize(Constants.MAX_WIDTH, Constants.MAX_HEIGHT);

        localStack.add(buildTopBar());
        localStack.add(buildBotBar());
        localStack.add(buildLogo());

        localStack.add(elemManager.getArrayTable());
        localStack.add(countSlotManager.getArrayTable());
    }

    private Table buildTopBar() {
        Table table = new Table();

        table.addActor(imgTopBar);
        table.addActor(btnSort);
        table.addActor(btnSearch);
        table.addActor(btnComp);
        table.addActor(btnAbout);
        table.addActor(btnSelect);
        table.addActor(btnInsert);
        table.addActor(btnBubble);
        table.addActor(btnQuick);
        table.addActor(btnMerge);
        table.addActor(btnCount);
        table.addActor(btnSequential);
        table.addActor(btnBinary);

        imgTopBar.setPosition(-vpWidth / 2.0f, vpHeight / 2.0f - imgTopBar.getHeight());
        btnSort.setPosition(-vpWidth / 2.0f, imgTopBar.getY());
        btnSearch.setPosition(btnSort.getX() + btnSearch.getWidth(), imgTopBar.getY());
        btnComp.setPosition(btnSearch.getX() + btnComp.getWidth(), imgTopBar.getY());
        btnAbout.setPosition(vpWidth/2.0f-btnAbout.getWidth(),imgTopBar.getY());
        btnSelect.setPosition(btnSort.getX(), btnSort.getY()-btnSelect.getHeight());
        btnInsert.setPosition(btnSort.getX(), btnSort.getY()-2*btnSelect.getHeight());
        btnBubble.setPosition(btnSort.getX(), btnSort.getY()-3*btnBubble.getHeight());
        btnQuick.setPosition(btnSort.getX(), btnSort.getY()-4*btnBubble.getHeight());
        btnMerge.setPosition(btnSort.getX(), btnSort.getY()-5*btnBubble.getHeight());
        btnCount.setPosition(btnSort.getX(), btnSort.getY()-6*btnBubble.getHeight());
        btnSequential.setPosition(btnSearch.getX(), btnSearch.getY()-btnSequential.getHeight());
        btnBinary.setPosition(btnSearch.getX(), btnSearch.getY()-2*btnSequential.getHeight());

        btnSelect.setColor(btnSelect.getColor().mul(new Color(1,1,1,0)));
        btnInsert.setColor(btnInsert.getColor().mul(new Color(1,1,1,0)));
        btnBubble.setColor(btnBubble.getColor().mul(new Color(1,1,1,0)));
        btnQuick.setColor(btnQuick.getColor().mul(new Color(1,1,1,0)));
        btnMerge.setColor(btnMerge.getColor().mul(new Color(1,1,1,0)));
        btnCount.setColor(btnCount.getColor().mul(new Color(1,1,1,0)));
        btnSequential.setColor(btnSequential.getColor().mul(new Color(1,1,1,0)));
        btnBinary.setColor(btnBinary.getColor().mul(new Color(1,1,1,0)));

        btnSort.addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                Scene2DManager.this.isExitSort = false;
                Scene2DManager.this.isEnterSort = true;
                Scene2DManager.this.showSorts();
                super.enter(event, x, y, pointer, fromActor);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Scene2DManager.this.isExitSort = true;
                super.exit(event, x, y, pointer, toActor);
            }
        });

        btnSelect.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectClicked) {
                    Scene2DManager.this.btnSelect.setChecked(true);
                    return;
                }
                Scene2DManager.this.resetFlags();
                selectClicked = true;

                Scene2DManager.this.elemManager.applyVals(ArrayFactory.genArray());
                // show title = selection sort

                Scene2DManager.this.sortClickActions();
                Scene2DManager.this.btnSelect.setChecked(true);
            }
        });

        btnInsert.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (insertClicked) {
                    Scene2DManager.this.btnInsert.setChecked(true);
                    return;
                }
                Scene2DManager.this.resetFlags();
                insertClicked = true;

                Scene2DManager.this.elemManager.applyVals(ArrayFactory.genArray());
                // show title = insertion sort

                Scene2DManager.this.sortClickActions();
                Scene2DManager.this.btnInsert.setChecked(true);
            }
        });

        btnBubble.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (bubbleClicked) {
                    Scene2DManager.this.btnBubble.setChecked(true);
                    return;
                }
                Scene2DManager.this.resetFlags();
                bubbleClicked = true;

                Scene2DManager.this.elemManager.applyVals(ArrayFactory.genArray());
                // show title = bubble sort

                Scene2DManager.this.sortClickActions();
                Scene2DManager.this.btnBubble.setChecked(true);
            }
        });

        btnQuick.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (quickClicked) {
                    Scene2DManager.this.btnQuick.setChecked(true);
                    return;
                }
                Scene2DManager.this.resetFlags();
                quickClicked = true;

                Scene2DManager.this.elemManager.applyVals(ArrayFactory.genArray());
                // show title = quick sort

                Scene2DManager.this.sortClickActions();
                Scene2DManager.this.btnQuick.setChecked(true);
            }
        });

        btnCount.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (countClicked) {
                    Scene2DManager.this.btnCount.setChecked(true);
                    return;
                }
                Scene2DManager.this.resetFlags();
                countClicked = true;

                Scene2DManager.this.elemManager.applyVals(ArrayFactory.genArrayForCount());
                Scene2DManager.this.countSlotManager.applyVals(ArrayFactory.genOrderArray(1,9, 30));
                // show title = count sort

                Scene2DManager.this.sortClickActions();
                Scene2DManager.this.btnCount.setChecked(true);
            }
        });

        btnSearch.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                Scene2DManager.this.isExitSearch = false;
                Scene2DManager.this.isEnterSearch = true;
                Scene2DManager.this.showSearchs();
                super.enter(event, x, y, pointer, fromActor);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Scene2DManager.this.isExitSearch = true;
                super.exit(event, x, y, pointer, toActor);
            }
        });

        return table;
    }

    private void resetFlags() {
        selectClicked = false;
        insertClicked = false;
        bubbleClicked = false;
        quickClicked = false;
        countClicked = false;
    }

    private void sortClickActions() {
        solveCheckSearch();
        hideSorts();
        if (firstAction) {
            showBotBar();
            hideLogo();
            firstAction = false;
            elemManager.showFirstTime();
        } else {
            elemManager.showCurTime();
        }

        if (countClicked) {
            countSlotManager.showFirstTime();
        } else {
            countSlotManager.hide();
        }
    }

    private void solveCheckSearch() {
        btnSort.setChecked(true);
        btnSearch.setChecked(false);
        btnSelect.setChecked(false);
        btnInsert.setChecked(false);
        btnBubble.setChecked(false);
        btnQuick.setChecked(false);
        btnMerge.setChecked(false);
        btnCount.setChecked(false);
    }

    private void showBotBar() {
        imgBotBar.addAction(Actions.moveBy(0,imgBotBar.getHeight(), 0.25f));
        btnHammer.addAction(Actions.moveBy(0,imgBotBar.getHeight(), 0.25f));
        btnPseudo.addAction(Actions.moveBy(0,imgBotBar.getHeight(), 0.25f));
        btnDescription.addAction(Actions.moveBy(0,imgBotBar.getHeight(), 0.25f));
        btnFStep.addAction(Actions.moveBy(0,imgBotBar.getHeight(), 0.25f));
        btnPStep.addAction(Actions.moveBy(0,imgBotBar.getHeight(), 0.25f));
        btnPlay.addAction(Actions.moveBy(0,imgBotBar.getHeight(), 0.25f));
        btnNStep.addAction(Actions.moveBy(0,imgBotBar.getHeight(), 0.25f));
        btnLStep.addAction(Actions.moveBy(0,imgBotBar.getHeight(), 0.25f));
    }

    private void hideLogo() {
        imgLogo.addAction(Actions.fadeOut(0.25f));
        imgName.addAction(Actions.fadeOut(0.25f));

        imgLogo.setTouchable(Touchable.disabled);
        imgName.setTouchable(Touchable.disabled);
    }

    private void showSearchs() {
        btnSequential.addAction(Actions.fadeIn(0.13f));
        btnBinary.addAction(Actions.fadeIn(0.15f));
        btnSequential.setTouchable(Touchable.enabled);
        btnBinary.setTouchable(Touchable.enabled);
    }

    private void hideSearchs() {
        btnSequential.addAction(Actions.fadeOut(0.15f));
        btnBinary.addAction(Actions.fadeOut(0.13f));
        btnSequential.setTouchable(Touchable.disabled);
        btnBinary.setTouchable(Touchable.disabled);
    }

    private void showSorts() {
        btnSelect.addAction(Actions.fadeIn(0.13f));
        btnInsert.addAction(Actions.fadeIn(0.15f));
        btnBubble.addAction(Actions.fadeIn(0.17f));
        btnQuick.addAction(Actions.fadeIn(0.19f));
        btnMerge.addAction(Actions.fadeIn(0.21f));
        btnCount.addAction(Actions.fadeIn(0.23f));
    }

    private void hideSorts() {
        btnSelect.addAction(Actions.fadeOut(0.21f));
        btnInsert.addAction(Actions.fadeOut(0.19f));
        btnBubble.addAction(Actions.fadeOut(0.17f));
        btnQuick.addAction(Actions.fadeOut(0.15f));
        btnMerge.addAction(Actions.fadeOut(0.13f));
        btnCount.addAction(Actions.fadeOut(0.11f));
    }

    private Table buildBotBar() {
        Table table = new Table();

        table.addActor(imgBotBar);
        table.addActor(btnHammer);
        table.addActor(btnPseudo);
        table.addActor(btnDescription);
        table.addActor(btnFStep);
        table.addActor(btnPStep);
        table.addActor(btnPlay);
        table.addActor(btnNStep);
        table.addActor(btnLStep);

        imgBotBar.setPosition(-vpWidth/2.0f, -vpHeight/2.0f - imgBotBar.getHeight());
        btnHammer.setPosition(-vpWidth/2.0f, imgBotBar.getY());
        btnPseudo.setPosition(vpWidth/2.0f - btnPseudo.getWidth(), imgBotBar.getY());
        btnDescription.setPosition(btnPseudo.getX()-btnDescription.getWidth(), imgBotBar.getY());
        float width = btnFStep.getWidth();
        float y = imgBotBar.getY() + imgBotBar.getHeight()/2.0f - btnFStep.getHeight()/2.0f;
        btnFStep.setPosition(paddingLeft,y);
        btnPStep.setPosition(btnFStep.getX()+width+disBwBtns, y);
        btnPlay.setPosition(btnPStep.getX()+width+disBwBtns, y);
        btnNStep.setPosition(btnPlay.getX()+width+disBwBtns, y);
        btnLStep.setPosition(btnNStep.getX()+width+disBwBtns, y);

        btnPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Scene2DManager.this.handleBtnPlay();
            }
        });

        return table;
    }

    private void handleBtnPlay() {
        if (!elemManager.isFinish()) {
            elemManager.setPaused(!elemManager.isPaused());
        } else {
            int [] argArr = elemManager.getCurVals();
            if (selectClicked) {
                elemManager.applyInstructions(ArrayFactory.selectionSort(argArr));
            } else if (insertClicked) {
                elemManager.applyInstructions(ArrayFactory.insertionSort(argArr));
            } else if (bubbleClicked) {
                elemManager.applyInstructions(ArrayFactory.bubbleSort(argArr));
            } else if (quickClicked) {
                elemManager.applyInstructions(ArrayFactory.quickSort(argArr.clone(), 0, argArr.length - 1, new ArrayList<String>()));
            } else if (countClicked) {
                elemManager.applyInstructions(ArrayFactory.countSort(argArr, 30));
            }
        }
    }

    private Table buildLogo() {
        Table table = new Table();

        table.addActor(imgLogo);
        table.addActor(imgName);

        imgLogo.setPosition(-imgLogo.getWidth()/2.0f,logoOffsetY);
        imgName.setPosition(-imgName.getWidth()/2.0f, imgLogo.getY() - disNameLogo - imgName.getHeight());

        return table;
    }


    public void update(float deltaTime) {

        this.elemManager.update(deltaTime);
        this.stage.act(deltaTime);

        if (this.elemManager.isFinish()) {
            btnPlay.setChecked(false);
        }
    }

    private void handleMousePosition() {

        Vector3 mousePos = stage.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

        if (isEnterSort && isExitSort) {
            if (!(btnSelect.getX() <= mousePos.x && mousePos.x <= btnSelect.getX() + btnSelect.getWidth()
                    && btnCount.getY() <= mousePos.y
                    && mousePos.y <= btnSelect.getY() + btnSelect.getHeight())) {
                hideSorts();
                isEnterSort = false;
            }
        }

        if (isEnterSearch && isExitSearch) {
            if (!(btnSequential.getX() <= mousePos.x && mousePos.x <= btnSequential.getX() + btnSequential.getWidth()
            && btnBinary.getY() <= mousePos.y && mousePos.y <= btnSequential.getY()+btnSequential.getHeight())) {
                hideSearchs();
                isEnterSearch = false;
            }
        }
    }

    public void render() {

        handleMousePosition();

        this.stage.draw();
    }

    public void resize(float vpWidth, float vpHeight) {
        this.vpWidth = vpWidth;
        this.vpHeight = vpHeight;
    }

    @Override
    public void dispose() {
        this.stage.dispose();
    }

    public ElementManager getElementManager() {
        return this.elemManager;
    }

    public boolean isUsingCount() { return this.countClicked; }

    public ElementManager getCountSlotManager() { return this.countSlotManager; }
}
