package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by apple on 3/12/16.
 */
public class Assets implements Disposable, AssetErrorListener{
    public static final String TAG = Assets.class.getName();

    public static final Assets instance = new Assets();
    public AssetManager assetManager;

    private Skin skin;
    public Scene2D scene2D;
    public Texture base;
    public AssetFont fonts;


    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        this.assetManager.setErrorListener(this);

        this.assetManager.load("scene2d/scene2d.json", Skin.class);
        this.assetManager.load("base.png", Texture.class);
        this.assetManager.load("fonts/arialhs.fnt", BitmapFont.class);
        assetManager.finishLoading();

        this.skin = ((Skin)this.assetManager.get("scene2d/scene2d.json"));
        this.base = ((Texture)this.assetManager.get("base.png"));

        this.scene2D = new Scene2D(this.skin);
        this.fonts = new AssetFont();
    }

    public class AssetFont {
        public final BitmapFont numFont = (BitmapFont)Assets.this.assetManager.get("fonts/arialhs.fnt");

        public AssetFont() {
            this.numFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }

    public class Scene2D {
        public final Image imgTopBar;
        public final Image imgBotBar;
        public final Image imgLogo;
        public final Image imgName;
        public final Image imgDetail;
        public final Image imgBase;

        public final Button btnAbout;
        public final Button btnBinary;
        public final Button btnBubble;
        public final Button btnDescription;
        public final Button btnFStep;
        public final Button btnHammer;
        public final Button btnInsert;
        public final Button btnLStep;
        public final Button btnMerge;
        public final Button btnCount;
        public final Button btnNearly;
        public final Button btnNStep;
        public final Button btnPause;
        public final Button btnPlay;
        public final Button btnPseudo;
        public final Button btnPStep;
        public final Button btnQuick;
        public final Button btnRandom;
        public final Button btnRepeat;
        public final Button btnReversed;
        public final Button btnSearch;
        public final Button btnSelect;
        public final Button btnSequential;
        public final Button btnSort;
        public final Button btnComp;

        public Scene2D(Skin skin) {
            this.imgTopBar = new Image(skin, "bar");
            this.imgBotBar = new Image(skin, "bar");
            this.imgLogo = new Image(skin, "logo");
            this.imgName = new Image(skin, "name");
            this.imgDetail = new Image(skin, "detail");
            this.imgBase = new Image(skin, "base");

            this.btnAbout = new Button(skin, "about");
            this.btnBinary = new Button(skin, "bin");
            this.btnBubble = new Button(skin, "bubble");
            this.btnDescription = new Button(skin, "des");
            this.btnFStep = new Button(skin, "fstep");
            this.btnHammer = new Button(skin, "ham");
            this.btnInsert = new Button(skin, "insert");
            this.btnLStep = new Button(skin, "lstep");
            this.btnMerge = new Button(skin, "merge");
            this.btnCount = new Button(skin, "count");
            this.btnNearly = new Button(skin, "nearly");
            this.btnNStep = new Button(skin, "nstep");
            this.btnPause = new Button(skin, "pause");
            this.btnPlay = new Button(skin, "play");
            this.btnPseudo = new Button(skin, "pseudo");
            this.btnPStep = new Button(skin, "pstep");
            this.btnQuick = new Button(skin, "quick");
            this.btnRandom = new Button(skin, "random");
            this.btnRepeat = new Button(skin, "repeat");
            this.btnReversed = new Button(skin, "rev");
            this.btnSearch = new Button(skin, "search");
            this.btnSelect = new Button(skin, "select");
            this.btnSequential = new Button(skin, "sequential");
            this.btnSort = new Button(skin, "sort");
            this.btnComp = new Button(skin, "cmp");
        }
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset '" + asset.fileName + "'", (Exception)throwable);
    }

    @Override
    public void dispose() {
        this.assetManager.dispose();
        this.skin.dispose();
    }
}
