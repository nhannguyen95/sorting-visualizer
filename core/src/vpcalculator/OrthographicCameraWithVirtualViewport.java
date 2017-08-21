package vpcalculator;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class OrthographicCameraWithVirtualViewport extends OrthographicCamera {
    Vector2 origin = new Vector2();
    Vector3 tmp = new Vector3();
    VirtualViewport virtualViewport;

    public void init(VirtualViewport paramVirtualViewport) {
        init(paramVirtualViewport, 0.0F, 0.0F);
    }

    public void init(VirtualViewport paramVirtualViewport, float paramFloat1, float paramFloat2) {
        this.virtualViewport = paramVirtualViewport;
        this.origin.set(paramFloat1, paramFloat2);
    }

    public void setPosition(float paramFloat1, float paramFloat2) {
        this.position.set(paramFloat1 - this.viewportWidth * this.origin.x, paramFloat2 - this.viewportHeight * this.origin.y, 0.0F);
    }

    public void setVirtualViewport(VirtualViewport paramVirtualViewport) {
        this.virtualViewport = paramVirtualViewport;
    }

    public void update() {
        float f1 = this.zoom * -this.viewportWidth / 2.0F;
        float f2 = this.virtualViewport.getVirtualWidth();
        float f3 = this.origin.x;
        float f4 = this.zoom * this.viewportWidth / 2.0F;
        float f5 = this.virtualViewport.getVirtualWidth();
        float f6 = this.origin.x;
        float f7 = this.zoom * this.viewportHeight / 2.0F;
        float f8 = this.virtualViewport.getVirtualHeight();
        float f9 = this.origin.y;
        float f10 = this.zoom * -this.viewportHeight / 2.0F;
        float f11 = this.virtualViewport.getVirtualHeight();
        float f12 = this.origin.y;
        this.projection.setToOrtho(f1 + f2 * f3, f4 + f5 * f6, f10 + f11 * f12, f7 + f8 * f9, Math.abs(this.near), Math.abs(this.far));
        this.view.setToLookAt(this.position, this.tmp.set(this.position).add(this.direction), this.up);
        this.combined.set(this.projection);
        Matrix4.mul(this.combined.val, this.view.val);
        this.invProjectionView.set(this.combined);
        Matrix4.inv(this.invProjectionView.val);
        this.frustum.update(this.invProjectionView);
    }

    public void updateViewport() {
        setToOrtho(false, this.virtualViewport.getWidth(), this.virtualViewport.getHeight());
    }

    public VirtualViewport getVirtualViewport() {
        return this.virtualViewport;
    }
}
