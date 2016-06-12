package vpcalculator;

/**
 * Created by apple on 3/12/16.
 */
public class MultipleVirtualViewportBuilder {
    private float maxHeight;
    private float maxWidth;
    private float minHeight;
    private float minWidth;

    private boolean insideBounds(float paramFloat1, float paramFloat2) {
        if ((paramFloat1 < this.minWidth) || (paramFloat1 > this.maxWidth)) ;
        while ((paramFloat2 < this.minHeight) || (paramFloat2 > this.maxHeight))
            return false;
        return true;
    }

    public VirtualViewport getVirtualViewport(float paramFloat1, float paramFloat2) {
        if ((paramFloat1 >= this.minWidth) && (paramFloat1 <= this.maxWidth) && (paramFloat2 >= this.minHeight) && (paramFloat2 <= this.maxHeight))
            return new VirtualViewport(paramFloat1, paramFloat2, true);
        paramFloat2 = paramFloat1 / paramFloat2;
        float f1 = this.minWidth / paramFloat1;
        float f2 = paramFloat1 * (this.maxWidth / paramFloat1);
        float f3 = f2 / paramFloat2;
        if (insideBounds(f2, f3))
            return new VirtualViewport(f2, f3, false);
        paramFloat1 *= f1;
        paramFloat2 = paramFloat1 / paramFloat2;
        if (insideBounds(paramFloat1, paramFloat2))
            return new VirtualViewport(paramFloat1, paramFloat2, false);
        return new VirtualViewport(this.minWidth, this.minHeight, true);
    }

    public void init(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
        this.minWidth = paramFloat1;
        this.minHeight = paramFloat2;
        this.maxWidth = paramFloat3;
        this.maxHeight = paramFloat4;
    }
}
