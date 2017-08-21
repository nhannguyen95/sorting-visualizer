package vpcalculator;

import com.badlogic.gdx.Gdx;

public class VirtualViewport {
    float realVirtualHeight;
    float realVirtualWidth;
    float virtualHeight;
    float virtualWidth;

    public VirtualViewport(float paramFloat1, float paramFloat2) {
        this(paramFloat1, paramFloat2, false);
    }

    public VirtualViewport(float paramFloat1, float paramFloat2, boolean paramBoolean) {
        this.virtualWidth = paramFloat1;
        this.virtualHeight = paramFloat2;
    }

    public float getHeight() {
        return getHeight(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public float getHeight(float paramFloat1, float paramFloat2) {
        float f = this.virtualWidth / this.virtualHeight;
        paramFloat1 /= paramFloat2;
        if ((paramFloat1 < f) || (Math.abs(paramFloat1 - f) < 0.01F)) ;
        for (this.realVirtualHeight = this.virtualHeight; ; this.realVirtualHeight = (this.virtualWidth / paramFloat1))
            return this.realVirtualHeight;
    }

    public float getRealVirtualHeight() {
        return this.realVirtualHeight;
    }

    public float getRealVirtualWidth() {
        return this.realVirtualWidth;
    }

    public float getVirtualHeight() {
        return this.virtualHeight;
    }

    public float getVirtualWidth() {
        return this.virtualWidth;
    }

    public float getWidth() {
        return getWidth(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public float getWidth(float paramFloat1, float paramFloat2) {
        float f = this.virtualWidth / this.virtualHeight;
        paramFloat1 /= paramFloat2;
        if ((paramFloat1 > f) || (Math.abs(paramFloat1 - f) < 0.01F)) ;
        for (this.realVirtualWidth = (this.virtualHeight * paramFloat1); ; this.realVirtualWidth = this.virtualWidth)
            return this.realVirtualWidth;
    }
}
